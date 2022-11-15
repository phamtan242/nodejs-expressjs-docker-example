package demo.services;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

@RestController
public class MessageController {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    @Qualifier("topicName")
    @Nonnull
    private String topicName;

    @GetMapping(value = "/send-message")
    public String sendMessage(@RequestParam(value = "msg") @Nonnull String msg) {
        // String topicName = "cafeincodeTopic";
        // String message = "Sending message to Hazelcast topic by hungtv:" + new Random().nextInt(100);
        System.out.println("----------------PUBLIC MESSAGE TO TOPIC:" + msg);
        ITopic<String> topic = hazelcastInstance.getTopic(topicName);
        topic.publish(msg);
        System.out.println("----------------END PUBLIC-------------");
        return "OK";
    }
}