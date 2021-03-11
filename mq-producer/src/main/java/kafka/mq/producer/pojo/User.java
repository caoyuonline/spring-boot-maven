package kafka.mq.producer.pojo;

import java.io.Serializable;

public class User implements Serializable {

  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private Integer age;

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getAge() {
    return age;
  }
}
