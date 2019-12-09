package com.mycompany.myapp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * FooResource controller
 */
@RestController
@RequestMapping("/api/network")
public class FooResource {

    private final Logger log = LoggerFactory.getLogger(FooResource.class);

    /**
    * GET hello
    */
    @PostMapping(path="/network-entry",produces = "application/json",consumes="application/json")
    public String network(@RequestBody Map<String,String> payload)
    {
        String cardNumber=payload.get("cardNumber");
        String cvv=payload.get("cvv");
        String expiry=payload.get("expiry");
        String currencyCode=payload.get("currencyCode");
        String amount=payload.get("amount");
        String merAccNo=payload.get("merAccNo");
        String merIfsc=payload.get("merIfsc");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> map=new HashMap<String, String>();
        map.put("cardNumber",cardNumber);
        map.put("cvv",cvv);
        map.put("expiry",expiry);
        map.put("currencyCode",currencyCode);
        map.put("amount",amount);
        map.put("cardNumber",cardNumber);
        map.put("merAccNo",merAccNo);
        map.put("merIfsc",merIfsc);
        
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate=new RestTemplate();
        String fooResourceUrl
            = "http://localhost:8083/api/issuingbank/issuing-bank-entry";
        ResponseEntity<String> response = restTemplate.postForEntity(
            fooResourceUrl, request , String.class);

        
        return response.getBody();
    }
    
    @PostMapping(path="/network-otp",produces="application/json",consumes="application/json")
    public String networkOtpValidation(@RequestBody Map<String,String> payload) {
    	
    	String otp=payload.get("otp");
    	String mobileNumber=payload.get("mobileNumber");
    	String merAccNo=payload.get("merAccNo");
        String merIfsc=payload.get("merIfsc");
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> map=new HashMap<String, String>();
        
        map.put("otp", otp);
        map.put("mobileNumber", mobileNumber);
        map.put("merAccNo",merAccNo);
        map.put("merIfsc",merIfsc);
        
        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate=new RestTemplate();
        
        String fooResourceUrl
        = "http://localhost:8083/api/issuingbank/otp-validation";
        ResponseEntity<String> response = restTemplate.postForEntity(
        fooResourceUrl, request , String.class);
    	
    	return response.getBody();
    	 
    }

}
