package io.heynow.poc.source.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EventController {

    private final Source source;

    @RequestMapping(method = RequestMethod.POST)
    public void tick(@RequestParam("data") String data) {
        log.info("Tick");
        source.output().send(MessageBuilder.withPayload(data).build());
    }
}
