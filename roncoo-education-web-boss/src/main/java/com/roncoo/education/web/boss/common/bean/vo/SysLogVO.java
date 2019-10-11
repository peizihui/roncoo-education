
package com.roncoo.education.web.boss.common.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台操作日志表
 * </p>
 *
 */
@Data
@Accessors(chain = true)
public class SysLogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date gmtCreate;

	private Long userNo;

	private String realName;

	private String ip;

	private String operation;

	private String method;

	private String path;

	private String content;

}
