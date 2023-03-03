package com.example.rickandmorty.service;

import com.example.rickandmorty.model.Character;
import java.util.List;

public interface CharacterService {
  Character getRandomCharacter();

  void saveAll(List<Character> characters);

  List<Character> getAllByPartOfName(String partOfName);

  List<Character> findAll();
}
