package com.xde.kernel.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 天翼云配置文件获取
 *
 * @author ken
 * @mailto <a href="liukailk.ken@gmail.com"/>
 * @date 2020/8/28 2:38 下午
 **/
@Component
@ConfigurationProperties(prefix = "tianyiyun")
@Data
public class TianYiYunProperties {

    private String accessId;

    private String secret;

    private String entpoints;

    private String bucket;
}
