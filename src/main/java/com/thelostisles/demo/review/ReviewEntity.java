package com.thelostisles.demo.review;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reviews")

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String description;
    private Integer rating;
}
