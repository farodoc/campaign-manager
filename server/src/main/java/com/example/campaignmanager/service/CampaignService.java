package com.example.campaignmanager.service;

import com.example.campaignmanager.model.Campaign;
import com.example.campaignmanager.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getCampaigns() {
        return campaignRepository.findAll();
    }

    public Optional<Campaign> getCampaign(Long id) {
        return campaignRepository.findById(id);
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Optional<Campaign> updateCampaign(Long id, Campaign campaign) {
        Optional<Campaign> existingCampaign = campaignRepository.findById(id);

        if (existingCampaign.isPresent()) {
            campaign.setId(id);
            return Optional.of(campaignRepository.save(campaign));
        }

        return Optional.empty();
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
