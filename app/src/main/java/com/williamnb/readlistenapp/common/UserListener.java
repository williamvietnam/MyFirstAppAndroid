package com.williamnb.readlistenapp.common;

import com.williamnb.readlistenapp.domain.models.User;

public interface UserListener {
    void onUserClicked(User user);
}
