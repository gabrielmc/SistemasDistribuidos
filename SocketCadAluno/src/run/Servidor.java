
package run;

import config.Config;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
        
public class Servidor {
    
    public static void main(String[] args) throws IOException{
        ServerSocket servidor = new ServerSocket(Config.IP);
        System.out.println("Servidor ligado");
        Socket socket = null;
        while(true){
            socket = servidor.accept();
            Thread thr = new Thread( new ProcessClass(socket));
            thr.start();
            System.out.println("Processo finalizado...\n\n\n\n");
        }
    }
    
}
