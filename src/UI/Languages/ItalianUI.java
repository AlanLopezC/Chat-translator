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

    @Override
    public String setActive() {
        return "Sei connesso";
    }

    @Override
    public String logOut() {
        return "Disconnettersi";
    }

    @Override
    public String setContact() {
        return "Aggiungi contatti";
    }

    @Override
    public String informationNotice() {
        return "Avviso informativo";
    }

    @Override
    public String addContact() {
        return "Aggiungi contatto";
    }

    @Override
    public String fieldEmpty() {
        return "Il campo è vuoto";
    }

    @Override
    public String enterUserName() {
        return "Inserisci il nome utente";
    }

    @Override
    public String userNameDontExit() {
        return "Lo username non esiste.";
    }

    @Override
    public String userHasAdded() {
        return "L'utente è già stato aggiunto";
    }

    @Override
    public String cannotAddYourself() {
        return "Non puoi aggiungere te stesso";
    }
}
