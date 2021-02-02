package com.example.demo.models;

import org.hibernate.annotations.Immutable;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;


@SqlResultSetMapping(name="Statement-SQL-Mapping1",
classes = {
        @ConstructorResult(targetClass = User.class,
        columns = {
                @ColumnResult(name="Id", type = int.class),
                @ColumnResult(name = "Name", type = String.class),
                @ColumnResult(name = "Level", type = int.class)
        })
})
@Entity
@Immutable
@Table(name = "users")
public class User {

    @javax.persistence.Id
    @GeneratedValue
    private int Id;
    private String Name;
    private int Level;

    public User(int id, String name, int level) {
        Id = id;
        Name = name;
        Level = level;
    }

    public User() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }
}
