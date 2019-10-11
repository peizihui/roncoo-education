package com.roncoo.education.web.boss.service;

import com.roncoo.education.user.common.bean.qo.UserQO;
import com.roncoo.education.user.common.bean.vo.UserVO;
import com.roncoo.education.user.feign.IBossUser;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.web.boss.common.bean.qo.SysUserQO;
import com.roncoo.education.web.boss.common.bean.vo.SysUserVO;
import com.roncoo.education.web.boss.service.dao.SysRoleUserDao;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUserExample;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @autohor:wangkun
 * @date 2019-05-07-19:41
 * @email:wk20041208@126.com
 */

@Component
public class SysUserService {
    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysRoleUserDao sysRoleUserDao;

    @Autowired
    private IBossUser bossUser;

    public Page<SysUserVO> listForPage(SysUserQO qo){
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria c = example.createCriteria();
        if(StringUtils.hasText(qo.getMobile())){
            c.andMobileEqualTo(qo.getMobile());
        }
        example.setOrderByClause(" sort asc, status_id desc, id desc ");
        Page<SysUser> page = userDao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysUserVO.class);
    }

    public int save(SysUserQO qo){
        SysUser record = null;
        record = BeanUtil.copyProperties(qo, SysUser.class);
        return  userDao.save(record);
    }

    public SysUserVO getById(long id){
        SysUser record = userDao.getById(id);
        SysUserVO sysUserVO = null;
        sysUserVO =  BeanUtil.copyProperties(record, SysUserVO.class);
        return sysUserVO;
    }

    @Transactional
    public int deleteById(Long id) {
        // 1、删除用户所有角色
        sysRoleUserDao.deleteByUserId(id);
        // 2、删除用户
        return userDao.deleteById(id);
    }

    @Transactional
    public int updateById(SysUserQO qo){
        int res = 0;
        SysUser sysUser = userDao.getById(qo.getId());
        UserVO user = bossUser.getByMobile(sysUser.getMobile());
        if(user == null){
            throw new BaseException("找不到用户信息");
        }
        UserQO userQO = new UserQO();
        userQO.setId(user.getId());
        userQO.setMobile(qo.getMobile());
        bossUser.updateById(userQO);
        SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
        res =  userDao.updateById(record);
        return res;

    }

    public int updateBypassword(SysUserQO qo){
        if(qo.getUserNo() == null){
            throw new BaseException("用户编号不能为空,请重试");
        }
        if(StringUtils.isEmpty(qo.getMobilePsw())){
            throw new BaseException("新密码不能为空,请重试");
        }
        if (StringUtils.isEmpty(qo.getRePwd())) {
            throw new BaseException("确认密码不能为空,请重试");
        }
        if (!qo.getRePwd().equals(qo.getMobilePsw())) {
            throw new BaseException("密码不一致,请重试");
        }
        UserVO userVO = bossUser.getByUserNo(qo.getUserNo());
        if (ObjectUtil.isNull(userVO)) {
            throw new BaseException("找不到用户信息,请重试");
        }
        if (DigestUtil.sha1Hex(userVO.getMobileSalt() + qo.getMobilePsw()).equals(userVO.getMobilePsw())) {
            throw new BaseException("输入的密码与原密码一致,请重试");
        }
        UserQO userQO = new UserQO();
        userQO.setId(userVO.getId());
        userQO.setMobilePsw(DigestUtil.sha1Hex(userVO.getMobileSalt() + qo.getMobilePsw()));
        return bossUser.updateById(userQO);
    }

    public SysUserVO getByUserNoAndOrgNo(Long userNo, String orgNo) {
        SysUser record = userDao.getByUserNo(userNo);
        if (ObjectUtil.isNull(record)) {
            throw new BaseException("找不到用户信息");
        }
        SysUserVO sysUserVO = null;
        sysUserVO =  BeanUtil.copyProperties(record, SysUserVO.class);
        return sysUserVO;
    }
}
