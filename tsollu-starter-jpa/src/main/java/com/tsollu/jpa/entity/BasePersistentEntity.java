package com.tsollu.jpa.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Base abstract class for persistent entities
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BasePersistentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

}
