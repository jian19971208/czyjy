package com.czdgyy.czyjy.core.config.flex;

import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.UpdateListener;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
public class DefaultUpdateListener implements UpdateListener {
	@Override
	public void onUpdate(Object o) {
		BaseEntity baseEntity = (BaseEntity) o;

	}
}
