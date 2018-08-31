/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.controller;

import it.cnr.ilc.cnli4onto.action.ontologyQuestions;
import it.cnr.ilc.cnli4onto.model.OntologyModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 *
 * @author andrea
 */
@ViewScoped
@Named

public class exportController implements Serializable {

    @Inject
    private transient OntologyModel ontologyModel;

    @Inject
    private transient ontologyQuestions oq;

    public StreamedContent OWLXMLexportAction() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ontologyModel.getManager().saveOntology(ontologyModel.getOntology(), baos);
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(exportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(baos.toByteArray());
        return new DefaultStreamedContent(in, "application/txt", oq.getExcelName().split("\\.")[0] + ".owl");
    }
    
        public StreamedContent TURTLEexportAction() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            TurtleOntologyFormat turtleFormat = new TurtleOntologyFormat();
//		turtleFormat.copyPrefixesFrom(pm);
//turtleFormat.setDefaultPrefix(ontologyIRI + "#");
            ontologyModel.getManager().saveOntology(ontologyModel.getOntology(), turtleFormat, baos);
        } catch (OWLOntologyStorageException ex) {
            Logger.getLogger(exportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(baos.toByteArray());
        return new DefaultStreamedContent(in, "application/txt", oq.getExcelName().split("\\.")[0] + ".ttl");
    }

}



//RDFXMLOntologyFormat rdfFormat = new RDFXMLOntologyFormat();
//		rdfFormat.copyPrefixesFrom(pm);
//		rdfFormat.setDefaultPrefix(ontologyIRI + "#");
//		
//		TurtleOntologyFormat turtleFormat = new TurtleOntologyFormat();
//		turtleFormat.copyPrefixesFrom(pm);
//turtleFormat.setDefaultPrefix(ontologyIRI + "#");