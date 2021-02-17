package com.sweater.models.dto;

import com.sweater.models.Message;
import com.sweater.models.User;
import com.sweater.models.util.MessageHelper;

public class MessageDto {
    private Long id;
    private String text;
    private String tag;
    private String filename;
    private User author;
    private Long likes;
    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id=message.getId();
        this.tag=message.getTag();
        this.text=message.getText();
        this.filename = message.getFilename();
        this.author=message.getAuthor();
        this.likes = likes;
        this.meLiked = meLiked;
    }
    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }
    public Long getAuthorId() {
        return MessageHelper.getAuthorId(author);
    }
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public String getFilename() {
        return filename;
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}
