package com.example.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HttpClient {
  private final ObjectMapper objectMapper;

  public <T> T get(String url, Class<T> clazz) {
    HttpGet request = new HttpGet(url);
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      InputStream content = client.execute(request).getEntity().getContent();
      return objectMapper.readValue(content, clazz);
    } catch (IOException e) {
      throw new RuntimeException("Can't get data by url: " + url);
    }
  }
}
