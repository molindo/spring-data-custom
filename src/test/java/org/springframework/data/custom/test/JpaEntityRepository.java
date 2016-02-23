package org.springframework.data.custom.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEntityRepository extends JpaRepository<JpaEntity, Integer> {
}
