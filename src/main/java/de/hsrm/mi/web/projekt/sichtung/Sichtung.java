package de.hsrm.mi.web.projekt.sichtung;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.sichtung.validierung.Siebzehnhaft;

public class Sichtung{
    
    @Valid
    @Size(min=3, message = "{valid.name(${min})}")
    @NotBlank(message = "{validleer}")
    private String name;
    
    @Valid
    @Size(min=3, message = "{valid.ort(${min})}")
    @NotBlank(message = "{valid.leer}")
    private String ort;
    
    @Valid
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull(message = "{valid.leer}") @PastOrPresent(message = "{valid.date}")
    private LocalDate datum;

    @Valid
    @Siebzehnhaft
    @Size(min=3, max=80, message = "{valid.beschreibung(${min},${max})}")
    @NotBlank(message = "{valid.leer}")
    private String beschreibung;

    public Sichtung(){}
    public Sichtung(String name, String ort, LocalDate datum, String beschreibung){
        this.name = name;
        this.ort = ort;
        this.datum = datum;
        this.beschreibung = beschreibung;
    }


    //getter und Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getOrt() {
        return ort;
    }
    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Ort: " + this.getOrt() + ", Datum: " + this.getDatum() + ", Beschreibung: " + this.getBeschreibung();
    }

}