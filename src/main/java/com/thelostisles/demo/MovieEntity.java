package com.thelostisles.demo;




import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String primaryTitle;
    private boolean isAdult;
    private boolean isFavorite;
    private int runtimeMinutes;
    private String genres;

    @OneToMany
    @JoinTable (
            name = "movies_reviews",
            joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "review_id", referencedColumnName = "id", unique = true)}
    )

    private List<ReviewEntity> reviews;
    @OneToMany
    @JoinTable (
            name = "title_name",
            joinColumns = {@JoinColumn(name = "title_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "name_id", referencedColumnName = "id", unique = true)}
    )
    private List<NameEntity> names;


}
