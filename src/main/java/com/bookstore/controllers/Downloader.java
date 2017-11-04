package com.bookstore.controllers;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Book;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class Downloader implements Serializable {

    @EJB(beanName = "bookDao")
    private Dao<Book> bookDao;

    private Long id;

    public void download() throws IOException {

        byte[] file = bookDao.read(id).getFile();
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset();
        ec.setResponseContentType("text/plain");
        ec.setResponseContentLength(file.length);
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "book.zip" + "\"");
        OutputStream output = ec.getResponseOutputStream();
        output.write(file);
        fc.responseComplete();

    }

    public Dao getBookDao() {
        return bookDao;
    }

    public void setBookDao(Dao bookDao) {
        this.bookDao = bookDao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
