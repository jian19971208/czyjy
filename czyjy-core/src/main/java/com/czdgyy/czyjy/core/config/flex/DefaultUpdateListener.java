package com.czdgyy.czyjy.core.config.flex;

import cn.dev33.satoken.stp.StpUtil;
import com.czdgyy.czyjy.core.entity.BaseEntity;
import com.mybatisflex.annotation.UpdateListener;

import java.time.LocalDateTime;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
public class DefaultUpdateListener implements UpdateListener {
	@Override
	public void onUpdate(Object o) {
		BaseEntity entity = (BaseEntity) o;
        LocalDateTime now = LocalDateTime.now();
        entity.setUpdateTime(now);
        boolean isLogin = StpUtil.isLogin();
        if (isLogin) {
            long sysUserId = StpUtil.getLoginIdAsLong();
            String sysUserName = StpUtil.getExtra("sysUserName").toString();
            entity.setUpdaterId(sysUserId);
            entity.setUpdaterName(sysUserName);
        }
    }
}
