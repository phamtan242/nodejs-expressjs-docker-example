package demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.multimap.MultiMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

@RestController
public class HazelcastController {
    @Autowired
    private HazelcastInstance hazelcastInstance;

    private ConcurrentMap<String, String> retrieveMap() {
        return hazelcastInstance.getMap("map");
    }

    private MultiMap<String, String> retrieveMultiMap() {
        return hazelcastInstance.getMultiMap("multi-map");
    }

    @PostMapping("/put")
    public ResponseEntity<String> put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        System.out.println("key: " + key);
        System.out.println("value: " + value);
        retrieveMap().put(key, value);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/put/{key}")
    public ResponseEntity<String> putByKey(@PathVariable(value = "key") String key, @RequestBody String value) {
        System.out.println("key: " + key);
        System.out.println("value: " + value);
        retrieveMap().put(key, value);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<String> get(@RequestParam(value = "key") String key) {
        String value = retrieveMap().get(key);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @GetMapping("/multi-map")
    public ResponseEntity<Object[]> getMultiMap(@RequestParam( value = "key") String key) {
        Collection<String> values = retrieveMultiMap().get(key);
        return new ResponseEntity<>(values.toArray(), HttpStatus.OK);
    }

    @PostMapping("/multi-map")
    public ResponseEntity<String> setMultiMap(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        if (retrieveMultiMap().put(key, value)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("FAIL", HttpStatus.OK);
        }
    }
}
