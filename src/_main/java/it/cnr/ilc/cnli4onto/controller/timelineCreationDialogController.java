/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.controller;

import it.cnr.ilc.cnli4onto.util.Timeline;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SlideEndEvent;

/**
 *
 * @author andrea
 */
@ViewScoped
@Named

public class timelineCreationDialogController implements Serializable {

    private ArrayList<Timeline> timelines = new ArrayList<Timeline>();
    private static final int MAX_INTERVAL = 1500;
    private static final int MIN_INTERVAL = 0;
    private static final int OFFSET_INTERVAL = 500;

    private String periodName;

    private int max_1 = 1000;
    private int min_1 = 500;

    private int max_2 = 1000;
    private int min_2 = 500;

    private int max_3 = 1000;
    private int min_3 = 500;

    private String intervalType_1 = "closed";
    private String intervalType_2 = "closed";
    private String intervalType_3 = "closed";

    public void resetTimelineCreationDialogController() {
        setPeriodName("");
        setMax_1(1000);
        setMax_2(1000);
        setMax_3(1000);
        setMin_1(500);
        setMin_2(500);
        setMin_3(500);
        setIntervalType_1("closed");
        setIntervalType_2("closed");
        setIntervalType_3("closed");
        timelines.clear();
    }
    
    public String getIntervalType_1() {
        return intervalType_1;
    }

    public void setIntervalType_1(String intervalType_1) {
        this.intervalType_1 = intervalType_1;
    }

    public String getIntervalType_2() {
        return intervalType_2;
    }

    public void setIntervalType_2(String intervalType_2) {
        this.intervalType_2 = intervalType_2;
    }

    public String getIntervalType_3() {
        return intervalType_3;
    }

    public void setIntervalType_3(String intervalType_3) {
        this.intervalType_3 = intervalType_3;
    }

    public int getMax_1() {
        return max_1;
    }

    public void setMax_1(int max_1) {
        this.max_1 = max_1;
    }

    public int getMin_1() {
        return min_1;
    }

    public void setMin_1(int min_1) {
        this.min_1 = min_1;
    }

    public int getMax_2() {
        return max_2;
    }

    public void setMax_2(int max_2) {
        this.max_2 = max_2;
    }

    public int getMin_2() {
        return min_2;
    }

    public void setMin_2(int min_2) {
        this.min_2 = min_2;
    }

    public int getMax_3() {
        return max_3;
    }

    public void setMax_3(int max_3) {
        this.max_3 = max_3;
    }

    public int getMin_3() {
        return min_3;
    }

    public void setMin_3(int min_3) {
        this.min_3 = min_3;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public ArrayList<Timeline> getTimelines() {
        return timelines;
    }

    public void setTimelines(ArrayList<Timeline> timelines) {
        this.timelines = timelines;
    }

    public void closeDialog() {
    }

    public void clear() {
        timelines.clear();
    }
    
    public void saveTimeline() {
        timelines.clear();
        if (getPeriodName().isEmpty()) {
            setPeriodName("unnamed");
        }
        Timeline tm_1 = new Timeline(getPeriodName() + "_1", getMin_1(), getMax_1(), intervalType_1);
        Timeline tm_2 = new Timeline(getPeriodName() + "_2", getMin_2(), getMax_2(), intervalType_2);
        Timeline tm_3 = new Timeline(getPeriodName() + "_3", getMin_3(), getMax_3(), intervalType_3);
        timelines.add(tm_1);
        timelines.add(tm_2);
        timelines.add(tm_3);
        FacesMessage message = new FacesMessage("Successful", " Timelines correctly inserted.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void periodName_Changing(ValueChangeEvent e) {
        periodName = e.getNewValue().toString();
    }

    public void intervalType_1_Changing(ValueChangeEvent e) {
        intervalType_1 = e.getNewValue().toString();
    }

    public void intervalType_1_Changed(AjaxBehaviorEvent event) {
        if (intervalType_1.equals("closed")) {
            if (max_1 == MAX_INTERVAL) {
                max_1 = MAX_INTERVAL - OFFSET_INTERVAL;
            }
            if (min_1 == MIN_INTERVAL) {
                min_1 = MIN_INTERVAL + OFFSET_INTERVAL;
            }
        } else if (intervalType_1.equals("left-open")) {
            min_1 = MIN_INTERVAL;
        } else if (intervalType_1.equals("right-open")) {
            max_1 = MAX_INTERVAL;
        }
    }

    public void onSlideEnd_1(SlideEndEvent event) {
        if (event.getValue() == MAX_INTERVAL) {
            intervalType_1 = "right-open";
        } else if (event.getValue() == MIN_INTERVAL) {
            intervalType_1 = "left-open";
        }
    }

    public void intervalType_2_Changing(ValueChangeEvent e) {
        intervalType_2 = e.getNewValue().toString();
    }

    public void intervalType_2_Changed(AjaxBehaviorEvent event) {
        if (intervalType_2.equals("closed")) {
            if (max_2 == MAX_INTERVAL) {
                max_2 = MAX_INTERVAL - OFFSET_INTERVAL;
            }
            if (min_2 == MIN_INTERVAL) {
                min_2 = MIN_INTERVAL + OFFSET_INTERVAL;
            }
        } else if (intervalType_2.equals("left-open")) {
            min_2 = MIN_INTERVAL;
        } else if (intervalType_2.equals("right-open")) {
            max_2 = MAX_INTERVAL;
        }
    }

    public void onSlideEnd_2(SlideEndEvent event) {
        if (event.getValue() == MAX_INTERVAL) {
            intervalType_2 = "right-open";
        } else if (event.getValue() == MIN_INTERVAL) {
            intervalType_2 = "left-open";
        }
    }

    public void intervalType_3_Changing(ValueChangeEvent e) {
        intervalType_3 = e.getNewValue().toString();
    }

    public void intervalType_3_Changed(AjaxBehaviorEvent event) {
        if (intervalType_3.equals("closed")) {
            if (max_3 == MAX_INTERVAL) {
                max_3 = MAX_INTERVAL - OFFSET_INTERVAL;
            }
            if (min_3 == MIN_INTERVAL) {
                min_3 = MIN_INTERVAL + OFFSET_INTERVAL;
            }
        } else if (intervalType_3.equals("left-open")) {
            min_3 = MIN_INTERVAL;
        } else if (intervalType_3.equals("right-open")) {
            max_3 = MAX_INTERVAL;
        }
    }

    public void onSlideEnd_3(SlideEndEvent event) {
        if (event.getValue() == MAX_INTERVAL) {
            intervalType_3 = "right-open";
        } else if (event.getValue() == MIN_INTERVAL) {
            intervalType_3 = "left-open";
        }
    }
}
