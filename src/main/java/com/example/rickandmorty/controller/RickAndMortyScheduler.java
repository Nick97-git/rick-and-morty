package com.example.rickandmorty.controller;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.example.rickandmorty.model.Character;
import com.example.rickandmorty.dto.CharacterApiDto;
import com.example.rickandmorty.dto.CharacterApiResponseDto;
import com.example.rickandmorty.mapper.CharacterMapper;
import com.example.rickandmorty.service.CharacterService;
import com.example.rickandmorty.service.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
@RequiredArgsConstructor
public class RickAndMortyScheduler {
  private static int count = 0;
  private final CharacterService characterService;
  private final CharacterMapper characterMapper;
  private final HttpClient client;
  @Value("${api.url}")
  private String apiUrl;

  @Scheduled(fixedRate = 1000)
  public void init() {
    if (count == 2) {
      return;
    }
    CharacterApiResponseDto responseDto = client.get(apiUrl, CharacterApiResponseDto.class);
    List<CharacterApiDto> characterDtos = new ArrayList<>(responseDto.results());
    while (count > 0 && nonNull(responseDto.info().next())) {
      log.info("Number of dtos: {}", characterDtos.size());
      log.info("Next page: {}", responseDto.info().next());
      responseDto = client.get(responseDto.info().next(), CharacterApiResponseDto.class);
      characterDtos.addAll(responseDto.results());
    }
    count++;
    List<Character> characters = characterDtos.stream()
        .map(characterMapper::toModel)
        .toList();
    updateCharacters(characters);
    characterService.saveAll(characters);
    log.info("Finish cron job");
  }

  private void updateCharacters(List<Character> characters) {
    List<Character> charactersFromDb = characterService.findAll();
    if (charactersFromDb.size() == 0) {
      log.info("We don't have any characters in database");
      return;
    }
    log.info("Start update of characters");
    log.info("Number of characters in database: {}", charactersFromDb.size());
    log.info("Number of characters from public API: {}", characters.size());
    Map<Long, Long> idByExternalIdMap = charactersFromDb.stream()
        .collect(Collectors.toMap(
            Character::getExternalId,
            Character::getId
        ));
    characters.forEach(character -> {
      if (nonNull(idByExternalIdMap.get(character.getExternalId()))) {
        character.setType("default type");
      }
    });
    characters.forEach(character -> character.setId(idByExternalIdMap.get(character.getExternalId())));
    log.info("Number of characters from public API without id: {}",
        characters.stream()
            .filter(character -> isNull(character.getId())).toList().size());
  }
}
