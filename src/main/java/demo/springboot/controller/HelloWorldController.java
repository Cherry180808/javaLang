package demo.springboot.controller;

import demo.springboot.annotation.ParamCheck;
import demo.springboot.common.ResponseData;
import demo.springboot.common.ResponseDataUtil;
import org.springframework.web.bind.annotation.*;

/**
 *@program: HelloWorldController
 *@description: HelloWorldController
 *@author: Xiong Aiqian
 *@create: 2019-08-15
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @RequestMapping("/home")
    public String home(){
        return "Hello world!";
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(String name){
        return "hello " + name;
    }

    @GetMapping("/testParamCheck1")
    public String testParamCheck1(@RequestParam String name){
        return "testParamCheck1 " + name;
    }

    @GetMapping("/testParamCheck2")
    public String testParamCheck2(@ParamCheck String name){
        return "testParamCheck2 " + name;
    }

    @GetMapping("/testParamCheck3")
    public ResponseData testParamCheck3(@ParamCheck @RequestParam String name){
//        return "testParamCheck3 " + name;
        return ResponseDataUtil.buildSuccess("testParamCheck3 " + name);
    }
}
