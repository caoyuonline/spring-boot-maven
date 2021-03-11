package kafka.mq.producer.kafka;

import com.google.gson.GsonBuilder;
import kafka.mq.producer.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KafkaProducer {

  @Resource
  private KafkaTemplate<String ,String> kafkaTemplate;

  @Value("${kafka.topic.user}")
  private String topicUser;

  public void sendUserMessage(User user){
    GsonBuilder builder = new GsonBuilder();
    builder.setPrettyPrinting();
    builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
    String message = builder.create().toJson(user);
    kafkaTemplate.send(topicUser,message);
    System.out.println("send message to kafka==>from console");
  }

}
