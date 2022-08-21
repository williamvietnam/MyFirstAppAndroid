package com.williamnb.readlistenapp.ui.features.book;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.data.local.models.book.BookResponse;
import com.williamnb.readlistenapp.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookMainViewModel extends BaseViewModel {

    private List<Book> bookSuggestionList;
    private List<Book> myBookList;

    public BookMainViewModel(@NonNull Application application) {
        super(application);
        this.bookSuggestionList = new ArrayList<>();
        this.myBookList = new ArrayList<>();
    }

    public List<Book> getBookSuggestionList() {
        return this.bookSuggestionList;
    }

    public List<Book> getMyBookList() {
        return this.myBookList;
    }

    public List<Book> getDataFromAssetsBookSuggestion() {
        String json = Utilities.getJsonFromAssets("book_suggestion.json", getViewModelContext());
        Gson gson = new Gson();
        BookResponse bookSuggestionResponse = gson.fromJson(json, BookResponse.class);
        this.bookSuggestionList = bookSuggestionResponse.getBooks();
        return this.bookSuggestionList;
    }

    public List<Book> getDataFromAssetsMyBook() {
        String json = Utilities.getJsonFromAssets("my_book.json", getViewModelContext());
        Gson gson = new Gson();
        BookResponse myBookResponse = gson.fromJson(json, BookResponse.class);
        this.myBookList = myBookResponse.getBooks();
        return this.myBookList;
    }

    public void deleteDataForAssetsMyBook() {
        //todo
    }
}