package com.krepsinis.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="krepsinis")
public class Krepsinis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/#+=@_ ]*$")
    @Length(min=1 ,max=20,message="Prasau įvesti reikiama kiekį")
    @Column(name = "teamname")
    private String teamname;
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/#+=@_ ]*$")
    @Length(min=1 ,max=20,message = "Prašau įvesti reikiama kiekį")
    @Column(name = "namesurname")
    private String namesurname;
    @Column(name = "league")
    private String league;
    @Column(name = "sponsors")
    private String sponsors;

    public Krepsinis(int id, String teamname, String namesurname, String league, String sponsors) {
        this.id = id;
        this.teamname = teamname;
        this.namesurname = namesurname;
        this.league = league;
        this.sponsors = sponsors;
    }
    public Krepsinis( String teamname, String namesurname, String league, String sponsors) {
        this.id = id;
        this.teamname = teamname;
        this.namesurname = namesurname;
        this.league = league;
        this.sponsors = sponsors;
    }
    public Krepsinis() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getSponsors() {
        return sponsors;
    }

    public void setSponsors(String sponsors) {
        this.sponsors = sponsors;
    }

}
