package com.murphy.mall.security.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.murphy.mall.core.po.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("user_")
public class User extends BaseEntity {

	// 登录名
	@TableField("user_name_")
	private String userName;
	// 真实姓名
	@TableField("real_name_")
	private String realName;
	@TableField("password_")
	private String password;
	// 加密密码的盐
	@TableField("salt")
    private String salt;
	@TableField("sex_")
	private String sex;
	@TableField("tel_")
	private String tel;
	@TableField("email_")
	private String email;
	@TableField("desc_")
	private String desc;
	// 是否锁定
	@TableField("lock_")
	private Boolean lock;
	@TableField("birthday_")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	// 是否为部门负责人，用于"常用语:直接上级"
	@TableField("principal_")
	private Boolean principal;
	// 部门
	@TableField("dept_id_")
	private Long deptId;
	// 岗位
	@TableField("post_id_")
	private Long postId;
	// 部门名称，用于列表显示
	@TableField(exist = false)
	private String deptName;
	// 瞬时属性，用户的角色列表，如：[1,3,4,5]
	@TableField(exist = false)
	private Long[] roleIds;
	// 部门名称，用于列表显示
	@TableField(exist = false)
	private String postName;

	public String credentialsSalt() {
		return userName + salt;
	}

}
