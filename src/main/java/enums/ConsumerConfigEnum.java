package enums;

/**
 * 消费者配置信息枚举类
 */
public enum ConsumerConfigEnum {
    BOOTSTRAP_SERVERS("192.168.1.236:9092,192.168.1.237:9092,192.168.1.240:9092", "kafka集群地址"),
    KEY_DESERIALIZER("org.apache.kafka.common.serialization.StringDeserializer", "键的反序列化方式"),
    VALUE_DESERIALIZER("org.apache.kafka.common.serialization.StringDeserializer", "值的反序列化方式");

    private String value;
    private String desc;

    ConsumerConfigEnum(String value, String desc) {
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
