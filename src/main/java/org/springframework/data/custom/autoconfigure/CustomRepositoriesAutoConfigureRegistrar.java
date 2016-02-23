package org.springframework.data.custom.autoconfigure;

import java.lang.annotation.Annotation;

import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.data.custom.repository.config.EnableCustomRepositories;
import org.springframework.data.custom.repository.support.CustomRepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

public class CustomRepositoriesAutoConfigureRegistrar extends AbstractRepositoryConfigurationSourceSupport {

	@Override
	protected Class<? extends Annotation> getAnnotation() {
		return EnableCustomRepositories.class;
	}

	@Override
	protected Class<?> getConfiguration() {
		return EnableCustomRepositoriesConfiguration.class;
	}

	@Override
	protected RepositoryConfigurationExtension getRepositoryConfigurationExtension() {
		return new CustomRepositoryConfigurationExtension();
	}

	@EnableCustomRepositories
	private static class EnableCustomRepositoriesConfiguration {
	}

}
