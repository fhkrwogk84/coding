package com.test.coding.business.place.domain.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlaceDTO implements Serializable {

    private static final long serialVersionUID = 4408002563251943226L;

    List<PlaceDocumentsDTO> documents = new ArrayList<>();
    PlaceMetaDTO meta = new PlaceMetaDTO();

}
/*

@Getter
@Setter
class PlaceDocuments implements Serializable {
    private static final long serialVersionUID = 145496231338185217L;

    private String address_name;
    private String category_group_code;
    private String category_group_name;
    private String category_name;
    private String distance;
    private String id;
    private String phone;
    private String place_name;
    private String place_url;
    private String road_address_name;
    private String x;
    private String y;
}

@Getter
@Setter
class PlaceMeta implements Serializable {
    private static final long serialVersionUID = 145496231338185217L;

    @JsonProperty("is_end")
    private boolean isEnd;

    private int pageable_count;
    private PlaceMetaSameName same_name;
    private int total_count;
}

@Getter
@Setter
class PlaceMetaSameName implements Serializable {
    private static final long serialVersionUID = 145496231338185217L;

    private List<String> region;
    private String keyword;
    private int selected_region;
}
*/
