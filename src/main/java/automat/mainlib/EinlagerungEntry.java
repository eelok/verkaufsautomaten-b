package automat.mainlib;

import automat.mainlib.kuchen.Kuchen;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EinlagerungEntry implements Serializable {

    private LocalDateTime einlagerungsDatum;
    private Kuchen kuchen;
    private int fachnummer;

    public EinlagerungEntry() {
    }

    public EinlagerungEntry(LocalDateTime einlagerungsDatum, Kuchen kuchen, int fachnummer) {
        this.einlagerungsDatum = einlagerungsDatum;
        this.kuchen = kuchen;
        this.fachnummer = fachnummer;
    }

    public LocalDateTime getEinlagerungsDatum() {
        return einlagerungsDatum;
    }

    public Kuchen getKuchen() {
        return kuchen;
    }

    public int getFachnummer() {
        return this.fachnummer;
    }

    public void setEinlagerungsDatum(LocalDateTime einlagerungsDatum) {
        this.einlagerungsDatum = einlagerungsDatum;
    }

    public void setKuchen(Kuchen kuchen) {
        this.kuchen = kuchen;
    }

    public void setFachnummer(int fachnummer) {
        this.fachnummer = fachnummer;
    }

    @Override
    public String toString() {
        return "EinlagerungEntry{" +
                "einlagerungsDatum=" + einlagerungsDatum +
                ", kuchen=" + kuchen +
                ", fachnummer=" + fachnummer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EinlagerungEntry that = (EinlagerungEntry) o;
        return fachnummer == that.fachnummer &&
                Objects.equals(einlagerungsDatum, that.einlagerungsDatum) &&
                Objects.equals(kuchen, that.kuchen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(einlagerungsDatum, kuchen, fachnummer);
    }
}
