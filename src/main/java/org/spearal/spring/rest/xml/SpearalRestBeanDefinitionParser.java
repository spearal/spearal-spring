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
package org.spearal.spring.rest.xml;

import org.spearal.spring.rest.SpearalMessageConverter;
import org.spearal.spring.rest.SpearalResponseBodyAdvice;
import org.spearal.spring.rest.SpearalRestConfigurator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class SpearalRestBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
    	if ("".equals(element.getAttribute(ID_ATTRIBUTE)))
    		element.setAttribute(ID_ATTRIBUTE, SpearalRestConfigurator.class.getName());
        
    	if (!parserContext.getRegistry().containsBeanDefinition(SpearalMessageConverter.class.getName())) {
	        BeanDefinitionBuilder messageConverterBuilder = BeanDefinitionBuilder.genericBeanDefinition(SpearalMessageConverter.class);
	        messageConverterBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
	        registerInfrastructureComponent(element, parserContext, messageConverterBuilder, SpearalMessageConverter.class.getName());
    	}
        
    	if (!parserContext.getRegistry().containsBeanDefinition(SpearalResponseBodyAdvice.class.getName())) {
	        BeanDefinitionBuilder responseBodyAdviceBuilder = BeanDefinitionBuilder.genericBeanDefinition(SpearalResponseBodyAdvice.class);
	        responseBodyAdviceBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
	        registerInfrastructureComponent(element, parserContext, responseBodyAdviceBuilder, SpearalResponseBodyAdvice.class.getName());
    	}
    }
    
    @Override
    protected String getBeanClassName(Element element) {
        return SpearalRestConfigurator.class.getName();
    }

    
    static void registerInfrastructureComponent(Element element, ParserContext parserContext, BeanDefinitionBuilder componentBuilder, String beanName) {
        componentBuilder.getRawBeanDefinition().setSource(parserContext.extractSource(element));
        componentBuilder.getRawBeanDefinition().setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        parserContext.registerBeanComponent(new BeanComponentDefinition(componentBuilder.getBeanDefinition(), beanName));
    }
}
