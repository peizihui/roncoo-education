package com.roncoo.education.web.boss.util;
import com.alibaba.druid.filter.config.ConfigTools;

public class DruidUtil {

    public static void main(String[] args) {
        try {
            String password = "root";
            System.out.println(ConfigTools.encrypt(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
