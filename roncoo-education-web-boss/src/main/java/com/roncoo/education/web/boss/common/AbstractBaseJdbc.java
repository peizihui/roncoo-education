package com.roncoo.education.web.boss.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @autohor:wangkun
 * @date 2019-04-28-23:33
 * @email:wk20041208@126.com
 */
public class AbstractBaseJdbc {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
    /**
     * 获取当前事务最后一次更新的主键值
     */
    public Long getLastId(){
        return jdbcTemplate.queryForObject("select last_insert_id() as id", Long.class);
    }
}
