package automat.mvc.deleteMode;

import automat.EinlagerungEntry;
import automat.StringUtils;
import automat.Verwaltung;
import automat.mvc.InputEvent;
import automat.mvc.InputEventListener;

public class DeleteKuchenInputListener implements InputEventListener {
    private Verwaltung verwaltung;
    private StringUtils stringUtils;

    public DeleteKuchenInputListener(StringUtils stringUtils, Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
        this.stringUtils = stringUtils;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText().matches("^f.[0-9]*$")) {
            int fachNum = stringUtils.extractFachNumberFromString(event.getText());
            EinlagerungEntry[] einlagerungList = verwaltung.getEinlagerungList();
            if (fachNum > einlagerungList.length - 1) {
                System.out.println(String.format("Fach %s doesn't exist", fachNum));
                return;
            }
            if (einlagerungList[fachNum] == null) {
                System.out.println(String.format("Fach %s is empty", fachNum));
                return;
            }
            verwaltung.removeKuchenFromAutomat(einlagerungList[fachNum].getKuchen());
        }
    }

}
