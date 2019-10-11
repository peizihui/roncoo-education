package com.roncoo.education.web.boss.service.dao;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUserExample;


/**
 * @autohor:wangkun
 * @date 2019-04-22-18:42
 * @email:wk20041208@126.com
 */
public interface SysUserDao {

    SysUser getById(Long id);
    SysUser getByUserNo(Long userNo);
    int save(SysUser record);
    int deleteById(Long id);
    int updateById(SysUser record);


    SysUser getByMobile(String mobile);
    Page<SysUser> listForPage(int pageCurrent, int pageSize, SysUserExample example);
}
