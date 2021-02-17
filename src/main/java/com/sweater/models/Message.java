package com.sweater.models;

import com.sweater.models.util.MessageHelper;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Please fill the message")
    @Length(max=2048, message = "Too long message")
    private String text;
    @Length(max=255, message = "Too long tag")
    private String tag;
    private String filename;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany
    @JoinTable(
            name = "message_likes",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    Set<User> likes = new HashSet<>();

    public Message() {}
    public Message(String text, String tag,User user) {
        this.text = text;
        this.tag = tag;
        this.author=user;
    }
    /*public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }*/
    public Long getAuthorId() {
        return MessageHelper.getAuthorId(author);
    }
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) &&
                text.equals(message.text) &&
                tag.equals(message.tag) &&
                filename.equals(message.filename) &&
                author.equals(message.author) &&
                likes.equals(message.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, tag, filename, author, likes);
    }
}
