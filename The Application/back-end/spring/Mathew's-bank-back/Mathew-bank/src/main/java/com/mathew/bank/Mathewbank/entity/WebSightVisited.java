package com.mathew.bank.Mathewbank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "website_visited")
public class WebSightVisited {

    @Column(name = "visited_date")
    private LocalDateTime visitedDate;

    public LocalDateTime getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(LocalDateTime visitedDate) {
        this.visitedDate = visitedDate;
    }
}

