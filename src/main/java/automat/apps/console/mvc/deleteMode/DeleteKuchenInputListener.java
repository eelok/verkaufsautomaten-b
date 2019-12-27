package automat.apps.console.mvc.deleteMode;

import automat.apps.console.service.StringUtils;
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
        //TODO: optimize
//        if (event.getText().matches("^f.[0-9]*$")) {
//            int fachNum = stringUtils.extractFachNumberFromString(event.getText());
//            List<EinlagerungEntry> einlagerungList = automat.getEinlagerungList();
//            if (fachNum > einlagerungList.size() - 1) {
//                System.out.println(String.format("Fach %s doesn't exist", fachNum));
//                return;
//            }
//            if (einlagerungList[fachNum] == null) {
//                System.out.println(String.format("Fach %s is empty", fachNum));
//                return;
//            }
//            automat.removeKuchenFromAutomat(einlagerungList[fachNum].getKuchen());
//        }
    }

}
