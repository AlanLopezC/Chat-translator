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

    @Override
    public String setActive() {
        return "Estas conectado";
    }

    @Override
    public String logOut() {
        return "Cerrar Sesión";
    }

    @Override
    public String setContact() {
        return "Añadir Contactos";
    }

    @Override
    public String informationNotice() {
        return "Aviso de Información";
    }

    @Override
    public String addContact() {
        return "Agregar contacto";
    }

    @Override
    public String fieldEmpty() {
        return "El campo está vació";
    }

    @Override
    public String enterUserName() {
        return "Introduce el nombre de usuario";
    }

    @Override
    public String userNameDontExit() {
        return "El usuario no existe";
    }

    @Override
    public String userHasAdded() {
        return "El usuario ya ha sido agregado";
    }

    @Override
    public String cannotAddYourself() {
        return "No te puedes agregar a tí mismo";
    }

}
