package automat;

import kuchen.Kuchen;

import java.time.LocalDateTime;

public class EinlagerungEntry {

    private LocalDateTime einlagerungsDatum;
    private Kuchen kuchen;
    private int fachnummer;

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
}
