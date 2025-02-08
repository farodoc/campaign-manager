package com.example.campaignmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@Table(name = "campaigns")
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private List<String> keywords = new ArrayList<>();

    @Column(name = "bid_amount")
    private Double bidAmount;

    @Column(name = "campaign_fund")
    private Double campaignFund;

    private Boolean status;

    private String town;

    private Integer radius;
}
