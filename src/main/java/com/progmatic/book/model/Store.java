package com.progmatic.book.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store_hib")
public class Store {
    @Id
    private int id;
    @Embedded
    private Address address;
    private String owner;
    private boolean isTheContractActive;
    @ManyToMany(mappedBy = "stores")
    private List<Book> books;

    public Store(int id, Address address, String owner) {
        this.id = id;
        this.address = address;
        this.owner = owner;
    }

    public Store() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addBooks(Book book) {
        this.books.add(book);
    }
    public void deleteBooks(Book book) {
        this.books.remove(book);
    }

    @Override
    public String toString() {
        List<String> titleOfBooks = new ArrayList<>();
        for (Book book: this.books) {
            titleOfBooks.add(book.getTitle());
        }
        return "Store{" +
                "id=" + id +
                ", address=" + address +
                ", owner='" + owner + '\'' +
                ", books=" + titleOfBooks +
                '}';
    }
}
