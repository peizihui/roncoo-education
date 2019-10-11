package com.roncoo.education.web.boss.service;

import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuVO;
import com.roncoo.education.web.boss.service.dao.SysMenuDao;
import com.roncoo.education.web.boss.service.dao.SysMenuRoleDao;
import com.roncoo.education.web.boss.service.dao.SysRoleUserDao;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-26-15:58
 * @email:wk20041208@126.com
 */
@Component
public class SysMenuService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleUserDao sysRoleUserDao;

    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;

    @Autowired
    private SysMenuDao menuDao;



    public List<SysMenuVO> listByUser(Long userNo) throws InstantiationException, IllegalAccessException {
        // 用户-->角色-->菜单
        SysUser sysUser = sysUserDao.getByUserNo(userNo);
        if(ObjectUtil.isNull(sysUser)){
            throw new BaseException("用户异常");
        }
        List<SysMenuRole> sysMenuRoleList = new ArrayList<>();
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
        for(SysRoleUser sru:sysRoleUserList){
            sysMenuRoleList.addAll(sysMenuRoleDao.listByRoleId(sru.getRoleId()));
        }
        System.out.println("SysMenuService:" + sysMenuRoleList);
        return listByRole(sysMenuRoleList);


    }
    public List<SysMenuVO> listByRole(List<SysMenuRole> sysMenuRoleList) throws IllegalAccessException, InstantiationException {
        List<SysMenuVO> list = recurision(0L);
        List<SysMenuVO> sysMenuVOList = new ArrayList<>();
        sysMenuVOList = listMenu(sysMenuVOList, sysMenuRoleList, list);
        return sysMenuVOList;
    }

    private List<SysMenuVO> listMenu(List<SysMenuVO> sysMenuVOList, List<SysMenuRole> sysMenuRoleList, List<SysMenuVO> list) throws InstantiationException, IllegalAccessException {
        for (SysMenuVO mv : list) {
            SysMenuVO v = null;
            for (SysMenuRole vo : sysMenuRoleList) {
                if (mv.getId().equals(vo.getMenuId())) {
                    v = BeanUtil.copyProperties(mv, SysMenuVO.class);
                    break;
                }
            }
            if (ObjectUtil.isNotNull(v)) {
                sysMenuVOList.add(v);
                List<SysMenuVO> l = new ArrayList<>();
                if (v != null) {
                    v.setList(l);
                }
                listMenu(l, sysMenuRoleList, mv.getList());
            }
        }
        return sysMenuVOList;
}
    /**
     * 递归显示菜单(角色关联菜单)
     */
    private List<SysMenuVO> recurision(Long parentId) throws InstantiationException, IllegalAccessException {
        List<SysMenuVO> lists = new ArrayList<>();
        List<SysMenu> list = menuDao.listByParentId(parentId);
        if(CollectionUtil.isNotEmpty(list)){
            for(SysMenu m:list){
               SysMenuVO vo =  BeanUtil.copyProperties(m, SysMenuVO.class);
               vo.setList(recurision(m.getId()));
               lists.add(vo);

            }
        }
        return lists;
    }


}
