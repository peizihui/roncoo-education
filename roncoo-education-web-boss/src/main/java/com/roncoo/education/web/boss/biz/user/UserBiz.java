package com.roncoo.education.web.boss.biz.user;

import com.roncoo.education.user.common.bean.qo.UserQO;
import com.roncoo.education.user.common.bean.vo.UserVO;
import com.roncoo.education.user.feign.IBossUser;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @autohor:wangkun
 * @date 2019-05-07-18:55
 * @email:wk20041208@126.com
 */
@Component
public class UserBiz extends BaseBiz {
    @Autowired
    private IBossUser bossUser;


    public Page<UserVO> listForPage(UserQO qo){
        return bossUser.listForPage(qo);
    }
}
