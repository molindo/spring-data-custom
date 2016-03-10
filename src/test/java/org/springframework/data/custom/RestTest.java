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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.custom.repository.config.EnableCustomRepositories;
import org.springframework.data.custom.test.CustomEntity;
import org.springframework.data.custom.test.CustomEntityRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.DefaultRelProvider;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.hateoas.hal.Jackson2HalModule.HalHandlerInstantiator;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestTest.Application.class)
@WebIntegrationTest({ "server.port=0", "management.port=0" })
@SuppressWarnings("serial")
public class RestTest {

	private static final MediaType CONTENT_READ = MediaType.valueOf("application/hal+json");

	@Value("${local.server.port}")
	private int _port;

	@Autowired
	private WebApplicationContext _webApplicationContext;

	private MockMvc _mockMvc;

	@Before
	public void setup() throws Exception {
		_mockMvc = webAppContextSetup(_webApplicationContext).build();
	}

	@Test
	public void test() throws Exception {
		final ObjectMapper mapper = mapper();

		final Resources<Void> root = mapper.readValue(_mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().contentType(CONTENT_READ)).andReturn().getResponse()
				.getContentAsString(), new TypeReference<Resources<Void>>() {
				});

		// findAll()

		final String listLink = root.getLink("customEntities").getHref();
		assertNotNull(listLink);

		final Resources<Resource<CustomEntity>> entities = mapper.readValue(_mockMvc.perform(get(URI.create(listLink)))
				.andExpect(status().isOk()).andExpect(content().contentType(CONTENT_READ)).andReturn().getResponse()
				.getContentAsString(), new TypeReference<Resources<Resource<CustomEntity>>>() {
				});

		assertNotNull(entities);
		assertNotNull(entities.getLink("self"));
		assertTrue(entities.getContent().iterator().hasNext());

		// findOne(..)

		final String entityLink = entities.getContent().iterator().next().getLink("self").getHref();
		assertNotNull(entityLink);

		final Resource<CustomEntity> entity = mapper.readValue(_mockMvc.perform(get(URI.create(entityLink)))
				.andExpect(status().isOk()).andExpect(content().contentType(CONTENT_READ)).andReturn().getResponse()
				.getContentAsString(), new TypeReference<Resource<CustomEntity>>() {
				});

		assertNotNull(entity);
		assertNotNull(entity.getLink("self"));
	}

	public static ObjectMapper mapper() {
		final HalHandlerInstantiator instantiator = new HalHandlerInstantiator(new DefaultRelProvider(), null, null);

		final ObjectMapper m = new ObjectMapper();
		m.registerModule(new Jackson2HalModule());
		m.registerModule(new SimpleModule() {
			{
				setMixInAnnotation(Link.class, LinkReadMixin.class);
			}
		});
		m.setHandlerInstantiator(instantiator);
		m.enable(SerializationFeature.INDENT_OUTPUT);
		return m;
	}

	@JsonIgnoreProperties(value = "templated")
	private abstract class LinkReadMixin extends Link {
	}

	// @JsonIgnoreProperties(value = "templated")
	// private abstract class ResourcesReadMixin<T> extends Resources<T> {
	// }

	@EnableAutoConfiguration
	@EnableCustomRepositories(basePackageClasses = CustomEntityRepository.class)
	public static class Application {
	}

}
