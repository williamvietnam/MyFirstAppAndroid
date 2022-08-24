package com.williamnb.readlistenapp.data.local.models.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class BookResponse {

    @SerializedName("books")
    @Expose
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
