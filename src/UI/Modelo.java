package UI;

import Chat.Usuario;

public class Modelo{

    private InformationBank bank = new InformationBank();

    public Usuario getUsuario(String idU){
        return bank.getUsuario(idU);
    }

    public void agregarUsuario(Usuario usuario){
        bank.agregarUsuario(usuario);
    }

    public void eliminarUsuario(String usuario){
        bank.eliminarUsuario(usuario);
    }
}
