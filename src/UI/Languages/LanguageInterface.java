package UI.Languages;

public interface LanguageInterface {

    /**
     * Método que devuelve las cadenas en el idioma que tendrán por nombre los Labels a utilizar en la interfaz gráfica.
     * @return String[] - Arreglo con el nombre de las Labels en su respectivo idioma.
     */
    public String[] menuLabels();

    /**
     * Método que te devuelve una cadena de texto "Estas hablando con... " en el idioma especificado por el usuario.
     * @return String - Estas hablando con ....
     */
    public String senderDescription();

    /**
     * Método que te devuelve el texto del botón en el idioma especificado.
     * @return String - Enviar.
     */
    public String sendButtonLabel();

    /**
     * Método que te devuelve una cadena de texto "Estas conecto" en el idioma especificado por el usuario.
     * @return String  - Estas conectado.
     */
    public String setActive();

    /**
     * Método que te devuelve una cadena de texto "Cerrar Sesión" en el idioma especificado por el usuario.
     * @return String  - Cerrar Sesión.
     */
    public String logOut();

    /**
     * Método que te devuelve una cadena de texto "Añadir Contactos" en el idioma especificado por el usuario.
     * @return String  - Añadir Contactos
     */
    public String setContact();

    /**
     * Método que te devuelve una cadena de texto "Aviso de Información" en el idioma especificado por el usuario.
     * @return String - Aviso de Información.
     */
    public String informationNotice();

    /**
     * Método que te devuelve una cadena de texto "Agregar contacto" en el idioma especificado por el usuario.
     * @return String  - Agregar contacto
     */
    public String addContact();

    /**
     * Método que te devuelve una cadena de texto "El campo está vació" en el idioma especificado por el usuario.
     * @return String - El campo está vació
     */
    public String fieldEmpty();

    /**
     * Método que te devuelve una cadena de texto "Introduce el nombre de usuario" en el idioma especificado por el usuario.
     * @return String - Introduce el nombre de usuario
     */
    public String enterUserName();

    /**
     * Método que te devuelve una cadena de texto "El usuario no existe" en el idioma especificado por el usuario.
     * @return String  - El usuario no existe
     */
    public String userNameDontExit();

    /**
     * Método que te devuelve una cadena de texto "El usuario ya ha sido agregado" en el idioma especificado por el usuario.
     * @return String  - El usuario ya ha sido agregado
     */
    public String userHasAdded();

    /**
     * Método que te devuelve una cadena de texto "No te puedes agregar a tí mismo" en el idioma especificado por el usuario.
     * @return String  - No te puedes agregar a tí mismo
     */
    public String cannotAddYourself();


}
