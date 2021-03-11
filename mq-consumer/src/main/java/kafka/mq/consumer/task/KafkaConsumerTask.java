package kafka.mq.consumer.task;

import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class KafkaConsumerTask implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    System.out.println("====>start consume kafka=====");
    consume();
  }

  @Value("${kafka.topic.user}")
  private String topicUser;

  @Value("${kafka.group.id}")
  private String groupId;

  @Value("${kafka.bootstrap.servers}")
  private String kafkaSevers;

  public void consume() {
    Properties props = new Properties();
    //设置必要的属性
    props.put("bootstrap.servers", kafkaSevers);
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("group.id", groupId);

    //消费方式配置
    /**
     * earliest： 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
     * latest： 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
     * none： topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
     */
    props.put("auto.offset.reset", "earliest ");//earliest：当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费

    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Collections.singletonList(topicUser));

    GsonBuilder builder = new GsonBuilder();

    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
      records.forEach(record -> {
        User user = builder.create().fromJson(record.value(),User.class);

        System.out.printf("成功消费消息：topic = %s ,partition = %d,offset = %d, key = %s, value = %s%n===> username:: %s",
          record.topic(), record.partition(), record.offset(), record.key(), record.value(),user.getName());
        }
      );

    }

  }

}
