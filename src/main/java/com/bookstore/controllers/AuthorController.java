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
import javax.validation.constraints.NotNull;

@Named
@SessionScoped
public class AuthorController implements Serializable {

    @EJB(beanName = "authorDao")
    private Dao authorDao;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Author author;

    private List<Author> authors;
//admin's methods

    public String creat() {
        authorDao.create(new Author(firstName, lastName));
        if (authors != null) {
            authors = authorDao.getList();
        }
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
        if (author == null || !id.equals(author.getId())) {
            author = (Author) authorDao.read(id);
        }

        return author;
    }

    public Author getAuthor(Long id) {
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
