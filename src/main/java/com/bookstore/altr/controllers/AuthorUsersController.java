package com.bookstore.altr.controllers;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Author;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.bookstore.utils.*;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class AuthorUsersController {

    @EJB(beanName = "authorDao")
    private Dao authorDao;

    public Dao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(Dao authorDao) {
        this.authorDao = authorDao;
    }

    public List<Author> getAuthors() {
        return authorDao.getList();
    }

    public Author getAuthor() {

        Long id = UtilsBean.getId(FacesContext.getCurrentInstance(), "id_author");
       
        return (Author) authorDao.read(id);
    }
}
