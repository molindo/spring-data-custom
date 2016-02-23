package org.springframework.data.custom.mapping;

import org.springframework.data.mapping.PersistentEntity;

public interface CustomPersistentEntity<T> extends PersistentEntity<T, CustomPersistentProperty> {

}
