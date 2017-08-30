package com.bookstore.controllers;

import com.bookstore.dao.AbstractDao;
import com.bookstore.entitys.Author;
import com.bookstore.entitys.Book;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

@Named
@SessionScoped
public class BookController implements Serializable {

    @EJB(beanName = "bookDao")
    private AbstractDao bookDao;

    @EJB(beanName = "authorDao")
    private AbstractDao authorDao;

    private List<Book> books;

    private List<String> idAuthors;

    private String title;

    private Part cover;

    private Part file;

    private String annotation;

    public List<Book> getBooks() {
        if (books == null) {
            books = bookDao.getList();
        }
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public AbstractDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(AbstractDao authorDao) {
        this.authorDao = authorDao;
    }

    public AbstractDao getBookDao() {
        return bookDao;
    }

    public List<String> getIdAuthors() {
        return idAuthors;
    }

    public void setIdAuthors(List<String> idAuthors) {
        this.idAuthors = idAuthors;
    }

    public void setBookDao(AbstractDao bookDao) {
        this.bookDao = bookDao;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Part getCover() {
        return cover;
    }

    public void setCover(Part cover) {
        this.cover = cover;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String create() {

        List<Author> authors = new ArrayList();
        for (String s : idAuthors) {
            authors.add((Author) authorDao.read(Long.valueOf(s)));
        }

        Book tmp = new Book();
        tmp.setTitle(title);
        tmp.setAnnotation(annotation);
        tmp.setAuthors(authors);

        try {
            tmp.setCover(IOUtils.toByteArray(cover.getInputStream()));
            tmp.setFile(IOUtils.toByteArray(file.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bookDao.update(tmp);

        return null;
    }

    public String edit(Book b) {
        b.setEditable(true);
        return null;
    }

    public String update(Book b) {
        bookDao.update(b);
        b.setEditable(false);
        return null;
    }

    public String delete(Book b) {
        System.out.println("Lanch from JSF bean" + b);
        bookDao.delete(b);
        return null;
    }

}
