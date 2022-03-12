package com.example.shirodemo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 系统版本升级表
 * @Author: jeecg-boot
 * @Date: 2021-10-13
 * @Version: V1.0
 */
@Data
@TableName("enterprise_version")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "enterprise_version对象", description = "系统版本升级表")
public class EnterpriseVersion {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**
	 * 身份码
	 */
	@ApiModelProperty(value = "身份码")
	private Object idCode;
	/**
	 * 随机码
	 */

	@ApiModelProperty(value = "身份码")
	private Integer randomCode;
	/**
	 * 授权码
	 */

	@ApiModelProperty(value = "授权码")
	private Object license;
	/**
	 * 云端主机IP

	@ApiModelProperty(value = "云端主机IP")
	private String maintenanceIp;
	/**
	 * 云端主机端口
	 */

	@ApiModelProperty(value = "云端主机端口")
	private Integer maintenancePort;
	/**
	 * 当前系统版本号
	 */

	@ApiModelProperty(value = "当前系统版本号")
	private String enterpriseVersion;
	/**
	 * 当前平台版本号
	 */

	@ApiModelProperty(value = "当前平台版本号")
	private String platformVersion;
	/**
	 * 升级时间
	 */

	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
	@DateTimeFormat(pattern = "HH:mm:ss")
	@ApiModelProperty(value = "升级时间")
	private Date upgradeTime;
	/**
	 * 操作时间
	 */

	@JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
	@DateTimeFormat(pattern = "HH:mm:ss")
	@ApiModelProperty(value = "升级时间")
	private Date createTime;

	/**
	 * 更新时间
	 */



	private Date updateTime;

	/**
	 * 操作人
	 */

	@ApiModelProperty(value = "操作人")
	private String userId;
	/**
	 * 企业信息表ID
	 */

	@ApiModelProperty(value = "企业信息表ID")
	private String enterpriceId;
}
