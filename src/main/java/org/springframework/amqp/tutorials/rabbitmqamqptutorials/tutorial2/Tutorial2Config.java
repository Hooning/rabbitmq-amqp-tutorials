package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tutorial2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile({"tutorial2", "work-queues"})
@Configuration
@EnableScheduling
public class Tutorial2Config {
  @Bean
  public Queue hello() {
    return new Queue("hello");
  }

  @Profile("receiver")
  private static class ReceiverConfig {
    @Bean
    public Tutorial2Receiver receiver1() {
      return new Tutorial2Receiver(1);
    }

    @Bean
    public Tutorial2Receiver receiver2() {
      return new Tutorial2Receiver(2);
    }
  }

  @Profile("sender")
  @Bean
  public Tutorial2Sender sender() {
    return new Tutorial2Sender();
  }
}
