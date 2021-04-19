package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tutorial1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class Tutorial1Receiver {

  @RabbitHandler
  public void receiver(String in) {
    System.out.println(" [x] Received '" + in + "'");
  }
}
