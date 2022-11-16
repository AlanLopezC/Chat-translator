package UI.Languages;

public class ItalianUI implements LanguageInterface {

    @Override
    public String[] menuLabels() {
        String[] labels = { "Contatti", "Configurazione" };
        return labels;
    }

    @Override
    public String senderDescription() {
        return "Stai parlando con ";
    }

    @Override
    public String sendButtonLabel() {
        return "Enviare";
    }

}
