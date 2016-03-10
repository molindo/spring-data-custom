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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.custom.RepositoryTest.Application;
import org.springframework.data.custom.repository.config.EnableCustomRepositories;
import org.springframework.data.custom.test.CustomEntityRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import at.molindo.utils.collections.IteratorUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RepositoryTest {

	@Autowired
	private CustomEntityRepository repo;

	@Test
	public void repositoriesAreAssignedToAppropriateStores() {
		assertNotNull(repo.findOne(42));
		assertEquals(1, IteratorUtils.list(repo.findAll()).size());
	}

	@EnableAutoConfiguration
	@EnableCustomRepositories(basePackageClasses = CustomEntityRepository.class)
	public static class Application {
	}

}
