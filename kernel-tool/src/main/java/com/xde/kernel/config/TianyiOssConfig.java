package com.xde.kernel.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.xde.kernel.config.properties.TianYiYunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 天翼云OSS配置类
 *
 * @author ken
 * @mailto <a href="liukailk.ken@gmail.com"/>
 * @date 2020/8/28 2:36 下午
 **/
@Configuration
@ConditionalOnClass(TianYiYunProperties.class)
public class TianyiOssConfig {

    @Autowired
    private TianYiYunProperties tianYiYunProperties;

    @Bean
    public AmazonS3 oosClient() {
        ClientConfiguration clientConfig = new ClientConfiguration();
        //设置连接的超时时间，单位毫秒
        clientConfig.setConnectionTimeout(30 * 1000);
        //设置 socket 超时时间，单位毫秒
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setProtocol(Protocol.HTTPS);
        clientConfig.setMaxConnections(999999);
        //设置 V4 签名算法中负载是否参与签名，关于签名部分请参看《OOS 开发者文档》
        S3ClientOptions options = new S3ClientOptions();
        options.setPayloadSigningEnabled(true);
        PropertiesCredentials propertiesCredentials = new PropertiesCredentials(tianYiYunProperties.getAccessId(), tianYiYunProperties.getSecret());
        AmazonS3 oosClient = new AmazonS3Client(propertiesCredentials, clientConfig);
        oosClient.setEndpoint(tianYiYunProperties.getEntpoints());
        oosClient.setS3ClientOptions(options);
        return oosClient;

    }


}
