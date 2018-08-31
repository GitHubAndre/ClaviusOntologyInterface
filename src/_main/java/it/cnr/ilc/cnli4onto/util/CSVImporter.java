/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andrea
 */
public class CSVImporter {

    private List<Lexicon> lexicalEntries;

    private String stat;

    public List<Lexicon> getLexicalEntries() {
        return lexicalEntries;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public CSVImporter(UploadedFile csv) {
        Lexicon lex = new Lexicon();
        try {
            lexicalEntries = lex.readLexiconFromExcelFile(csv);
            setStat("Lexicon " + csv.getFileName() + " successfully imported. <br/><br/>Lexical entries found: 8 <br/>");
            setStat(getStat() + "<br/> " + lex.getSemanticShiftInfo());
        } catch (IOException ex) {
            Logger.getLogger(CSVImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
