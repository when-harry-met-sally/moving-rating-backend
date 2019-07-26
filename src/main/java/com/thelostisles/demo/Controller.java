package com.thelostisles.demo;



import com.thelostisles.demo.movie.Movie;
import com.thelostisles.demo.movie.MovieEntity;
import com.thelostisles.demo.movie.MovieRepository;
import com.thelostisles.demo.name.Name;
import com.thelostisles.demo.name.NameEntity;
import com.thelostisles.demo.name.NameRepository;
import com.thelostisles.demo.review.Review;
import com.thelostisles.demo.review.ReviewEntity;
import com.thelostisles.demo.review.ReviewRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
public class Controller {
    private final MovieRepository movieRepository;
    private final NameRepository nameRepository;
    private final ReviewRepository reviewRepository;
    public Controller(MovieRepository movieRepository, NameRepository nameRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.nameRepository = nameRepository;
        this.reviewRepository = reviewRepository;
    }


    @GetMapping
    @RequestMapping("movies")
    public List<Movie> getAll(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max, @RequestParam(required = false) String name) {
        if (year != null){
            return movieRepository.findYear(year)
                    .stream()
                    .map(movieEntity -> toMovie(movieEntity))
                    .collect(Collectors.toList());
        } else if (min != null & max != null){
            return movieRepository.findBetweenYears(min, max)
                    .stream()
                    .map(movieEntity -> toMovie(movieEntity))
                    .collect(Collectors.toList());
        } else if (min != null){
            return movieRepository.findAfterYear(min)
                    .stream()
                    .map(movieEntity -> toMovie(movieEntity))
                    .collect(Collectors.toList());
        } else if (max != null){
            return movieRepository.findBeforeYear(max)
                    .stream()
                    .map(movieEntity -> toMovie(movieEntity))
                    .collect(Collectors.toList());
        } else if (name != null){
            return movieRepository.findByPrimaryTitleContaining(name)
                    .stream()
                    .map(movieEntity -> toMovie(movieEntity))
                    .collect(Collectors.toList());
        }
            return movieRepository.findAll()
                    .stream()
                    .map(movieEntity -> toMovie(movieEntity))
                    .collect(Collectors.toList());

    }

    @GetMapping
    @RequestMapping("movies/favorites")
    public List<Movie> getFavorites() {
        return movieRepository.findByFavoriteIsTrue()
                .stream()
                .map(movieEntity -> toMovie(movieEntity))
                .collect(Collectors.toList());
    }


    @GetMapping
    @RequestMapping("movies/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return toMovie(movieRepository.findById(id).get());
    }


    @PostMapping
    @RequestMapping("movies/{id}/favorite")
    public Movie getMovieForFavoriting(@PathVariable Long id) {
        MovieEntity movieEntity = movieRepository.findById(id).get();
        movieEntity.setFavorite(!movieEntity.isFavorite());
        movieRepository.saveAndFlush(movieEntity);
        return toMovie(movieEntity);
    }

    @GetMapping
    @RequestMapping("names")
    public List<Name> getNames() {
        return nameRepository.findAll()
                .stream()
                .map(nameEntity-> toName(nameEntity))
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("reviews")
    public List<Review> getReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewEntity-> toReview(reviewEntity))
                .collect(Collectors.toList());
    }

    @PostMapping
    @RequestMapping("movies/{id}/reviews")
    public Review createReview(@PathVariable("id") Long id, @RequestBody Review review) {
         ReviewEntity reviewEntity = toReviewEntity(review);
         reviewRepository.saveAndFlush(reviewEntity);
         MovieEntity movieEntity = movieRepository.findById(id).get();
         List<ReviewEntity> reviews = movieEntity.getReviews();
         reviews.add(reviewEntity);
         movieEntity.setReviews(reviews);
         movieRepository.saveAndFlush(movieEntity);
         return toReview(reviewEntity);
    }

    private Movie toMovie(MovieEntity movieEntity) {
        return Movie.builder()
                .id(movieEntity.getId())
                .primaryTitle(movieEntity.getPrimaryTitle())
                .genres(movieEntity.getGenres())
                .isAdult(movieEntity.isAdult())
                .isFavorite(movieEntity.isFavorite())
                .runtimeMinutes(movieEntity.getRuntimeMinutes())
                .reviews(movieEntity
                            .getReviews()
                            .stream()
                            .map(this::toReview)
                            .collect(Collectors.toList()))
                .names(movieEntity
                        .getNames()
                        .stream()
                        .map(this::toName)
                        .collect(Collectors.toList()))
                .startYear(movieEntity.getStartYear())
                .build();
    }

    private MovieEntity toMovieEntity(Movie movie) {
        return MovieEntity.builder()
                .id(movie.getId())
                .primaryTitle(movie.getPrimaryTitle())
                .genres(movie.getGenres())
                .isAdult(movie.isAdult())
                .isFavorite(movie.isFavorite())
                .runtimeMinutes(movie.getRuntimeMinutes())
                .startYear(movie.getStartYear())
                .reviews(movie
                        .getReviews()
                        .stream()
                        .map(this::toReviewEntity)
                        .collect(Collectors.toList()))
                .names(movie
                        .getNames()
                        .stream()
                        .map(this::toNameEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    private Review toReview(ReviewEntity reviewEntity) {
        return Review.builder()
                .id(reviewEntity.getId())
                .description(reviewEntity.getDescription())
                .rating(reviewEntity.getRating())
                .build();
    }

    private ReviewEntity toReviewEntity(Review review) {
        return ReviewEntity.builder()
                .id(review.getId())
                .description(review.getDescription())
                .rating(review.getRating())
                .build();
    }

    private Name toName(NameEntity nameEntity){
        return Name.builder()
                .id(nameEntity.getId())
                .birthYear(nameEntity.getBirthYear())
                .deathYear(nameEntity.getDeathYear())
                .primaryName(nameEntity.getPrimaryName())
                .primaryProfession(nameEntity.getPrimaryName())
                .build();
    }

    private NameEntity toNameEntity(Name name){
        return NameEntity.builder()
                .id(name.getId())
                .birthYear(name.getBirthYear())
                .deathYear(name.getDeathYear())
                .primaryName(name.getPrimaryName())
                .primaryProfession(name.getPrimaryName())
                .build();
    }




}
