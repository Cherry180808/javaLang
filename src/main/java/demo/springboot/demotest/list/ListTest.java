package demo.springboot.demotest.list;

import demo.springboot.demotest.lamada.InitData;
import demo.springboot.demotest.lamada.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: javaLang
 * @description:
 * @author: Xiong Aiqian
 * @create: 2019-08-26 14:50
 */
public class ListTest {
    public static void main(String[] args){
        ListTest.distinctUsers1();

        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            list.add("haha-" + i);
        }

        long time = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            distinctUsers2(list);
        }
        long time1 = System.currentTimeMillis();
        System.out.println("time1:"+(time1-time));

        for (int i = 0; i < 10000; i++) {
            distinctUsers3(list);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("time2:"+(time2-time1));

        for (int i = 0; i < 10000; i++) {
            distinctUsers4(list);
        }
        long time3 = System.currentTimeMillis();
        System.out.println("time3:"+(time3-time2));

        //Log结果
//        [Student{name='刘一', score=85}, Student{name='陈二', score=90}, Student{name='李四', score=88}, Student{name='王五', score=83}, Student{name='赵六', score=95}, Student{name='孙七', score=87}, Student{name='周八', score=84}, Student{name='吴九', score=100}]
//        time1:618
//        time2:889
//        time3:18130
    }

    //测试list去重
    public static List<Student> distinctUsers1(){
        List<Integer> scores = new ArrayList<>();  //临时存储所有学生的score
        List<Student> users =InitData.getStuList();
        List<Student> studentList = users.stream().filter(v -> {
            boolean flag = !scores.contains(v.getScore());
            scores.add(v.getScore());
            return flag;
        }).collect(Collectors.toList());
        System.out.println(studentList);
        return users;
    }

    /**
     * 利用HashSet不能添加重复数据的特性 由于HashSet不能保证添加顺序，所以只能作为判断条件
     * @param list
     */
    public static void distinctUsers2(List<String> list){
        HashSet<String> stringHashSet = new HashSet<>(list.size());
        List<String> strings = new ArrayList<>(list.size());
        for(String str : list){
            if(stringHashSet.add(str)){
                strings.add(str);
            }
        }
        list.clear();
        list.addAll(strings);
    }

    /**
     * 利用LinkedHashSet不能添加重复数据并能保证添加顺序的特性 ：
     * @param list
     */
    public static void distinctUsers3(List<String> list){
        LinkedHashSet<String> stringLinkedHashSet = new LinkedHashSet<>(list.size());
        stringLinkedHashSet.addAll(list);
        list.clear();
        list.addAll(stringLinkedHashSet);
    }

    /**
     * 利用List的contains方法循环遍历：
     * @param list
     */
    public static void distinctUsers4(List<String> list){
        List<String> stringList = new ArrayList<>(list.size());
        for(String str : list){
            if(!stringList.contains(str)){
                stringList.add(str);
            }
        }
        list.clear();
        list.addAll(stringList);
    }
}
