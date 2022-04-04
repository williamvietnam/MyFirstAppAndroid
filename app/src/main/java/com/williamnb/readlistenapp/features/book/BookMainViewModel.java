package com.williamnb.readlistenapp.features.book;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.common.Key;

public class BookMainViewModel extends BaseViewModel {

    public boolean checkLoginSessionDefault(String id){
        if(id.equals(Key.ID_DEFAULT)){
            return true;
        }
        return false;
    }

    public void saveLoginSessionDefault(){

    }

}
