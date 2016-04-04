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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;

import lombok.NonNull;

public class CustomRepositoryQuery implements RepositoryQuery {

	private final Method method;
	private final QueryMethod queryMethod;
	private final ApplicationContext applicationContext;

	public CustomRepositoryQuery(@NonNull final Method method, @NonNull final RepositoryMetadata metadata, @NonNull final ApplicationContext applicationContext) {
		this.method = method;
		queryMethod = new QueryMethod(method, metadata);
		this.applicationContext = applicationContext;
	}

	@Override
	public QueryMethod getQueryMethod() {
		return queryMethod;
	}

	@Override
	public Object execute(final Object[] parameters) {
		try {
			return method.invoke(applicationContext.getBean(method.getDeclaringClass()), parameters);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("failed to execute query " + method.getName(), e);
		}
	}
}
