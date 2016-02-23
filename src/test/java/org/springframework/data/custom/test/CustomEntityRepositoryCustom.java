package org.springframework.data.custom.test;

public interface CustomEntityRepositoryCustom {

	CustomEntity findOne(Integer id);

	Iterable<CustomEntity> findAll();
}
