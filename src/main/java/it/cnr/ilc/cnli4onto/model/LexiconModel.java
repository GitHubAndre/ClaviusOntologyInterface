/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.model;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import it.cnr.ilc.cnli4onto.controller.timelineCreationDialogController;
import it.cnr.ilc.cnli4onto.util.Lexicon;
import it.cnr.ilc.cnli4onto.util.Timeline;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

/**
 *
 * @author andrea
 */
@Named
@ViewScoped
public class LexiconModel {

    @Inject
    private transient timelineCreationDialogController timelines;

    PrefixManager lem;
    PrefixManager lemdia;
    PrefixManager time;
    PrefixManager mine;
    PrefixManager lexinfo;
    PrefixManager anglo;
    PrefixManager sem;
    OntologyModel ontoModel;

    public OntologyModel getOntoModel() {
        return ontoModel;
    }

    public void setOntoModel(OntologyModel ontoModel) {
        this.ontoModel = ontoModel;
    }

    public LexiconModel(OntologyModel om) {
        setOntoModel(om);
        lem = new DefaultPrefixManager(null, null, getOntoModel().getLEMON_NS());
        lemdia = new DefaultPrefixManager(null, null, getOntoModel().getLEMONDIA_NS());
        time = new DefaultPrefixManager(null, null, getOntoModel().getTIME_NS());
        mine = new DefaultPrefixManager(null, null, getOntoModel().getMINE_NS());
        lexinfo = new DefaultPrefixManager(null, null, getOntoModel().getLEXINFO_NS());
        anglo = new DefaultPrefixManager(null, null, getOntoModel().getANGLO_NS());
        sem = new DefaultPrefixManager(null, null, getOntoModel().getSEM_NS());
    }

    public void addLexiconEntryToModel(Lexicon lex) {

//        addTimelineToModel();

        OWLClass LexicalEntryClass = getOntoModel().getFactory().getOWLClass("#LexicalEntry", lem);
        OWLClass ExpressionClass = getOntoModel().getFactory().getOWLClass("#Expression", lemdia);
        OWLClass LemmaClass = getOntoModel().getFactory().getOWLClass("#Lemma", lemdia);

        OWLNamedIndividual lexicalEntry = getOntoModel().getFactory().getOWLNamedIndividual("#" + lex.getLexicalEntry(), mine);
        OWLNamedIndividual expression = getOntoModel().getFactory().getOWLNamedIndividual("#" + lex.getLexicalRoot(), mine);

        addIndividualAxiom(LexicalEntryClass, lexicalEntry);
        addIndividualAxiom(ExpressionClass, expression);

        addObjectPropertyAxiom("lexexp", lexicalEntry, expression, lemdia);
        addObjectPropertyAxiom("explex", expression, lexicalEntry, lemdia);
        addDataPropertyAxiom("language", expression, "ang", lem);
        addDataPropertyAxiom("language", lexicalEntry, "ang", lem);

        addReconstructedTerm(lex.getEthimology(), lexicalEntry);
        addLemmasAxioms(lex.getLemmas(), LemmaClass, lexicalEntry);

        addSenses(lexicalEntry, lex.getMeaning_1(), lex.getMeaning_2(), lex.getSemanticShift(), lex.getEtimologicalTheme());

    }

    private void addTimelineToModel() {
        for (Timeline tm : timelines.getTimelines()) {
            addInterval(tm.getName(), tm.getIntervalType(), tm.getStartYear(), tm.getEndYear());
        }
    }

    private void addInterval(String interval, String type, int start, int end) {
        OWLClass ProperIntervalClass = getOntoModel().getFactory().getOWLClass("#ProperInterval", time);
        OWLClass InstantClass = getOntoModel().getFactory().getOWLClass("#Instant", time);
        OWLNamedIndividual _interval = getOntoModel().getFactory().getOWLNamedIndividual("#" + interval, mine);
        OWLNamedIndividual startInstant = getOntoModel().getFactory().getOWLNamedIndividual("#start_" + interval, mine);
        OWLNamedIndividual endInstant = getOntoModel().getFactory().getOWLNamedIndividual("#end_" + interval, mine);
        addIndividualAxiom(ProperIntervalClass, _interval);
        addIndividualAxiom(InstantClass, startInstant);
        addIndividualAxiom(InstantClass, endInstant);
        addObjectPropertyAxiom("hasBeginning", _interval, startInstant, time);
        addObjectPropertyAxiom("hasEnd", _interval, endInstant, time);
    }

//    :AREODIAN_VB_Red_to_Shame_sense a owl:NamedIndividual , lemond:SemanticShift ;
//	lemond:shiftDomain :REDNESS_IN_THE_FACE ;
//	lemond:shiftSource :sense_Red_AREODIAN_VB ;
//	lemond:shiftTarget :sense_Shame_AREODIAN_VB ;
//	lemond:shiftType sem:resultative_metonymy .
    private void addSenses(OWLNamedIndividual le, String s1, String s2, String shift, String theme) {
        String _s1 = "sense_" + s1 + "_" + le.getIRI().toString().split("#")[1];
        String _s2 = "sense_" + s2 + "_" + le.getIRI().toString().split("#")[1];
        OWLClass LexicalSenseClass = getOntoModel().getFactory().getOWLClass("#LexicalSense", lem);
        OWLNamedIndividual sense_1 = getOntoModel().getFactory().getOWLNamedIndividual("#" + _s1, mine);
        OWLNamedIndividual sense_2 = getOntoModel().getFactory().getOWLNamedIndividual("#" + _s2, mine);
        addIndividualAxiom(LexicalSenseClass, sense_1);
        addIndividualAxiom(LexicalSenseClass, sense_2);
        addObjectPropertyAxiom("sense", le, sense_1, lem);
        addObjectPropertyAxiom("sense", le, sense_2, lem);
        addSemanticShift(le, sense_2, sense_1, s1, s2, shift, theme);
    }

    private void addSemanticShift(OWLNamedIndividual le, OWLNamedIndividual srcSense, OWLNamedIndividual trgSense, String srcSenseName, String trgSenseName, String shift, String theme) {
        String semShiftInst = le.getIRI().toString().split("#")[1] + "_" + srcSenseName + "_to_" + trgSenseName + "_sense";
        OWLClass SemanticShiftClass = getOntoModel().getFactory().getOWLClass("#SemanticShift", lemdia);
        OWLNamedIndividual semShift = getOntoModel().getFactory().getOWLNamedIndividual("#" + semShiftInst, mine);
        addIndividualAxiom(SemanticShiftClass, semShift);

        OWLNamedIndividual shiftDomain = getOntoModel().getFactory().getOWLNamedIndividual("#" + theme, mine);
        addObjectPropertyAxiom("shiftDomain", semShift, shiftDomain, lemdia);

        addObjectPropertyAxiom("shiftSource", semShift, srcSense, lemdia);
        addObjectPropertyAxiom("shiftTarget", semShift, trgSense, lemdia);

        String ethimologicalTheme;
        if (shift.contains(" ")) ethimologicalTheme = shift.toLowerCase().split(" ")[0] + "_" + shift.toLowerCase().split(" ")[1];
        else ethimologicalTheme = shift;
        OWLClass clazz = getOntoModel().getFactory().getOWLClass("#" + ethimologicalTheme, sem);
        OWLNamedIndividual etTheme = getOntoModel().getFactory().getOWLNamedIndividual("#_" + ethimologicalTheme, mine);
        addIndividualAxiom(clazz, etTheme);
        addObjectPropertyAxiom("shiftType", semShift, etTheme, lemdia);

    }

    private void addReconstructedTerm(String e, OWLNamedIndividual le) {
        OWLClass ReconstructedTermClass = getOntoModel().getFactory().getOWLClass("#ReconstructedTerm", lemdia);
        OWLClass FormClass = getOntoModel().getFactory().getOWLClass("#Form", lem);
        OWLNamedIndividual ethimology = getOntoModel().getFactory().getOWLNamedIndividual("#" + getEthimologyTerm(e), mine);
        OWLNamedIndividual form = getOntoModel().getFactory().getOWLNamedIndividual("#" + 
                (e.contains("UNKNOWN") ? e : e.split(" ")[1]) + 
                "_inst", mine);
        addIndividualAxiom(ReconstructedTermClass, ethimology);
        addIndividualAxiom(FormClass, form);
        addObjectPropertyAxiom("canonicalForm", ethimology, form, lem);
        addDataPropertyAxiom("writtenRep", form, (e.contains("UNKNOWN") ? e : e.split(" ")[1]), lem);
        addDataPropertyAxiom("language", ethimology, 
                (e.contains("UNKNOWN") ? e : e.split("\\*")[0].trim()), lem);
        addObjectPropertyAxiom("root", ethimology, le, lexinfo);
    }

    private void addIndividualAxiom(OWLClass c, OWLNamedIndividual i) {
        OWLClassAssertionAxiom classAssertion = getOntoModel().getFactory().getOWLClassAssertionAxiom(c, i);
        getOntoModel().getManager().addAxiom(getOntoModel().getOntology(), classAssertion);
    }

    private void addObjectPropertyAxiom(String objProp, OWLNamedIndividual src, OWLNamedIndividual trg, PrefixManager ns) {
        OWLObjectProperty p = getOntoModel().getFactory().getOWLObjectProperty("#" + objProp, ns);
        OWLObjectPropertyAssertionAxiom propertyAssertion = getOntoModel().getFactory().getOWLObjectPropertyAssertionAxiom(p, src, trg);
        getOntoModel().getManager().addAxiom(getOntoModel().getOntology(), propertyAssertion);
    }

    private void addDataPropertyAxiom(String dataProp, OWLNamedIndividual src, String trg, PrefixManager ns) {
        OWLDataProperty p = getOntoModel().getFactory().getOWLDataProperty("#" + dataProp, ns);
        OWLDataPropertyAssertionAxiom dataPropertyAssertion = getOntoModel().getFactory().getOWLDataPropertyAssertionAxiom(p, src, trg);
        getOntoModel().getManager().addAxiom(getOntoModel().getOntology(), dataPropertyAssertion);
    }

    private void addLemmasAxioms(String lemmaList, OWLClass LemmaClass, OWLNamedIndividual lexicalEntry) {
        for (String lemma : getLemmas(lemmaList)) {
            OWLNamedIndividual _lemma = getOntoModel().getFactory().getOWLNamedIndividual("#" + lemma, mine);
            addIndividualAxiom(LemmaClass, _lemma);
            addObjectPropertyAxiom("lemma", lexicalEntry, _lemma, lemdia);
        }
    }

    private String getPoS(String s) {
        switch (s) {
            case "VB":
                return "Verb";
        }
        return null;
    }

    private ArrayList<String> getLemmas(String s) {
        ArrayList<String> lemmas = new ArrayList<>();
        for (String l : s.split(" ")) {
            String _l = l.split("\\[")[0].toUpperCase();
            _l = _l.replaceAll("\\+", "_PLUS_");
            _l = _l.replaceAll("\\^", "_HAT_");
            lemmas.add("LEM_" + _l);
        }
        return lemmas;
    }

    private String getEthimologyTerm(String et) {
        String a = et;
        int i = 0;
        if (et.contains("UNKNOWN")) return et;
        else return et.split("\\*")[1].split("-")[0];
    }

    public OWLOntologyManager getManager() {
        return getOntoModel().getManager();
    }

    public OWLOntology getOntology() {
        return getOntoModel().getOntology();
    }

}
