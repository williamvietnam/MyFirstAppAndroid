package com.williamnb.readlistenapp.data.model;

public class Book {
    private String id;
    private String bookThumbnail;
    private String bookName;
    private String bookCategory;
    private String book;
    private String rating;
    private String bookPrice;

    public Book(String id, String bookThumbnail, String bookName, String rating, String bookPrice) {
        this.id = id;
        this.bookThumbnail = bookThumbnail;
        this.bookName = bookName;
        this.rating = rating;
        this.bookPrice = bookPrice;
    }

    public Book(String id, String bookThumbnail, String bookName, String bookCategory, String book, String rating, String bookPrice) {
        this.id = id;
        this.bookThumbnail = bookThumbnail;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.book = book;
        this.rating = rating;
        this.bookPrice = bookPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookThumbnail() {
        return bookThumbnail;
    }

    public void setBookThumbnail(String bookThumbnail) {
        this.bookThumbnail = bookThumbnail;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}
