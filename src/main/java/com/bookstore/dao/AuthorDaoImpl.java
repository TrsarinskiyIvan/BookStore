package com.bookstore.dao;

import com.bookstore.entitys.Author;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "authorDao")
public class AuthorDaoImpl implements Dao<Author>, Serializable {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Author e) {
        em.persist(e);
    }

    @Override
    public Author read(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public void update(Author e) {
        em.merge(e);
    }

    @Override
    public void delete(Author a) {
        em.remove(em.merge(a));
    }

    @Override
    public List<Author> getList() {
        return new ArrayList<>(em.createNamedQuery("Author.list").getResultList());
    }

}
