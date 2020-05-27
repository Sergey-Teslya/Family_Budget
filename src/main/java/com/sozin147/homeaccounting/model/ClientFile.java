package com.sozin147.homeaccounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ClientFile {
  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  CustomUser customUser;

  byte[] file;

  public ClientFile(CustomUser customUser, byte[] file) {
    this.customUser = customUser;
    this.file = file;
  }
}
