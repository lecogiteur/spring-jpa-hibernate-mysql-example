package com.techprimers.jpa.springjpahibernateexample.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users", catalog = "test")
public class Users implements Serializable {

    @Id
    private UUID uuid;

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    private Integer salary;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "teams", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Collection<Team> teams;

    public Collection<Team> getTeams() {
        return teams;
    }

    public void setTeams(Collection<Team> teams) {
        this.teams = teams;
    }

    public Users() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


}
