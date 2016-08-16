package io.heynow.poc.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.router.AbstractMappingMessageRouter;
import org.springframework.integration.router.MethodInvokingRouter;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(Sink.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    Sink channels;

    @Bean
    @ServiceActivator(inputChannel = Sink.INPUT)
    public AbstractMappingMessageRouter router(BinderAwareChannelResolver channelResolver) {
        AbstractMappingMessageRouter router = new MethodInvokingRouter(this, "whereTo");
        router.setChannelResolver(channelResolver);
        return router;
    }

    public String whereTo(Message<?> message) {
        return "ticktock2";
    }
}
