package com.xmansion.triskelion.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * Created by anunay on 21/1/17.
 */
@Component
public class Scheduler {

    @Value("${server.port:8080}")
    String port;

    @Value("${server.contextPath}")
    String contextPath = "";

    @Value("${com.xmansion.refreshDelay:5000}")
    String delay;

    @Scheduled(fixedDelayString = "${com.xmansion.refreshDelay:60}000")
    public void refreshConfig() {
        RestTemplate restTemplate = new RestTemplate();
        String refreshURL
                = "http://localhost:"+port+"/"+contextPath+"/refresh";

        System.out.println(refreshURL);

        ResponseEntity<String> response = restTemplate.postForEntity(refreshURL,new HttpEntity<>(""),String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response);

    }
}

