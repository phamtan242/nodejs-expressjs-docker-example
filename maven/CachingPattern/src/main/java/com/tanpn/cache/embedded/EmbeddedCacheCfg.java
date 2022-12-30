package com.tanpn.cache.embedded;

import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.tanpn.interfaces.ICache;

@Configuration
public class EmbeddedCacheCfg {

    @Bean
    public ICache<String, String> embeddedCacheTemplate() {
        return new ICache<String, String>() {
            LoadingCache<String, String> simpleCaching = CacheBuilder.newBuilder()
                    .build(new CacheLoader<String, String>() {
                        @Override
                        public String load(final String pKey) throws Exception {
                            // do something, but in this case we return null
                            return null;
                        }
                    });

            @Override
            public String get(String pKey) {
                try {
                    return simpleCaching.get(pKey);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void set(String pKey, String pValue) {
                simpleCaching.put(pKey, pValue);
            }

            @Override
            public String getIfPresent(String key) {
                return simpleCaching.getIfPresent(key);
            }

        };
    }
}
