package com.williamnb.readlistenapp.ui.features.book.detail;

import android.app.Application;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.book.Book;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookDetailViewModel extends BaseViewModel {

    private Book bookDetail;

    public BookDetailViewModel(@NonNull Application application) {
        super(application);
        this.bookDetail = new Book();
    }

    public Book getBookDetail() {
        return this.bookDetail;
    }

    public void setBookDetail(Book bookDetail) {
        this.bookDetail = bookDetail;
    }
}
