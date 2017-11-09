package com.bookstore.controllers;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Author;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AuthorController extends AbstractController<Author> {

    @EJB(beanName = "authorDao")
    private Dao<Author> dao;

    private String firstName;

    private String lastName;

    @Override
    @PostConstruct
    protected void init() {
        super.init();
    }

    @Override
    public void create() {
        getDao().create(new Author(firstName, lastName));
        firstName = null;
        lastName = null;
        init();
    }

    public Author getCurrentAuthor() {
        return getCurrentT("id_author");
    }

    @Override
    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
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
