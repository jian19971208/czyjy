package com.czdgyy.czyjy.core.config.flex;

import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.KeyGenerators;
import org.springframework.context.annotation.Configuration;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Configuration
public class MyBatisFlexConfiguration {

	public MyBatisFlexConfiguration() {
		//配置主键策略
		FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
		keyConfig.setKeyType(KeyType.Generator);
		keyConfig.setValue(KeyGenerators.flexId);
		FlexGlobalConfig.getDefaultConfig().setKeyConfig(keyConfig);
		//配置逻辑删除
		FlexGlobalConfig.getDefaultConfig().setLogicDeleteColumn("is_deleted");
		//配置乐观锁
		FlexGlobalConfig.getDefaultConfig().setVersionColumn("version");
		//配置插入监听
		DefaultInsertListener insertListener = new DefaultInsertListener();
		FlexGlobalConfig.getDefaultConfig().registerInsertListener(insertListener);
		//配置更新监听
		DefaultUpdateListener updateListener = new DefaultUpdateListener();
		FlexGlobalConfig.getDefaultConfig().registerUpdateListener(updateListener);
	}
}
