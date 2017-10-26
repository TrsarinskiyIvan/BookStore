package com.bookstore.dao;

import com.bookstore.entitys.Author;
import com.bookstore.entitys.Book;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "bookDao")
public class BookDaoImpl implements Dao<Book>, Serializable {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book b) {

        List<Author> authors = b.getAuthors();
        List<Book> books = null;

        for (Author a : authors) {

            books = a.getBooks();
            books.add(b);
            a.setBooks(books);
            em.merge(a);

        }
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
