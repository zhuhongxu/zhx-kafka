package util;

import enums.ConsumerConfigEnum;
import enums.ProducerConfigEnum;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

public class KafkaUtil {
    /**
     * 获取kafka属性的Properties对象
     * @return
     */
    public static Properties getBasicKafkaProperties(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", ProducerConfigEnum.BOOTSTRAP_SERVERS.getValue());
        return properties;
    }

    /**
     * 获取生产者属性
     * @return
     */
    public static Properties getKafkaProducerProp(){
        Properties properties = getBasicKafkaProperties();
        properties.put("key.serializer", ProducerConfigEnum.KEY_SERIALIZER.getValue());
        properties.put("value.serializer", ProducerConfigEnum.VALUE_SERIALIZER.getValue());
        return properties;
    }

    /**
     * 获取消费者属性
     * @return
     */
    public static Properties getKafkaComsumerProp(){
        Properties properties = getBasicKafkaProperties();
        properties.put("key.deserializer", ConsumerConfigEnum.KEY_DESERIALIZER.getValue());
        properties.put("value.deserializer", ConsumerConfigEnum.VALUE_DESERIALIZER.getValue());
        return properties;
    }

    /**
     * 新建一个kafka消费者
     * @param groupId
     * @return
     */
    public static KafkaConsumer<String, String> getNewConsumer(String groupId){
        KafkaConsumer<String, String> consumer = null;
        if (null != groupId && (!"".equals(groupId))){
            Properties properties = getKafkaComsumerProp();
            properties.put("group.id", groupId);
            consumer = new KafkaConsumer<String, String>(properties);
        }
        return consumer;
    }
}
