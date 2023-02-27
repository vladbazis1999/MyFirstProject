package com.vlad.Entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class JobEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private int salary;

    @OneToMany(mappedBy = "jobEntity", fetch = FetchType.EAGER)
    private List<PersonEntity> personEntities;

    @ManyToMany(fetch = FetchType.EAGER ,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "people",joinColumns = @JoinColumn (name = "job_id")
            ,inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<CountryEntity> countryEntities;

    public Set<CountryEntity> getCountryEntities() {
        return countryEntities;
    }

    public void setCountryEntities(Set<CountryEntity> countryEntities) {
        this.countryEntities = countryEntities;
    }

    public List<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(List<PersonEntity> personEntities) {
        this.personEntities = personEntities;
    }

    public JobEntity() {
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "JobEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
