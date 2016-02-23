package org.springframework.data.custom.repository.support;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.ReflectionEntityInformation;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import lombok.Setter;

@Setter
public class CustomRepositoryFactorySupport extends RepositoryFactorySupport {

	@Override
	public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(final Class<T> domainClass) {
		return new ReflectionEntityInformation<T, ID>(domainClass);
	}

	@Override
	protected Object getTargetRepository(final RepositoryInformation metadata) {
		return BeanUtils.instantiate(metadata.getRepositoryBaseClass());
	}

	@Override
	protected Class<?> getRepositoryBaseClass(final RepositoryMetadata metadata) {
		return SimpleCustomRepository.class;
	}

	@Override
	public <T> T getRepository(final Class<T> repositoryInterface, final Object customImplementation) {
		return super.getRepository(repositoryInterface, customImplementation);
	}
}
