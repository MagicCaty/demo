package com.example.shirodemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Parent {
    private String id;
    private String date;
    private String name;
    private Integer age;
    private Integer fraction;
    private List<Course> courseList;
    public  class Study {
        private   String a;
    }

    public Study getStudy() {
        return new Study();
    }

}
