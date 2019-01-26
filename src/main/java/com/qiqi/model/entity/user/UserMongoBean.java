package com.qiqi.model.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * mongo user集合对应的实体
 */
@Document(collection = "user")
@Component
@Data
public class UserMongoBean{

    @Id
    public String id;

    @Field(value = "user_id")
    @SerializedName(value = "user_id")
    @JsonProperty(value = "user_id")
    public long userId;

    @Field(value = "age")
    @SerializedName(value = "age")//将对象里的属性跟json里字段对应值匹配起来
    @JsonProperty(value = "age")//把userId转为json时为user_id
    public int age;

    @Field(value = "name")
    @SerializedName(value = "name")
    @JsonProperty(value = "name")
    public String name;

    public UserMongoBean() {
    }

    public UserMongoBean(long userId, int age, String name) {
        this.userId = userId;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserMongoBean{" +
                "user_id='" + userId + '\'' +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
