package com.example.testfx.persistance.dao;

import com.example.testfx.persistance.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseEntityDao<ENTITY extends BaseEntity> {
    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    Optional<ENTITY> findById(Long id);
    Collection<ENTITY> findAll();
}
