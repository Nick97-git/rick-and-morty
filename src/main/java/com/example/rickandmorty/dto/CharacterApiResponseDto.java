package com.example.rickandmorty.dto;

import java.util.List;

public record CharacterApiResponseDto(
    CharacterApiInfoDto info,
    List<CharacterApiDto> results
) {
}
