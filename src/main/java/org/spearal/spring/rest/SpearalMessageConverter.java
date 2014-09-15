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

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.spearal.SpearalDecoder;
import org.spearal.SpearalEncoder;
import org.spearal.SpearalFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * Spring MVC message converter 
 * 
 * @author William DRAI
 */
public class SpearalMessageConverter implements HttpMessageConverter<Object> {
	
	private final SpearalFactory factory;
	
	public SpearalMessageConverter(SpearalFactory factory) {
		this.factory = factory;
	}
	
	public boolean canWrite(Class<?> type, MediaType mediaType) {
		return mediaType == null || SpearalSpring.APPLICATION_SPEARAL_TYPE.equals(mediaType);
	}
	
	public boolean canRead(Class<?> type, MediaType mediaType) {
		return mediaType == null || SpearalSpring.APPLICATION_SPEARAL_TYPE.equals(mediaType);
	}
	
	public void write(Object obj, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		
		outputMessage.getHeaders().setContentType(SpearalSpring.APPLICATION_SPEARAL_TYPE);
		
		SpearalEncoder encoder = factory.newEncoder(outputMessage.getBody());
		
		if (obj instanceof SpearalEntity) {
			((SpearalEntity<?>)obj).applyClientPropertyFilter(encoder.getPropertyFilter());
			
			obj = ((SpearalEntity<?>)obj).getBody();
		}
		
		encoder.writeAny(obj);
	}
	
	public Object read(Class<? extends Object> type, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		
		SpearalDecoder decoder = factory.newDecoder(inputMessage.getBody());
		return decoder.readAny(type);
	}
	
	public List<MediaType> getSupportedMediaTypes() {
		return Collections.singletonList(SpearalSpring.APPLICATION_SPEARAL_TYPE);
	}

}
