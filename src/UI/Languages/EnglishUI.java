package UI.Languages;

public class EnglishUI implements LanguageInterface {

    /**
     * Método que devuelve las cadenas en el idioma que tendrán por nombre los
     * Labels a utilizar en la interfaz gráfica.
     * 
     * @return String[] - Arreglo con el nombre de las Labels en inglés.
     */
    @Override
    public String[] menuLabels() {
        String[] labels = { "Contacts", "Settings" };
        return labels;
    }

    /**
     * Método que te devuelve una cadena de texto "Estas hablando con... "
     * en inglés.
     * 
     * @return String - Estas hablando con ....
     */
    @Override
    public String senderDescription() {
        return "You are talking to ";
    }

    /**
     * Método que te devuelve el texto del botón en inglés.
     * 
     * @return String - Enviar.
     */
    @Override
    public String sendButtonLabel() {
        return "Send";
    }

    /**
     * Método que te devuelve una cadena de texto "Estas conecto" en inglés
     * 
     * @return String - Estas conectado.
     */
    @Override
    public String setActive() {
        return "You are connected";
    }

    /**
     * Método que te devuelve una cadena de texto "Cerrar Sesión" en inglés
     * 
     * @return String - Cerrar Sesión.
     */
    @Override
    public String logOut() {
        return "Log Out";
    }

    /**
     * Método que te devuelve una cadena de texto "Añadir Contactos" en inglés
     * 
     * @return String - Añadir Contactos
     */
    @Override
    public String setContact() {
        return "Add contacts";
    }

    /**
     * Método que te devuelve una cadena de texto "Aviso de Información" en inglés.
     * 
     * @return String - Aviso de Información.
     */
    @Override
    public String informationNotice() {
        return "Information Notice";
    }

    /**
     * Método que te devuelve una cadena de texto "Agregar contacto" en inglés
     * 
     * @return String - Agregar contacto
     */
    @Override
    public String addContact() {
        return "Add contact";
    }

    /**
     * Método que te devuelve una cadena de texto "El campo está vació" en inglés
     * 
     * @return String - El campo está vació
     */
    @Override
    public String fieldEmpty() {
        return "The field is empty";
    }

    /**
     * Método que te devuelve una cadena de texto "Introduce el nombre de usuario"
     * en inglés.
     * 
     * @return String - Introduce el nombre de usuario
     */
    @Override
    public String enterUserName() {
        return "Enter the username";
    }

    /**
     * Método que te devuelve una cadena de texto "El usuario no existe" en inglés.
     * 
     * @return String - El usuario no existe
     */
    @Override
    public String userNameDontExit() {
        return "Username does not exist";
    }

    /**
     * Método que te devuelve una cadena de texto "El usuario ya ha sido agregado"
     * en inglés.
     * 
     * @return String - El usuario ya ha sido agregado
     */
    @Override
    public String userHasAdded() {
        return "The user has already been added";
    }

    /**
     * Método que te devuelve una cadena de texto "No te puedes agregar a tí mismo"
     * en inglés.
     * 
     * @return String - No te puedes agregar a tí mismo
     */
    @Override
    public String cannotAddYourself() {
        return "You cannot add yourself";
    }

}
