package com.omnicuris.medstore.entity;

import com.omnicuris.medstore.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = Constant.CUSTOMER_INFO)
public class Customer implements Serializable {

  private static final long serialVersionUID = 5192762115451194029L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  String email;

  String name;

  String phone;

  String password;

  @Transient
  Timestamp createdOn;

}
