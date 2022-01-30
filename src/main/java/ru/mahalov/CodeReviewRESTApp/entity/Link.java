package ru.mahalov.CodeReviewRESTApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Indexed;
import ru.mahalov.CodeReviewRESTApp.entity.jsonview.JSON_Views;

import javax.persistence.*;

@Entity(name = "LINK")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @Column(nullable = false, unique = true)
    @JsonView(JSON_Views.PutOriginal.class)
    private String original;

    @Column(unique = true)
    @JsonView(JSON_Views.PutShortLink.class)
    @JsonProperty("link")
    private String shortLink;

    public Link() {
    }

    public Link(String original, String shortLink) {
        this.original = original;
        this.shortLink = shortLink;
    }

    public Link(String original) {
        this.original = original;
    }

    public long getId() {
        return id;
    }

    public String getOriginal() {
        return original;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }
}
