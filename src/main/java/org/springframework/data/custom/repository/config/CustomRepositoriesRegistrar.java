package org.springframework.data.custom.repository.config;

import java.lang.annotation.Annotation;

import org.springframework.data.custom.repository.support.CustomRepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

class CustomRepositoriesRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

	@Override
	protected Class<? extends Annotation> getAnnotation() {
		return EnableCustomRepositories.class;
	}

	@Override
	protected RepositoryConfigurationExtension getExtension() {
		return new CustomRepositoryConfigurationExtension();
	}
}
