package com.sozin147.homeaccounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BudgetCategory {
  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  private Category category;

  @ManyToOne
  private CustomUser customUser;

  private Integer money;

  public BudgetCategory(Category category, CustomUser customUser, Integer money) {
    this.category = category;
    this.customUser = customUser;
    this.money = money;
  }
}
