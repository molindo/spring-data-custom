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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.custom.repository.CustomRepository;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomRepositoryFactoryBean<T extends CustomRepository<S, ID>, S, ID extends Serializable> extends RepositoryFactoryBeanSupport<T, S, ID> {

	@Autowired
	private ApplicationContext applicationContext;

	public CustomRepositoryFactoryBean() {
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory() {
		return new CustomRepositoryFactorySupport(applicationContext);
	}

	@Override
	public void setMappingContext(final MappingContext<?, ?> mappingContext) {
		super.setMappingContext(mappingContext);
	}

}
