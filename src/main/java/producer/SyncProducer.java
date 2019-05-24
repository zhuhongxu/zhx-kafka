package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import util.KafkaUtil;

/**
 * 同步发送
 */
public class SyncProducer implements ZhxProducer {
    public void produce() {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("zhxTopic3", "ni hao.", "ni ye hao.");
        Producer producer = new KafkaProducer<String, String>(KafkaUtil.getKafkaProducerProp());
        try {
            //在这里，producer.send()方法返回一个Future对象，然后调用其get()方法等待kafka服务端的响应，如果服务器端返回错误，get()方法会抛出异常，
            // 如果没有发生错误，我们会得到一个RecordMetadata对象，可以用谈来获取消息的偏移量
            RecordMetadata recordMetadata = (RecordMetadata) producer.send(record).get();
            System.out.println(recordMetadata);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SyncProducer syncProducer = new SyncProducer();
        syncProducer.produce();
    }
}
