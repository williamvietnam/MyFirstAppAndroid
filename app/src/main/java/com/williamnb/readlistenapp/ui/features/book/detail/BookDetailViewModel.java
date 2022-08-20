package com.williamnb.readlistenapp.ui.features.book.detail;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.Comment;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookDetailViewModel extends BaseViewModel {

    private Book bookDetail;
    private List<Comment.Data> dataList;

    public BookDetailViewModel(@NonNull Application application) {
        super(application);
        this.bookDetail = new Book();
        this.dataList = new ArrayList<>();
    }

    public Book getBookDetail() {
        return this.bookDetail;
    }

    public void setBookDetail(Book bookDetail) {
        this.bookDetail = bookDetail;
    }

    public List<Comment.Data> getCommentList() {
        String json = Utilities.getJsonFromAssets("book_comment.json", getViewModelContext());
        Gson gson = new Gson();
        Comment comment = gson.fromJson(json, Comment.class);
        this.dataList = comment.getCommentList();
        return this.dataList;
    }
}