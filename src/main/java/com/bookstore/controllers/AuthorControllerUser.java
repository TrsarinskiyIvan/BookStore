package com.bookstore.controllers;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Author;
import java.util.List;
import javax.ejb.EJB;

public class AuthorControllerUser {

    @EJB(beanName = "authorDao")
    private Dao authorDao;

    public Dao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(Dao authorDao) {
        this.authorDao = authorDao;
    }

    public List<Author> authors() {
        return authorDao.getList();
    }
    
    public Author getAuthor(String id){
        
        return null;
    }

}
