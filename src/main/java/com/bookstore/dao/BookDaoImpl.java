package com.bookstore.dao;

import com.bookstore.entitys.Book;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name="bookDao")
public class BookDaoImpl implements AbstractDao<Book>, Serializable {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book e) {
        em.persist(e);
    }

    @Override
    public Book read(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public void update(Book e) {
        em.merge(e);
    }

    @Override
    public void delete(Long id) {
        
    }

    @Override
    public List<Book> getAll() {
        return em.createNamedQuery("Book.list").getResultList();
    }

}
