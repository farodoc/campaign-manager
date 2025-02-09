package com.example.campaignmanager.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CampaignFundBidAmountValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CampaignFundBidAmountConstraint {
    String message() default "campaignFund should be greater than or equal to bidAmount";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
