package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import util.KafkaUtil;

/**
 * 最简单的生产者：只发送消息，不关心是否发送成功
 */
public class SimpleProducer implements ZhxProducer {

    public void produce() {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("zhxTopic3", "bbb!", "nice to see you.");
        Producer producer = new KafkaProducer<String, String>(KafkaUtil.getKafkaProducerProp());
        try {
            producer.send(record);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void batchProduce(){
        Producer producer = new KafkaProducer<String, String>(KafkaUtil.getKafkaProducerProp());
        for(int i = 0; i < 1000; i++){
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("zhxTopic3", "ni hao" + i, "nice to see you." + i);
            try {
                producer.send(record);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ZhxProducer zhxProducer = new SimpleProducer();
        zhxProducer.produce();
    }
}
