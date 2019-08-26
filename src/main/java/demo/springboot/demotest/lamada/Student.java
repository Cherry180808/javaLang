package demo.springboot.demotest.lamada;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *@program: Student
 *@description: Student实体类
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
@Getter
@Setter
public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        super();
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
