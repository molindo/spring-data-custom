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
package org.springframework.data.custom.mapping;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.custom.annotation.Custom;
import org.springframework.data.mapping.context.AbstractMappingContext;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.util.TypeInformation;

public class CustomMappingContext extends AbstractMappingContext<CustomPersistentEntityImpl<?>, CustomPersistentProperty> {

	public CustomMappingContext() {
	}

	@Override
	protected <T> CustomPersistentEntityImpl<?> createPersistentEntity(final TypeInformation<T> typeInformation) {
		return new CustomPersistentEntityImpl<T>(typeInformation);
	}

	@Override
	protected CustomPersistentProperty createPersistentProperty(final Field field, final PropertyDescriptor descriptor, final CustomPersistentEntityImpl<?> owner, final SimpleTypeHolder simpleTypeHolder) {
		return new CustomPersistentProperty(field, descriptor, owner, simpleTypeHolder);
	}

	@Override
	protected boolean shouldCreatePersistentEntityFor(final TypeInformation<?> type) {
		return super.shouldCreatePersistentEntityFor(type) && typeHasCustomAnnotation(type);
	}

	private boolean typeHasCustomAnnotation(final TypeInformation<?> type) {
		return AnnotationUtils.findAnnotation(type.getType(), Custom.class) != null;
	}

}
