package com.czdgyy.czyjy.core.config.flex;

import cn.dev33.satoken.stp.StpUtil;
import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.InsertListener;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
public class DefaultInsertListener implements InsertListener {

	@Override
	public void onInsert(Object o) {
		BaseEntity entity = (BaseEntity) o;
        LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setDeleteTime(0L);
        entity.setVersion(0L);
        entity.setIsDeleted(0);
        //获取当前用户上下文
        boolean isLogin = StpUtil.isLogin();
        if (isLogin) {
            long sysUserId = StpUtil.getLoginIdAsLong();
            String sysUserName = StpUtil.getExtra("sysUserName").toString();
            entity.setCreatorId(sysUserId);
            entity.setCreatorName(sysUserName);
            entity.setUpdaterId(sysUserId);
            entity.setUpdaterName(sysUserName);
        }
	}
}
