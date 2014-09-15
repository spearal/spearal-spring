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
package org.spearal.spring.jpa.xml;

import org.spearal.spring.jpa.SpearalJpaConfigurator;
import org.spearal.spring.rest.SpearalConfigurator;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class SpearalJpaBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        CompositeComponentDefinition componentDefinition = new CompositeComponentDefinition(element.getLocalName(), parserContext.extractSource(element));
        parserContext.pushContainingComponent(componentDefinition);

        element.setAttribute(ID_ATTRIBUTE, SpearalConfigurator.class.getName());
        
        parserContext.popAndRegisterContainingComponent();
    }
    
    @Override
    protected String getBeanClassName(Element element) {
        return SpearalJpaConfigurator.class.getName();
    }
}
