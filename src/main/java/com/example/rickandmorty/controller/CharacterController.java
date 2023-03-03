package com.example.rickandmorty.controller;

import com.example.rickandmorty.model.Character;
import com.example.rickandmorty.dto.CharacterDto;
import com.example.rickandmorty.mapper.CharacterMapper;
import com.example.rickandmorty.service.CharacterService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@AllArgsConstructor
public class CharacterController {
  private final CharacterService characterService;
  private final CharacterMapper characterMapper;

  @GetMapping("/random")
  public CharacterDto getRandom() {
    Character randomCharacter = characterService.getRandomCharacter();
    return characterMapper.toDto(randomCharacter);
  }

  @GetMapping
  public List<CharacterDto> getAllByPartOfName(
      @RequestParam("partOfName") String partOfName
  ) {
    List<Character> characters = characterService.getAllByPartOfName(partOfName);
    return characters.stream()
        .map(characterMapper::toDto)
        .toList();
  }
}
