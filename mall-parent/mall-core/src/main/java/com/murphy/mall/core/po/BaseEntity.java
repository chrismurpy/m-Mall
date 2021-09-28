package com.murphy.mall.core.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @file BaseEntity.java
 * @author murphy
 * JsonIgnoreProperties(value = {"handler"})：避免懒加载产生的handler代理属性，在进行json序列化时的异常
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
public abstract class BaseEntity implements Serializable {

	/**
	 * 实体编号（唯一标识）
	 */
	@TableId(value = "id_", type = IdType.AUTO)
	protected Long id;

}
