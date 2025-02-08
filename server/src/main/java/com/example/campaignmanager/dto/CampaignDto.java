package com.example.campaignmanager.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CampaignDto {
    private Long id;
    private String name;
    private List<String> keywords;
    private Double bidAmount;
    private Double campaignFund;
    private Boolean status;
    private String town;
    private Integer radius;
}
