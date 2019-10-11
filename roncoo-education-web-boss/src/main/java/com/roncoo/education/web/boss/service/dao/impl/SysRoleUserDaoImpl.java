package com.roncoo.education.web.boss.service.dao.impl;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.SysRoleUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.SysRoleUserMapper;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-26-17:10
 * @email:wk20041208@126.com
 */
@Repository
public class SysRoleUserDaoImpl implements SysRoleUserDao {
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Override
    public List<SysRoleUser> listByUserId(Long userId) {
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        System.out.println("SysRoleUserDaoImpl=================>" + userId);
        System.out.println("SysRoleUserDaoImpl=================>" + sysRoleUserMapper.selectByExample(example));
       return this.sysRoleUserMapper.selectByExample(example);
    }

    @Override
    public int save(SysRoleUser record) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int updateById(SysRoleUser record) {
        return 0;
    }

    @Override
    public SysRoleUser getById(Long id) {
        return null;
    }

    @Override
    public Page<SysRoleUser> listForPage(int pageCurrent, int pageSize, SysRoleUserExample example) {
        return null;
    }

    @Override
    public int deleteByUserId(Long userId) {
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return this.sysRoleUserMapper.deleteByExample(example);
    }
}
