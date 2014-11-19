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
package org.spearal.spring.rest.config;

import org.spearal.SpearalFactory;
import org.spearal.spring.rest.SpearalRestConfigurator;
import org.spearal.spring.rest.SpearalMessageConverter;
import org.spearal.spring.rest.SpearalResponseBodyAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author William DRAI
 */
@Configuration
public class SpearalRestConfig {
	
	@Autowired(required=true)
	private SpearalFactory spearalFactory;
	
	@Bean
	public SpearalMessageConverter spearalMessageConverter() {
		return new SpearalMessageConverter(spearalFactory);
	}
	
	@Bean
	public SpearalResponseBodyAdvice spearalResponseBodyAdvice() {
		return new SpearalResponseBodyAdvice(spearalFactory);
	}
	
	@Bean
	public SpearalRestConfigurator spearalConfigurator() {
		return new SpearalRestConfigurator();
	}
}
