package com.williamnb.readlistenapp.data.local.models.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class Book implements Serializable {

    @SerializedName("bookId")
    @Expose
    private String bookId;

    @SerializedName("bookThumbnail")
    @Expose
    private String bookThumbnail;

    @SerializedName("bookName")
    @Expose
    private String bookName;

    @SerializedName("bookCategory")
    @Expose
    private String bookCategory;

    @SerializedName("book")
    @Expose
    private String book;

    @SerializedName("rating")
    @Expose
    private Integer rating;

    @SerializedName("bookPrice")
    @Expose
    private String bookPrice;

    @SerializedName("optionRead")
    @Expose
    private String optionRead;

    @SerializedName("progress")
    @Expose
    private Integer progress;

    public Book(){
    }
    public Book(String id, String bookThumbnail, String bookName, Integer rating, String bookPrice) {
        this.bookId = id;
        this.bookThumbnail = bookThumbnail;
        this.bookName = bookName;
        this.rating = rating;
        this.bookPrice = bookPrice;
    }

    public Book(String id, String bookThumbnail, String bookName, String bookCategory, String book, Integer rating, String bookPrice) {
        this.bookId = id;
        this.bookThumbnail = bookThumbnail;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.book = book;
        this.rating = rating;
        this.bookPrice = bookPrice;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String id) {
        this.bookId = id;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getOptionRead() {
        return optionRead;
    }

    public void setOptionRead(String optionRead) {
        this.optionRead = optionRead;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}