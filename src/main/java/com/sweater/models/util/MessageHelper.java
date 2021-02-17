package com.sweater.models.util;

import com.sweater.models.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author){
        return author != null ? author.getUsername() : "<none>";
    }

    public static Long getAuthorId(User author) {
        return author != null ? author.getId() : 0;
    }
}
