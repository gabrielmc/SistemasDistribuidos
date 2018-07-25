package rmiCadAluno;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IAluno extends Remote {
    public String cadastrar(String nome,String matricula, String curso) throws Exception, RemoteException;
    public ArrayList<String> listar() throws Exception, RemoteException;
    public String buscarAluno(String matricula) throws Exception, RemoteException;
    public String excluir(String matricula) throws Exception, RemoteException;
}
