package com.example.demo.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;



/*@SqlResultSetMapping(name="Statement-SQL-Mapping2",
        classes = {
                @ConstructorResult(targetClass = Views.class,
                        columns = {
                                @ColumnResult(name="Id", type = int.class),
                                @ColumnResult(name = "UsersName", type = String.class),
                                @ColumnResult(name = "VideoId", type = String.class),
                        })
        })*/
@Entity
@Table(name = "views")
public class Views {

    @Id
    @GeneratedValue
    private int Id;
    private String UsersName;
    private String VideoId;

    public Views() {
    }


    public Views(int id, String usersName, String videoId) {
        Id = id;
        UsersName = usersName;
        VideoId = videoId;
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

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }
}
