package com.williamnb.readlistenapp.ui.features.book.detail;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.CommentResponse;
import com.williamnb.readlistenapp.data.local.models.book.Book;
import com.williamnb.readlistenapp.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookDetailViewModel extends BaseViewModel {

    private Book bookDetail;
    private List<CommentResponse.Comment> commentList;

    public BookDetailViewModel(@NonNull Application application) {
        super(application);
        this.bookDetail = new Book();
        this.commentList = new ArrayList<>();
    }

    public Book getBookDetail() {
        return this.bookDetail;
    }

    public void setBookDetail(Book bookDetail) {
        this.bookDetail = bookDetail;
    }

    public List<CommentResponse.Comment> getCommentList() {
        String json = Utilities.getJsonFromAssets("book_comment.json", getViewModelContext());
        Gson gson = new Gson();
        CommentResponse commentResponse = gson.fromJson(json, CommentResponse.class);
        this.commentList = commentResponse.getCommentList();
        return this.commentList;
    }
}