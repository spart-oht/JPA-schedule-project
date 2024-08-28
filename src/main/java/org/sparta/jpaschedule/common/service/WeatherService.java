package org.sparta.jpaschedule.common.service;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sparta.jpaschedule.common.dto.WeatherDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "WEATHER API")
@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<WeatherDto> searchItem() {

        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://f-api.github.io")
                .path("/f-api/weather.json") // 경로 수정
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();

        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        // JSON 문자열을 가져와서 JSONObject로 변환
        return fromJSONtoItems(responseEntity.getBody());

    }

    public List<WeatherDto> fromJSONtoItems(String responseEntity) {

        JSONArray jsonArray = new JSONArray(responseEntity);

        List<WeatherDto> weatherList = new ArrayList<>();

        // JSON 배열을 순회하며 WeatherDto 객체로 변환
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            WeatherDto weatherDto = new WeatherDto(jsonObject.getString("date"), jsonObject.getString("weather"));
            weatherList.add(weatherDto);
        }

        return weatherList;
    }
}
