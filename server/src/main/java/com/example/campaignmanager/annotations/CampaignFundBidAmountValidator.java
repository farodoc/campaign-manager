package com.example.campaignmanager.annotations;

import com.example.campaignmanager.dto.CampaignRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CampaignFundBidAmountValidator implements ConstraintValidator<CampaignFundBidAmountConstraint, CampaignRequest> {

    @Override
    public void initialize(CampaignFundBidAmountConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CampaignRequest campaignRequest, ConstraintValidatorContext context) {
        if (campaignRequest.getCampaignFund() == null || campaignRequest.getBidAmount() == null) {
            return false;
        }
        return campaignRequest.getCampaignFund() >= campaignRequest.getBidAmount();
    }
}
