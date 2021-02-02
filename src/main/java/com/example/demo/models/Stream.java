package com.example.demo.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Date;


@SqlResultSetMapping(name="Statement-SQL-Mapping3",
        classes = {
                @ConstructorResult(targetClass = Stream.class,
                        columns = {
//                                @ColumnResult(name="Id", type = int.class),
                                @ColumnResult(name = "UsersName", type = String.class),
                                @ColumnResult(name = "StreamName", type = String.class),
                                @ColumnResult(name = "Type", type = String.class),
                                @ColumnResult(name = "Game", type = String.class),
                                @ColumnResult(name = "Date", type = String.class),
                                @ColumnResult(name = "PhotoUrl", type = String.class),
                                @ColumnResult(name = "StreamKey", type = String.class)
                        })
        })
@Entity
@Immutable
@Table(name = "streams")
public class Stream {

    @javax.persistence.Id
    @GeneratedValue
    private int Id;
    private String UsersName;
    private String StreamName;
    private String Type;
    private String Game;
    private String Date;
    private String PhotoUrl;
    private String Key;


    public Stream(String usersName, String streamName, String type, String game, String date, String photoUrl, String key) {
        UsersName = usersName;
        StreamName = streamName;
        Type = type;
        Game = game;
        Date = date;
        PhotoUrl = photoUrl;
        Key = key;
    }

    public Stream() {
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

    public String getStreamName() {
        return StreamName;
    }

    public void setStreamName(String streamName) {
        StreamName = streamName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getGame() {
        return Game;
    }

    public void setGame(String game) {
        Game = game;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
