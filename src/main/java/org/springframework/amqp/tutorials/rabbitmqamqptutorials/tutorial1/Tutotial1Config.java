package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tutorial1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile({"tut1", "hello-world"})
@Configuration
@EnableScheduling
public class Tutotial1Config {

  @Bean
  public Queue hello() {
    return new Queue("hello");
  }

  @Profile("receiver")
  @Bean
  public Tutorial1Receiver receiver() {
    System.out.println("Run receiver!");
    return new Tutorial1Receiver();
  }

  @Profile("sender")
  @Bean
  public Tutorial1Sender sender() {
    System.out.println("Run sender!");
    return new Tutorial1Sender();
  }
}
