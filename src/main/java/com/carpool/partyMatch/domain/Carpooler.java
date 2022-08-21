package com.carpool.partyMatch.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class Carpooler {

    Long userId;
    String name;

    protected Carpooler(){}
    public Carpooler(Long userId,String name){
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId(){
        return userId;
    }

    public String getName(){
        return name;
    }

}
