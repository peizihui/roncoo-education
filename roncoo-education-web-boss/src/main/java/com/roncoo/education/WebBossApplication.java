package com.roncoo.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @autohor:wangkun
 * @date 2019-04-22-12:42
 * @email:wk20041208@126.com
 */
@EnableFeignClients
@ServletComponentScan
@SpringCloudApplication
public class WebBossApplication {
    public static void main(String[] args){
        SpringApplication.run(WebBossApplication.class,args);

    }
}
