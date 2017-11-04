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
        
        for (Author a : b.getAuthors()) {
            em.find(Author.class, a.getId()).addBook(b);
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
