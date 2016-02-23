package org.springframework.data.custom.test;

import java.util.Collections;

public class CustomEntityRepositoryImpl implements CustomEntityRepositoryCustom {

	@Override
	public CustomEntity findOne(final Integer id) {
		return id != null && id == 42 ? new CustomEntity(id, "foo") : null;
	}

	@Override
	public Iterable<CustomEntity> findAll() {
		return Collections.singletonList(findOne(42));
	}

}
