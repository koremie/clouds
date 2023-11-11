package com.lviv.iot.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class Trademark {
    @Id
    @Column(name = "name", length = 50)
    private String name;
    @OneToMany(mappedBy = "trademark")
    private List<Snack> snacks;
}
