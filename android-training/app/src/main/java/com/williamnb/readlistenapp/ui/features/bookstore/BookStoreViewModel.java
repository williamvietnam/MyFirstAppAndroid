package com.williamnb.readlistenapp.ui.features.bookstore;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.BookStore;
import com.williamnb.readlistenapp.utilities.Utilities;
import com.williamnb.readlistenapp.utilities.callback.BookStoreCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class BookStoreViewModel extends BaseViewModel {

    private List<BookStore.BookCategory> dataList;
    private List<BookStoreAdapterChild> adapterChildList;
    private int dataSize;

    public BookStoreViewModel(@NonNull Application application) {
        super(application);
        this.dataList = new ArrayList<>();
        this.adapterChildList = new ArrayList<>();
    }

    public List<BookStore.BookCategory> getDataFromAssetsBookStore() {
        String json = Utilities.getJsonFromAssets("book_store.json", getViewModelContext());
        Gson gson = new Gson();
        BookStore bookStore = gson.fromJson(json, BookStore.class);
        this.dataList = bookStore.getBookCategoryList();
        this.dataSize = this.dataList.size();
        return this.dataList;
    }

    public List<BookStoreAdapterChild> getAdapterChildList(BookStoreCallBack callBack) {
        for (int i = 0; i < dataSize; i++) {
            BookStoreAdapterChild adapterChild = new BookStoreAdapterChild(dataList.get(i).getBookList(), callBack);
            adapterChildList.add(adapterChild);
        }
        return this.adapterChildList;
    }
}