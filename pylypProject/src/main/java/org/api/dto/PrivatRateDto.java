package org.api.dto;

import lombok.Data;

@Data
public class PrivatRateDto {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}