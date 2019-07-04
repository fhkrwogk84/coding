package com.test.coding.business.place.domain.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class PlaceMetaDTO implements Serializable {
    private static final long serialVersionUID = 245496231338185217L;

    @JsonProperty("is_end")
    private boolean isEnd;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("same_name")
    private PlaceMetaSameName sameName;

    @JsonProperty("total_count")
    private int totalCount;
}

@Getter
@Setter
class PlaceMetaSameName implements Serializable {
    private static final long serialVersionUID = 345496231338185217L;

    private List<String> region;
    private String keyword;

    @JsonProperty("selected_region")
    private String selectedRegion;
}
