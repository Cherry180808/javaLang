package demo.springboot.demotest.lamada;

import demo.springboot.util.WebLogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *@program: StreamTest01
 *@description: 测试常规的list处理方式
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public class StreamTest01 {
    private final static Logger logger = LoggerFactory.getLogger(StreamTest01.class);

    public static void main(String[] args){
        List<Student> stuList = InitData.getStuList();

        //需求：列出90分以上的学生姓名，并按照分数降序排序
        //遍历分数大于90的学生获取list1
        List<Student> list1 = new ArrayList<Student>();
        for(Student s : stuList){
            if(s.getScore() >= 90){
                list1.add(s);
            }
        }
        //对list1进行降序排列
        list1.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });

        System.out.println(list1);

        //使用stream的写法
        list1 = stuList.stream()
                .filter(s -> s.getScore() >= 90)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .collect(Collectors.toList());
        System.out.println(list1);
    }
}
