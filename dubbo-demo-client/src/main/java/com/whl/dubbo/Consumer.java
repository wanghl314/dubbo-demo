package com.whl.dubbo;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService"); // obtain proxy object for remote invocation
        String hello = demoService.sayHello("world"); // execute remote invocation
        System.out.println(hello); // show the result

        FileService fileService = (FileService) context.getBean("fileService");
        String name = fileService.upload("15-火车票2.JPG", Files.newInputStream(Paths.get("C:\\Users\\Administrator\\Desktop\\发票\\15-火车票2.JPG")));
        System.out.println(name);
        InputStream is = fileService.download(name);
        System.out.println(is);

        System.in.read();
    }

}
