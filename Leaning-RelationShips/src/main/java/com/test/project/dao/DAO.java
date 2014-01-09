package com.test.project.dao;

import java.util.Collection;

/**
 * @author Valentin
 */
public interface DAO<T, P> {
    T get(P parameter);

    void add(T entity);

    void update(T entity);

    Collection<T> getAll();
}
