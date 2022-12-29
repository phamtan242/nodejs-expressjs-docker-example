package com.tanpn.cache.distributed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.tanpn.interfaces.ICache;

@Configuration
@Lazy
public class DistributedCacheCfg {

    @Bean
    public ICache<String, String> distributedCacheTemplate() {
        return new ICache<String, String>() {
            // Start the Hazelcast Client and connect to an already running Hazelcast
            // Cluster on 127.0.0.1
            ClientConfig lvClientConfig = new ClientConfig();
            {
                lvClientConfig.setClusterName("hazelcast");
                lvClientConfig.setInstanceName("dev-instance");
            }
            HazelcastInstance lvHazelcastInstance = HazelcastClient.newHazelcastClient(lvClientConfig);
            {
                System.out.println("Hazelcast connected to cluster name " + lvHazelcastInstance.getConfig().getClusterName());
            }
            IMap<String, String> simpleCaching = lvHazelcastInstance.getMap("my-distributed-map");

            @Override
            public String get(String pKey) {
                return simpleCaching.get(pKey);
            }

            @Override
            public void set(String pKey, String pValue) {
                simpleCaching.put(pKey, pValue);
            }

        };
    }
}
