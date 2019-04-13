package com.omnicuris.medstore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

  Integer id;

  String name;

  String description;

  Integer quantity;

  @JsonProperty("actual_price")
  Integer actualPrice;

  @JsonProperty("offered_price")
  Integer offeredPrice;


}
