package com.thelostisles.demo;
import lombok.*;

import javax.persistence.*;



@Entity
@Table(name = "name")

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class NameEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String primaryName;
    private Integer birthYear;
    private Integer deathYear;
    private String primaryProfession;
}
