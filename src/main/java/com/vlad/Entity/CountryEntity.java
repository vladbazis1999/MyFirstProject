package com.vlad.Entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "countries")
public class CountryEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private int population;
    @OneToMany(mappedBy = "countryEntity", fetch = FetchType.EAGER)
    private List<PersonEntity> personEntities;
    @ManyToMany(fetch = FetchType.EAGER,cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "people",joinColumns = @JoinColumn (name = "country_id"),
                inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<JobEntity> jobEntities;

    public Set<JobEntity> getJobEntities() {
        return jobEntities;
    }

    public void setJobEntities(Set<JobEntity> jobEntities) {
        this.jobEntities = jobEntities;
    }

    public List<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(List<PersonEntity> personEntities) {
        this.personEntities = personEntities;
    }

    public CountryEntity() {
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "CountryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
