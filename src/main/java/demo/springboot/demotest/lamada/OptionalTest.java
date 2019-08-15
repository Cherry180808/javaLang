package demo.springboot.demotest.lamada;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *@program: OptionalTest
 *@description: 测试Optional返回
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public class OptionalTest {
    public static void main(String[] args) {
        List<Student> studentList = InitData.getStuList();
        //计算分数在60分一下的分数总和
        Optional<Integer> score = studentList.stream()
                .map(Student :: getScore)
                .filter(s -> s<60)
                .reduce((a,b) -> a+b); //没有60分以下的，以前不加判断就会出现空指针异常
        System.out.println(score.orElse(0));   //0

        Map<Integer,String> map = new HashMap<>();
        map.put(20180001,"章子");
        map.put(20180002,"小米");
        map.put(20180003,"大黄");
        map.put(20180004,"靓妹");

        String name = Optional.ofNullable(map.get(20180005)).orElse("无");
        System.out.println(name);  //无
    }
}
