package cn.edu.lingnan.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author: chen_man_cheng
 * @Description: //TODO
 * @Date: Create in 17:24 2020/6/15
 */
@MapperScan("cn.edu.lingnan.exam.dao")
@SpringBootApplication
public class ExamApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class,args);
    }
}
