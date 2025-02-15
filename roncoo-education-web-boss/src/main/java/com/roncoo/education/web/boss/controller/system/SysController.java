package com.roncoo.education.web.boss.controller.system;

import com.roncoo.education.system.common.bean.qo.SysQO;
import com.roncoo.education.util.annotation.AdminLog;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.system.SysBiz;
import com.roncoo.education.web.boss.common.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @autohor:wangkun
 * @date 2019-05-18-15:52
 * @email:wk20041208@126.com
 */

/**
 * 系统配置表
 */
@Controller
@RequestMapping(value = "/system/sys")
public class SysController extends BaseController {
    private final static String TARGETID = "system-sys";
    @Autowired
    private SysBiz biz;

    @RequestMapping(value = "/add")
    public void add() {

    }
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(@ModelAttribute SysQO qo) {
        if (biz.save(qo) > 0) {
            return success(TARGETID);
        }
        return error("添加失败");
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") Long id) {
        if (biz.deleteById(id) > 0) {
            return delete(TARGETID);
        }
        return error("删除失败");
    }

    @RequestMapping(value = "/edit")
    public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap) {
        modelMap.put("bean", biz.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(@ModelAttribute SysQO qo) {
        if (biz.updateById(qo) > 0) {
            return success(TARGETID);
        }
        return error("修改失败");
    }

    @RequestMapping(value = "/view")
    public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
        modelMap.put("bean", biz.getById(id));
    }

    /**
     * 系统配置页面展示
     *
     */
    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public void set(ModelMap modelMap) {
        // 加载站点信息
        System.out.println("加载站点信息" + biz);
        modelMap.put("bean", biz.getSys());
        // 获取用户信息
        modelMap.put("user", biz.getSysUser(SessionUtil.getUserNo()));
    }

    /**
     * 系统配置
     *
     */
    @ResponseBody
    @AdminLog(value = "系统配置更新")
    @RequestMapping(value = "/updateSys", method = RequestMethod.POST)
    public String updateSys(@ModelAttribute SysQO qo) {
        System.out.println("fffffff"+qo.getSignName());
        // 更新站点信息
        if (null != qo.getId()) {
            if (biz.updateSys(qo) > 0) {
                return success(TARGETID);
            }
            // 保存站点信息
        } else {
            if (biz.save(qo) > 0) {
                return success(TARGETID);
            }
        }
        return error("修改失败");
    }
}
