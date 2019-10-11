package com.roncoo.education.web.boss.service.dao.impl;

import com.roncoo.education.web.boss.common.AbstractBaseJdbc;
import com.roncoo.education.web.boss.service.dao.SysMenuDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.SysMenuMapper;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-28-23:32
 * @email:wk20041208@126.com
 */
@Repository
public class SysMenuDaoImpl extends AbstractBaseJdbc implements SysMenuDao {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public Long save(SysMenu record) {
        return null;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int updateById(SysMenu record) {
        return 0;
    }

    @Override
    public SysMenu getById(Long id) {
        return null;
    }

    @Override
    public List<SysMenu> listByParentId(Long parentId) {
        SysMenuExample example = new SysMenuExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause(" sort desc,id desc");
        System.out.println("SysMenuDaoImpl:listByParnet:----------->" + sysMenuMapper.selectByExample(example));
        return this.sysMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysMenu> listAll() {
        return null;
    }
}
