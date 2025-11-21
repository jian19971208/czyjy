package com.czdgyy.czyjy.core.common.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Data
public class CommonResult {

	private Long id;

	private LocalDateTime createTime;

	private Long creatorId;

	private String creatorName;

	private LocalDateTime updateTime;

	private Long updaterId;

	private String updaterName;
}
