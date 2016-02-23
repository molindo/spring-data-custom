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
