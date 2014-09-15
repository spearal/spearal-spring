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
package org.spearal.spring.jpa;

import javax.persistence.EntityManagerFactory;

import org.spearal.SpearalFactory;
import org.spearal.jpa2.EntityManagerFactoryWrapper;
import org.spearal.jpa2.SpearalConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spearal bean postprocessor to configure and wrap the EntityManagerFactory
 * 
 * @author William DRAI
 */
public class SpearalJpaConfigurator implements BeanPostProcessor, ApplicationContextAware {
	
	private SpearalFactory spearalFactory = null;
	
	public SpearalJpaConfigurator() {		
	}
	
	public SpearalJpaConfigurator(SpearalFactory spearalFactory) {
		this.spearalFactory = spearalFactory;
	}
	
	public void setSpearalFactory(SpearalFactory spearalFactory) {
		this.spearalFactory = spearalFactory;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		if (this.spearalFactory == null)
			this.spearalFactory = applicationContext.getBean(SpearalFactory.class);
	}
	
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof EntityManagerFactory && this.spearalFactory != null) {
			// Init the SpearalFactory with the EMF
			SpearalConfigurator.init(spearalFactory, (EntityManagerFactory)bean);
			// Wrap the EMF
			return new EntityManagerFactoryWrapper((EntityManagerFactory)bean);
		}
		
		return bean;
	}
	
}
