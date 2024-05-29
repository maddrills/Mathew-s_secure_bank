package com.mathew.bank.Mathewbank.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "website_visited")
public class WebSightVisited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "visited_date")
    private LocalDateTime visitedDate;

    public LocalDateTime getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(LocalDateTime visitedDate) {
        this.visitedDate = visitedDate;
    }
}

