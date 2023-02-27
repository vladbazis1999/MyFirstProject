package com.vlad.Entity;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class CarEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private int price;
    public CarEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarEnity{" +
                "id=" + id +
                ",Name ='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
