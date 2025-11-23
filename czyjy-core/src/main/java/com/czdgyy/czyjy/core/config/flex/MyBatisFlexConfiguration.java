package com.czdgyy.czyjy.core.config.flex;

import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.mybatisflex.core.logicdelete.LogicDeleteProcessor;
import com.mybatisflex.core.logicdelete.impl.DefaultLogicDeleteProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Configuration
public class MyBatisFlexConfiguration {

	public MyBatisFlexConfiguration() {
		//配置插入监听
		DefaultInsertListener insertListener = new DefaultInsertListener();
		FlexGlobalConfig.getDefaultConfig().registerInsertListener(insertListener);
		//配置更新监听
		DefaultUpdateListener updateListener = new DefaultUpdateListener();
		FlexGlobalConfig.getDefaultConfig().registerUpdateListener(updateListener);
	}

    @Bean
    public LogicDeleteProcessor logicDeleteProcessor() {
        return new DefaultLogicDeleteProcessor();
    }
}
