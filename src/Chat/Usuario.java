import java.util.*;
import java.net.*;
import java.io.*;
public class Usuario implements Observador,Serializable{
    private String idUsusario;
    private Server servidor;
    private int puertoCliente;
    private int puertoServer;
    

    public Usuario(String idU,int puertoServer, int puertoCliente){
        idUsusario = idU;
        this.puertoServer = puertoServer;
        this.puertoCliente = puertoCliente;
        
        
    }    
    public void iniciarServer(){
        servidor = Server.getServer(puertoServer);
        servidor.agregar(this);
        Thread t = new Thread(servidor);
        t.start();
    }



    public void enviarMensaje(String mensaje){ 
        Cliente c = new Cliente(puertoCliente,mensaje);
        Thread t = new Thread(c);
        t.start();
        
        /*c = new Cliente(puertoServer,mensaje);
        t = new Thread(c);
        t.start();*/
        
        
    }
    
    public void actualizar(){
        //System.out.print(servidor.getMensaje());
        Controller.receiveMessage(servidor.getMensaje());
        
    }

    
    public String getNombre(){
        return idUsusario;
    }

    public int getPuertoServer(){
        return puertoServer;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        boolean bandera = true;
        while(bandera){
        System.out.println("Bienvenido al chat privado...");
        System.out.println("1. iniciar chat. ");
        System.out.println("2. salir");
        String opcion = scn.nextLine();
        if(opcion.equals("1")){
            System.out.println("ingrese su nick");
            opcion = scn.nextLine();
            Usuario user = new Usuario(opcion, 5000,5001);
            user.iniciarServer(); 
            System.out.println("Chat de prueba");
            System.out.println("escriba: Salir para cerrar sesion xd");
            String mensaje = "";
            while(!mensaje.equals("Salir")){
            //Scanner scn = new Scanner(System.in);
            mensaje = scn.nextLine();
            if(!mensaje.equals("Salir")){
                mensaje = user.getNombre() +": "+ mensaje + "\n";
                user.enviarMensaje(mensaje);
            }
            
            }
        }else{
            bandera = false;
        }
        }


 



        //Usuario user = new Usuario(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        //user.iniciarServer(); 
        //user = new Usuario(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        //user.iniciarServer(); 
        /*System.out.println("Chat de prueba");
        while(true){
            //Scanner scn = new Scanner(System.in);
            String mensaje = scn.nextLine();
            mensaje = user.getNombre() +": "+ mensaje + "\n";
            user.enviarMensaje(mensaje);
        }*/


        



    }
}
