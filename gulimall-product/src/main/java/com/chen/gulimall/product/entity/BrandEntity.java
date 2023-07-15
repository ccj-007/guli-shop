package com.chen.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.chen.common.validator.group.AddGroup;
import com.chen.common.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author ccj
 * @email sunlightcs@gmail.com
 * @date 2023-06-04 13:12:35
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@Null(message = "添加时品牌id必须为空",groups ={AddGroup.class} )
	@NotNull(message = "修改时品牌id不能为空",groups = {UpdateGroup.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotEmpty(message = "品牌名必须要提交", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, UpdateGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(message = "显示状态不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(message = "检索首字母不能为空",groups = {AddGroup.class,UpdateGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索字符必须是一个字母",groups = {AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
