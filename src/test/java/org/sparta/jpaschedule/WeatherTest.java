package org.sparta.jpaschedule;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.sparta.jpaschedule.common.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WeatherTest {

    @Autowired
    private RestTemplateBuilder builder;

    private RestTemplate restTemplate;

    @Test
    void testWeather() {

        restTemplate = builder.build();

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        String formattedDate = date.format(formatter);

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
        String jsonResponse = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(jsonResponse);

        List<WeatherDto> weatherList = new ArrayList<>();

        // JSON 배열을 순회하며 WeatherDto 객체로 변환
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            WeatherDto weatherDto = new WeatherDto(jsonObject.getString("date"), jsonObject.getString("weather"));
            weatherList.add(weatherDto);
        }



    }
}
