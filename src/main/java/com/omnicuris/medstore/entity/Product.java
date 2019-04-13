package com.omnicuris.medstore.entity;

import com.omnicuris.medstore.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constant.PRODUCT_INFO)
public class Product implements Serializable {

  private static final long serialVersionUID = 5192562115451194029L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  String name;

  String description;

  @Column(name = Constant.ACTUAL_PRICE)
  Integer actualPrice;

  @Column(name = Constant.OFFERED_PRICE)
  Integer offeredPrice;

  @Transient
  Timestamp createdOn;

}
