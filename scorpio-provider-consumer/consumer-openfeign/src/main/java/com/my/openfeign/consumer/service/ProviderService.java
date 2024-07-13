package com.my.openfeign.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "sc-provider", path = "/api/provider")
// 新版已廢棄 @RequestMapping("/provider")
public interface ProviderService {
    @PostMapping
    String createExample(@RequestBody String example);

    @DeleteMapping("/{id}")
    String deleteExample(@PathVariable Long id);

    @GetMapping
    String getAllExamples();

    @GetMapping("/{id}")
    String getExampleById(@PathVariable Long id);

    @PutMapping("/{id}")
    String updateExample(@PathVariable Long id, @RequestBody String example);
}
