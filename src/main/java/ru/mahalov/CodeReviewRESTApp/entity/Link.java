package ru.mahalov.CodeReviewRESTApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.stereotype.Indexed;
import ru.mahalov.CodeReviewRESTApp.entity.jsonview.JSON_Views;

import javax.persistence.*;

@Entity(name = "LINK")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @Column(nullable = false, unique = true)
    @JsonView(JSON_Views.PutOriginal.class)
    @NonNull
    private String original;

    @Column(unique = true)
    @JsonView(JSON_Views.PutShortLink.class)
    @JsonProperty("link")
    @Setter
    @NonNull
    private String shortLink;

    public Link(String original) {
        this.original = original;
    }

}
