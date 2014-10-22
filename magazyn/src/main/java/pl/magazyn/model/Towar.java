package pl.altkom.magazyn.model;

import java.io.Serializable;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.NumberFormat;

public class Towar implements Serializable, Comparable {

    private long id;
    private String nazwa;
    private String opis;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=0)
    private double cena;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(0) //gdy jest tylko jedna w³sœciwoœæ mo¿na pisaæ bez value
    private double ilosc;
    private String kategoria;

    public Towar(long id, String nazwa, String opis, double cena, double ilosc,
            String kategoria) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena = cena;
        this.ilosc = ilosc;
        this.kategoria = kategoria;
    }

    public Towar() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public int compareTo(Object o) {
        return -1;
    }
}
