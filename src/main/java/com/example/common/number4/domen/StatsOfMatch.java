/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.common.number4.domen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.reinert.jjschema.Attributes;
import java.util.Objects;

/**
 *
 * @author Gaca
 */
@JsonPropertyOrder({
    "id",
    "description",
    "date"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)// ovo znaci da se ne gleda property koji nema getere i setere!!!
public class StatsOfMatch {

    @Attributes(title = "Id", maximum = 100, minimum = 0, exclusiveMaximum = false, exclusiveMinimum = false, description = "Insert id ", maxLength = 3, minLength = 0, readonly = false, pattern = "^[0-9]+", required = true)
    private int id;
    @Attributes(required = true, title = "Description", description = "Insert description", readonly = false, maxLength = 20, minLength = 2, pattern = "^[a-zA-Z]+")   
    private String sdescription;
    @Attributes(description = "Insert time", title = "Time", format = "time", required = true)    
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSdescription() {
        return sdescription;
    }

    public void setSdescription(String sdescription) {
        this.sdescription = sdescription;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.sdescription);
        hash = 71 * hash + Objects.hashCode(this.time);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StatsOfMatch other = (StatsOfMatch) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.sdescription, other.sdescription)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "{" + "id=" + id + ", sdescription=" + sdescription + ", time=" + time + '}';
    }    


}
