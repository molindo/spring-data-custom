package org.springframework.data.custom.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends Repository<T, ID> {

}
