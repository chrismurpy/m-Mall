package com.murphy.mall.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * 职位
 * @author murphy
 */
@Data
@TableName("post_")
public class Post extends BaseEntity {

	@TableField("name_")
	private String name;
	@TableField("title_")
	private String title;
	@TableField("desc_")
	private String desc;

}
