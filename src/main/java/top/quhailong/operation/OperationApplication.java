package top.quhailong.operation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 操作日志启动类
 *
 * @author: quhailong
 * @date: 2020/7/26
 */
@SpringBootApplication
@MapperScan("top.quhailong.operation.dao")
@EnableAsync
public class OperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationApplication.class, args);
    }
}
