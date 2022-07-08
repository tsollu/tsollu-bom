package com.kaddo.components.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaRepositoryWrapper<T, ID>
    extends
    JpaRepository<T, ID>,
    JpaSpecificationExecutor<T> {

}
