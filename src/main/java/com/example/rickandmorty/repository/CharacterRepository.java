package com.example.rickandmorty.repository;

import com.example.rickandmorty.model.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CharacterRepository extends JpaRepository<Character, Long> {
  @Query(value = """      
      select *
      from characters
      limit 1
      offset :offset
      """,
      nativeQuery = true
  )
  Character getRandomCharacter(@Param("offset") int offset);

  @Query(value = """
    select *
    from characters
    where name like %:partOfName%
    """,
    nativeQuery = true
  )
  List<Character> getAllByPartOfName(@Param("partOfName") String partOfName);
}
