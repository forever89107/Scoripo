package com.my.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate template;

    private static final String PROVIDER_URL = "http://sc-provider/api/provider";

    @GetMapping
    public ResponseEntity<String> getAllExamples() {
        String res = template.getForObject(PROVIDER_URL, String.class);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getExampleById(@PathVariable Long id) {
        return ResponseEntity.ok(template.getForObject(PROVIDER_URL, String.class, id));
    }

    @PostMapping
    public ResponseEntity<String> createExample(@RequestBody String example) {
        return ResponseEntity.ok(template.postForObject(PROVIDER_URL, example, String.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExample(@PathVariable Long id, @RequestBody String example) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(example, headers);

        ResponseEntity<String> response = template.exchange(PROVIDER_URL, HttpMethod.PUT, entity, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        } else {
            System.out.println("Failed to update Example");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExample(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = template.exchange(PROVIDER_URL, HttpMethod.DELETE, entity, Void.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Provider delete id " + id + " ,Success!!");
        }
        return ResponseEntity.notFound().build();
    }
}
