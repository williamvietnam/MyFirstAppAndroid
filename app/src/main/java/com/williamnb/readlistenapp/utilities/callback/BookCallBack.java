package com.williamnb.readlistenapp.utilities.callback;

import com.williamnb.readlistenapp.data.local.models.book.Book;

public interface BookCallBack {
    void removeBook(int position);

    void openBookDetail(Book item);
}
