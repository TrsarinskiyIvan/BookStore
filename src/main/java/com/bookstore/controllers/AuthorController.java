package com.bookstore.controllers;

import com.bookstore.entitys.Author;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.bookstore.dao.Dao;
import com.bookstore.utils.Paginator;
import com.bookstore.utils.UtilsBean;
import javax.annotation.PostConstruct;
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

    private Author currentAuthor;

    private List<Author> authors;

    private List<Author> subList;

    private Paginator paginator;

    public AuthorController() {
    }

    @PostConstruct
    private void init() {
        authors = authorDao.getList();
        paginator = new Paginator(authors);
        subList = paginator.firstSheet();
    }

    public List<Author> getSubList() {
        return subList;
    }

    public void setSubList(List<Author> subList) {
        this.subList = subList;
    }

//admin's methods
    public void creat() {
        authorDao.create(new Author(firstName, lastName));
        init();
        firstName = null;
        lastName = null;
    }

    public void update(Author a) {
        authorDao.update(a);
        a.setEditable(false);
    }

    public void edit(Author a) {
        a.setEditable(true);
    }

    public void delete(Author a) {
        authorDao.delete(a);
        init();
    }

//    user's methods
    public Author getCurrentAuthor() {

        Long id = UtilsBean.getId(FacesContext.getCurrentInstance(), "id_author");
        if (currentAuthor == null || !id.equals(currentAuthor.getId())) {
            currentAuthor = (Author) authorDao.read(id);
        }

        return currentAuthor;
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

    public void next() {
        subList = paginator.nextSheet();
    }

    public void prev() {
        subList = paginator.prevSheet();
    }

}
