package com.ckgj;

import com.ckgj.config.WeChatConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//http://kielczewski.eu/2014/12/spring-boot-security-application/
//AppID(应用ID)wx70c01952742520c8
//AppSecret(应用密钥)de544884eddf11c62af7d2bf0367d40e 隐藏 重置
@SpringBootApplication
@EnableConfigurationProperties(WeChatConfig.class)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
