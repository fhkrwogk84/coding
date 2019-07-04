package com.test.coding.business.place.domain.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlaceDocumentsDTO implements Serializable {
    private static final long serialVersionUID = 145496231338185217L;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("category_group_code")
    private String categoryGroupCode;

    @JsonProperty("category_group_name")
    private String categoryGroupName;

    @JsonProperty("category_name")
    private String categoryName;

    private String distance;
    private String id;
    private String phone;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("place_url")
    private String placeUrl;

    @JsonProperty("road_address_name")
    private String roadAddressName;
    private String x;
    private String y;
}

