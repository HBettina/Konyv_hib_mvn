package com.progmatic.book.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author_hib")
public class Author {
    @Id
    private int id;
    private String name;
    private Year dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> books;

    public Author(int id, String name, Year dob, Gender gender) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
    }
    public Author() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Year getDob() {
        return dob;
    }

    public void setDob(Year dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", books=" + titleOfBooks +
                '}';
    }
}
