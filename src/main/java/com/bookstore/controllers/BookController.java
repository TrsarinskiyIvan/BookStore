package com.bookstore.controllers;

import com.bookstore.dao.Dao;
import com.bookstore.entitys.Author;
import com.bookstore.entitys.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

@Named
@SessionScoped
public class BookController extends AbstractController<Book> {

    @EJB(beanName = "bookDao")
    private Dao<Book> dao;

    private List<String> idAuthors;

    private String title;

    private Part cover;

    private Part file;

    private String annotation;

    @Override
    @PostConstruct
    protected void init() {
        super.init();
    }

    @Override
    public void create() {

        List<Author> authors = new ArrayList();

        for (String s : idAuthors) {
            Author a = new Author();
            a.setId(Long.valueOf(s));
            authors.add(a);
        }

        Book tmp = new Book();
        tmp.setTitle(title);
        tmp.setAnnotation(annotation);
        tmp.setAuthors(authors);

        try {
            tmp.setCover(IOUtils.toByteArray(cover.getInputStream()));
            tmp.setFile(IOUtils.toByteArray(file.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }

        getDao().create(tmp);

        title = null;
        cover = null;
        file = null;
        annotation = null;
        init();

    }

    public Book getCurrentBook() {
        return getCurrentT("id_book");
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Part getCover() {
        return cover;
    }

    public void setCover(Part cover) {
        this.cover = cover;
    }

    public List<String> getIdAuthors() {
        return idAuthors;
    }

    public void setIdAuthors(List<String> idAuthors) {
        this.idAuthors = idAuthors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    @Override
    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

}
