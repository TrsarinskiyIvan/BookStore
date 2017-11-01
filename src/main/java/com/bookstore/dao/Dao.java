package com.bookstore.dao;

import java.util.List;
import javax.ejb.Local;

@Local
public interface Dao<T> {

    void create(T t);

    T read(Long id);

    void update(T t);

    void delete(T t);

    List<T> getList();

}
