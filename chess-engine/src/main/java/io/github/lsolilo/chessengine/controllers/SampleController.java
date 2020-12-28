package io.github.lsolilo.chessengine.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SampleController {

    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("sample")
    ResponseEntity<Void> sample(@RequestParam String msg) {
        kafkaTemplate.send("sampleTopic", msg);
        return ResponseEntity.ok().build();
    }

    @KafkaListener(topics = "sampleTopic", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println(message);
    }
}
