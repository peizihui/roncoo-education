package com.roncoo.education.web.boss.common;

import com.roncoo.education.util.tools.Constants;
import org.apache.shiro.SecurityUtils;

/**
 * @autohor:wangkun
 * @date 2019-05-18-16:47
 * @email:wk20041208@126.com
 */
public class SessionUtil {
    private SessionUtil() {
    }

    public static Long getUserNo() {
        return Long.valueOf(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.USER_NO).toString());
    }

    public static String getRealName() {
        return SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.REAL_NAME).toString();
    }
}
