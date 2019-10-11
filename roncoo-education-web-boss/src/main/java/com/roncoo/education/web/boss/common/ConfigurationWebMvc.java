package com.roncoo.education.web.boss.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @autohor:wangkun
 * @date 2019-04-28-22:37
 * @email:wk20041208@126.com
 */
@Configuration
public class ConfigurationWebMvc implements WebMvcConfigurer {

    private static final String[] PATH = new String[] { "/admin/**", "/course/**", "/system/**", "/user/**" };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }




}
