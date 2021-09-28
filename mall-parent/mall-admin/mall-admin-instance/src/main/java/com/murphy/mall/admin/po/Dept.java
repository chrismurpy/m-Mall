package com.murphy.mall.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.murphy.mall.core.po.BaseTreeEntity;
import lombok.Data;

/**
 * @Title: 部门
 * @Description:
 */
@Data
@TableName("dept_")
public class Dept extends BaseTreeEntity {

	@TableField("address_")
	private String address;
	@TableField("tel_")
	private String tel;
	@TableField("desc_")
	private String desc;

	public String getLabel() { //treeselect需要的属性
		return this.getTitle();
	}

}
