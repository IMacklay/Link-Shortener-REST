package ru.mahalov.CodeReviewRESTApp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity(name = "REQUEST_DATA")
public class RequestData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date requestDate;

    private String requestHost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "link_id", nullable = false)
    private Link link;

    public RequestData() {

    }

    public RequestData(String requestHost, Link link) {
        this.requestDate = new Date(System.currentTimeMillis());
        this.requestHost = requestHost;
        this.link = link;
    }

    public Date getRequestDate() {
        return requestDate;
    }
}
