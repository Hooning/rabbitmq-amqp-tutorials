package org.springframework.amqp.tutorials.tutorial1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tutorial1Sender {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  private Queue queue;

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    System.out.println("## Send message! => " + queue.getName());
    String message = "Hello World!";
    this.template.convertAndSend(queue.getName(), message);
    System.out.println(" [x] sent '" + message + "'");
  }
}
