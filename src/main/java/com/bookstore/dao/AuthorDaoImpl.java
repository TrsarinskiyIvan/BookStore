package com.bookstore.dao;

import com.bookstore.entitys.Author;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "authorDao")
public class AuthorDaoImpl implements AbstractDao<Author>, Serializable {
    
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
    public void delete(Long id) {
        Author tmp = em.find(Author.class, id);
        em.remove(tmp);
    }
    
    @Override
    public List<Author> getAll() {
        return em.createNamedQuery("Author.list").getResultList();
    }
    
}
