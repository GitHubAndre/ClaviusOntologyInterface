/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.controller;

import it.cnr.ilc.cnli4onto.action.ontologyQuestions;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andrea
 */
@ViewScoped
@Named
public class timelineCreationController implements Serializable {

    @Inject
    private transient timelineCreationDialogController timelines;

    @Inject
    private transient ontologyQuestions onto;

    public void timelineCreationAction() {
    }

    public void timelineModificationAction() {

    }

}
