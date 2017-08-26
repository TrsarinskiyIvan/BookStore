package com.bookstore.dao;

import java.util.List;
import javax.ejb.Local;

@Local
public interface AbstractDao<E> {

    void create(E e);

    E read(Long id);

    void update(E e);

    void delete(E e);

    List<E> getAll();

}
