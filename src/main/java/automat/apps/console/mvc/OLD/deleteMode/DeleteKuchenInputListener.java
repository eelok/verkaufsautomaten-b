//package automat.apps.console.mvc.OLD.deleteMode;
//
//import automat.apps.console.service.StringUtils;
//import automat.mainlib.Automat;
//import automat.apps.console.mvc.InputEvent;
//import automat.apps.console.mvc.InputEventListener;
//
//public class DeleteKuchenInputListener implements InputEventListener {
//    private Automat automat;
//    private StringUtils stringUtils;
//
//    public DeleteKuchenInputListener(Automat automat, StringUtils stringUtils) {
//        this.automat = automat;
//        this.stringUtils = stringUtils;
//    }
//
//    @Override
//    public void onInputEvent(InputEvent event) {
//        if(event.getText() == null){
//            return;
//        }
//        if(event.getText().matches("^f.[0-9]*$")){
//            int fachNumber = stringUtils.extractFachNumberFromString(event.getText());
//            try {
//                automat.removeKuchenFromAutomat(fachNumber);
//            } catch (IllegalArgumentException ex){
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//
//}
