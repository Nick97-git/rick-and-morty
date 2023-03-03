package com.example.rickandmorty.mapper;

import com.example.rickandmorty.model.Character;
import com.example.rickandmorty.dto.CharacterApiDto;
import com.example.rickandmorty.dto.CharacterDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {
  public CharacterDto toDto(Character character) {
    return new CharacterDto(
        character.getName(),
        character.getGender(),
        character.getSpecies(),
        character.getStatus(),
        character.getType(),
        character.getImageUrl()
    );
  }

  public Character toModel(CharacterApiDto dto) {
    Character character = new Character();
    character.setExternalId(dto.id());
    character.setType(dto.type());
    character.setName(dto.name());
    character.setGender(dto.gender());
    character.setSpecies(dto.species());
    character.setImageUrl(dto.image());
    character.setStatus(dto.status());
    return character;
  }
}
