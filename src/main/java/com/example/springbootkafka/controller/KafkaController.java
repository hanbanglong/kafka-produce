package com.example.springbootkafka.controller;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class KafkaController {
    @Autowired
    Producer producer;
    private static final String topic = "long";
    @RequestMapping("/produce")
    public void sendProduce(){
       /* for (int i=0;i<1000;i++){
            kafkaTemplate.send(MY_TOPIC,String.valueOf(i+"::hanbanglong"));
        }*/

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello, Kafka!"+ UUID.randomUUID().toString());
        try {
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println(metadata.partition() + ":" + metadata.offset());
                    }
                }
            });
        } catch (Exception e) {
        }
    }
}
