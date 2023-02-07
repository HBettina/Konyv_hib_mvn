package com.progmatic.book.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "book_hib")
public class Book {
    @Id
    private Long isbn;
    private String title;
    @ManyToOne(cascade = CascadeType.PERSIST)
//    @MapsId("id")
    @JoinColumn(name = "author_id")
    private Author author;
    private Year dop;
    private int edition;
    @ManyToMany
    @JoinTable(
            name = "book_stores_hib",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    private List<Store> stores;

    public Book(Long isbn, String title, Author author, Year dop, int edition) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.dop = dop;
        this.edition = edition;
    }
    public Book() {
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Year getDop() {
        return dop;
    }

    public void setDop(Year dop) {
        this.dop = dop;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
    public void addStore(Store store){
        this.stores.add(store);
    }
    public void deleteStore(Store store){
        this.stores.remove(store);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author=" + author.getName() +
                ", dop=" + dop +
                ", edition=" + edition +
                ", stores=" + stores +
                '}';
    }
}
