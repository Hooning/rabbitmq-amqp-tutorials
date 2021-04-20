package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tutorial2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
public class Tutorial2Receiver {
  private final int instance;

  public Tutorial2Receiver(int instance) {
    this.instance = instance;
  }

  @RabbitHandler
  public void receive(String in) throws InterruptedException {
    StopWatch watch = new StopWatch();
    watch.start();
    System.out.println("instance " + this.instance +
            " [x] Received '" + in + "'");
    doWork(in);
    watch.stop();
    System.out.println("instance " + this.instance +
            " [x] [" + in + "] Done in " + watch.getTotalTimeSeconds() + "s");
  }

  private void doWork(String in) throws InterruptedException {
    for (char ch : in.toCharArray()) {
      if (ch == '.') {
        Thread.sleep(1000);
      }
    }
  }
}
