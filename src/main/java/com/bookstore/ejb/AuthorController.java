package com.bookstore.ejb;

import com.bookstore.entitys.Author;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AuthorController implements Serializable{
    
    @Inject
    private AuthorDao dao;
    
    public Author getAuthor(){
        return dao.getAuthors(1l);
    }
    
}
