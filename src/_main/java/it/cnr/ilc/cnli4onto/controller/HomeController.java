package it.cnr.ilc.cnli4onto.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oakgen
 */
@RequestScoped
@Named
public class HomeController extends BaseController {

    public String homeAction() {
        return "index";
    }

    public String reportAction() {
        return "reportView";
    }

    public String questionAction() {
        return "questionView";
    }

    public String translationAction() {
        return "graphicalView";
    }
}
