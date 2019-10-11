package com.roncoo.education.web.boss.controller.user;

import com.roncoo.education.user.common.bean.qo.UserQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.user.UserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @autohor:wangkun
 * @date 2019-05-07-18:51
 * @email:wk20041208@126.com
 */
@Controller
@RequestMapping(value = {"/user/user"})
public class UserController extends BaseController {
    private final static String TARGETID = "user-user";

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value={"/sysUserList"})
    public void sysUserlist(@ModelAttribute  UserQO qo, ModelMap modelMap){
        modelMap.put("page",userBiz.listForPage(qo));
        modelMap.put("bean",qo);
    }

    @RequestMapping(value = {"/list"})
    public void list(@ModelAttribute   UserQO qo,ModelMap modelMap){
        modelMap.put("page",userBiz.listForPage(qo));
        modelMap.put("bean",qo);
    }

}
