import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;

public class RmiServer extends java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface {
    Registry registro;

    public void receiveMessage(String x) throws RemoteException{
        System.out.println(x);
    }

    public void outMsg(){
        System.out.println("O endereco="+endereco+", porta ="+porta);
    }

    public RmiServer() throws RemoteException{
        try{
            String endereco = (InetRegistry.getLocalHost()).toString();
            int porta = 3232;
            this.outMsg();

            registro = LocateRegistry.createRegistry( porta );
            registro.rebind("rmiServer", this);


        }catch(Exception e){
            throw new RemoteException("can't get inet endereco.");
        }catch(RemoteException e){
            throw e;
        }
    }

    static public void main(String args[]){
        try{
            RmiServer s = new RmiServer();

        }catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}