package com.roncoo.education.web.boss.service.dao;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUserExample;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-26-17:09
 * @email:wk20041208@126.com
 */
public interface SysRoleUserDao {

    int save(SysRoleUser record);

    int deleteById(Long id);

    int updateById(SysRoleUser record);

    SysRoleUser getById(Long id);

    Page<SysRoleUser> listForPage(int pageCurrent, int pageSize, SysRoleUserExample example);

    List<SysRoleUser> listByUserId(Long userId);

    int deleteByUserId(Long userId);
}
