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
