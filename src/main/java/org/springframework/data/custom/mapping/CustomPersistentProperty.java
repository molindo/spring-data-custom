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
import org.springframework.data.mapping.Association;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.model.AnnotationBasedPersistentProperty;
import org.springframework.data.mapping.model.SimpleTypeHolder;

public class CustomPersistentProperty extends AnnotationBasedPersistentProperty<CustomPersistentProperty> {

	public CustomPersistentProperty(final Field field, final PropertyDescriptor propertyDescriptor, final PersistentEntity<?, CustomPersistentProperty> owner, final SimpleTypeHolder simpleTypeHolder) {
		super(field, propertyDescriptor, owner, simpleTypeHolder);
	}

	@Override
	protected Association<CustomPersistentProperty> createAssociation() {
		return new Association<CustomPersistentProperty>(this, null);
	}

	@Override
	public boolean isEntity() {
		return !isTransient() && typeHasCustomAnnotation();
	}

	private boolean typeHasCustomAnnotation() {
		return AnnotationUtils.findAnnotation(getType(), Custom.class) != null;
	}

}
