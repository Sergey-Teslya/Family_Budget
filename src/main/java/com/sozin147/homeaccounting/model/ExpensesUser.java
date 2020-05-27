package com.sozin147.homeaccounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class ExpensesUser {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private CustomUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "money_expenses")
    private Integer money;

    private String comment;

    @Temporal(TemporalType.DATE)
    private Date date;

    public ExpensesUser(CustomUser user, Category category, Integer money, String comment, Date date) {
        this.user = user;
        this.category = category;
        this.money = money;
        this.comment = comment;
        this.date = date;
    }
}
