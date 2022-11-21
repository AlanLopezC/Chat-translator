package UI.BancoInformacion;

import Chat.Usuario;

public class IbankProxy implements InterfaceIBank {

    private InformationBank informationBank;
    private Usuario usuarioAgregado;
    private String usuarioEliminado;

    /**
     * Método que crea un objeto IbankProxy
     * @param infoBank - Banco de Información Real.
     */
    public IbankProxy(InformationBank infoBank){
        this.informationBank = infoBank;
    }

    @Override
    public void agregarUsuario(Usuario usuario){
        usuarioAgregado = usuario;
    }

    @Override
    public void eliminarUsuario(String usuario){
        usuarioEliminado = usuario;
    }

    @Override
    public Usuario getUsuario(String usuarioId){
        return informationBank.getUsuario(usuarioId);
    }

    /**
     * Método para agregar de verdad el usuario agregado al Banco de Información.
     */
    public void agregarUsuarioReal(){
        informationBank.agregarUsuario(usuarioAgregado);
    }

    /**
     * Método para eliminar de verdad el usuario eliminado en el Banco de Información.
     */
    public void eliminarUsuarioReal(){
        informationBank.eliminarUsuario(usuarioEliminado);
    }

}
