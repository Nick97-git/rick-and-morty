package com.example.rickandmorty.dto;

public record CharacterApiDto(
    Long id,
    String name,
    String gender,
    String species,
    String status,
    String type,
    String image
) {
}
