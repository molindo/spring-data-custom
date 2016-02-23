package org.springframework.data.custom.mapping;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

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

}
