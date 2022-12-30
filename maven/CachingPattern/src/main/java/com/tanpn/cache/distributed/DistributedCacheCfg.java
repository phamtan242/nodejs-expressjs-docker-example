package com.tanpn.cache.distributed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.tanpn.interfaces.ICache;

@Configuration
@Lazy
public class DistributedCacheCfg {

    @Bean
    public ICache<String, String> distributedCacheTemplate() {
        return new ICache<String, String>() {
            // ClientConfig lvClientConfig = new ClientConfig();
            // {
            //     // Cluster name must be same as on config at src/main/resources/hazelcast.xml
            //     final String lvClusterName = "hazelcast";
            //     lvClientConfig.setClusterName(lvClusterName);
            //     lvClientConfig.setInstanceName("dev-instance");
            // }
            // HazelcastInstance lvHzClient = HazelcastClient.newHazelcastClient(lvClientConfig);
            // {
            //     System.out.println("Hazelcast connected to cluster name " + lvHzClient.getConfig().getClusterName());
            // }

            Config lvHzConfig = new Config();
            {
                lvHzConfig.setClusterName("hazelcast");
            }
            HazelcastInstance lvHzInstance = Hazelcast.newHazelcastInstance(lvHzConfig);
            IMap<String, String> simpleCaching = lvHzInstance.getMap("distributed-map");

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
