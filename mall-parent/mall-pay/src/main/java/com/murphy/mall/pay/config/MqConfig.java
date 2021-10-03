package com.murphy.mall.pay.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * RabbitMQ - 配置类
 *
 * @author murphy
 * @since 2021/10/3 11:37 上午
 */
@Configuration
public class MqConfig {

    @Autowired
    private Environment environment;

    /**
     * 发送订单已支付消息的交换机
     * @return
     */
    @Bean
    public Exchange orderExchange() {
        return ExchangeBuilder.directExchange(environment.getProperty("mq.pay.exchange.order")).durable(true).build();
    }

    /**
     * 声明Queue
     * @return
     */
    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(environment.getProperty("mq.pay.queue.order")).build();
    }

    /**
     * 声明 绑定Queue和Exchange
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding orderBinding(@Qualifier("orderQueue") Queue queue, @Qualifier("orderExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(environment.getProperty("mq.pay.routing.key")).noargs();
    }
}
