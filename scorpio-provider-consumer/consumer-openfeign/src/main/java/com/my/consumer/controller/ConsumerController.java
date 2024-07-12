package com.my.consumer.controller;

import com.my.consumer.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ProviderService providerService;


    @GetMapping
    public String getAllExamples() {
        return providerService.getAllExamples();
    }

    @GetMapping("/{id}")
    public String getExampleById(@PathVariable Long id) {
        return providerService.getExampleById(id);
    }

    @PostMapping
    public String createExample(@RequestBody String example) {
        return providerService.createExample(example);
    }

    @PutMapping("/{id}")
    public String updateExample(@PathVariable Long id, @RequestBody String example) {
        return providerService.updateExample(id, example);

    }

    @DeleteMapping("/{id}")
    public String deleteExample(@PathVariable Long id) {
        return providerService.deleteExample(id);
    }
}
