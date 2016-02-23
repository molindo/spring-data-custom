package org.springframework.data.custom.test;

import org.springframework.data.custom.repository.CustomRepository;

public interface CustomEntityRepository extends CustomRepository<CustomEntity, Integer>, CustomEntityRepositoryCustom {
}
