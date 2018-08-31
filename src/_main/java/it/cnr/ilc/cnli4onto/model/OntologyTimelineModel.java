/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.model;

import it.cnr.ilc.cnli4onto.action.ontologyQuestions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.Order;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineGroup;
import org.primefaces.extensions.model.timeline.TimelineModel;

/**
 *
 * @author andrea
 */
@Named
@ViewScoped
public class OntologyTimelineModel {

    @Inject
    private transient ontologyQuestions ontologyQuestion;

    private TimelineModel model = new TimelineModel();
    private Date start;
    private Date end;
    private final int timelineStart = 0;
    private final int timelineEnd = 1500;

    @PostConstruct
    private void initialize() {
        Calendar s = Calendar.getInstance();
        s.set(Calendar.YEAR, timelineStart);
        Calendar e = Calendar.getInstance();
        e.set(Calendar.YEAR, timelineEnd);
        start = s.getTime();
        end = e.getTime();
    }

    private void setCurrentTimeline() {
//        model.clear();
//        if (ontologyQuestion.getResults().size() > 0) {
//            List<OntoResult> res = new ArrayList<OntoResult>();
//            res = ontologyQuestion.getResults();
//            for (OntoResult or : res) {
//                createTemporalEvent(or);
//            }
//        }
        model.clear();
        List<OntoResult> res = new ArrayList<OntoResult>();
        OntoResult o1 = new OntoResult();
        OntoResult o2 = new OntoResult();
        OntoResult o3 = new OntoResult();
        OntoResult o4 = new OntoResult();
        OntoResult o5 = new OntoResult();
        OntoResult o6 = new OntoResult();
        OntoResult o7 = new OntoResult();
        OntoResult o8 = new OntoResult();
        OntoResult o9 = new OntoResult();
        OntoResult o10 = new OntoResult();
        OntoResult o11 = new OntoResult();
        o1.setTempo("shame");
        o2.setTempo("shameful");
        o3.setTempo("scorn");
        o4.setTempo("to cut");
        o5.setTempo("obscenity");
        o6.setTempo("to become red");
        o7.setTempo("to move back");
        o8.setTempo("abasement");
        o9.setTempo("to trouble");
        o10.setTempo("rotten");
        o11.setTempo("to cover");
        res.add(o1);
        res.add(o2);
        res.add(o3);
        res.add(o4);
        res.add(o5);
        res.add(o6);
        res.add(o7);
        res.add(o8);
        res.add(o9);
        res.add(o10);
        res.add(o11);
        for (OntoResult or : res) {
            createTemporalEvent(or);
        }
    }

//    private void createTemporalEvent(OntoResult or) {
//        TimelineEvent event = null;
//        String subj = or.getTermine();
//        String pred = or.getRelazione();
//        String obj = or.getTermine_target();
//        String triple = subj + " " + pred + " " + obj;
//        if (or.getTempo().equals("always")) {
//            event = new TimelineEvent("Always", getMin(), getMax(), true, triple, "available");
//            model.add(event);
//        } else {
//            if (or.getTempo().contains("to")) {
//                event = new TimelineEvent(or.getTempo(), getYear(or.getTempo().split(" ")[1]), getYear(or.getTempo().split(" ")[3]), true, triple, "unavailable");
//                model.add(event);
//            } else {
//                event = new TimelineEvent(or.getTempo(), getYear(or.getTempo().split(" ")[1]), getYear("2500"), true, triple, "maybe");
//                model.add(event);
//            }
//        }
//    }
    private void createTemporalEvent(OntoResult or) {
        TimelineEvent event = null;
        String subj = or.getTermine();
        String pred = or.getRelazione();
        String obj = or.getTermine_target();
        if (or.getTempo().equals("shameful")) {
            event = new TimelineEvent("METONYMY (left-open interval)", getMin2(), getMax2(), true, "shameful (shame is caused by dishonour)", "maybe");
            model.add(event);
        }
        if (or.getTempo().equals("scorn")) {
            event = new TimelineEvent("METONYMY (left-open interval)", getMin3(), getMax3(), true, "scorn (shame is caused by scorn)", "available");
            model.add(event);
        }
        if (or.getTempo().equals("to cut")) {
            event = new TimelineEvent("METONYMY (left-open interval)", getMin4(), getMax4(), true, "to cut (shame is caused by amputation)", "available");
            model.add(event);
        }
        if (or.getTempo().equals("obscenity")) {
            event = new TimelineEvent("METONYMY (left-open interval)", getMin5(), getMax5(), true, "obscenity (shame is caused by nakedness)", "available");
            model.add(event);
        }
        if (or.getTempo().equals("to become red")) {
            event = new TimelineEvent("METONYMY", getMin6(), getMax6(), true, "to become red (shame produces redness in the face)", "unavailable");
            model.add(event);
        }
        if (or.getTempo().equals("to move back")) {
            event = new TimelineEvent("METONYMY (left-open interval)", getMin7(), getMax7(), true, "to move back (shame produces motion backwards)", "maybe");
            model.add(event);
        }
        if (or.getTempo().equals("abasement")) {
            event = new TimelineEvent("METONYMY (left-open interval)", getMin8(), getMax8(), true, "abasement (shame produces motion downwards)", "maybe");
            model.add(event);
        }
        if (or.getTempo().equals("to trouble")) {
            event = new TimelineEvent("METONYMY", getMin9(), getMax9(), true, "to trouble (shame produces mental distress)", "unavailable");
            model.add(event);
        }
        if (or.getTempo().equals("rotten")) {
            event = new TimelineEvent("METONYMY (left-open interval)", getMin10(), getMax10(), true, "rotten (shame produces rottenness)", "maybe");
            model.add(event);
        }
        if (or.getTempo().equals("to cover")) {
            event = new TimelineEvent("METAPHOR (left-open interval)", getMin11(), getMax11(), true, "to cover (shame is a cover)", "maybe");
            model.add(event);
        }
    }

    private Date getYear(String year) {
        Calendar c = Calendar.getInstance();
        String y = year.replace("300b.c.", "-300");
        c.set(Calendar.YEAR, Integer.parseInt(y));
        return c.getTime();
    }

    public TimelineModel getModel() {
        setCurrentTimeline();
        return model;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Date getMax() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1500);
        return max.getTime();
    }

    public Date getMin() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    ////////////////////
    public Date getMax1() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1150);
        return max.getTime();
    }

    public Date getMin1() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax2() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1150);
        return max.getTime();
    }

    public Date getMin2() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax3() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1150);
        return max.getTime();
    }

    public Date getMin3() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax4() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1050);
        return max.getTime();
    }

    public Date getMin4() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax5() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 950);
        return max.getTime();
    }

    public Date getMin5() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax6() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1050);
        return max.getTime();
    }

    public Date getMin6() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 950);
        return min.getTime();
    }

    public Date getMax7() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1150);
        return max.getTime();
    }

    public Date getMin7() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax8() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1150);
        return max.getTime();
    }

    public Date getMin8() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax9() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1050);
        return max.getTime();
    }

    public Date getMin9() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 950);
        return min.getTime();
    }

    public Date getMax10() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 1150);
        return max.getTime();
    }

    public Date getMin10() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    public Date getMax11() {
        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 950);
        return max.getTime();
    }

    public Date getMin11() {
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 0);
        return min.getTime();
    }

    /////////////////////////////////
}

//
//        String[] NAMES = new String[]{"FIRMAMENTUM - Synonym - SPHAERA_STELLARUM_FIXARUM",
//            "FIRMAMENTUM - Synonym - PRIMUM_MOBILE",
//            "FIRMAMENTUM - denotes - EIGHTH_SPHERE"};
//        // create timeline model  
//        model = new TimelineModel();
//
//        for (String name : NAMES) {
//
//            Calendar _s = Calendar.getInstance();
//            _s.set(Calendar.YEAR, -300);
//            Calendar _e = Calendar.getInstance();
//            _e.set(Calendar.YEAR, 152);
//
//            Date _start = _s.getTime();
//            Date _end = _e.getTime();
//
//            long r = Math.round(Math.random() * 2);
//            String availability = (r == 0 ? "Unavailable" : (r == 1 ? "Available" : "Maybe"));
//
//            // create an event with content, start / end dates, editable flag, group name and custom style class  
//            TimelineEvent event = new TimelineEvent("Always", _start, _end, true, name, "available");
//            model.add(event);
