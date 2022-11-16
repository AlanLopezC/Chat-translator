package UI.Languages;

public class SpanishUI implements LanguageInterface {

    @Override
    public String[] menuLabels() {
        String[] labels = { "Contactos", "Configuración" };
        return labels;
    }

    @Override
    public String senderDescription() {
        return "Estás hablando con ";
    }

    @Override
    public String sendButtonLabel() {
        return "Enviar";
    }

}
