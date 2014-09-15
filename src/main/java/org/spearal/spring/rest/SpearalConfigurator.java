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
import org.spearal.configuration.Configurable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * Spring bean postprocessor to configure the SpearalFactory
 * 
 * @author William DRAI
 */
public class SpearalConfigurator implements BeanPostProcessor, ApplicationContextAware {
	
	private ApplicationContext applicationContext = null;
	private SpearalFactory spearalFactory = null;
	
	public SpearalConfigurator() {		
	}
	
	public SpearalConfigurator(SpearalFactory spearalFactory) {
		this.spearalFactory = spearalFactory;
	}
	
	public void setSpearalFactory(SpearalFactory spearalFactory) {
		this.spearalFactory = spearalFactory;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		if (this.spearalFactory == null)
			this.spearalFactory = applicationContext.getBean(SpearalFactory.class);
	}
	
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof Configurable && spearalFactory != null)
			spearalFactory.getContext().configure((Configurable)bean);
		
		if (bean instanceof RequestMappingHandlerAdapter && applicationContext != null) {
			SpearalMessageConverter messageConverter = applicationContext.getBean(SpearalMessageConverter.class);
			((RequestMappingHandlerAdapter)bean).getMessageConverters().add(messageConverter);
		}
		
		return bean;
	}
}
