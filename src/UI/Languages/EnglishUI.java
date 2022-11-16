package UI.Languages;

public class EnglishUI implements LanguageInterface {

    @Override
    public String[] menuLabels() {
        String[] labels = { "Contacts", "Settings" };
        return labels;
    }

    @Override
    public String senderDescription() {
        return "You are talking to ";
    }

    @Override
    public String sendButtonLabel() {
        return "Send";
    }

}
