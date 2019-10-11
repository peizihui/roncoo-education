package com.roncoo.education.web.boss.service.dao;

import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenuRole;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-28-23:19
 * @email:wk20041208@126.com
 */
public interface SysMenuRoleDao {

    int save(SysMenuRole record);

    int deleteById(Long id);

    int updateById(SysMenuRole record);

    SysMenuRole getById(Long id);

//    Page<SysMenuRole> listForPage(int pageCurrent, int pageSize, SysMenuRoleExample example);

    List<SysMenuRole> listByRoleId(Long roleId);

    int deleteByRoleId(Long roleId);
}
