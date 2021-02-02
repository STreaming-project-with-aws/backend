package com.example.demo.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@SqlResultSetMapping(name="Statement-SQL-Mapping2",
        classes = {
                @ConstructorResult(targetClass = Comment.class,
                        columns = {
//                                @ColumnResult(name="Id", type = int.class),
                                @ColumnResult(name = "UsersName", type = String.class),
                                @ColumnResult(name = "Content", type = String.class),
                                @ColumnResult(name = "Date", type = String.class),
                                @ColumnResult(name = "VideoId", type = String.class),
                        })
        })
@Entity
@Immutable
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private int Id;
    private String UsersName;
    private String Content;
    private String Date;
    private String VideoId;


    public Comment(String usersName, String content, String date, String videoId) {
        UsersName = usersName;
        Content = content;
        Date = date;
        VideoId = videoId;
    }

    public Comment() {
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsersName() {
        return UsersName;
    }

    public void setUsersName(String usersName) {
        UsersName = usersName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }
}
