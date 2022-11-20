package UI;

import Chat.Usuario;

public class IbankProxy {

    private InformationBank informationBank;
    private Usuario usuarioAgregado;
    private String usuarioEliminado;

    public IbankProxy(InformationBank infoBank){
        this.informationBank = infoBank;
    }
    public void agregarUsuario(Usuario usuario){
        usuarioAgregado = usuario;

    }
    public void eliminarUsuario(String usuario){
        usuarioEliminado = usuario;
    }
    public Usuario getUsuario(String usuarioId){
        return informationBank.getUsuario(usuarioId);
    }

    public void agregarUsuarioReal(){
        informationBank.agregarUsuario(usuarioAgregado);

    }
    public void eliminarUsuarioReal(){
        informationBank.eliminarUsuario(usuarioEliminado);
    }

}
