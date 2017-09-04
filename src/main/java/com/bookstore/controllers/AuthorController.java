package com.bookstore.controllers;

import com.bookstore.entitys.Author;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.bookstore.dao.Dao;

@Named
@SessionScoped
public class AuthorController implements Serializable {

    @EJB(beanName = "authorDao")
    private Dao authorDao;

    private String firstName;

    private String lastName;

    private List<Author> authors;

    public Dao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(Dao authorDao) {
        this.authorDao = authorDao;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Author> getAuthors() {
        if (authors == null) {
            authors = authorDao.getList();
        }
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String creat() {
        authorDao.create(new Author(firstName, lastName));
        authors = authorDao.getList();
        firstName = "";
        lastName = "";
        return null;
    }

    public String update(Author a) {
        authorDao.update(a);
        a.setEditable(false);
        return null;
    }

    public String edit(Author a) {
        a.setEditable(true);
        return null;
    }

    public String delete(Author a) {
        authorDao.delete(a);
        authors = authorDao.getList();
        return null;
    }
}
