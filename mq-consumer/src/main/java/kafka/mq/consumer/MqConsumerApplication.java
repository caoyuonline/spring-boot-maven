package kafka.mq.consumer;

import kafka.mq.consumer.task.KafkaConsumerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MqConsumerApplication.class, args);
  }

}
