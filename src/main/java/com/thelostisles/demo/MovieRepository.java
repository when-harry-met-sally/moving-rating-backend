package com.thelostisles.demo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query("from MovieEntity as movie where movie.isFavorite = true")
    List<MovieEntity> findByFavoriteIsTrue();


}
