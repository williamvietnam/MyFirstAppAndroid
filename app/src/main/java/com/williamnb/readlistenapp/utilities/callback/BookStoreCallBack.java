package com.williamnb.readlistenapp.utilities.callback;

import com.williamnb.readlistenapp.data.local.models.BookStore;

public interface BookStoreCallBack {

    void onSeeMoreClicked(String bookCategoryId);

    void openBookDetail(BookStore.Data item);
}
