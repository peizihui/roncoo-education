package com.roncoo.education.web.boss.service.dao.impl;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.SysUserMapper;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-26-16:14
 * @email:wk20041208@126.com
 */
@Repository
public class SysUserDaoImpl implements SysUserDao {
    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser getById(Long id)
    {
        return this.sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser getByUserNo(Long userNo) {
        System.out.println("5555555555555555555555555555555==>" +  userNo);
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserNoEqualTo(userNo);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public SysUser getByMobile(String mobile) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<SysUser> list = sysUserMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }

        return list.get(0);
    }

    @Override
    public Page<SysUser> listForPage(int pageCurrent, int pageSize, SysUserExample example) {
        int count = this.sysUserMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysUser>(count, totalPage, pageCurrent, pageSize, this.sysUserMapper.selectByExample(example));

    }

    @Override
    public int save(SysUser record) {

        return this.sysUserMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {

        return this.sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(SysUser record) {

        return this.sysUserMapper.updateByPrimaryKeySelective(record);
    }
}
