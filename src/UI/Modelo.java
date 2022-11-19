package UI;

import Chat.Usuario;

public class Modelo{

    private static InformationBank bank = new InformationBank();

    public static Usuario getUsuario(String idU){
        return bank.getUsuario(idU);
    }

    public static void agregarUsuario(Usuario usuario){
        bank.agregarUsuario(usuario);
    }

    public static void eliminarUsuario(String usuario){
        bank.eliminarUsuario(usuario);
    }
}
