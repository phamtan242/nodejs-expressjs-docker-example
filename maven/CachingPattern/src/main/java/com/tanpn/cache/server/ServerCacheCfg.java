package com.tanpn.cache.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;
import com.tanpn.interfaces.ICache;

@Configuration
@Lazy
public class ServerCacheCfg {
    @Value("${redis.uri}")
	String uri;

    @Bean
    public ICache<String, String> serverCacheTemplate() {
        return new ICache<String, String>() {
            RedisClient redisClient = new RedisClient(RedisURI.create(uri));
            RedisConnection<String, String> connection = redisClient.connect();

            @Override
            public String get(String pKey) {
                return connection.get(pKey);
            }

            @Override
            public void set(String pKey, String pValue) {
                connection.set(pKey, pValue);                
            }
        };
    }
}
