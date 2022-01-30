package ru.mahalov.CodeReviewRESTApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@Entity
public class LinkDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String original;
    private String link;
    private int rank;
    private long count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LinkDetail() {
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
