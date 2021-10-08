
import java.net.*;
import java.io.*;

public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            while (true) {
                System.out.println("The Server is running....");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(),
                request.getLength(), request.getAddress(), request.getPort());
                String mensaje = new String(request.getData());
                //linea de abajo para imprimir la ip del cliente
                mensaje += " ip cliente: "+ request.getAddress();
                reply = new DatagramPacket(mensaje.getBytes(), mensaje.length(), request.getAddress(),request.getPort());
                aSocket.send(reply);
                /*System.out.println("The server is running...");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(),
                        request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);*/
            }
        } catch (SocketException e) {System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if (aSocket != null) aSocket.close();}
    }
}


