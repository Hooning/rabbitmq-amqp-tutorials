package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tutorial3;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tutorial3Sender {
  @Autowired
  private RabbitTemplate template;

  @Autowired
  private FanoutExchange fanout;

  AtomicInteger dots = new AtomicInteger(0);
  AtomicInteger counts = new AtomicInteger(0);

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    StringBuilder builder = new StringBuilder("Hello");
    if (dots.getAndIncrement() == 3) {
      dots.set(1);
    }
    builder.append(".".repeat(Math.max(0, dots.get())));
    builder.append(counts.incrementAndGet());

    String message = builder.toString();
    template.convertAndSend(fanout.getName(), "", message);
    System.out.println(" [x] Sent '" + message + "'");
  }
}
