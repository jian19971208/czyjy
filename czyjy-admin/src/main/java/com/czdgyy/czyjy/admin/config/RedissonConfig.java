package com.czdgyy.czyjy.admin.config;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lcj
 * @since 2025/11/21/周五
 */
@Configuration
public class RedissonConfig {

	@Value("${spring.data.redis.host}")
	private String host;

	@Value("${spring.data.redis.port}")
	private Integer port;

	@Value("${spring.data.redis.password:}")
	private String password;

	@Bean
	public RedissonClient redissonClient() {
		// Redisson 全局配置
		Config config = new Config();
		String address = "redis://" + host + ":" + port;
		SingleServerConfig serverConfig = config.useSingleServer()
				.setAddress(address)
				.setDatabase(0);

		if (StrUtil.isNotBlank(password)) {
			serverConfig.setPassword(password);
		}

		return Redisson.create(config);
	}
}
