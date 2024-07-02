package com.my.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public ResponseEntity<String> getAllExamples() {

        return ResponseEntity.ok("Provider getAllExamples Success!!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getExampleById(@PathVariable Long id) {
        return ResponseEntity.ok("Provider getExampleById " + id + " ,Success!!");
    }

    @PostMapping
    public ResponseEntity<String> createExample(@RequestBody String example) {
        return ResponseEntity.ok("Provider createExample " + example + " ,Success!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExample(@PathVariable Long id, @RequestBody String example) {
        return ResponseEntity.ok("Provider updateExample id " + id + " to " + example + " ,Success!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExample(@PathVariable Long id) {
        return ResponseEntity.ok("Provider delete id " + id + " ,Success!!");
    }

    @GetMapping("/discovery/services")
    public ResponseEntity<List<Map<String, Object>>> discoveryList() {
        List<Map<String, Object>> res = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        for (String serviceName : services) {
            List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceName);
            for (ServiceInstance instance : instanceList) {
                Map<String, Object> map = new HashMap<>();
                map.put("serviceName", serviceName);
                map.put("serviceId", instance.getServiceId());
                map.put("serviceHost", instance.getHost());
                map.put("servicePort", instance.getPort());

                res.add(map);
            }
        }
        return ResponseEntity.ok(res);
    }
}
