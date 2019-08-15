package demo.springboot.demotest.lamada;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *@program: StreamTest03
 *@description: 测试lamada表达式的list处理方式
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
public class StreamTest03 {
    public static void main(String[] args){
        List<Student> list = InitData.getStuList();
        //将stream转化为IntStream
        //计算总分
        int totalScore = list.stream().mapToInt(Student::getScore).sum();
        System.out.println(totalScore);
        //计算平均分
        OptionalDouble avgScore = list.stream().mapToInt(Student::getScore).average();
        System.out.println(avgScore);
        //生成1-100之间的数字中偶数的个数
        long count = IntStream.rangeClosed(1,100).filter(n -> n%2 == 0).count();
        System.out.println(count);

        //自己创建流
        Stream<String> str = Stream.of("i","love","this","game");
        str.map(String::toUpperCase).forEach(System.out::println);

        int[] num = {2,5,9,8,6,7};
        IntStream intStream = Arrays.stream(num);
        int sum = intStream.sum();
        System.out.println(sum);

        //由函数生成流，创建无限流
        Stream.iterate(0, n -> n+2).limit(10).forEach(System.out::println);
    }
}
