package com.thelostisles.demo.name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    private long id;
    private String primaryName;
    private Integer birthYear;
    private Integer deathYear;
    private String primaryProfession;
}
