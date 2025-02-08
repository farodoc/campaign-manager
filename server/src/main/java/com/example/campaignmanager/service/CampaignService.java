package com.example.campaignmanager.service;

import com.example.campaignmanager.model.Campaign;
import com.example.campaignmanager.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final UserService userService;
    private final static Long USER_ID = 1L;

    public CampaignService(CampaignRepository campaignRepository, UserService userService) {
        this.campaignRepository = campaignRepository;
        this.userService = userService;
    }

    public List<Campaign> getCampaigns() {
        return campaignRepository.findAll();
    }

    public Optional<Campaign> getCampaign(Long id) {
        return campaignRepository.findById(id);
    }

    @Transactional
    public Campaign createCampaign(Campaign campaign) {
        double balance = userService.getUserBalance(USER_ID);

        if (balance < campaign.getCampaignFund()) {
            throw new IllegalArgumentException("Insufficient balance to create campaign");
        }

        userService.updateUserBalance(USER_ID, -campaign.getCampaignFund());

        return campaignRepository.save(campaign);
    }

    @Transactional
    public Optional<Campaign> updateCampaign(Long id, Campaign campaign) {
        Optional<Campaign> existingCampaign = campaignRepository.findById(id);

        if (existingCampaign.isPresent()) {
            double oldFund = existingCampaign.get().getCampaignFund();
            double newFund = campaign.getCampaignFund();
            double balance = userService.getUserBalance(USER_ID);

            if (balance + oldFund < newFund) {
                throw new IllegalArgumentException("Insufficient balance to update campaign");
            }

            userService.updateUserBalance(USER_ID, oldFund - newFund);
            campaign.setId(id);
            return Optional.of(campaignRepository.save(campaign));
        }

        return Optional.empty();
    }

    @Transactional
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
