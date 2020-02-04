package automat.mainlib.interfaceSubBeob;

public interface Subject {

    void meldeAn(Beobachter beobachter);

    void benachrichtige(Class<? extends Beobachter> beobachterType);
}
