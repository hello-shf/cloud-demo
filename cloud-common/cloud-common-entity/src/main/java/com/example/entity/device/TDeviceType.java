package com.example.entity.device;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述：设备类型表实体
 * @author: shf
 * @date: 2019-05-15 16:11:34
 * @version: V1.0
 */
@Entity
@Table(name = "T_DEVICE_TYPE")
@Data
public class TDeviceType {

	/**
	 * 主键
	 */
	@Id
	private Long id;
	/**
	 * 类型编码
	 */
	private String code;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 是否删除 0：未删除 1：删除
	 */
	private Integer isDelete;
	/**
	 * 设备类型
	 */
	private Integer type;
	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 类型描述
	 */
	private String description;
}
