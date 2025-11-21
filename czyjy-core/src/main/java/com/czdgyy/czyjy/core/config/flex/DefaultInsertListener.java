package com.czdgyy.czyjy.core.config.flex;

import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.InsertListener;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
public class DefaultInsertListener implements InsertListener {
	@Override
	public void onInsert(Object o) {
		BaseEntity baseEntity = (BaseEntity) o;
		//设置创建人ID
		baseEntity.setCreatorId(1L);
		//设置创建人名称
		baseEntity.setCreatorName("");
	}
}
