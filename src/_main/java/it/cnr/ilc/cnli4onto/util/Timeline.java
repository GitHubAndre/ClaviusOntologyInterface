/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.util;

import java.io.Serializable;

/**
 *
 * @author andrea
 */
public class Timeline implements Serializable {

    private String name;
    private String intervalType;
    private int startYear;
    private int endYear;

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public Timeline(String n, int s, int e, String t) {
        this.name = n;
        this.startYear = s;
        this.endYear = e;
        this.intervalType = t;
    }

}
