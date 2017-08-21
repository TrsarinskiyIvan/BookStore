package com.bookstore.controllers;

import com.bookstore.dao.AbstractDao;
import com.bookstore.entitys.Author;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AuthorController implements Serializable {

    @EJB(beanName = "authorDao")
    private AbstractDao authorDao;

    private Author author;

    private String id;

    private String firstName;

    private String lastName;

    private boolean canEdit = false;

    public AbstractDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(AbstractDao authorDao) {
        this.authorDao = authorDao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String creat() {

        Author tmp = new Author();
        tmp.setFirstName(firstName);
        tmp.setLastName(lastName);
        authorDao.create(tmp);

        return "admin";
    }

    public String update() {
        canEdit = !canEdit;
        return null;
    }

    public String edit() {
        canEdit = !canEdit;

        return null;
    }

    public String delete() {

        authorDao.delete(Long.parseLong(id));

        return null;
    }

    public List<Author> readAll() {
        return authorDao.getAll();
    }

}
