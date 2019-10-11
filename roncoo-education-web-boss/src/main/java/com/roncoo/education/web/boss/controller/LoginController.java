package com.roncoo.education.web.boss.controller;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Bjui;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.util.tools.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @autohor:wangkun
 * @date 2019-04-26-14:47
 * @email:wk20041208@126.com
 */

@Controller
public class LoginController  extends BaseController {


    @RequestMapping(value = {"/"},method = RequestMethod.GET)
    public String index(){
        return redirect("/index");
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断是否是ajax请求
        if(WebUtil.isAjax(request)){
            resp(request,response);
            return null;
        }
        return "login";
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    public String loginPost(UsernamePasswordToken token, ModelMap modelMap){
        try{
            System.out.println("token:username------------>" + token.getUsername());
            System.out.println("token:password------------>" + token.getPassword());
            SecurityUtils.getSubject().login(token);

            return redirect("/index");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("登录异常",e.getMessage());
            modelMap.put("msg",e.getMessage());
            return "login";

        }
    }

    @RequestMapping(value = "/timeout", method = { RequestMethod.GET, RequestMethod.POST })
    public void timeout() {

    }


    public void resp(HttpServletRequest request,HttpServletResponse response) throws IOException {
        WebUtils.getAndClearSavedRequest(request);
        PrintWriter out = response.getWriter();
        Bjui bj = new Bjui();
        bj.setStatusCode(301);//登录超时
        bj.setMessage("timeout");
        out.println(JSONUtil.toJSONString(bj));
        out.flush();


    }

}
