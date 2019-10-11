package com.roncoo.education.web.boss.controller.admin;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.enums.UserTypeEnum;
import com.roncoo.education.util.tools.Constants;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.web.boss.common.bean.qo.SysUserQO;
import com.roncoo.education.web.boss.common.bean.vo.SysUserVO;
import com.roncoo.education.web.boss.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @autohor:wangkun
 * @date 2019-05-07-19:39
 * @email:wk20041208@126.com
 */
@Controller
@RequestMapping(value = {"/admin/sysUser"})
public class SysUserController extends BaseController {
    private final static String TARGETID = "admin-sysUser";

    @Autowired
    private SysUserService sysUserService;


    /**
     * 重新登录功能
     */
    @RequestMapping(value= "/login")
    public String login(Long id, ModelMap modelMap){
        SysUserVO sysUser = sysUserService.getById(id);
        if(sysUser == null){
            modelMap.put("msg","用户不存在");
            return "error";
        }
        if(sysUser.getStatusId().equals(StatusIdEnum.NO.getCode())){
            modelMap.put("msg","用户状态已禁用");
            return "error";
        }
        SecurityUtils.getSubject().getSession().setAttribute(Constants.Session.USER_NO, sysUser.getUserNo());
        SecurityUtils.getSubject().getSession().setAttribute(Constants.Session.USER_VO, JSONUtil.toJSONString(sysUser));
        return redirect("/index");
    }

    /**
     * 设置分成
     */
    @RequestMapping(value = "/proportion")
    public void proportion(@RequestParam(value = "id") Long id, ModelMap modelMap) {
        modelMap.put("bean", sysUserService.getById(id));
    }

    @RequestMapping(value = "/orgSysUserList")
    public void orgSysUserList(@ModelAttribute SysUserQO qo, ModelMap modelMap) {
        modelMap.put("page", sysUserService.listForPage(qo));
        modelMap.put("bean", qo);
    }

    @RequestMapping(value = "/list")
    public void list(@ModelAttribute SysUserQO qo, ModelMap modelMap) {
        modelMap.put("page", sysUserService.listForPage(qo));
        modelMap.put("bean", qo);
        modelMap.put("userTypeEnums", UserTypeEnum.values());
    }

    @RequestMapping(value = {"/add"})
    public void add(ModelMap modelMap){
        // 获取机构信息
        modelMap.put("userTypeEnums",UserTypeEnum.values());
    }

    @RequestMapping(value = {"/save"})
    @ResponseBody
    public String save(@ModelAttribute SysUserQO qo){
        if(sysUserService.save(qo) > 0){
            return success(TARGETID);
        }
        return error("添加失败");
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        if (sysUserService.deleteById(id) > 0) {
            return delete(TARGETID);
        }
        return error("删除失败");
    }

    @RequestMapping(value = {"/edit"})
    public void edit(@RequestParam(value="id") Long id, ModelMap modelMap){
        modelMap.put("bean",sysUserService.getById(id));
        modelMap.put("userTypeEnums",UserTypeEnum.values());

    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(@ModelAttribute SysUserQO qo) {
        if (sysUserService.updateById(qo) > 0) {
            return success(TARGETID);
        }
        return error("修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/updateBypassword")
    public String updateBypassword(@ModelAttribute SysUserQO qo) {
        if (sysUserService.updateBypassword(qo) > 0) {
            return success(TARGETID);
        }
        return error("更新失败");
    }

    @RequestMapping(value = "/password")
    public void password(@RequestParam(value = "userNo") Long userNo, ModelMap modelMap) {
        modelMap.put("userNo", userNo);
    }

    @RequestMapping(value = "/view")
    public void view(@RequestParam(value = "userNo") Long userNo,
                     @RequestParam(value = "orgNo") String orgNo,
                     ModelMap modelMap) {
        modelMap.put("bean", sysUserService.getByUserNoAndOrgNo(userNo, orgNo));
    }



}
