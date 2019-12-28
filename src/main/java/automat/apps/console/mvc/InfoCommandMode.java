package automat.apps.console.mvc;

public class InfoCommandMode implements InputEventListener {


    @Override
    public void onInputEvent(InputEvent event) {
        if (!event.getText().equals(":a") && !event.getText().equals(":l") && !event.getText().equals(":d")){
            System.out.println("Expected input: :a <input mode> | :l <list mode> | :d <delete mode>");
        }
    }
}

