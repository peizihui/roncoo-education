/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色用户关联表
 * </p>
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Data
@Accessors(chain = true)
public class SysRoleUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 状态(0:无效,1:有效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 用户ID
     */
    private Long userId;

}
