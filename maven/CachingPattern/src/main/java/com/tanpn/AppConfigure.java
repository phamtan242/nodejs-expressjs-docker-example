package com.tanpn;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfigure {

    @Bean("simpleCaching")
    @Scope("singleton")
    public LoadingCache getSimpleCachingInstance() {
        LoadingCache<String, String> simpleCaching = CacheBuilder.newBuilder()
        .build(new CacheLoader<String, String>() {
            @Override
            public String load(final String pKey) throws Exception {
                
                // return slowMethod(s);
                return "";
            }
        });
        return simpleCaching;
    }
}