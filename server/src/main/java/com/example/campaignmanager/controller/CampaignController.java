package com.example.campaignmanager.controller;

import com.example.campaignmanager.dto.CampaignDto;
import com.example.campaignmanager.dto.CampaignRequest;
import com.example.campaignmanager.model.Campaign;
import com.example.campaignmanager.service.CampaignService;
import com.example.campaignmanager.util.CampaignMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/campaigns")
public class CampaignController {
    private final CampaignService campaignService;
    private final CampaignMapper campaignMapper;

    public CampaignController(CampaignService campaignService, CampaignMapper campaignMapper) {
        this.campaignService = campaignService;
        this.campaignMapper = campaignMapper;
    }

    @GetMapping
    public ResponseEntity<List<CampaignDto>> getCampaigns() {
        List<Campaign> campaigns = campaignService.getCampaigns();
        List<CampaignDto> campaignDtos = campaigns.stream().map(campaignMapper::convertToDto).toList();
        return ResponseEntity.ok(campaignDtos);
    }

    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignRequest campaignRequest) {
        Campaign campaign = campaignMapper.convertToEntity(campaignRequest);
        Campaign result = campaignService.createCampaign(campaign);

        return ResponseEntity.ok(campaignMapper.convertToDto(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignDto> updateCampaign(@PathVariable Long id, @RequestBody CampaignRequest campaignRequest) {
        Campaign campaign = campaignMapper.convertToEntity(campaignRequest);
        Campaign result = campaignService.updateCampaign(id, campaign);

        return ResponseEntity.ok(campaignMapper.convertToDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok("Campaign deleted");
    }
}
