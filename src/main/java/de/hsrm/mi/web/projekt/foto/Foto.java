package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
public class Foto {
    @Id // falls was noch funktioniert mal das andere Id importieren.
    @GeneratedValue
    private long id;
    
    private long version;

    @Valid
    @NotEmpty
    private String mimetype;
    
    @NotEmpty
    @Size(min = 3)
    private String dateiname;
    private String ort;
    
    @Valid
    @PastOrPresent
    private LocalDateTime zeitstempel;

    private double geolaenge;
    private double geobreite;

    @Lob private byte[] fotodaten;

    public Foto(){
        zeitstempel = LocalDateTime.MIN;
        mimetype ="";
        dateiname="";
        ort = "";

    }

    public long getId() {
        return id;
    }
    public long getVersion() {
        return version;
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
    public double getGeolaenge() {
        return geolaenge;
    }
    public String getMimetype() {
        return mimetype;
    }
    public String getOrt() {
        return ort;
    }
    public LocalDateTime getZeitstempel() {
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
    public void setGeolaenge(double geoleange) {
        this.geolaenge = geoleange;
    }
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
    public void setOrt(String ort) {
        this.ort = ort;
    }
    public void setZeitstempel(LocalDateTime zeitstempel) {
        this.zeitstempel = zeitstempel;
    }
    

    @Override
    public String toString() {
        return "Foto [dateiname=" + dateiname + ", fotodaten=" + Arrays.toString(fotodaten) + ", geobreite=" + geobreite
                + ", geoleange=" + geolaenge + ", mimetype=" + mimetype + ", ort=" + ort + ", zeitstempel="
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
        temp = Double.doubleToLongBits(geolaenge);
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
        if (Double.doubleToLongBits(geolaenge) != Double.doubleToLongBits(other.geolaenge))
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
