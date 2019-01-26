package com.qiqi.model.entity.user;

import lombok.Data;
import javax.persistence.*;

/**
 * mysql user表对应实体
 */
@Entity
@Table(name = "user")
@Data
public class UserBean {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    public UserBean() {
    }

    public UserBean(long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
