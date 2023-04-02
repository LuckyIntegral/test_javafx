package com.example.testfx.service;

import com.example.testfx.persistance.entity.BaseEntity;

import java.util.Collection;

public interface BaseService <ENTITY extends BaseEntity> {
    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    Collection<ENTITY> findAll();
}
