package com.thelostisles.demo.movie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    @Query("from MovieEntity as movie where movie.isFavorite = true")
    List<MovieEntity> findByFavoriteIsTrue();

    @Query("from MovieEntity as movie where movie.startYear >= ?1 AND movie.startYear <= ?2")
    List<MovieEntity> findBetweenYears(Integer min, Integer max);

    @Query("from MovieEntity as movie where movie.startYear < ?1")
    List<MovieEntity> findBeforeYear(Integer max);

    @Query("from MovieEntity as movie where movie.startYear > ?1")
    List<MovieEntity> findAfterYear(Integer min);

    @Query("from MovieEntity as movie where movie.startYear = ?1")
    List<MovieEntity> findYear(Integer year);

    List<MovieEntity> findByPrimaryTitleContaining(String name);
}
