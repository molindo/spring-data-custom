package org.springframework.data.custom.repository.support;

import java.io.Serializable;

import org.springframework.data.custom.repository.CustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleCustomRepository<T, ID extends Serializable> implements CustomRepository<T, ID> {
}
