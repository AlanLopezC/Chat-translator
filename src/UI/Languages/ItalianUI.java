package UI.Languages;

public class ItalianUI implements LanguageInterface {
    /**
     * Método que devuelve las cadenas en el idioma que tendrán por nombre los
     * Labels a utilizar en la interfaz gráfica.
     * 
     * @return String[] - Arreglo con el nombre de las Labels en su respectivo
     *         idioma.
     */
    @Override
    public String[] menuLabels() {
        String[] labels = { "Contatti", "Configurazione" };
        return labels;
    }

    /**
     * Método que te devuelve una cadena de texto "Estas hablando con... "
     * en el idioma especificado por el usuario.
     * 
     * @return String - Estas hablando con ....
     */
    @Override
    public String senderDescription() {
        return "Stai parlando con ";
    }

    /**
     * Método que te devuelve el texto del botón en el idioma especificado.
     * 
     * @return String - Enviar.
     */
    @Override
    public String sendButtonLabel() {
        return "Enviare";
    }

    /**
     * Método que te devuelve una cadena de texto "Estas conecto" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Estas conectado.
     */
    @Override
    public String setActive() {
        return "Sei connesso";
    }

    /**
     * Método que te devuelve una cadena de texto "Cerrar Sesión" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Cerrar Sesión.
     */
    @Override
    public String logOut() {
        return "Disconnettersi";
    }

    /**
     * Método que te devuelve una cadena de texto "Añadir Contactos" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Añadir Contactos
     */
    @Override
    public String setContact() {
        return "Aggiungi contatti";
    }

    /**
     * Método que te devuelve una cadena de texto "Aviso de Información" en el
     * idioma especificado por el usuario.
     * 
     * @return String - Aviso de Información.
     */
    @Override
    public String informationNotice() {
        return "Avviso informativo";
    }

    /**
     * Método que te devuelve una cadena de texto "Agregar contacto" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Agregar contacto
     */
    @Override
    public String addContact() {
        return "Aggiungi contatto";
    }

    /**
     * Método que te devuelve una cadena de texto "El campo está vació" en el idioma
     * especificado por el usuario.
     * 
     * @return String - El campo está vació
     */
    @Override
    public String fieldEmpty() {
        return "Il campo è vuoto";
    }

    /**
     * Método que te devuelve una cadena de texto "Introduce el nombre de usuario"
     * en el idioma especificado por el usuario.
     * 
     * @return String - Introduce el nombre de usuario
     */
    @Override
    public String enterUserName() {
        return "Inserisci il nome utente";
    }

    /**
     * Método que te devuelve una cadena de texto "El usuario no existe" en el
     * idioma especificado por el usuario.
     * 
     * @return String - El usuario no existe
     */
    @Override
    public String userNameDontExit() {
        return "Lo username non esiste.";
    }

    /**
     * Método que te devuelve una cadena de texto "El usuario ya ha sido agregado"
     * en el idioma especificado por el usuario.
     * 
     * @return String - El usuario ya ha sido agregado
     */
    @Override
    public String userHasAdded() {
        return "L'utente è già stato aggiunto";
    }

    /**
     * Método que te devuelve una cadena de texto "No te puedes agregar a tí mismo"
     * en el idioma especificado por el usuario.
     * 
     * @return String - No te puedes agregar a tí mismo
     */
    @Override
    public String cannotAddYourself() {
        return "Non puoi aggiungere te stesso";
    }
}
