package com.bookstore.entitys;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@NamedQueries(
        @NamedQuery(name = "Author.list", query = "select a from Author a order by a.firstName")
)
public class Author extends AbstractEntity implements Serializable {
    
    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "authors_ID"),
            inverseJoinColumns = @JoinColumn(name = "books_ID"))
    private List<Book> books;
    
    public Author() {
    }
    
    public Author(String fistName, String lastName) {
        this.firstName = fistName;
        this.lastName = lastName;
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
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public void addBook(Book b) {
        books.add(b);
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
