package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import producer.SimpleProducer;
import producer.ZhxProducer;
import util.KafkaUtil;

import java.time.Duration;
import java.util.Collections;

/**
 * 一个简单的消费者
 */
public class SimpleConsumer implements ZhxConsumer {
    Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);

    public void consume() {
        KafkaConsumer<String, String> consumer = KafkaUtil.getNewConsumer("zhxGroupId1");
        consumer.subscribe(Collections.singletonList("zhxTopic3"));
        logger.info("等待消费。。。。。。");
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(150));
            for (ConsumerRecord<String, String> record : records){
                logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                logger.info("key: {}", record.key());
                logger.info("value: {}", record.value());
                logger.info("topic: {}", record.topic());
                logger.info("offset: {}", record.offset());
                logger.info("partition: {}", record.partition());
                logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            }

        }
    }

    public static void main(String[] args) {
        //先生产1000条消息
//        ZhxProducer producer = new SimpleProducer();
//        ((SimpleProducer) producer).batchProduce();
        //再消费
        ZhxConsumer consumer = new SimpleConsumer();
        consumer.consume();
    }
}
