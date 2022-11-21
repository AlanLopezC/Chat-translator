package Chat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/*
* Clase que modela el comportamiento de paso de mensajes remotos.
*/
public class RemoteMessagePassing<T extends Serializable> {

	public Socket socket; // El socket usado para la comunicacion.
	public ObjectOutputStream out; // El objeto de salida
	public ObjectInputStream in; // El objeto de entrada

	/**
	 * Método constructor
	 * @param socket - Socket para enviar los objetos.
	 */
	public RemoteMessagePassing(Socket socket) {
		if (socket == null) {
			System.out.println("El socket esta vacio.");
			return;
		}
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para enviar un objeto. Se sincroniza con la variable sending.
	 * Se envía el objeto a traves del ObjectOutputStream out.
	 * @param obj Un objeto que se pretende enviar.
	 */
	public void send(T obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para recibir un objeto. Se sincroniza con la variable receiving.
	 * Se recibe el objeto a través del ObjectInputStream in.
	 * @return El objeto leído de in.
	 */
	public T receive() {
		T value = null;
		try {
			// Se interpreta el objeto que se leera en el ObjectInputStream in.
			value = (T) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/*
	 * Método que cierra la comunicación. Se cierra el socket
	 * y los ObjectOutputStream y ObjectInputStream.
	 */
	public void close() throws IOException {
		try {
			if (socket != null)
				socket.close();
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}