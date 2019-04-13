package com.omnicuris.medstore.entity;

import com.omnicuris.medstore.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name = Constant.ORDER_INFO)
public class Order implements Serializable {

  private static final long serialVersionUID = 5192762145451194029L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @Column(name = Constant.CUSTOMER_ID)
  Integer customerId;

  @Column(name = Constant.PRODUCT_ID)
  Integer productId;

  Integer quantity;

  @Transient
  Timestamp createdOn;
}
