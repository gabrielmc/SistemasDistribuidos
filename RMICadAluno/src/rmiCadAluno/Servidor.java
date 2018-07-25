

package rmiCadAluno;

import java.rmi.registry.Registry;


public class Servidor {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Registry registro = java.rmi.registry.LocateRegistry.createRegistry(1099);
                    registro.rebind("ProcessRMI", new ProcessRMI());
                    System.out.println("Server conectado");
                } catch (Exception e) {
                    System.out.println("Server não conectado" + e);
                }
            }
        });
    }
}
