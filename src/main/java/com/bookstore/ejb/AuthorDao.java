package com.bookstore.ejb;

import com.bookstore.entitys.Author;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorDao implements Serializable {

    @PersistenceContext(name = "jndi/store_book_pool")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Author getAuthors(Long id) {
        return em.find(Author.class, id);
    }

}
