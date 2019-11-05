package com.sozin147.homeaccounting.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table()
@NoArgsConstructor
@EqualsAndHashCode
public class MoneyBudget {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private CustomUser user;

    @Column(name = "money")
    private Integer money;

    public MoneyBudget(CustomUser user) {
        this.user = user;
    }

    public MoneyBudget(CustomUser user, Integer money) {
        this.user = user;
        this.money = money;
    }
}
