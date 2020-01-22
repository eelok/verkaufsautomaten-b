package automat.mainlib;

public interface Subject {

    void meldeAn(Beobachter beobachter);

    void meldeAb(Beobachter beobachter);

    void benachrichtige(Class<? extends Beobachter> beobachterType);

}
