package kafka.mq.producer.controller;

import kafka.mq.producer.kafka.KafkaProducer;
import kafka.mq.producer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private KafkaProducer kafkaProducer;

  @GetMapping("/send")
  public String sendMessage(){
    User user = new User();
    user.setName("tom-cao");
    user.setAge(35);
    kafkaProducer.sendUserMessage(user);
    return "send message ok ==> from web";
  }

}
