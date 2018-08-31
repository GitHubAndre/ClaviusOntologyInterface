    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.model;

import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

@Named
@ApplicationScoped
public class OntologyModel implements Serializable {

    private static final long serialVersionUID = 2L;

    private final String LEMON_NS = "http://lemon-model.net/lemon";
    private final String LEMONDIA_NS = "http://www.languagelibrary.eu/lemonDia/lemonDia";
    private final String TIME_NS = "http://www.w3.org/2006/time-entry";
    private final String MINE_NS = "http://www.semanticweb.org/lexicon";
    private final String LEXINFO_NS = "http://www.lexinfo.net/ontology/2.0/lexinfo";
    private final String ANGLO_NS = "http://www.languagelibrary.eu/lemonDia/anglosaxtimeline";
    private final String SEM_NS = "http://www.languagelibrary.eu/lemonDia/semantics";

    private OWLOntologyManager manager = null;
    private OWLOntology ontology = null;
    private OWLDataFactory factory = null;
    private OntModel staticModel;

    public OntModel getStaticModel() {
        return staticModel;
    }

    @PostConstruct
    public void initOnto() {
        try {
            if (manager == null) manager = OWLManager.createOWLOntologyManager();
            if (ontology == null) ontology = manager.createOntology(IRI.create(MINE_NS));
            if (factory == null) factory = manager.getOWLDataFactory();
//            addImport(LEMON_NS);
            addImport(LEMONDIA_NS);
//            addImport(TIME_NS);
            addImport(LEXINFO_NS);
            addImport(ANGLO_NS);
            addImport(SEM_NS);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(OntologyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addImport(String ns) {
        OWLImportsDeclaration importDeclaration = factory.getOWLImportsDeclaration(IRI.create(ns));
        AddImport imp = new AddImport(ontology, importDeclaration);
        manager.applyChange(imp);
    }

    public OWLOntologyManager getManager() {
        return manager;
    }

    public OWLOntology getOntology() {
        return ontology;
    }

    public OWLDataFactory getFactory() {
        return factory;
    }

    public String getLEMON_NS() {
        return LEMON_NS;
    }

    public String getLEMONDIA_NS() {
        return LEMONDIA_NS;
    }

    public String getTIME_NS() {
        return TIME_NS;
    }

    public String getMINE_NS() {
        return MINE_NS;
    }

    public String getLEXINFO_NS() {
        return LEXINFO_NS;
    }

    public String getANGLO_NS() {
        return ANGLO_NS;
    }

    public String getSEM_NS() {
        return SEM_NS;
    }
}
