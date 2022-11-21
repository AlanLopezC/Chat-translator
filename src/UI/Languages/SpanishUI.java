package UI.Languages;

public class SpanishUI implements LanguageInterface {
    /**
     * Método que devuelve las cadenas en el idioma que tendrán por nombre los
     * Labels a utilizar en la interfaz gráfica.
     * 
     * @return String[] - Arreglo con el nombre de las Labels en su respectivo
     *         idioma.
     */
    @Override
    public String[] menuLabels() {
        String[] labels = { "Contactos", "Configuración" };
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
        return "Estás hablando con ";
    }

    /**
     * Método que te devuelve el texto del botón en el idioma especificado.
     * 
     * @return String - Enviar.
     */
    @Override
    public String sendButtonLabel() {
        return "Enviar";
    }

    /**
     * Método que te devuelve una cadena de texto "Estas conecto" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Estas conectado.
     */
    @Override
    public String setActive() {
        return "Estas conectado";
    }

    /**
     * Método que te devuelve una cadena de texto "Cerrar Sesión" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Cerrar Sesión.
     */
    @Override
    public String logOut() {
        return "Cerrar Sesión";
    }

    /**
     * Método que te devuelve una cadena de texto "Añadir Contactos" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Añadir Contactos
     */
    @Override
    public String setContact() {
        return "Añadir Contactos";
    }

    /**
     * Método que te devuelve una cadena de texto "Aviso de Información" en el
     * idioma especificado por el usuario.
     * 
     * @return String - Aviso de Información.
     */
    @Override
    public String informationNotice() {
        return "Aviso de Información";
    }

    /**
     * Método que te devuelve una cadena de texto "Agregar contacto" en el idioma
     * especificado por el usuario.
     * 
     * @return String - Agregar contacto
     */
    @Override
    public String addContact() {
        return "Agregar contacto";
    }

    /**
     * Método que te devuelve una cadena de texto "El campo está vació" en el idioma
     * especificado por el usuario.
     * 
     * @return String - El campo está vació
     */
    @Override
    public String fieldEmpty() {
        return "El campo está vació";
    }

    /**
     * Método que te devuelve una cadena de texto "Introduce el nombre de usuario"
     * en el idioma especificado por el usuario.
     * 
     * @return String - Introduce el nombre de usuario
     */
    @Override
    public String enterUserName() {
        return "Introduce el nombre de usuario";
    }

    /**
     * Método que te devuelve una cadena de texto "El usuario no existe" en el
     * idioma especificado por el usuario.
     * 
     * @return String - El usuario no existe
     */
    @Override
    public String userNameDontExit() {
        return "El usuario no existe";
    }

    /**
     * Método que te devuelve una cadena de texto "El usuario ya ha sido agregado"
     * en el idioma especificado por el usuario.
     * 
     * @return String - El usuario ya ha sido agregado
     */
    @Override
    public String userHasAdded() {
        return "El usuario ya ha sido agregado";
    }

    /**
     * Método que te devuelve una cadena de texto "No te puedes agregar a tí mismo"
     * en el idioma especificado por el usuario.
     * 
     * @return String - No te puedes agregar a tí mismo
     */
    @Override
    public String cannotAddYourself() {
        return "No te puedes agregar a tí mismo";
    }

}
