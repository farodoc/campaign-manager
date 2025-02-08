package com.example.campaignmanager.util;

import com.example.campaignmanager.dto.CampaignDto;
import com.example.campaignmanager.dto.CampaignRequest;
import com.example.campaignmanager.model.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {
    public CampaignDto convertToDto(Campaign campaign) {
        return CampaignDto.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .keywords(campaign.getKeywords())
                .bidAmount(campaign.getBidAmount())
                .campaignFund(campaign.getCampaignFund())
                .status(campaign.getStatus())
                .town(campaign.getTown())
                .radius(campaign.getRadius())
                .build();
    }

    public Campaign convertToEntity(CampaignRequest campaignRequest) {
        return Campaign.builder()
                .name(campaignRequest.getName())
                .keywords(campaignRequest.getKeywords())
                .bidAmount(campaignRequest.getBidAmount())
                .campaignFund(campaignRequest.getCampaignFund())
                .status(campaignRequest.getStatus())
                .town(campaignRequest.getTown())
                .radius(campaignRequest.getRadius())
                .build();
    }
}
