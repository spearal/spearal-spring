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
package org.spearal.spring.xml;

import org.spearal.spring.jpa.xml.SpearalJpaBeanDefinitionParser;
import org.spearal.spring.rest.xml.SpearalRestBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SpearalNamespaceHandler extends NamespaceHandlerSupport {
	
	public void init() {
		registerBeanDefinitionParser("rest", new SpearalRestBeanDefinitionParser());
		registerBeanDefinitionParser("jpa", new SpearalJpaBeanDefinitionParser());
	}
}
