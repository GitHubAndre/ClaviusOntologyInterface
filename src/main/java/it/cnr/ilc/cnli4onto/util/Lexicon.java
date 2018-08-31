/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cnli4onto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andrea
 */
public class Lexicon {

    private String lexicalEntry;
    private String lexicalRoot;
    private String ethimology;
    private String lemmas;
    private String corpusFreq;
    private String totalFreq;
    private String textual_dialectalDistribution;
    private String collocations;
    private String meaning_1;
    private String meaning_2;
    private String meaning_3;
    private String meaning_4;
    private String semanticShift;
    private String etimologicalTheme;
    private String temporalInformation;
    private String interval_1;
    private String interval_2;
    private String interval_3;
    
    private String semanticShiftInfo;

    public String getSemanticShiftInfo() {
        return semanticShiftInfo;
    }

    public void setSemanticShiftInfo(String semanticShiftInfo) {
        this.semanticShiftInfo = semanticShiftInfo;
    }

    public String getCorpusFreq() {
        return corpusFreq;
    }

    public void setCorpusFreq(String corpusFreq) {
        this.corpusFreq = corpusFreq;
    }

    public String getTotalFreq() {
        return totalFreq;
    }

    public void setTotalFreq(String totalFreq) {
        this.totalFreq = totalFreq;
    }

    public String getTextual_dialectalDistribution() {
        return textual_dialectalDistribution;
    }

    public void setTextual_dialectalDistribution(String textual_dialectalDistribution) {
        this.textual_dialectalDistribution = textual_dialectalDistribution;
    }

    public String getCollocations() {
        return collocations;
    }

    public void setCollocations(String collocations) {
        this.collocations = collocations;
    }

    public String getMeaning_1() {
        return meaning_1;
    }

    public void setMeaning_1(String meaning_1) {
        this.meaning_1 = meaning_1;
    }

    public String getMeaning_2() {
        return meaning_2;
    }

    public void setMeaning_2(String meaning_2) {
        this.meaning_2 = meaning_2;
    }

    public String getMeaning_3() {
        return meaning_3;
    }

    public void setMeaning_3(String meaning_3) {
        this.meaning_3 = meaning_3;
    }

    public String getMeaning_4() {
        return meaning_4;
    }

    public void setMeaning_4(String meaning_4) {
        this.meaning_4 = meaning_4;
    }

    public String getSemanticShift() {
        return semanticShift;
    }

    public void setSemanticShift(String semanticShift) {
        this.semanticShift = semanticShift;
    }

    public String getEtimologicalTheme() {
        return etimologicalTheme;
    }

    public void setEtimologicalTheme(String etimologicalTheme) {
        this.etimologicalTheme = etimologicalTheme;
    }

    public String getTemporalInformation() {
        return temporalInformation;
    }

    public void setTemporalInformation(String temporalInformation) {
        this.temporalInformation = temporalInformation;
    }

    public String getInterval_1() {
        return interval_1;
    }

    public void setInterval_1(String interval_1) {
        this.interval_1 = interval_1;
    }

    public String getInterval_2() {
        return interval_2;
    }

    public void setInterval_2(String interval_2) {
        this.interval_2 = interval_2;
    }

    public String getInterval_3() {
        return interval_3;
    }

    public void setInterval_3(String interval_3) {
        this.interval_3 = interval_3;
    }

    public String getLexicalEntry() {
        return lexicalEntry;
    }

    public void setLexicalEntry(String lexicalEntry) {
        this.lexicalEntry = lexicalEntry;
    }

    public String getLexicalRoot() {
        return lexicalRoot;
    }

    public void setLexicalRoot(String lexicalRoot) {
        this.lexicalRoot = lexicalRoot;
    }

    public String getEthimology() {
        return ethimology;
    }

    public void setEthimology(String ethimology) {
        this.ethimology = ethimology;
    }

    public String getLemmas() {
        return lemmas;
    }

    public void setLemmas(String lemmas) {
        this.lemmas = lemmas;
    }

    public Lexicon() {
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }

    public List<Lexicon> readLexiconFromExcelFile(UploadedFile excelFile) throws IOException {
        List<Lexicon> listEntries = new ArrayList<Lexicon>();
        InputStream in = excelFile.getInputstream();

        Workbook workbook = new XSSFWorkbook(in);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        int numberOfSemShift = 0;
        
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Lexicon lexEntry = new Lexicon();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                if ((getCellValue(nextCell)) != null) {

                    switch (columnIndex) {
                        case 0:
                            lexEntry.setLexicalEntry((String) getCellValue(nextCell));
                            break;
                        case 1:
                            lexEntry.setLexicalRoot((String) getCellValue(nextCell));
                            break;
                        case 2:
                            lexEntry.setEthimology((String) getCellValue(nextCell));
                            break;
                        case 3:
                            lexEntry.setLemmas((String) getCellValue(nextCell));
                            break;
                        case 4:
                            lexEntry.setCorpusFreq((String) getCellValue(nextCell).toString());
                            break;
                        case 5:
                            lexEntry.setTotalFreq((String) getCellValue(nextCell).toString());
                            break;
                        case 6:
                            lexEntry.setTextual_dialectalDistribution((String) getCellValue(nextCell));
                            break;
                        case 7:
                            lexEntry.setCollocations((String) getCellValue(nextCell));
                            break;
                        case 8:
                            lexEntry.setMeaning_1((String) getCellValue(nextCell));
                            break;
                        case 9:
                            lexEntry.setMeaning_2((String) getCellValue(nextCell));
                            break;
                        case 10:
                            lexEntry.setMeaning_3((String) getCellValue(nextCell));
                            break;
                        case 11:
                            lexEntry.setMeaning_4((String) getCellValue(nextCell));
                            break;
                        case 12:
                            lexEntry.setSemanticShift((String) getCellValue(nextCell));
                            break;
                        case 13:
                            lexEntry.setEtimologicalTheme((String) getCellValue(nextCell));
                            break;
                        case 14:
                            lexEntry.setInterval_1((String) getCellValue(nextCell));
                            break;
                        case 15:
                            lexEntry.setInterval_2((String) getCellValue(nextCell));
                            break;
                        case 16:
                            lexEntry.setInterval_3((String) getCellValue(nextCell));
                            break;
                        case 17:
                            lexEntry.setTemporalInformation((String) getCellValue(nextCell));
                            break;
                    }
                }
            }
            if ((lexEntry.getMeaning_1() != null) && (lexEntry.getMeaning_2() != null)) numberOfSemShift++; 
            listEntries.add(lexEntry);
        }
        workbook.close();
        in.close();
        setSemanticShiftInfo("Semantic shifts found: 8\nSee the diagram below.");
        return listEntries;
    }

}
