package UI;
import Chat.*;
import javafx.stage.Stage;

public class Controlador{


    // private InformationBank informationBank = new InformationBank();
    private Vista vista;
    private Usuario usuario; //  = informationBank.getUsuario("jona123");
    public Controlador(){

    }

    public Controlador(Stage stage){
        vista = new Vista(this, stage);
        vista.CrearComponentesyVista();
    }
    
    public Usuario getUsuario(String un){

        usuario = Modelo.getUsuario(un);
        // return informationBank.getUsuario(un);
        return usuario;
    }
    
    public void salir(){
    System.exit(0);
    }



    public void entrarChat(){
        vista.crearElementos();
        vista.iniciarVistaChat();
    }

    public void Chatear(){
        vista.chatear();
    }



    
}
