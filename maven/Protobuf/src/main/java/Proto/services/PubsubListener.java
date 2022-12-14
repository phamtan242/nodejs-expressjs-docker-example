package Proto.services;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;

@Lazy
@Component
public class PubsubListener {
    @Lazy
    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    @Qualifier("topicName")
    @Nonnull
    private String topicName;

    public PubsubListener() { }

    public void consume() {
        ITopic<String> topic = hazelcastInstance.getTopic(topicName);
        topic.addMessageListener((Message<String> message) -> {
            System.out.println("-------------------Start consumer message from topic -------------------");
            String messageValue = message.getMessageObject();
            System.out.println(String.format("-------------------End consumer message from topic: %s", messageValue));
        });
        System.out.println("Start listen topic " + topicName);
    }

    public void consume(@Nonnull String pTopicName) {
        ITopic<String> topic = hazelcastInstance.getTopic(pTopicName);
        topic.addMessageListener((Message<String> message) -> {
            System.out.println("-------------------Start consumer message from topic -------------------");
            String messageValue = message.getMessageObject();
            System.out.println(String.format("-------------------End consumer message from topic: %s", messageValue));
        });
        System.out.println("Start listen topic " + topicName);
    }

    // public class TestPubSubListenerImpl implements MessageListener<String> {
    //     @Override
    //     public void onMessage(Message<String> message) {
    //         System.out.println("-------------------Start consumer message from topic -------------------");
    //         String messageValue = message.getMessageObject();
    //         System.out.println(String.format("-------------------End consumer message from topic: %s", messageValue));
    //     }
    // }
}