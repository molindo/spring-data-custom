/**
 * Copyright 2016 Molindo GmbH
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
package org.springframework.data.custom.repository.support;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.data.custom.annotation.Custom;
import org.springframework.data.custom.repository.CustomRepository;
import org.springframework.data.custom.repository.config.CustomMappingContextFactoryBean;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import org.springframework.data.repository.config.RepositoryConfigurationSource;

public class CustomRepositoryConfigurationExtension extends RepositoryConfigurationExtensionSupport {

	public static final String CUSTOM_MAPPING_CONTEXT_BEAN_NAME = "customMappingContext";

	@Override
	public String getModuleName() {
		return "Custom";
	}

	@Override
	protected String getModulePrefix() {
		return "custom";
	}

	@Override
	public String getRepositoryFactoryClassName() {
		return CustomRepositoryFactoryBean.class.getName();
	}

	@Override
	protected Collection<Class<? extends Annotation>> getIdentifyingAnnotations() {
		return Collections.<Class<? extends Annotation>> singleton(Custom.class);
	}

	@Override
	protected Collection<Class<?>> getIdentifyingTypes() {
		return Collections.<Class<?>> singleton(CustomRepository.class);
	}

	@Override
	public void registerBeansForRoot(final BeanDefinitionRegistry registry, final RepositoryConfigurationSource config) {

		super.registerBeansForRoot(registry, config);

		final Object source = config.getSource();

		final BeanDefinitionBuilder builder = BeanDefinitionBuilder
				.rootBeanDefinition(CustomMappingContextFactoryBean.class);
		builder.addPropertyValue("basePackages", config.getBasePackages());
		registerIfNotAlreadyRegistered(builder.getBeanDefinition(), registry, CUSTOM_MAPPING_CONTEXT_BEAN_NAME, source);

	}

	@Override
	public void postProcess(final BeanDefinitionBuilder builder, final RepositoryConfigurationSource source) {
		builder.addPropertyReference("mappingContext", CUSTOM_MAPPING_CONTEXT_BEAN_NAME);
	}

}
