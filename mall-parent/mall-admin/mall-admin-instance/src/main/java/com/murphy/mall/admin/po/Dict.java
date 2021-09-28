package com.murphy.mall.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;

/**
 * @file Dict.java - 字典
 * @author murphy
 */
@Data
@TableName("dict_")
public class Dict extends BaseEntity {

	// 数据值
	@TableField("value_")
	private String value;
	// 标签名
	@TableField("label_")
	private String label;
	// 类型
	@TableField("type_")
	private String type;
	// 描述
	@TableField("description_")
	private String description;

}
