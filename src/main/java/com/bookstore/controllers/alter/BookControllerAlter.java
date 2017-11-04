package com.bookstore.controllers.alter;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Book;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BookControllerAlter extends AbstractController<Book> {

    @EJB(beanName = "bookDao")
    private Dao<Book> dao;

    @Override
    @PostConstruct
    protected void init() {
        super.init();
    }

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
    
    public Book getCurrentBook(){
        return getCurrentT("id_book");
    }

}
