package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tutorial3;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tutorial3", "pub-sub", "publish-subscribe"})
@Configuration
public class Tutorial3Config {
  @Bean
  public FanoutExchange fanout() {
    return new FanoutExchange("tutorial.fanout");
  }

  @Profile("receiver")
  private static class ReceiverConfig {
    @Bean
    public Queue autoDeleteQueue1() {
      return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
      return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(FanoutExchange fanout,
                            Queue autoDeleteQueue1) {
      return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout,
                            Queue autoDeleteQueue2) {
      return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
    }

    @Bean
    public Tutorial3Receiver receiver() {
      return new Tutorial3Receiver();
    }
  }

  @Profile("sender")
  @Bean
  public Tutorial3Sender sender() {
    return new Tutorial3Sender();
  }
}
