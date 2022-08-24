package com.williamnb.readlistenapp.data.local.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class BookStore implements Serializable {

    @SerializedName("bookStore")
    @Expose
    private List<BookCategory> bookCategoryList;

    public List<BookCategory> getBookCategoryList() {
        return bookCategoryList;
    }

    public void setBookCategoryList(List<BookCategory> bookCategoryList) {
        this.bookCategoryList = bookCategoryList;
    }

    public static class BookCategory implements Serializable {

        @SerializedName("bookStoreId")
        @Expose
        private String bookStoreId;

        @SerializedName("bookCategoryId")
        @Expose
        private String bookCategoryId;

        @SerializedName("bookCategory")
        @Expose
        private String bookCategoryName;

        @SerializedName("data")
        @Expose
        private List<Data> bookList;

        public String getBookStoreId() {
            return bookStoreId;
        }

        public String getBookCategoryId() {
            return bookCategoryId;
        }

        public String getBookCategoryName() {
            return bookCategoryName;
        }

        public List<Data> getBookList() {
            return bookList;
        }
    }

    public static class Data implements Serializable {

        @SerializedName("bookId")
        @Expose
        private String bookId;

        @SerializedName("bookThumbnail")
        @Expose
        private String bookThumbnail;

        @SerializedName("bookName")
        @Expose
        private String bookName;

        @SerializedName("rating")
        @Expose
        private Integer rating;

        @SerializedName("bookPrice")
        @Expose
        private String bookPrice;

        @SerializedName("bookSummaryContent")
        @Expose
        private String bookSummaryContent;

        @SerializedName("bookFrontCover")
        @Expose
        private String bookFrontCover;

        @SerializedName("bookBackCover")
        @Expose
        private String bookBackCover;

        @SerializedName("bookAuthor")
        @Expose
        private String bookAuthor;

        @SerializedName("bookCategory")
        @Expose
        private String bookCategory;

        public String getBookId() {
            return bookId;
        }

        public String getBookThumbnail() {
            return bookThumbnail;
        }

        public String getBookName() {
            return bookName;
        }

        public Integer getRating() {
            return this.rating;
        }

        public String getBookPrice() {
            return bookPrice;
        }

        public String getBookSummaryContent() {
            return bookSummaryContent;
        }

        public String getBookFrontCover() {
            return bookFrontCover;
        }

        public String getBookBackCover() {
            return bookBackCover;
        }

        public String getBookAuthor() {
            return bookAuthor;
        }

        public String getBookCategory() {
            return bookCategory;
        }
    }
}