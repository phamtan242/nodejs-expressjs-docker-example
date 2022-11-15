package demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentMap;

@RestController
public class HazelcastController {
    @Autowired
    private HazelcastInstance hazelcastInstance;

    private ConcurrentMap<String,String> retrieveMap() {
        return hazelcastInstance.getMap("map");
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
}
