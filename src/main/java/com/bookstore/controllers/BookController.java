package com.bookstore.controllers;

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
import com.bookstore.dao.Dao;
import com.bookstore.utils.UtilsBean;
import java.util.Objects;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class BookController implements Serializable {

    @EJB(beanName = "bookDao")
    private Dao bookDao;

    @EJB(beanName = "authorDao")
    private Dao authorDao;

    private Book book;

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

    public Dao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(Dao authorDao) {
        this.authorDao = authorDao;
    }

    public Dao getBookDao() {
        return bookDao;
    }

    public List<String> getIdAuthors() {
        return idAuthors;
    }

    public void setIdAuthors(List<String> idAuthors) {
        this.idAuthors = idAuthors;
    }

    public void setBookDao(Dao bookDao) {
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
    //user's methods

    public Book getBook() {

        Long id = UtilsBean.getId(FacesContext.getCurrentInstance(), "id_book");
        if (book==null || !Objects.equals(id, book.getId())) {
            book = (Book) bookDao.read(id);
        }

        return book;
    }

//admin's methods
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
        books = bookDao.getList();

        title = "";
        cover = null;
        file = null;

        return null;
    }

    public String edit(Book b) {
        b.setEditable(true);
        return null;
    }

    public String update(Book b) {
        bookDao.update(b);
        b.setEditable(false);
        books = bookDao.getList();
        return null;
    }

    public String delete(Book b) {
        bookDao.delete(b);
        books = bookDao.getList();
        return null;
    }

}
