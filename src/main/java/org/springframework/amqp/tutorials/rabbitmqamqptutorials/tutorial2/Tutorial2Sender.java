package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tutorial2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tutorial2Sender {
  @Autowired
  private RabbitTemplate template;

  @Autowired
  private Queue queue;

  AtomicInteger dots = new AtomicInteger(0);
  AtomicInteger count = new AtomicInteger(0);

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    StringBuilder builder = new StringBuilder("Hello");
    if (dots.incrementAndGet() == 4) {
      dots.set(1);
    }

    builder.append(".".repeat(Math.max(0, dots.get())));

    builder.append(count.incrementAndGet());
    String message = builder.toString();
    template.convertAndSend(queue.getName(), message);
    System.out.println(" [X] Send '" + message + "'");
  }

}
