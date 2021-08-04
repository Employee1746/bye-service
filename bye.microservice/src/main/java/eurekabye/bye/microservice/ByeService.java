package eurekabye.bye.microservice;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service
public class ByeService {

    public Long countGreetings() {
        Long totalCount;
        Properties props = new Properties();
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("hello"));
            Set<TopicPartition> assignment;
            while ((assignment = consumer.assignment()).isEmpty()) {
                consumer.poll(Duration.ofMillis(100));
            }
            final Map<TopicPartition, Long> endOffsets = consumer.endOffsets(assignment);
            final Map<TopicPartition, Long> beginningOffsets = consumer.beginningOffsets(assignment);
            assert (endOffsets.size() == beginningOffsets.size());
            assert (endOffsets.keySet().equals(beginningOffsets.keySet()));

            totalCount = beginningOffsets.entrySet().stream().mapToLong(entry -> {
                TopicPartition tp = entry.getKey();
                Long beginningOffset = entry.getValue();
                Long endOffset = endOffsets.get(tp);
                return endOffset - beginningOffset;
            }).sum();
        }
        return totalCount;
    }
}
