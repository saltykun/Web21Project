package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

public class Foto {
    long id;
    long version;
    
    @Valid
    @NotEmpty
    private String mimetype;
    
    @NotEmpty
    @Size(min = 3)
    private String dateiname;
    private String ort;
    
    @Valid
    @PastOrPresent
    private LocalDate zeitstempel;

    private double geoleange;
    private double geobreite;

    private byte[] fotodaten;

    public Foto(){
        zeitstempel = LocalDate.MIN;
        mimetype ="";
        dateiname="";
        ort = "";

    }

    public String getDateiname() {
        return dateiname;
    }
    public byte[] getFotodaten() {
        return fotodaten;
    }
    public double getGeobreite() {
        return geobreite;
    }
    public double getGeoleange() {
        return geoleange;
    }
    public String getMimetype() {
        return mimetype;
    }
    public String getOrt() {
        return ort;
    }
    public LocalDate getZeitstempel() {
        return zeitstempel;
    }
    public void setDateiname(String dateiname) {
        this.dateiname = dateiname;
    }
    public void setFotodaten(byte[] fotodaten) {
        this.fotodaten = fotodaten;
    }
    public void setGeobreite(double geobreite) {
        this.geobreite = geobreite;
    }
    public void setGeoleange(double geoleange) {
        this.geoleange = geoleange;
    }
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
    public void setOrt(String ort) {
        this.ort = ort;
    }
    public void setZeitstempel(LocalDate zeitstempel) {
        this.zeitstempel = zeitstempel;
    }
    

    @Override
    public String toString() {
        return "Foto [dateiname=" + dateiname + ", fotodaten=" + Arrays.toString(fotodaten) + ", geobreite=" + geobreite
                + ", geoleange=" + geoleange + ", mimetype=" + mimetype + ", ort=" + ort + ", zeitstempel="
                + zeitstempel + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateiname == null) ? 0 : dateiname.hashCode());
        result = prime * result + Arrays.hashCode(fotodaten);
        long temp;
        temp = Double.doubleToLongBits(geobreite);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(geoleange);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((mimetype == null) ? 0 : mimetype.hashCode());
        result = prime * result + ((ort == null) ? 0 : ort.hashCode());
        result = prime * result + ((zeitstempel == null) ? 0 : zeitstempel.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Foto other = (Foto) obj;
        if (dateiname == null) {
            if (other.dateiname != null)
                return false;
        } else if (!dateiname.equals(other.dateiname))
            return false;
        if (!Arrays.equals(fotodaten, other.fotodaten))
            return false;
        if (Double.doubleToLongBits(geobreite) != Double.doubleToLongBits(other.geobreite))
            return false;
        if (Double.doubleToLongBits(geoleange) != Double.doubleToLongBits(other.geoleange))
            return false;
        if (mimetype == null) {
            if (other.mimetype != null)
                return false;
        } else if (!mimetype.equals(other.mimetype))
            return false;
        if (ort == null) {
            if (other.ort != null)
                return false;
        } else if (!ort.equals(other.ort))
            return false;
        if (zeitstempel == null) {
            if (other.zeitstempel != null)
                return false;
        } else if (!zeitstempel.equals(other.zeitstempel))
            return false;
        return true;
    }
    
}
