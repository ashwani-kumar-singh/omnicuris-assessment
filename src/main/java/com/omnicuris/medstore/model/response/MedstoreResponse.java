package com.omnicuris.medstore.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
@Setter
public class MedstoreResponse<T> {
  private T responseObject;

  @JsonIgnore
  private HttpStatus statusCode;

  public MedstoreResponse() {
    this.statusCode = HttpStatus.OK;
  }

  public MedstoreResponse(T responseObject) {
    this.responseObject = responseObject;
    this.statusCode = HttpStatus.OK;
  }

}
