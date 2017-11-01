package com.bookstore.controllers.alter;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Book;
import javax.ejb.EJB;

public class BookControllerAlter extends AbstractController<Book> {

    @EJB(beanName = "bookDao")
    private Dao dao;

    @Override
    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
