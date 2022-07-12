package com.example.world_of_tanks.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tanks")
public class Tank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private long health;

    private long power;

    @Column(nullable = false)
    private LocalDate created;

    @ManyToOne
    private Category category;

    @ManyToOne
    private UserEntity user;

    public Tank() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Tank setId(Long id) {
        this.id = id;
        return this;
    }

    public Tank setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public Tank setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public Tank setPower(long power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Tank setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Tank setCategory(Category category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public Tank setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
