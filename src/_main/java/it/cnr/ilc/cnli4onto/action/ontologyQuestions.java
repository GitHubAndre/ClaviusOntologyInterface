///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package it.cnr.ilc.cnli4onto.action;
//
//import com.hp.hpl.jena.ontology.OntModel;
//import com.hp.hpl.jena.query.Query;
//import com.hp.hpl.jena.query.QueryExecution;
//import com.hp.hpl.jena.query.QueryExecutionFactory;
//import com.hp.hpl.jena.query.QueryFactory;
//import com.hp.hpl.jena.query.QuerySolution;
//import com.hp.hpl.jena.query.ResultSet;
//import it.cnr.ilc.cnli4onto.model.OntoResult;
//import it.cnr.ilc.cnli4onto.model.OntologyModel;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.faces.event.AjaxBehaviorEvent;
//import javax.faces.event.ValueChangeEvent;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import static org.apache.commons.lang3.StringUtils.isAllUpperCase;
//import org.primefaces.event.FileUploadEvent;
//
///**
// *
// * @author andrea
// */
//@Named
//@ViewScoped
//public class ontologyQuestions implements Serializable {
//
//    @PostConstruct
//    private void loadPlotinoModel() {
//        try {
//            res.clear();
//            currentQuestion = 0;
//            initializeQuestion_1();
//            initializeQuestion_3();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        csvProcessing(event);
//    }
//    
//    private void csvProcessing(FileUploadEvent event) {
//        try {
//            InputStream csv = event.getFile().getInputstream();
//        } catch (IOException ex) {
//            Logger.getLogger(ontologyQuestions.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    
//    
//    static public class ColumnModel implements Serializable {
//
//        private String header;
//        private String property;
//
//        public ColumnModel(String header, String property) {
//            this.header = header;
//            this.property = property;
//        }
//
//        public String getHeader() {
//            return header;
//        }
//
//        public String getProperty() {
//            return property;
//        }
//    }
//
//    public List<ColumnModel> getColumns() {
//        return columns;
//    }
//
//    public void createDynamicColumns(String template) {
//        String[] columnKeys = template.split(" ");
//        columns.clear();
//        for (String columnKey : columnKeys) {
//            String key = columnKey.trim();
//            if (VALID_COLUMN_KEYS.contains(key)) {
//                columns.add(new ColumnModel(getColumnTitle(columnKey),
//                        columnKey));
//            }
//        }
//    }
//
////    private String getColumnTitle(String col) {
////        if (col.equals("termine")) {
////            return "SOURCE TERM / CONCEPT";
////        } else if (col.equals("classe")) {
////            return "TYPE ONTOLOGIQUE";
////        } else if (col.equals("inferita")) {
////            return "INFERRED";
////        } else if (col.equals("relazione")) {
////            return "RELATION";
////        } else if (col.equals("termine_target")) {
////            return "TARGET TERM / CONCEPT ";
////        } else if (col.equals("classe_target")) {
////            return "TYPE ONTOLOGIQUE";
////        } else if (col.equals("tempo")) {
////            return "TEMPORAL EXISTANCE";
////        } else {
////            return "TEMPORAL EXISTANCE";
////        }
////    }
//    private String getColumnTitle(String col) {
//        if (col.equals("termine")) {
//            return "EVENT NAME";
//        } else if (col.equals("classe")) {
//            return "TYPE ONTOLOGIQUE";
//        } else if (col.equals("inferita")) {
//            return "VERB - Stripped Form";
//        } else if (col.equals("relazione")) {
//            return "GREEK TEXT";
//        } else if (col.equals("termine_target")) {
//            return "VERB - Lemma";
//        } else if (col.equals("classe_target")) {
//            return "TYPE ONTOLOGIQUE";
//        } else if (col.equals("tempo")) {
//            return "TEMPORAL INFO";
//        } else {
//            return "TEMPORAL EXISTANCE";
//        }
//    }
//
//    @Inject
//    private transient OntologyModel ontologyModel;
//
//    private final String NAMESPACES = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
//            + "PREFIX claviusAstro: <" + claviusAstroNamespace + "> "
//            + "PREFIX claviusSkos: <" + claviusSkosNamespace + "> "
//            + "PREFIX timeEvent: <http://www.intelligence.tuc.gr/2011/timeEvents#> "
//            + "PREFIX time: <http://www.w3.org/2006/time#> "
//            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
//            + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "
//            + "PREFIX owl: <http://www.w3.org/2002/07/owl#> ";
//    private static final String claviusSkosNamespace = "http://www.semanticweb.org/silvia/ontologies/2016/1/untitled-ontology-32#";
//    private static final String claviusAstroNamespace = "http://www.semanticweb.org/ontologies/2013/8/Ontology1378394921444.owl#";
//    private String query_1_param_1 = "";
//
//    private String columnTemplateQ1 = "termine relazione termine_target tempo";
//    private String columnTemplateQ4 = "termine relazione termine_target";
//
//    private String columnTemplateODY = "termine relazione termine_target inferita tempo";
//
//    private Date start;
//    private Date end;
//
//    private String query_2_param_1 = "";
//    private String query_2_param_2 = "";
//    private String query_2_param_3 = "";
//    private String query_3_param_1 = "";
//    private String query_3_param_2 = "";
//    private String query_4_param_1 = "";
//    private String query_4_param_2 = "";
//    private String query_4_param_3 = "";
//    private String query_4_param_4 = "";
//    private String query_4_param_5 = "";
//    private static Map<String, String> instancesOf_q1_p1 = new LinkedHashMap<String, String>();
//    private static Map<String, String> instancesOf_q2_p1 = new LinkedHashMap<String, String>();
//    private static Map<String, String> instancesOf_q3_p1 = new LinkedHashMap<String, String>();
//    private static Map<String, String> instancesOf_q4_p2 = new LinkedHashMap<String, String>();
//    private static Map<String, String> instancesOf_q4_p3 = new LinkedHashMap<String, String>();
//    private static Map<String, String> objRelationMap = new LinkedHashMap<String, String>();
//    private List<ColumnModel> columns = new ArrayList<ColumnModel>();
//    private final static List<String> VALID_COLUMN_KEYS = Arrays.asList(
//            "termine", "relazione", "tratto", "classe", "valore",
//            "classe_target", "termine_target", "inferita", "tempo");
//    private String columnTemplate_1_relazione = "termine relazione termine_target inferita";
//    // private String columnTemplate_1_relazione =
//    // "relazione termine classe tipo";
//    private String columnTemplate_1_tratto = "relazione termine_target inferita";
//    private String columnTemplate_2 = "termine classe inferita";
//    // private String columnTemplate_3 = "termine classe tipo";
//    private String columnTemplate_3 = "termine relazione termine_target inferita";
//    private String columnTemplate_4 = "termine classe inferita";
//    private static List<OntoResult> res = new ArrayList<OntoResult>();
//
//    private int currentQuestion = 0;
//
//    // vale anche per la question 2
//    public void initializeQuestion_1() throws IOException {
//        String queryString = null;
//        String inst = null;
//        instancesOf_q1_p1.clear();
//        instancesOf_q2_p1.clear();
//
//        // prendo i termini della parte statica
//        queryString = NAMESPACES
//                + "SELECT ?i WHERE { ?class rdfs:subClassOf* claviusAstro:TERM . ?i rdf:type ?class } ORDER BY ?i";
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getStaticModel());
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            inst = binding.get("i").toString().split("#")[1];
//            instancesOf_q1_p1.put(inst, inst);
//            instancesOf_q2_p1.put(inst, inst);
//        }
//
//        // prendo i termini della parte dinamica
//        queryString = NAMESPACES
//                + "SELECT ?c WHERE { ?c skos:broader claviusSkos:TERM } ORDER BY ?c";
//        query = QueryFactory.create(queryString);
//        qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            inst = binding.get("c").toString().split("#")[1];
//            if (!inst.equals("TERM") && !inst.equals("CONCEPT")) {
//                instancesOf_q1_p1.put(inst, inst);
//                instancesOf_q2_p1.put(inst, inst);
//            }
//        }
//        qe.close();
//
//    }
//
//    public void initializeQuestion_3() throws IOException {
//        String queryString = null;
//        String inst = null;
//        instancesOf_q3_p1.clear();
//        instancesOf_q4_p2.clear();
//        instancesOf_q4_p3.clear();
//
//        // prendo i termini della parte statica
//        queryString = NAMESPACES
//                + "SELECT ?i WHERE { ?class rdfs:subClassOf* claviusAstro:CONCEPT . ?i rdf:type ?class } ORDER BY ?i";
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getStaticModel());
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            inst = binding.get("i").toString().split("#")[1];
//            instancesOf_q3_p1.put(inst, inst);
//            instancesOf_q4_p2.put(inst, inst);
//            instancesOf_q4_p3.put(inst, inst);
//        }
//
//        // prendo i termini della parte dinamica
//        queryString = NAMESPACES
//                + "SELECT ?c WHERE { ?c skos:broader* claviusSkos:CONCEPT } ORDER BY ?c";
//        query = QueryFactory.create(queryString);
//        qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            inst = binding.get("c").toString().split("#")[1];
//            if (!inst.startsWith("Ev") && !inst.equals("CONCEPT")) {
//                instancesOf_q3_p1.put(inst, inst);
//                instancesOf_q4_p2.put(inst, inst);
//                instancesOf_q4_p3.put(inst, inst);
//            }
//        }
//        qe.close();
//
//    }
//
//    public String getQuery_4_param_4() {
//        return query_4_param_4;
//    }
//
//    public void setQuery_4_param_4(String query_4_param_4) {
//        this.query_4_param_4 = query_4_param_4;
//    }
//
//    public String getQuery_4_param_5() {
//        return query_4_param_5;
//    }
//
//    public void setQuery_4_param_5(String query_4_param_5) {
//        this.query_4_param_5 = query_4_param_5;
//    }
//
//    public String getQuery_4_param_1() {
//        return query_4_param_1;
//    }
//
//    public void setQuery_4_param_1(String query_4_param_1) {
//        this.query_4_param_1 = query_4_param_1;
//    }
//
//    public String getQuery_4_param_2() {
//        return query_4_param_2;
//    }
//
//    public void setQuery_4_param_2(String query_4_param_2) {
//        this.query_4_param_2 = query_4_param_2;
//    }
//
//    public String getQuery_4_param_3() {
//        return query_4_param_3;
//    }
//
//    public void setQuery_4_param_3(String query_4_param_3) {
//        this.query_4_param_3 = query_4_param_3;
//    }
//
//    public String getQuery_1_param_1() {
//        return query_1_param_1;
//    }
//
//    public void setQuery_1_param_1(String query_1_param_1) {
//        this.query_1_param_1 = query_1_param_1;
//    }
//
//    public String getQuery_3_param_1() {
//        return query_3_param_1;
//    }
//
//    public void setQuery_3_param_1(String query_3_param_1) {
//        this.query_3_param_1 = query_3_param_1;
//    }
//
//    public String getQuery_3_param_2() {
//        return query_3_param_2;
//    }
//
//    public void setQuery_3_param_2(String query_3_param_2) {
//        this.query_3_param_2 = query_3_param_2;
//    }
//
//    public String getQuery_2_param_1() {
//        return query_2_param_1;
//    }
//
//    public void setQuery_2_param_1(String query_2_param_1) {
//        this.query_2_param_1 = query_2_param_1;
//    }
//
//    public String getQuery_2_param_2() {
//        return query_2_param_2;
//    }
//
//    public void setQuery_2_param_2(String query_2_param_2) {
//        this.query_2_param_2 = query_2_param_2;
//    }
//
//    public String getQuery_2_param_3() {
//        return query_2_param_3;
//    }
//
//    public void setQuery_2_param_3(String query_2_param_3) {
//        this.query_2_param_3 = query_2_param_3;
//    }
//
//    public void query_4_relTypeChanged(ValueChangeEvent e) {
//        query_4_param_1 = e.getNewValue().toString();
//    }
//
//    public void q_4_p_1Changed(AjaxBehaviorEvent event) {
//        String queryString = null;
//        String inst = null;
//        instancesOf_q4_p2.clear();
//        instancesOf_q4_p3.clear();
//
//        if (query_4_param_1.equals("semantic")) {
//            // prendo i termini della parte statica
//            queryString = NAMESPACES
//                    + "SELECT ?i WHERE { ?class rdfs:subClassOf* claviusAstro:CONCEPT . ?i rdf:type ?class } ORDER BY ?i";
//            Query query = QueryFactory.create(queryString);
//            QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getStaticModel());
//            for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                QuerySolution binding = rs.nextSolution();
//                inst = binding.get("i").toString().split("#")[1];
//                instancesOf_q4_p2.put(inst, inst);
//                instancesOf_q4_p3.put(inst, inst);
//            }
//
//            // prendo i termini della parte dinamica
//            queryString = NAMESPACES
//                    + "SELECT ?c WHERE { ?c skos:broader* claviusSkos:CONCEPT } ORDER BY ?c";
//            query = QueryFactory.create(queryString);
//            qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//            for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                QuerySolution binding = rs.nextSolution();
//                inst = binding.get("c").toString().split("#")[1];
//                if (!inst.startsWith("Ev") && !inst.equals("CONCEPT")) {
//                    instancesOf_q4_p2.put(inst, inst);
//                    instancesOf_q4_p3.put(inst, inst);
//                }
//            }
//            qe.close();
//        } else {
//            // prendo i termini della parte statica
//            queryString = NAMESPACES
//                    + "SELECT ?i WHERE { ?class rdfs:subClassOf* claviusAstro:TERM . ?i rdf:type ?class } ORDER BY ?i";
//            Query query = QueryFactory.create(queryString);
//            QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getStaticModel());
//            for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                QuerySolution binding = rs.nextSolution();
//                inst = binding.get("i").toString().split("#")[1];
//                instancesOf_q4_p2.put(inst, inst);
//                instancesOf_q4_p3.put(inst, inst);
//            }
//            // prendo i termini della parte dinamica
//            queryString = NAMESPACES
//                    + "SELECT ?c WHERE { ?c skos:broader claviusSkos:TERM } ORDER BY ?c";
//            query = QueryFactory.create(queryString);
//            qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//            for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                QuerySolution binding = rs.nextSolution();
//                inst = binding.get("c").toString().split("#")[1];
//                if (!inst.equals("TERM") && !inst.equals("CONCEPT")) {
//                    instancesOf_q4_p2.put(inst, inst);
//                    instancesOf_q4_p3.put(inst, inst);
//                }
//            }
//            qe.close();
//        }
//    }
//
//    public void query_4_relTimeChanged(ValueChangeEvent e) {
//        query_4_param_4 = e.getNewValue().toString();
//    }
//
//    public Map<String, String> getInstances_q1_p1() {
//        return instancesOf_q1_p1;
//    }
//
//    public Map<String, String> getInstances_q2_p1() {
//        return instancesOf_q2_p1;
//    }
//
//    public Map<String, String> getInstances_q3_p1() {
//        return instancesOf_q3_p1;
//    }
//
//    public Map<String, String> getInstances_q4_p2() {
//        return instancesOf_q4_p2;
//    }
//
//    public Map<String, String> getInstances_q4_p3() {
//        return instancesOf_q4_p3;
//    }
//
//    public Map<String, String> getObjRelations() {
//        return objRelationMap;
//    }
//
//    private Map<String, String> updateSPARQLQueryResults(String queryString, OntModel m, String ns, String filter) {
//        Map<String, String> Map = new LinkedHashMap<String, String>();
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qe = QueryExecutionFactory.create(query, m);
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            String relazione, oggetto;
//            if (filter.equals("relobj")) {
//                relazione = binding.get("relazione").toString();
//                oggetto = binding.get("oggetto").toString();
//                if ((isValidEntity(relazione, ns)) && (!relazione.contains("Definition")) && (!relazione.contains("definition")) && (!oggetto.contains("TERM"))) {
//                    Map.put(oggetto.split("#")[1], relazione.split("#")[1]);
//                }
//            } else {
//                if (filter.equals("obj")) {
//                    oggetto = binding.get("oggetto").toString();
//                    Map.put(oggetto.split("#")[1], oggetto.split("#")[1]);
//                }
//            }
//        }
//        return Map;
//    }
//
//    private boolean isValidEntity(String entity, String ns) {
//        if (entity.contains(ns) || (entity.contains("broader"))) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void runQuestion_1() throws IOException {
//        currentQuestion = 1;
//        String queryString;
//        res.clear();
//        String qStrip = query_1_param_1.replaceAll("_", "");
//        // BARBATRUCCO: se il termine è tutto maiuscolo è dinamico altrimenti è statico
//        if (isAllUpperCase(qStrip)) {
//            // query al modello dinamico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione ?oggetto WHERE { claviusSkos:" + query_1_param_1 + " ?relazione ?oggetto }";
//            System.out.println(queryString);
//            Map<String, String> resMap = updateSPARQLQueryResults(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "relobj");
//            for (String key : resMap.keySet()) {
//                System.out.println("chiave: " + key + " valore: " + resMap.get(key));
//                if (key.startsWith("Ev")) {
//                    OntoResult or = new OntoResult();
//                    queryString = NAMESPACES
//                            + "SELECT ?oggetto WHERE { claviusSkos:" + key + " claviusSkos:" + resMap.get(key) + " ?oggetto }";
//                    System.out.println(queryString);
//                    Map<String, String> resMapEvent = updateSPARQLQueryResults(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "obj");
//                    for (String k : resMapEvent.keySet()) {
//                        or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                        or.setTermine_target(k);
//                    }
//                    or.setTermine(query_1_param_1);
//                    queryString = NAMESPACES
//                            + "SELECT ?bg ?ed WHERE { claviusSkos:" + key + " timeEvent:during ?int . ?int time:hasBeginning ?b . "
//                            + "?int time:hasEnd ?e . ?b time:inXSDDateTime ?bg . ?e time:inXSDDateTime ?ed }";
//                    Query query = QueryFactory.create(queryString);
//                    QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//                    for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                        QuerySolution binding = rs.nextSolution();
//                        String start = binding.get("bg").toString();
//                        String end = binding.get("ed").toString();
//                        if ((end.equals("open")) || (end.contains("999"))) {
//                            or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " on");
//                        } else {
//                            or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " to " + end.split("T00")[0].split("-")[0]);
//                        }
//                    }
//                    res.add(or);
//                } else {
//                    OntoResult or = new OntoResult();
//                    or.setTermine(query_1_param_1);
//                    or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                    or.setTermine_target(key);
//                    or.setTempo("always");
//                    res.add(or);
//                }
//            }
//        } else {
//            // query al modello statico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione ?oggetto "
//                    + "WHERE { claviusAstro:" + query_1_param_1 + " ?relazione ?oggetto }";
//            System.out.println(queryString);
//            Map<String, String> resMap = updateSPARQLQueryResults(queryString, ontologyModel.getStaticModel(), claviusAstroNamespace, "relobj");
//            for (String key : resMap.keySet()) {
//                OntoResult or = new OntoResult();
//                or.setTermine(query_1_param_1);
//                or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                or.setTermine_target(key);
//                or.setTempo("always");
//                res.add(or);
//            }
//        }
//
//        // TOGLIERE //
//        res.clear();
//
////        OntoResult orz = new OntoResult();
////        orz.setTempo("DAY39");
////        res.add(orz);
////        OntoResult ory = new OntoResult();
////        ory.setTempo("DAY38");
////        res.add(ory);
////        OntoResult orx = new OntoResult();
////        orx.setTempo("DAY1");
////        res.add(orx);
//        OntoResult or3 = new OntoResult();
//        or3.setTermine("telemachusThreateningEvent");
//        or3.setRelazione("Τηλέμαχος δ᾽ ἑτέρωθεν ἀπειλήσας ἐγεγώνει");
//        or3.setInferita("epic perf. with pres. signf., pluperf. used as impf., to call out so as to be heard, ὅσσον τε γέγωνε βοήσασ as far as a man can make himself heard by shouting, Od.:&mdash;c. dat. pers. to cry out to, id=Od.");
//        or3.setTermine_target("γέγωνα");
//        or3.setTempo("Day 39 - after seeingEvent, after telemachusThreateningEvent");
//        res.add(or3);
//        OntoResult or2 = new OntoResult();
//        or2.setTermine("telemachusGoingForthEvent");
//        or2.setRelazione("Τηλέμαχος δ᾽ ἄρ᾽ ἔπειτα διὲκ μεγάροιο βεβήκει");
//        or2.setInferita("intr. to walk, step, properly of motion on foot, ποσσὶ or ποσὶ βαίνειν Hom., etc.; c. inf. in Hom., βῆ ἰέναι, βῆ ἰέμεν set out to go, went his way, Il.; βῆ θέειν started to run, id=Il.; βῆ δ' ἐλάαν, id=Il., etc.:&mdash;c. acc. loci, Soph.; and with all Preps. implying motion, as, ἐπὶ νηὸσ ἔβαινεν was going on board ship, Od.; ἐφ' ἵππων βάντεσ having mounted the chariot, id=Od.; βαίνειν δι' αἵματοσ to wade through blood, Eur., etc.");
//        or2.setTermine_target("βαίνω");
//        or2.setTempo("Day 38 - after seeingEvent, before telemachusThreateningEvent");
//        res.add(or2);
//        OntoResult or = new OntoResult();
//        or.setTermine("seeingEvent");
//        or.setRelazione("τὴν δὲ πολὺ πρῶτος ἴδε Τηλέμαχος θεοειδής");
//        or.setInferita("to see, perceive, behold, Hom., etc.; after a Noun, θαῦμα ἰδέσθαι a marvel to behold, Il.; οἰκτρὸσ ἰδεῖν Aesch.");
//        or.setTermine_target("εἶδον");
//        or.setTempo("Day 1 - before telemachusGoingForthEvent, before telemachusThreateningEvent");
//        res.add(or);
//        // ---------//
////        createDynamicColumns(columnTemplateQ1);
//        createDynamicColumns(columnTemplateODY);
//    }
//
//    public void runQuestion_2() throws IOException {
//        currentQuestion = 2;
//        String queryString;
//        res.clear();
//        String qStrip = query_2_param_1.replaceAll("_", "");
//
//        // BARBATRUCCO: se il termine è tutto maiuscolo è dinamico altrimenti è statico
//        if (isAllUpperCase(qStrip)) {
//            // query al modello dinamico
//            queryString = NAMESPACES
//                    + "SELECT ?oggetto WHERE { claviusSkos:" + query_2_param_1 + " claviusSkos:denotes ?oggetto }";
//            Map<String, String> resMap = updateSPARQLQueryResults(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "obj");
//            for (String key : resMap.keySet()) {
//                OntoResult or = new OntoResult();
//                if (key.startsWith("Ev")) {
//                    queryString = NAMESPACES
//                            + "SELECT ?oggetto WHERE { claviusSkos:" + key + " claviusSkos:denotes ?oggetto }";
//                    Map<String, String> resMapConcepts = updateSPARQLQueryResults(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "obj");
//                    for (String k : resMapConcepts.keySet()) {
//                        or.setTermine(query_2_param_1);
//                        or.setRelazione("denotes");
//                        or.setTermine_target(k);
//                        queryString = NAMESPACES
//                                + "SELECT ?bg ?ed WHERE { claviusSkos:" + key + " timeEvent:during ?int . ?int time:hasBeginning ?b . "
//                                + "?int time:hasEnd ?e . ?b time:inXSDDateTime ?bg . ?e time:inXSDDateTime ?ed }";
//                        Query query = QueryFactory.create(queryString);
//                        QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//                        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                            QuerySolution binding = rs.nextSolution();
//                            String start = binding.get("bg").toString();
//                            String end = binding.get("ed").toString();
//                            if ((end.equals("open")) || (end.contains("999"))) {
//                                or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " on");
//                            } else {
//                                or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " to " + end.split("T00")[0].split("-")[0]);
//                            }
//                        }
//                        res.add(or);
//                        getConceptRelations(k);
//                    }
//                } else {
//                    // eventuali concetti denotati per sempre
//                    or.setTermine(query_2_param_1);
//                    or.setRelazione("denotes");
//                    or.setTermine_target(key);
//                    or.setTempo("always");
//                    res.add(or);
//                    getConceptRelations(key);
//                }
//            }
//
//        } else {
//            // query al modello statico
//            queryString = NAMESPACES
//                    + "SELECT ?oggetto "
//                    + "WHERE { claviusAstro:" + query_2_param_1 + " claviusAstro:denotes ?oggetto }";
//            Map<String, String> resMap = updateSPARQLQueryResults(queryString, ontologyModel.getStaticModel(), claviusAstroNamespace, "obj");
//            for (String key : resMap.keySet()) {
//                queryString = NAMESPACES
//                        + "SELECT ?relazione ?oggetto "
//                        + "WHERE { claviusAstro:" + key + " ?relazione ?oggetto }";
//                Map<String, String> resMapRelations = updateSPARQLQueryResults(queryString, ontologyModel.getStaticModel(), claviusAstroNamespace, "relobj");
//                for (String k : resMapRelations.keySet()) {
//                    OntoResult or = new OntoResult();
//                    or.setTermine(key + "(denoted by " + query_2_param_1 + ")");
//                    or.setRelazione(resMapRelations.get(k).contains("broader") ? "subClassOf" : resMapRelations.get(k));
//                    or.setTermine_target(k);
//                    or.setTempo("always");
//                    res.add(or);
//                    getConceptRelations(k);
//                }
//            }
//        }
//        // VISUALIZZAIZONE RISULTATI
//        createDynamicColumns(columnTemplateQ1);
//    }
//
//    private void getConceptRelations(String concept) {
//        String queryString;
//        String qStrip = concept.replaceAll("_", "");
//
//        // BARBATRUCCO: se il termine è tutto maiuscolo è dinamico altrimenti è statico
//        if (isAllUpperCase(qStrip)) {
//            // query al modello dinamico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione ?oggetto WHERE { claviusSkos:" + concept + " ?relazione ?oggetto }";
//            Map<String, String> resMap = updateSPARQLQueryResults(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "relobj");
//            for (String key : resMap.keySet()) {
//                if (key.startsWith("Ev")) {
//                    OntoResult or = new OntoResult();
//                    if (resMap.get(key).contains("broader")) {
//                        queryString = NAMESPACES
//                                + "SELECT ?oggetto WHERE { claviusSkos:" + key + " skos:broader ?oggetto }";
//                    } else {
//                        queryString = NAMESPACES
//                                + "SELECT ?oggetto WHERE { claviusSkos:" + key + " claviusSkos:" + resMap.get(key) + " ?oggetto }";
//                    }
//                    Map<String, String> resMapEvent = updateSPARQLQueryResults(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "obj");
//                    for (String k : resMapEvent.keySet()) {
//                        or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                        or.setTermine_target(k);
//                    }
//                    or.setTermine(concept);
//                    queryString = NAMESPACES
//                            + "SELECT ?bg ?ed WHERE { claviusSkos:" + key + " timeEvent:during ?int . ?int time:hasBeginning ?b . "
//                            + "?int time:hasEnd ?e . ?b time:inXSDDateTime ?bg . ?e time:inXSDDateTime ?ed }";
//                    Query query = QueryFactory.create(queryString);
//                    QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//                    for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                        QuerySolution binding = rs.nextSolution();
//                        String start = binding.get("bg").toString();
//                        String end = binding.get("ed").toString();
//                        if ((end.equals("open")) || (end.contains("999"))) {
//                            or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " on");
//                        } else {
//                            or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " to " + end.split("T00")[0].split("-")[0]);
//                        }
//                    }
//                    res.add(or);
//                } else {
//                    OntoResult or = new OntoResult();
//                    or.setTermine(concept);
//                    or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                    or.setTermine_target(key);
//                    or.setTempo("always");
//                    res.add(or);
//                }
//            }
//        } else {
//            // query al modello statico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione ?oggetto "
//                    + "WHERE { claviusAstro:" + concept + " ?relazione ?oggetto }";
//            System.out.println(queryString);
//            Map<String, String> resMap = updateSPARQLQueryResults(queryString, ontologyModel.getStaticModel(), claviusAstroNamespace, "relobj");
//            for (String key : resMap.keySet()) {
//                OntoResult or = new OntoResult();
//                or.setTermine(concept);
//                or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                or.setTermine_target(key);
//                or.setTempo("always");
//                res.add(or);
//            }
//        }
//    }
//
//    private Map<String, String> updateSPARQLQueryResultsConceptsOnly(String queryString, OntModel m, String ns, String filter) {
//        Map<String, String> Map = new LinkedHashMap<String, String>();
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qe = QueryExecutionFactory.create(query, m);
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            String relazione, oggetto;
//            if (filter.equals("relobj")) {
//                relazione = binding.get("relazione").toString();
//                oggetto = binding.get("oggetto").toString();
//                if ((isValidEntityConceptsOnly(relazione, ns)) && (!relazione.contains("Definition") && (!relazione.contains("definition")))) {
//                    Map.put(oggetto.split("#")[1], relazione.split("#")[1]);
//                }
//            } else {
//                if (filter.equals("obj")) {
//                    oggetto = binding.get("oggetto").toString();
//                    Map.put(oggetto.split("#")[1], oggetto.split("#")[1]);
//                }
//            }
//        }
//        return Map;
//    }
//
//    private boolean isValidEntityConceptsOnly(String entity, String ns) {
//        if (entity.contains(ns) || entity.contains("skos")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void runQuestion_3() throws IOException {
//        currentQuestion = 3;
//        String queryString;
//        res.clear();
//        String qStrip = query_3_param_1.replaceAll("_", "");
//        // BARBATRUCCO: se il termine è tutto maiuscolo è dinamico altrimenti è statico
//        if (isAllUpperCase(qStrip)) {
//            // query al modello dinamico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione ?oggetto WHERE { claviusSkos:" + query_3_param_1 + " ?relazione ?oggetto }";
//            Map<String, String> resMap = updateSPARQLQueryResultsConceptsOnly(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "relobj");
//            for (String key : resMap.keySet()) {
//                System.out.println("chiave: " + key + " valore: " + resMap.get(key));
//                if (key.startsWith("Ev")) {
//                    OntoResult or = new OntoResult();
//                    if (resMap.get(key).equals("broader")) {
//                        queryString = NAMESPACES
//                                + "SELECT ?oggetto WHERE { claviusSkos:" + key + " skos:" + resMap.get(key) + " ?oggetto }";
//                    } else {
//                        queryString = NAMESPACES
//                                + "SELECT ?oggetto WHERE { claviusSkos:" + key + " claviusSkos:" + resMap.get(key) + " ?oggetto }";
//                    }
//                    Map<String, String> resMapEvent = updateSPARQLQueryResultsConceptsOnly(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "obj");
//                    for (String k : resMapEvent.keySet()) {
//                        or.setRelazione(resMap.get(key).equals("broader") ? "subClassOf" : resMap.get(key));
//                        or.setTermine_target(k);
//                    }
//                    or.setTermine(query_3_param_1);
//                    queryString = NAMESPACES
//                            + "SELECT ?bg ?ed WHERE { claviusSkos:" + key + " timeEvent:during ?int . ?int time:hasBeginning ?b . "
//                            + "?int time:hasEnd ?e . ?b time:inXSDDateTime ?bg . ?e time:inXSDDateTime ?ed }";
//                    Query query = QueryFactory.create(queryString);
//                    QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//                    for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//                        QuerySolution binding = rs.nextSolution();
//                        String start = binding.get("bg").toString();
//                        String end = binding.get("ed").toString();
//                        if ((end.equals("open")) || (end.contains("999"))) {
//                            or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " on");
//                        } else {
//                            or.setTempo("from " + (start.split("T00")[0].contains("300") ? "300b.c." : start.split("T00")[0].split("-")[0]) + " to " + end.split("T00")[0].split("-")[0]);
//                        }
//                    }
//                    if (!key.equals("CONCEPT")) {
//                        res.add(or);
//                    }
//                } else {
//                    OntoResult or = new OntoResult();
//                    or.setTermine(query_3_param_1);
//                    or.setRelazione(resMap.get(key).equals("broader") ? "subClassOf" : resMap.get(key));
//                    or.setTermine_target(key);
//                    or.setTempo("always");
//                    if (!key.equals("CONCEPT")) {
//                        res.add(or);
//                    }
//                }
//            }
//        } else {
//            // query al modello statico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione ?oggetto "
//                    + "WHERE { claviusAstro:" + query_3_param_1 + " ?relazione ?oggetto }";
//            System.out.println(queryString);
//            Map<String, String> resMap = updateSPARQLQueryResults(queryString, ontologyModel.getStaticModel(), claviusAstroNamespace, "relobj");
//            for (String key : resMap.keySet()) {
//                OntoResult or = new OntoResult();
//                or.setTermine(query_3_param_1);
//                or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                or.setTermine_target(key);
//                or.setTempo("always");
//                res.add(or);
//            }
//        }
//        createDynamicColumns(columnTemplateQ1);
//    }
//
//    public void runQuestion_4() throws IOException {
//        currentQuestion = 4;
//        String queryString;
//        res.clear();
//
//        String qStrip = query_4_param_2.replaceAll("_", "");
//        // BARBATRUCCO: se il termine è tutto maiuscolo è dinamico altrimenti è statico
//        if (isAllUpperCase(qStrip)) {
//            // query al modello dinamico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione WHERE { claviusSkos:" + query_4_param_2 + " ?relazione claviusSkos:" + query_4_param_3 + " }";
//            Map<String, String> resMap = updateSPARQLQueryResultsTemporal(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "rel");
//            for (String key : resMap.keySet()) {
//                OntoResult or = new OntoResult();
//                or.setRelazione(key.contains("broader") ? "subClassOf" : key);
//                or.setTermine_target(query_4_param_3);
//                or.setTermine(query_4_param_2);
//                res.add(or);
//            }
//            queryString = NAMESPACES
//                    + "SELECT ?relazione ?oggetto WHERE { claviusSkos:" + query_4_param_2 + " ?relazione ?oggetto }";
//            Map<String, String> resMapEv = updateSPARQLQueryResults(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "relobj");
//            for (String key : resMapEv.keySet()) {
//                if (key.startsWith("Ev")) {
//                    queryString = NAMESPACES
//                            + "SELECT ?relazione WHERE { claviusSkos:" + key + " ?relazione claviusSkos:" + query_4_param_3 + " }";
//                    Map<String, String> resMapRelEv = updateSPARQLQueryResultsTemporal(queryString, ontologyModel.getDynModel(), claviusSkosNamespace, "rel");
//                    for (String k : resMapRelEv.keySet()) {
//                        if (timeValidProperty(key)) {
//                            OntoResult or = new OntoResult();
//                            or.setRelazione(k.contains("broader") ? "subClassOf" : k);
//                            or.setTermine_target(query_4_param_3);
//                            or.setTermine(query_4_param_2);
//                            res.add(or);
//                        }
//                    }
//                }
//            }
//        } else {
//            // query al modello statico
//            queryString = NAMESPACES
//                    + "SELECT ?relazione WHERE { claviusAstro:" + query_4_param_2 + " ?relazione claviusAstro:" + query_4_param_3 + " }";
//            System.out.println(queryString);
//            Map<String, String> resMap = updateSPARQLQueryResultsTemporal(queryString, ontologyModel.getStaticModel(), claviusAstroNamespace, "rel");
//            for (String key : resMap.keySet()) {
//                OntoResult or = new OntoResult();
//                or.setTermine(query_4_param_2);
//                or.setRelazione(resMap.get(key).contains("broader") ? "subClassOf" : resMap.get(key));
//                or.setTermine_target(query_4_param_3);
//                res.add(or);
//            }
//        }
//        createDynamicColumns(columnTemplateQ4);
//    }
//
//    private boolean timeValidProperty(String timeEvent) {
//        String queryString = NAMESPACES
//                + "SELECT ?bg ?ed WHERE { claviusSkos:" + timeEvent + " timeEvent:during ?int . ?int time:hasBeginning ?b . "
//                + "?int time:hasEnd ?e . ?b time:inXSDDateTime ?bg . ?e time:inXSDDateTime ?ed }";
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qe = QueryExecutionFactory.create(query, ontologyModel.getDynModel());
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            String start = binding.get("bg").toString();
//            String end = binding.get("ed").toString();
//            if (end.contains("open")) {
//                end = "3000";
//                start = start.split("-01-01")[0];
//            } else {
//                start = end.split("-01-01")[0];
//                end = start.split("-01-01")[0];
//            }
//
//            int s = Integer.parseInt(start);
//            int e = Integer.parseInt(end);
//            int i = Integer.parseInt(query_4_param_5);
//            if (query_4_param_4.equals("before")) {
//                if (i <= s) {
//                    return false;
//                }
//                if ((i > s) && (i <= e)) {
//                    return true;
//                }
//                if (i > e) {
//                    return true;
//                }
//            }
//            if (query_4_param_4.equals("after")) {
//                if (i <= s) {
//                    return true;
//                }
//                if ((i > s) && (i <= e)) {
//                    return true;
//                }
//                if (i > e) {
//                    return false;
//                }
//            }
//            if (query_4_param_4.equals("in")) {
//                if (i < s) {
//                    return false;
//                }
//                if ((i >= s) && (i <= e)) {
//                    return true;
//                }
//                if (i > e) {
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    private Map<String, String> updateSPARQLQueryResultsTemporal(String queryString, OntModel m, String ns, String filter) {
//        Map<String, String> Map = new LinkedHashMap<String, String>();
//        Query query = QueryFactory.create(queryString);
//        QueryExecution qe = QueryExecutionFactory.create(query, m);
//        for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
//            QuerySolution binding = rs.nextSolution();
//            String relazione, oggetto;
//            if (filter.equals("relobj")) {
//                relazione = binding.get("relazione").toString();
//                oggetto = binding.get("oggetto").toString();
//                if ((isValidEntityConceptsOnly(relazione, ns)) && (!relazione.contains("Definition") && (!relazione.contains("definition")))) {
//                    Map.put(oggetto.split("#")[1], relazione.split("#")[1]);
//                }
//            } else {
//                if (filter.equals("rel")) {
//                    relazione = binding.get("relazione").toString();
//                    Map.put(relazione.split("#")[1], relazione.split("#")[1]);
//                }
//            }
//        }
//        return Map;
//    }
//
//    public List<OntoResult> getResults() {
//        return res;
//    }
//
//    public int getCurrentQuery() {
//        return currentQuestion;
//    }
//
//    public String getLowercased(String s) {
//        return s.toLowerCase();
//    }
//
//}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.action;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import it.cnr.ilc.cnli4onto.controller.exportController;
import it.cnr.ilc.cnli4onto.controller.timelineCreationDialogController;
import it.cnr.ilc.cnli4onto.model.LexiconModel;
import it.cnr.ilc.cnli4onto.model.OntoResult;
import it.cnr.ilc.cnli4onto.model.OntologyModel;
import it.cnr.ilc.cnli4onto.util.CSVImporter;
import it.cnr.ilc.cnli4onto.util.Lexicon;
import it.cnr.ilc.cnli4onto.util.Timeline;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import static org.apache.commons.lang3.StringUtils.isAllUpperCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author andrea
 */
@Named
@ViewScoped
public class ontologyQuestions implements Serializable {

    @Inject
    private transient timelineCreationDialogController timelines;

    @Inject
    private transient OntologyModel ontologyModel;

    private String fileStat;
    private UploadedFile file;
    private boolean timelineCreationCompleted, timelineModificationCompleted, 
            uploadAvailability, exportAvailability, importAvailability, timelineAvailability;

    public boolean isTimelineAvailability() {
        return timelineAvailability;
    }

    public void setTimelineAvailability(boolean timelineAvailability) {
        this.timelineAvailability = timelineAvailability;
    }

    public boolean isImportAvailability() {
        return importAvailability;
    }

    public void setImportAvailability(boolean importAvailability) {
        this.importAvailability = importAvailability;
    }

    public boolean isExportAvailability() {
        return exportAvailability;
    }

    public void setExportAvailability(boolean exportAvailability) {
        this.exportAvailability = exportAvailability;
    }
    private String lexiconTriples;
    private String excelName;

    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public OntologyModel getOntologyModel() {
        return ontologyModel;
    }

    public void setOntologyModel(OntologyModel ontologyModel) {
        this.ontologyModel = ontologyModel;
    }

    public boolean isUploadAvailability() {
        return uploadAvailability;
    }

    public void setUploadAvailability(boolean uploadAvailability) {
        this.uploadAvailability = uploadAvailability;
    }

    public boolean isUploadAvailable() {
        return uploadAvailability;
    }

    public void setUploadAvailable(boolean uploadAvailability) {
        this.uploadAvailability = uploadAvailability;
    }

    public timelineCreationDialogController getTimelines() {
        return timelines;
    }

    public void setTimelines(timelineCreationDialogController timelines) {
        this.timelines = timelines;
    }

    public String getLexiconTriples() {
        return lexiconTriples;
    }

    public void setLexiconTriples(String lexiconTriples) {
        this.lexiconTriples = lexiconTriples;
    }

    public boolean isTimelineModificationCompleted() {
        return timelineModificationCompleted;
    }

    public void setTimelineModificationCompleted(boolean timelineModificationCompleted) {
        this.timelineModificationCompleted = timelineModificationCompleted;
    }

    public boolean isTimelineCreationCompleted() {
        return timelineCreationCompleted;
    }

    public void setTimelineCreationCompleted(boolean timelineCreationCompleted) {
        this.timelineCreationCompleted = timelineCreationCompleted;
    }

    public String getFileStat() {
        return fileStat;
    }

    public void setFileStat(String fileStat) {
        this.fileStat = fileStat;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @PostConstruct
    public void init() {
        setFileStat("");
        setLexiconTriples("");
        setExcelName("");
        setTimelineCreationCompleted(false);
        setTimelineModificationCompleted(false);
        setUploadAvailable(false);
        setExportAvailability(false);
        setImportAvailability(true);
        setTimelineAvailability(false);
    }

    public void newInit() {
        setOntology();
        timelines.resetTimelineCreationDialogController();
        setFileStat("");
        setLexiconTriples("");
        setExcelName("");
        setTimelineCreationCompleted(false);
        setTimelineModificationCompleted(false);
        setUploadAvailable(false);
        setExportAvailability(false);
        setImportAvailability(true);
        setTimelineAvailability(false);
    }

    private void setOntology() {
        Set<OWLAxiom> axiomsToRemove = new HashSet<OWLAxiom>();
        for (OWLAxiom ax : ontologyModel.getOntology().getAxioms()) {
            axiomsToRemove.add(ax);
        }
        ontologyModel.getManager().removeAxioms(ontologyModel.getOntology(), axiomsToRemove);
        ontologyModel.initOnto();
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        setExcelName(event.getFile().getFileName());
        csvProcessing(event);
        setTimelineModificationCompleted(false);
        setTimelineCreationCompleted(true);
        setUploadAvailable(false);
        setExportAvailability(true);
        setImportAvailability(false);
        setTimelineAvailability(true);
    }

    private void csvProcessing(FileUploadEvent event) {
        UploadedFile csv = event.getFile();
        CSVImporter csvi = new CSVImporter(csv);
        setFileStat(getFileStat() + csvi.getStat());
        getLexicon(csvi);
    }

    public String getCsvStat() {
        if (!isTimelineCreationCompleted() || isTimelineModificationCompleted()) {
                getTimelineInfo();
        }
        return getFileStat();
    }

    private void getTimelineInfo() {
        ArrayList<Timeline> tm = timelines.getTimelines();
        if (tm.size() > 0) {
            String action;
            if (!isTimelineModificationCompleted()) {
                action = " created. <br/>";
            } else {
                action = " modified. <br/>";
            }
            setFileStat(getFileStat() + tm.get(0).getName().split("_")[0] + " period" + action);
            for (Timeline t : tm) {
                if (t.getIntervalType().equals("closed")) {
                    setFileStat(getFileStat() + t.getName() + " from " + t.getStartYear() + " to " + t.getEndYear() + action);
                } else if (t.getIntervalType().equals("left-open")) {
                    setFileStat(getFileStat() + t.getName() + " up to " + t.getEndYear() + action);
                } else if (t.getIntervalType().equals("right-open")) {
                    setFileStat(getFileStat() + t.getName() + " since " + t.getStartYear() + action);
                }
            }
            setFileStat(getFileStat() + "<br/>");
            setTimelineCreationCompleted(true);
            setTimelineModificationCompleted(true);
            setUploadAvailable(true);
        }
    }

    private void __getLexicon(CSVImporter csvi) {
//        LexiconModel lm = new LexiconModel();
        String s = "";
        for (Lexicon lex : csvi.getLexicalEntries()) {
            s = s + "Lexical entry: " + lex.getLexicalEntry() + "<br/>";
            s = s + "Lemmas: " + lex.getLemmas() + "<br/>";
            s = s + "Lexical root: " + lex.getLexicalRoot() + "<br/>";
            s = s + "Total freq.: " + lex.getTotalFreq() + "<br/>";
            s = s + "Corpus freq.: " + lex.getCorpusFreq() + "<br/>";
            s = s + "Collocation: " + lex.getCollocations() + "<br/>";
            s = s + "Ethimology: " + lex.getEthimology() + "<br/>";
            s = s + "Etimological theme: " + lex.getEtimologicalTheme() + "<br/>";
            s = s + "Dialectal distribution: " + lex.getTextual_dialectalDistribution() + "<br/>";
            s = s + "Semantic shift: " + lex.getSemanticShift() + "<br/>";
            s = s + "Temporal information: " + lex.getTemporalInformation() + "<br/>";
            s = s + "Meaning 1: " + lex.getMeaning_1() + "<br/>";
            s = s + "Meaning 2: " + lex.getMeaning_2() + "<br/>";
            s = s + "Meaning 3: " + lex.getMeaning_3() + "<br/>";
            s = s + "Meaning 4: " + lex.getMeaning_4() + "<br/>";
            s = s + "Temporal info 1: " + lex.getInterval_1() + "<br/>";
            s = s + "Temporal info 2: " + lex.getInterval_2() + "<br/>";
            s = s + "Temporal info 3: " + lex.getInterval_3() + "<br/>";
            s = s + "Temporal information: " + lex.getTemporalInformation() + "<br/>";
//            if (lex.getLexicalEntry() != null) {
//                lm.addLexiconEntryToModel(ontologyModel.getStaticModel(), lex);
//            }
        }
        writeLexicon(s);
    }

    private void writeLexicon(String s) {

//        setLexiconTriples(getLexiconTriples() + s);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ontologyModel.getStaticModel().write(baos, "TURTLE");
//        String cleanBaos;
//        String cleanBaos = ":REOD a owl:NamedIndividual , lemon:LexicalEntry , lemond:Expression ;<br/>"
//                + "	lemon:language \"ang\" ;<br/>"
//                + "	lemond:explex :AREODIAN_VB .<br/>"
//                + ":REUDH a owl:NamedIndividual , lemon:LexicalEntry , lemond:ReconstructedTerm ;<br/>"
//                + "	lemon:canonicalForm \"*reudh-\" ;<br/>"
//                + "	lemon:language \"PrIde\" ;<br/>"
//                + "	lexinfo:root :AREODIAN_VB .<br/>"
//                + "<br/>"
//                + ":AREODIAN_VB a owl:NamedIndividual , lemon:LexicalEntry ;<br/>"
//                + "	lemon:language \"ang\" ;<br/>"
//                + "	lemon:sense :sense_Red_AREODIAN_VB , :sense_Shame_AREODIAN_VB ;<br/>"
//                + "	wordnet:synset_member :SHAME_VB ;<br/>"
//                + "	anglo:corpusFrequency \"6\"^^xsd:integer ;<br/>"
//                + "	anglo:textualDistribution :glosses ;<br/>"
//                + "	anglo:totalFrequency \"6\"^^xsd:integer ;<br/>"
//                + "	lemond:lemma :LEM_AREODDIAN , :LEM_AREODIGEN , :LEM_AREODODE_VBD ;<br/>"
//                + "	lemond:lexexp :REOD ;<br/>"
//                + "	lexinfo:partOfSpeech lexinfo:Verb .<br/>"
//                + "<br/>"
//                + ":AREODIAN_VB_Red_to_Shame_sense a owl:NamedIndividual , lemond:SemanticShift ;<br/>"
//                + "	lemond:shiftDomain :REDNESS_IN_THE_FACE ;<br/>"
//                + "	lemond:shiftSource :sense_Red_AREODIAN_VB ;<br/>"
//                + "	lemond:shiftTarget :sense_Shame_AREODIAN_VB ;<br/>"
//                + "	lemond:shiftType sem:resultative_metonymy .<br/>"
//                + "<br/>"
//                + ":sense_Red_AREODIAN_VB a owl:NamedIndividual , lemond:LexicalpSense ;<br/>"
//                + "	lemon:reference dbpedia:Red ;<br/>"
//                + "	lemond:temporalExtent anglo:OE .<br/>"
//                + "<br/>"
//                + ":sense_Shame_AREODIAN_VB a owl:NamedIndividual , lemond:LexicalpSense ;<br/>"
//                + "	lemon:reference dbpedia:Shame ;<br/>"
//                + "	lemond:temporalExtent anglo:OE23 .<br/>"
//                + "<br/>"
//                + ":LEM_AREODDIAN a owl:NamedIndividual , lemon:LexicalEntry , lemond:Lemma ;<br/>"
//                + "	anglo:isLemmaOf :AREODIAN_VB .<br/>"
//                + ":LEM_AREODIGEN a owl:NamedIndividual , lemon:LexicalEntry , lemond:Lemma ;<br/>"
//                + "	anglo:isLemmaOf :AREODIAN_VB .<br/>"
//                + ":LEM_AREODODE_VBD a owl:NamedIndividual , lemon:LexicalEntry , lemond:Lemma ;<br/>"
//                + "	anglo:isLemmaOf :AREODIAN_VB ";
//        cleanBaos = baos.toString().replaceAll(">", "&gt;");
//        cleanBaos = cleanBaos.replaceAll("<", "&lt;");
//        cleanBaos = cleanBaos.replaceAll("<br/>", "<br/>");
//        setLexiconTriples(getLexiconTriples() + cleanBaos);
    }

    private void getLexicon(CSVImporter csvi) {

        LexiconModel lm = new LexiconModel(ontologyModel);
        for (Lexicon lex : csvi.getLexicalEntries()) {
            if (lex.getLexicalEntry() != null) {
                lm.addLexiconEntryToModel(lex);
            }
        }
        lexiconTriples = getPrettyFormat(getLexiconOutput());
    }

    private String getLexiconOutput() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ontologyModel.getManager().saveOntology(ontologyModel.getOntology(), baos);
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(ontologyQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return baos.toString();
    }

    private String getPrettyFormat(String s) {
        String _s = s;
        _s = _s.replaceAll("<", "&lt;");
        _s = _s.replaceAll(">", "&gt;");

        _s = _s.replaceAll("xml:", "<br/>xml:");
        _s = _s.replaceAll("xmlns:", "<br/>xmlns:");
        _s = _s.replaceAll("&gt;", "&gt;<br/>");

        _s = _s.replaceAll("&lt;\\!-- ///////////////////////////////////////////////////////////////////////////////////////", "&lt;\\!-- ///////////////////////////////////////////////////////////////////////////////////////<br/>");
        _s = _s.replaceAll("/////////////////////////////////////////////////////////////////////////////////////// --&gt;", "<br/>/////////////////////////////////////////////////////////////////////////////////////// --&gt;");

        _s = _s.replaceAll("&lt;\\!--", "<br/><br/><br/>&lt;!--<br/>");
        _s = _s.replaceAll("--&gt;", "<br/>--&gt;<br/><br/><br/>");

        return _s;
//        Document doc = Jsoup.parse(s);
//        return doc.toString();
    }

}
