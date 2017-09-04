package com.bookstore.dao;

import com.bookstore.entitys.Author;
import com.bookstore.entitys.Book;
import java.io.Serializable;
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
        Author tmp = em.merge(a);
        List<Book> books = tmp.getBooks();
        for (Book b : books) {
            em.remove(b);
        }
        em.remove(tmp);
    }

    @Override
    public List<Author> getList() {
        return em.createNamedQuery("Author.list").getResultList();
    }

}
