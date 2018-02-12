package com.leonov.springboot.jwt.integration.config;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class FcmConfig extends ResourceServerConfigurerAdapter {
    @Value("${fcm.api-key}")
    private String apiKey;

    @Value("${fcm.url}")
    private String url;

    @Bean
    public IFcmClient fcmClient() {
        return new FcmClient(new FcmSettings());
    }

    public class FcmSettings implements IFcmClientSettings {
        @Override
        public String getApiKey() {
            return apiKey;
        }

        @Override
        public String getFcmUrl() {
            return url;
        }
    }

}
