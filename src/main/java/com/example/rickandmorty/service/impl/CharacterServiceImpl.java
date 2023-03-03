package com.example.rickandmorty.service.impl;

import com.example.rickandmorty.model.Character;
import com.example.rickandmorty.repository.CharacterRepository;
import com.example.rickandmorty.service.CharacterService;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {
  private final CharacterRepository characterRepository;

  @Override
  public Character getRandomCharacter() {
    long count = characterRepository.count();
    int offset = new Random().nextInt((int) count);
    return characterRepository.getRandomCharacter(offset);
  }

  @Override
  public void saveAll(List<Character> characters) {
    characterRepository.saveAll(characters);
  }

  @Override
  public List<Character> getAllByPartOfName(String partOfName) {
    return characterRepository.getAllByPartOfName(partOfName);
  }

  @Override
  public List<Character> findAll() {
    return characterRepository.findAll();
  }
}
