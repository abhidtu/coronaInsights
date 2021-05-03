
package com.corona.insights.model;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateDistrict {

    @JsonProperty("districts")
    public List<District> districts;
    @JsonProperty("ttl")
    public Integer ttl;

}
