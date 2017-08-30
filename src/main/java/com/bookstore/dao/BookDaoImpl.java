package com.bookstore.dao;

import com.bookstore.entitys.Book;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "bookDao")
public class BookDaoImpl implements AbstractDao<Book>, Serializable {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book b) {
        em.persist(b);
    }

    @Override
    public Book read(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public void update(Book b) {
        em.merge(b);
    }

    @Override
    public void delete(Book b) {
        em.remove(em.merge(b));
    }

    @Override
    public List<Book> getList() {
        return em.createNamedQuery("Book.list").getResultList();
    }

}
