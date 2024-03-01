package me.ishutin.dadata.Controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/street")
@DependsOn("camelContext") // Добавлено для обеспечения порядка инициализации
public class StreetController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    public StreetController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @GetMapping("/count")
    public String getCount(@RequestParam String streetName) throws UnsupportedEncodingException {
        String encodedStreetName = URLEncoder.encode(streetName, "UTF-8");
        // Отправка запроса на Camel Route
        Long count = producerTemplate.requestBody("direct:dadataRoute", encodedStreetName, Long.class);

        return "Количество найденных улиц: " + count;
    }
//    @GetMapping("/street/count")
//    public String getStreetCount(@RequestParam String streetName) {
//        RestTemplate restTemplate = new RestTemplate();
//        String apiKey = "915fd9ffd2ee0354d07be85a1bab3ca5fd060c6b";
//        String kladrId = "7dfa745e-aa19-4688-b121-b655c11e482f";
//
//        String url = "https://cleaner.dadata.ru/api/v1/clean/address";
//        String apiUrl = String.format("%s?apiKey=%s&kladrId=%s&address=%s", url, apiKey, kladrId, streetName);
//
//        String result = restTemplate.getForObject(apiUrl, String.class);
//        return "Result: " + result;
//    }

    @GetMapping("/list")
    public Object getList(@RequestParam String streetName) {
        // Отправка запроса на Camel Route
        return producerTemplate.requestBody("direct:dadataRoute", streetName, Object.class);
    }
}

