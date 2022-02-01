package ru.mahalov.CodeReviewRESTApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class LinkDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Setter
    private String original;
    @Setter
    private String link;
    @Setter
    private int rank;
    @Setter
    private long count;

    public String getLink() {
        return "/l/"+link;
    }

}
