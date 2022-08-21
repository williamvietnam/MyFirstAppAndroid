package com.williamnb.readlistenapp.utilities.callback;

import com.williamnb.readlistenapp.data.local.models.BookStore;

public interface BookStoreCallBack {

    void onSeeMoreClicked(BookStore.BookCategory item);

    void openBookDetail(BookStore.Data item);
}
