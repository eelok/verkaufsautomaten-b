package automat.apps.console.mvc.deleteMode;

import automat.mainlib.EinlagerungEntry;
import automat.apps.console.StringUtils;
import automat.mainlib.Automat;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;

public class DeleteKuchenInputListener implements InputEventListener {
    private Automat automat;
    private StringUtils stringUtils;

    public DeleteKuchenInputListener(StringUtils stringUtils, Automat automat) {
        this.automat = automat;
        this.stringUtils = stringUtils;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText().matches("^f.[0-9]*$")) {
            int fachNum = stringUtils.extractFachNumberFromString(event.getText());
            EinlagerungEntry[] einlagerungList = automat.getEinlagerungList();
            if (fachNum > einlagerungList.length - 1) {
                System.out.println(String.format("Fach %s doesn't exist", fachNum));
                return;
            }
            if (einlagerungList[fachNum] == null) {
                System.out.println(String.format("Fach %s is empty", fachNum));
                return;
            }
            automat.removeKuchenFromAutomat(einlagerungList[fachNum].getKuchen());
        }
    }

}
