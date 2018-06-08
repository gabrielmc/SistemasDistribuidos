import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

public class RmiClient
{
    static public void main(String args[])
    {
       String serverAddress =args[0];
       String serverPort    =args[1];
       String text          =args[2];

       System.out.println("sending "+text+" to "+serverAddress+":"+serverPort);

       try{
          Registry registry = LocateRegistry.getRegistry( serverAddress, ( new Integer(serverPort)).intValue() );
          ReceiveMessageInterface rmiServer = (ReceiveMessageInterface) (registry.lookup("rmiServer"));
          rmiServer.receiveMessage(text);
       
       }
       catch(RemoteException e){
           e.printStackTrace();
       }
       catch(NotBoundException e){
           e.printStackTrace();
       }
    }

}
