package com.example.rickandmorty.dto;

public record CharacterDto(
  String name,
  String gender,
  String species,
  String status,
  String type,
  String imageUrl
){
}
