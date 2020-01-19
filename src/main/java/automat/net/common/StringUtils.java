package automat.net.common;

public class StringUtils {

    public boolean isOneWord(String inputText) {
        inputText = inputText.trim();
        if(inputText.startsWith(":")){
            return false;
        }
        for (int i = 0; i < inputText.length(); i++) {
            if (Character.isWhitespace(inputText.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public int extractFachNumberFromString(String userInput){
        String number = userInput.replace("f", "");
        return Integer.parseInt(number);
    }

}

