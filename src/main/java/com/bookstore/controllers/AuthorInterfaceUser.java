package com.bookstore.controllers;

import com.bookstore.entitys.Author;
import com.bookstore.entitys.Book;
import java.util.List;

public interface AuthorInterfaceUser {

    Author getBook(String id);

    List<Author> authors();

    List<Book> getAuthorBooks(String id);
    
    

}
