package com.test.coding.business.user.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@Entity(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@ApiModelProperty(required = true, example = "1")
    private Long userId;

    //@ApiModelProperty(required = false, example = "name")
    private String loginId;

    //@ApiModelProperty(required = false, example = "desc")
    private String password;
}
