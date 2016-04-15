package com.ckgj;

import com.ckgj.config.WeChatConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//http://kielczewski.eu/2014/12/spring-boot-security-application/
// http://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
@SpringBootApplication
@EnableConfigurationProperties(WeChatConfig.class)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
