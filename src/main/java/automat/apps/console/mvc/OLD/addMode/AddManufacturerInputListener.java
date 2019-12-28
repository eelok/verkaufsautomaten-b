//package automat.apps.console.mvc.OLD.addMode;
//
//import automat.apps.console.mvc.InputEvent;
//import automat.apps.console.mvc.InputEventListener;
//import automat.apps.console.service.StringUtils;
//import automat.mainlib.Automat;
//import automat.mainlib.exceptions.ManufacturerExistException;
//import automat.mainlib.hersteller.HerstellerImplementation;
//
//public class AddManufacturerInputListener implements InputEventListener {
//
//    private StringUtils stringUtils;
//    private Automat automat;
//
//    public AddManufacturerInputListener(StringUtils stringUtils, Automat automat) {
//        this.stringUtils = stringUtils;
//        this.automat = automat;
//    }
//
//    @Override
//    public void onInputEvent(InputEvent event) {
//        if (event.getText() == null) {
//            return;
//        }
//        String userInput = event.getText().toLowerCase().trim();
//        if (stringUtils.isOneWord(userInput)) {
//            try {
//                automat.addHersteller(new HerstellerImplementation(userInput));
//            } catch (ManufacturerExistException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//}
