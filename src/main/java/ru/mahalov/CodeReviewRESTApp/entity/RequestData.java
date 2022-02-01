package ru.mahalov.CodeReviewRESTApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "REQUEST_DATA")
@NoArgsConstructor
@Getter
public class RequestData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date requestDate;

    private String requestHost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "link_id", nullable = false)
    private Link link;

    public RequestData(String requestHost, Link link) {
        this.requestDate = new Date(System.currentTimeMillis());
        this.requestHost = requestHost;
        this.link = link;
    }
}
