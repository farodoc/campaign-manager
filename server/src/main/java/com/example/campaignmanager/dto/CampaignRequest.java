package com.example.campaignmanager.dto;

import com.example.campaignmanager.annotations.CampaignFundBidAmountConstraint;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@CampaignFundBidAmountConstraint
public class CampaignRequest {
    @NotBlank(message = "name should not be null")
    private String name;

    @NotEmpty(message = "at least one keyword should be present")
    private List<String> keywords;

    @DecimalMin(value = "0.1", message = "bid amount should be greater than or equal to 0.1")
    private Double bidAmount;

    @DecimalMin(value = "0.1", message = "campaign fund should be greater than or equal to 0.1")
    private Double campaignFund;

    @NotNull(message = "status should not be null")
    private Boolean status;

    @NotNull(message = "town should not be null")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "town should contain only letters")
    private String town;

    @Min(value = 1, message = "radius should be greater or equal to 1")
    private Integer radius;
}
