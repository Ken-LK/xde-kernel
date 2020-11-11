package com.xde.auth.component;

import com.xde.auth.domain.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT 增强
 *
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/11/10 3:35 下午
 **/
@Component
public class JwtTokenEnhancer implements TokenEnhancer {


    /**
     * Provides an opportunity for customization of an access token (e.g. through its additional information map) during
     * the process of creating a new token for use by a client.
     *
     * @param accessToken    the current access token with its expiration and refresh token
     * @param authentication the current authentication including client and user details
     * @return a new token enhanced with additional information
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> info = new HashMap<>();
        if (authentication.getPrincipal() instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

            //把用户ID设置到JWT中
            info.put("id", securityUser.getId());
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;


    }
}
