package com.example.campaignmanager.controller;

import com.example.campaignmanager.dto.CampaignDto;
import com.example.campaignmanager.dto.CampaignRequest;
import com.example.campaignmanager.exceptions.CampaignNotFoundException;
import com.example.campaignmanager.model.Campaign;
import com.example.campaignmanager.service.CampaignService;
import com.example.campaignmanager.util.CampaignMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<CampaignDto> getCampaign(@PathVariable Long id) {
        Optional<Campaign> campaign = campaignService.getCampaign(id);
        if (campaign.isPresent()) {
            return ResponseEntity.ok(campaignMapper.convertToDto(campaign.get()));
        }
        throw new CampaignNotFoundException("Campaign not found with Id: " + id);
    }

    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody @Valid CampaignRequest campaignRequest) {
        Campaign campaign = campaignMapper.convertToEntity(campaignRequest);
        Campaign result = campaignService.createCampaign(campaign);

        return ResponseEntity.ok(campaignMapper.convertToDto(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampaignDto> updateCampaign(@PathVariable Long id, @RequestBody @Valid CampaignRequest campaignRequest) {
        Campaign campaign = campaignMapper.convertToEntity(campaignRequest);
        Optional<Campaign> result = campaignService.updateCampaign(id, campaign);
        if (result.isPresent()) {
            return ResponseEntity.ok(campaignMapper.convertToDto(result.get()));
        }
        throw new CampaignNotFoundException("Campaign not found with Id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.ok("Campaign deleted");
    }
}
