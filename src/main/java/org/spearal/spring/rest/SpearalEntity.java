/**
 * == @Spearal ==>
 * 
 * Copyright (C) 2014 Franck WOLFF & William DRAI (http://www.spearal.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spearal.spring.rest;

import org.spearal.SpearalFactory;
import org.spearal.SpearalPropertyFilter;
import org.spearal.filter.SpearalPropertyFilterBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Spearal entity
 * 
 * @author William DRAI
 */
public class SpearalEntity<T> extends HttpEntity<T> {
	
	private final SpearalPropertyFilterBuilder clientPropertyFilterBuilder;
	
	public final static String PROPERTY_FILTER_HEADER = "Spearal-PropertyFilter";
	
	public SpearalEntity(SpearalFactory factory, SpearalPropertyFilterBuilder clientPropertyFilterBuilder, SpearalPropertyFilterBuilder serverPropertyFilterBuilder) {
		super(null, toHeaders(factory, serverPropertyFilterBuilder));
		this.clientPropertyFilterBuilder = clientPropertyFilterBuilder;
	}
	
	public SpearalEntity(SpearalFactory factory, T object, SpearalPropertyFilterBuilder clientPropertyFilterBuilder, SpearalPropertyFilterBuilder serverPropertyFilterBuilder) {
		super(object, toHeaders(factory, serverPropertyFilterBuilder));
		this.clientPropertyFilterBuilder = clientPropertyFilterBuilder;
	}
	
	public void applyClientPropertyFilter(SpearalPropertyFilter propertyFilter) {
		if (clientPropertyFilterBuilder != null)
			clientPropertyFilterBuilder.apply(propertyFilter);
	}
	
	private static HttpHeaders toHeaders(SpearalFactory factory, SpearalPropertyFilterBuilder serverPropertyFilterBuilder) {
		HttpHeaders headers = new HttpHeaders();
		if (serverPropertyFilterBuilder == null)
			return headers;
		headers.put(PROPERTY_FILTER_HEADER, serverPropertyFilterBuilder.toHeaders(factory.getContext()));
		return headers;
	}
}
