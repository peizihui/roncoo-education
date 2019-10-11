package com.roncoo.education.web.boss.biz.system;

import com.roncoo.education.system.common.bean.qo.SysQO;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @autohor:wangkun
 * @date 2019-05-18-16:42
 * @email:wk20041208@126.com
 */
@Component
public class SysBiz extends BaseBiz {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private IBossSys bossSys;

    public Page<SysVO> listForPage(SysQO qo) {
        return bossSys.listForPage(qo);
    }

    public int save(SysQO qo) {
        return bossSys.save(qo);
    }

    public int deleteById(Long id) {
        return bossSys.deleteById(id);
    }

    public SysVO getById(Long id) {
        return bossSys.getById(id);
    }

    public int updateById(SysQO qo) {
        return bossSys.updateById(qo);
    }

    public SysVO getSys() {
        return bossSys.getSys();
    }

    public int updateSys(SysQO qo) {
        return bossSys.updateById(qo);
    }

    public SysUser getSysUser(Long userNo) {
        return sysUserDao.getByUserNo(userNo);
    }
}
