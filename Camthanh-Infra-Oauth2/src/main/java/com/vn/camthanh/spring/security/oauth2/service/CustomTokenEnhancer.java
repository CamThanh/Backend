package com.vn.camthanh.spring.security.oauth2.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vn.camthanh.CamthanhAccount.User;

@Service
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode node = null;
    	String userStr = null;
        User user = (User) authentication.getPrincipal();
        try {
			userStr = mapper.writeValueAsString(user);
			node = mapper.readTree(userStr);
		} catch (JsonProcessingException e) {
			userStr = "";
		} catch (IOException e) {
			node = null;
		}
        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("username", user.getUsername());
        additionalInfo.put("avatarUri", user.getUserDetail().getAvatarUri());
        additionalInfo.put("email", "");
//        additionalInfo.put("username", user.getUsername());
//        additionalInfo.put("authorities", user.getAuthorities());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}