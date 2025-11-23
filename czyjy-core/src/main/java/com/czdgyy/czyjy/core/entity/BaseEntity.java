package com.czdgyy.czyjy.core.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.keygen.KeyGenerators;
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

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
	private Long id;

	private LocalDateTime createTime;

	private Long creatorId;

	private String creatorName;

	private LocalDateTime updateTime;

	private Long updaterId;

	private String updaterName;

	private String updateRemark;

    @Column(version = true)
	private Long version;

	private Long deleteTime;

    @Column(isLogicDelete = true)
	private Integer isDeleted;
}
