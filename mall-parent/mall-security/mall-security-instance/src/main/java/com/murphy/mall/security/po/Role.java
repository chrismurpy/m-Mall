package com.murphy.mall.security.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * @Title:
 * @Description:
 *
 */
@Data
@TableName("role_")
public class Role extends BaseEntity {

	@TableField("name_")
	private String name;
	@TableField("title_")
	private String title;
	@TableField("desc_")
	private String desc;

	// 瞬时属性，角色的用户列表，如：[1,3,4,5]
	@TableField(exist = false)
	private Long[] userIds;

}
