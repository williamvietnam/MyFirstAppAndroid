package com.williamnb.readlistenapp.ui.features.bookstore.book_category;

import android.app.Application;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.BookStore;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class BookCategoryViewModel extends BaseViewModel {

    private BookStore.BookCategory bookCategory;

    public BookCategoryViewModel(@NonNull Application application) {
        super(application);
        this.bookCategory = new BookStore.BookCategory();
    }

    public void setBookCategory(BookStore.BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public BookStore.BookCategory getBookCategory() {
        return bookCategory;
    }
}
