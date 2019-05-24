package enums;

/**
 * 生产者枚举类
 * 官方参考文档：http://kafka.apache.org/documentation/#producerconfigs
 */
public enum ProducerConfigEnum {
    BOOTSTRAP_SERVERS("192.168.1.236:9092,192.168.1.237:9092,192.168.1.240:9092", "kafka集群地址"),
    KEY_SERIALIZER("org.apache.kafka.common.serialization.StringSerializer", "键的序列化方式"),
    VALUE_SERIALIZER("org.apache.kafka.common.serialization.StringSerializer", "值的序列化方式");

    private String value;
    private String desc;

    ProducerConfigEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
