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
package org.springframework.data.custom;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.custom.RepositoryTest.Application;
import org.springframework.data.custom.repository.config.EnableCustomRepositories;
import org.springframework.data.custom.test.CustomEntity;
import org.springframework.data.custom.test.CustomEntityRepository;
import org.springframework.data.custom.test.CustomEntityRepositoryImpl;
import org.springframework.data.repository.support.Repositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

import at.molindo.utils.collections.IteratorUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RepositoryTest {

	@Autowired
	private CustomEntityRepository repo;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void crud() {
		assertNotNull(repo.findOne(CustomEntityRepositoryImpl.KNOWN_ID));
		assertEquals(1, IteratorUtils.list(repo.findAll()).size());
	}

	@Test
	public void query() {
		final Repositories repositories = new Repositories(applicationContext);

		final List<Method> queries = Lists
				.newArrayList(repositories.getRepositoryInformationFor(CustomEntity.class).getQueryMethods());
		assertEquals(1, queries.size());

		assertEquals(CustomEntityRepositoryImpl.SOMETHING, repo
				.findSomething(CustomEntityRepositoryImpl.SOMETHING_ARG));
	}

	@EnableAutoConfiguration
	@EnableCustomRepositories(basePackageClasses = CustomEntityRepository.class)
	public static class Application {
	}

}
