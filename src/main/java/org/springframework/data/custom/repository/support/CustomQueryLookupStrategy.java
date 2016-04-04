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

import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CustomQueryLookupStrategy implements QueryLookupStrategy {

	@NonNull
	private final ApplicationContext applicationContext;

	@Override
	public RepositoryQuery resolveQuery(final Method method, final RepositoryMetadata metadata, final NamedQueries namedQueries) {
		return new CustomRepositoryQuery(method, metadata, applicationContext);
	}
}
