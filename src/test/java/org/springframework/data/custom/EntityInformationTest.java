package org.springframework.data.custom;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.custom.EntityInformationTest.Application;
import org.springframework.data.custom.repository.config.EnableCustomRepositories;
import org.springframework.data.custom.test.CustomEntity;
import org.springframework.data.custom.test.CustomEntityRepository;
import org.springframework.data.custom.test.JpaEntity;
import org.springframework.data.custom.test.JpaEntityRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.repository.core.support.ReflectionEntityInformation;
import org.springframework.data.repository.support.Repositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EntityInformationTest {

	@Autowired
	private ApplicationContext _context;

	@Test
	public void repositoriesAreAssignedToAppropriateStores() {

		final Repositories repositories = new Repositories(_context);

		assertThat(repositories.getEntityInformationFor(JpaEntity.class), is(instanceOf(JpaEntityInformation.class)));
		assertThat(repositories
				.getEntityInformationFor(CustomEntity.class), is(instanceOf(ReflectionEntityInformation.class)));
	}

	@EnableAutoConfiguration
	@EntityScan(basePackageClasses = JpaEntity.class)
	@EnableJpaRepositories(basePackageClasses = JpaEntityRepository.class)
	@EnableCustomRepositories(basePackageClasses = CustomEntityRepository.class)
	public static class Application {
	}

}
