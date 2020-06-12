package me.sta.controller;

import com.netflix.discovery.EurekaClient;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
public class IndexController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            System.out.println(s);
            List<ServiceInstance> instances = discoveryClient.getInstances(s);
            System.out.println(instances);
        }
        return 0;
    }

    @ResponseBody
    @GetMapping("/eurekaUnRegister")
    public String shutDown() {
        eurekaClient.shutdown();
        return "eurekaUnRegistering";
    }
}
