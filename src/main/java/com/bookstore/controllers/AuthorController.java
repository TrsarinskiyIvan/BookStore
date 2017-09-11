package com.bookstore.controllers;

import com.bookstore.entitys.Author;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.bookstore.dao.Dao;
import com.bookstore.utils.UtilsBean;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class AuthorController implements Serializable {

    @EJB(beanName = "authorDao")
    private Dao authorDao;

    private String firstName;

    private String lastName;

    private List<Author> authors;
//admin's methods

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

//    user's methods
    public Author getAuthor() {

        Long id = UtilsBean.getId(FacesContext.getCurrentInstance(), "id_author");

        return (Author) authorDao.read(id);
    }

//    getters and setters
    public Dao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(Dao authorDao) {
        this.authorDao = authorDao;
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
}
