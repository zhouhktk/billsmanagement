package com.zfnotes;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zfnotes.mapper") // 指定扫描指定包下的所有mapper
@SpringBootApplication
public class SpringBoot10BillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot10BillApplication.class, args);
    }

}
