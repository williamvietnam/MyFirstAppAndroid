package com.williamnb.readlistenapp.common;

import com.williamnb.readlistenapp.domain.model.User;

public interface UserListener {
    void onUserClicked(User user);
}
