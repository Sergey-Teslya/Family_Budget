package com.sozin147.homeaccounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Categories")
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
