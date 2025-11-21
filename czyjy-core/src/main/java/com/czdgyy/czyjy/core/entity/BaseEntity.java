package com.czdgyy.czyjy.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

	private Long id;

	private LocalDateTime createTime;

	private Long creatorId;

	private String creatorName;

	private LocalDateTime updateTime;

	private Long updaterId;

	private String updaterName;

	private String updateRemark;

	private Long version;

	private Long deleteTime;

	private Integer isDeleted;
}
