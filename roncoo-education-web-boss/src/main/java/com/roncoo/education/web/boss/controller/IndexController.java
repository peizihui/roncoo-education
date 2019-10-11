package com.roncoo.education.web.boss.controller;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.tools.Constants;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.web.boss.biz.IndexBiz;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuVO;
import com.roncoo.education.web.boss.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @autohor:wangkun
 * @date 2019-04-26-15:56
 * @email:wk20041208@126.com
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private IndexBiz indexBiz;

    @RequestMapping(value = {"/index"},method = RequestMethod.GET)
    public void index(ModelMap modelMap) throws IllegalAccessException, InstantiationException {
        Long userNo = Long.valueOf(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.USER_NO).toString());
        List<SysMenuVO> menuVOList = sysMenuService.listByUser(userNo);
        System.out.println(menuVOList);
        if(menuVOList == null || menuVOList.isEmpty()){
            throw new BaseException("没有权限");
        }
        modelMap.put("menuList",menuVOList);
        SecurityUtils.getSubject().getSession(false).setAttribute(Constants.Session.BOSS_MENU, JSONUtil.toJSONString(menuVOList));


    }
    /**
     * 进入首页
     */
    @RequestMapping(value = {"/index"},method = RequestMethod.POST)
    public String postIndex(){
        System.out.println("认证成功，登录首页");
        return redirect("/index");
    }

    @RequestMapping(value = {"/content"})
    public void context(ModelMap modelMap){
        modelMap.put("javaVersion",System.getProperty("java.version"));
        modelMap.put("osname",System.getProperty("os.name"));
    }


}
