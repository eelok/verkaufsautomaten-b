package automat.apps.console.mvc.addMode;

import automat.apps.console.Printer;
import automat.apps.console.mvc.InputEvent;
import automat.apps.console.mvc.InputEventListener;
import automat.apps.console.service.StringUtils;
import automat.net.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddManufacturerInputListener implements InputEventListener {

    private StringUtils stringUtils;
    private Printer printer;
    private Socket socketConnection;
    private ObjectOutputStream clientOutputStream;
    private ObjectInputStream clientInputStream;


    public AddManufacturerInputListener(
            StringUtils stringUtils,
            Printer printer,
            Socket socketConnection,
            ObjectOutputStream clientOutputStream,
            ObjectInputStream clientInputStream
    ) {
        this.stringUtils = stringUtils;
        this.printer = printer;
        this.socketConnection = socketConnection;
        this.clientOutputStream = clientOutputStream;
        this.clientInputStream = clientInputStream;
    }

    @Override
    public void onInputEvent(InputEvent event) {
        if (event.getText() == null) {
            return;
        }
        String userInput = event.getText().toLowerCase().trim();
        if (stringUtils.isOneWord(userInput)) {
            try {
                sendDataToServer(userInput);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendDataToServer(String userInput) throws IOException, ClassNotFoundException {
        String dataForTransport = "";
        dataForTransport = Command.addH + "/" + userInput;

        this.clientOutputStream.writeObject(dataForTransport);
        Object replyFromServer = this.clientInputStream.readObject();
        this.printer.println(replyFromServer.toString());
    }
}
