package com.xde.kernel;

import com.xde.kernel.wsendpoint.WsServerEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Ken
 */
@SpringBootApplication
@EnableDiscoveryClient
public class XdeKernelApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(XdeKernelApplication.class, args);
		//解决WebSocket不能注入的问题 webSocket没有被spring注入
		WsServerEndpoint.setApplicationContext(configurableApplicationContext);

	}

}
