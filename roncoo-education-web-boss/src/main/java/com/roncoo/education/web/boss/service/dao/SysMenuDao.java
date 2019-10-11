package com.roncoo.education.web.boss.service.dao;

import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenu;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-28-23:31
 * @email:wk20041208@126.com
 */
public interface SysMenuDao {
    Long save(SysMenu record);

    int deleteById(Long id);

    int updateById(SysMenu record);

    SysMenu getById(Long id);

//    Page<SysMenu> listFgetByMobilorPage(int pageCurrent, int pageSize, SysMenuExample example);

    List<SysMenu> listByParentId(Long parentId);

    List<SysMenu> listAll();
}
