package com.example.shirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-02-15 21:06:15
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -79823171881618792L;

    private String id;

    private String name;

    private String password;

    private String permission;

    private LocalDateTime granularityMinute;

    private int age;

    public User(String id, String name, String password, String permission) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password, user.password) && Objects.equals(permission, user.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, permission);
    }
}