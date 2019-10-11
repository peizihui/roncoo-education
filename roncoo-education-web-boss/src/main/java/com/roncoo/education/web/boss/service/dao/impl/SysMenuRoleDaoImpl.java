package com.roncoo.education.web.boss.service.dao.impl;

import com.roncoo.education.web.boss.service.dao.SysMenuRoleDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.SysMenuRoleMapper;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenuRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-28-23:20
 * @email:wk20041208@126.com
 */
@Repository
public class SysMenuRoleDaoImpl implements SysMenuRoleDao {
    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;
    @Override
    public int save(SysMenuRole record) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int updateById(SysMenuRole record) {
        return 0;
    }

    @Override
    public SysMenuRole getById(Long id) {
        return null;
    }

    @Override
    public List<SysMenuRole> listByRoleId(Long roleId) {
        System.out.println("SysMenuRoleDaoImpl======================>" + roleId);
        SysMenuRoleExample example = new SysMenuRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        System.out.println(sysMenuRoleMapper.selectByExample(example));
        return sysMenuRoleMapper.selectByExample(example);
    }

    @Override
    public int deleteByRoleId(Long roleId) {

        return 0;
    }
}
