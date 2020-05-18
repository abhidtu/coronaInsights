package com.corona.insights.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class HardestHitDO {

    private BigInteger confirmed;
    private String country;
    private String state;
    private String district;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String geometric_distance;

}