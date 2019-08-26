package demo.springboot.demotest.lamada;

import java.util.ArrayList;
import java.util.List;
/**
 *@program: InitData
 *@description: InitData
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public class InitData {
    public static List<Student> getStuList(){
        List<Student> stuList = new ArrayList<>(10);
        stuList.add(new Student("刘一", 85));
        stuList.add(new Student("陈二", 90));
        stuList.add(new Student("张三", 85));
        stuList.add(new Student("李四", 88));
        stuList.add(new Student("王五", 83));
        stuList.add(new Student("赵六", 95));
        stuList.add(new Student("孙七", 87));
        stuList.add(new Student("周八", 84));
        stuList.add(new Student("吴九", 100));
        stuList.add(new Student("郑十", 95));
        return stuList;
    }
}
