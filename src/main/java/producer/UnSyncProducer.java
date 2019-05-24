package producer;

import org.apache.kafka.clients.producer.*;
import util.KafkaUtil;

/**
 * 异步发送消息
 */
public class UnSyncProducer implements ZhxProducer{
    public void produce() {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("zhxTopic2", "ni hao.", "ni ye hao.");
        Producer producer = new KafkaProducer<String, String>(KafkaUtil.getKafkaProducerProp());
        try {
            /*假设消息在应用程序和kafka集群之间一个来回需要10ms，如果同步发送每次都需要等待回应，那么发送100条消息就需要1秒，但如果只发送而不等待响应，那么发送
            100条消息速度会快的多，大多数时候，对于生产者来说我们并不需要等待响应，尽管kafka会把目标主体、分区信息和消息偏移量发送回来，不过消息发送失败的时候，
            我们需要捕获到异常并把它记录下来。*/
            producer.send(record, new UnSyncProducerCallBack());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    class UnSyncProducerCallBack implements Callback{

        /*当消息发送成功的时候，该方法会被调用，exception为null，当发送失败的时候，exception会展示具体当错误信息，我们可以把错误信息记录下来，以后再做处理。*/
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            System.out.println("回调---------------" + metadata);
            System.out.println("回调---------------" + exception);
        }
    }

    public static void main(String[] args) {
        ZhxProducer zhxProducer = new UnSyncProducer();
        zhxProducer.produce();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
