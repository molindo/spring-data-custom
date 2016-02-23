package org.springframework.data.custom.repository.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.custom.annotation.Custom;
import org.springframework.data.custom.mapping.CustomMappingContext;
import org.springframework.util.ClassUtils;

import lombok.Setter;

@Setter
public class CustomMappingContextFactoryBean extends AbstractFactoryBean<CustomMappingContext> implements ResourceLoaderAware {

	private static final String CLASS_RESOURCE_PATTERN = "/**/*.class";

	private static final Set<TypeFilter> entityTypeFilters;

	static {
		entityTypeFilters = new LinkedHashSet<TypeFilter>();
		entityTypeFilters.add(new AnnotationTypeFilter(Custom.class, false));
	}

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	private String[] basePackages;

	@Override
	public void setResourceLoader(final ResourceLoader resourceLoader) {
		resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	}

	@Override
	public Class<?> getObjectType() {
		return CustomMappingContext.class;
	}

	@Override
	protected CustomMappingContext createInstance() throws Exception {

		final CustomMappingContext context = new CustomMappingContext();
		context.setInitialEntitySet(scanPackages(basePackages));
		context.initialize();

		return context;
	}

	private Set<Class<?>> scanPackages(final String... packagesToScan) {
		final Set<Class<?>> entities = new HashSet<>();

		if (packagesToScan != null) {
			for (final String pkg : packagesToScan) {
				try {
					final String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
							+ ClassUtils.convertClassNameToResourcePath(pkg) + CLASS_RESOURCE_PATTERN;
					final Resource[] resources = resourcePatternResolver.getResources(pattern);
					final MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
					for (final Resource resource : resources) {
						if (resource.isReadable()) {
							final MetadataReader reader = readerFactory.getMetadataReader(resource);
							final String className = reader.getClassMetadata().getClassName();
							if (matchesFilter(reader, readerFactory)) {
								entities.add(ClassUtils
										.resolveClassName(className, ClassUtils.getDefaultClassLoader()));
							}
						}
					}
				} catch (final IOException ex) {
					throw new RuntimeException("Failed to scan classpath for unlisted entity classes", ex);
				}
			}
		}

		return entities;
	}

	/**
	 * Check whether any of the configured entity type filters matches the current class descriptor contained in the
	 * metadata reader.
	 */
	private boolean matchesFilter(final MetadataReader reader, final MetadataReaderFactory readerFactory) throws IOException {
		for (final TypeFilter filter : entityTypeFilters) {
			if (filter.match(reader, readerFactory)) {
				return true;
			}
		}
		return false;
	}

}
