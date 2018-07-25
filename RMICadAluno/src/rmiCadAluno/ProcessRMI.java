package rmiCadAluno;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessRMI extends UnicastRemoteObject implements IAluno{
    
    List<Aluno> alunos;
    Conexao conn;
    
    public ProcessRMI() throws RemoteException{
        this.alunos = new ArrayList<>();
        this.conn = new Conexao();
    }
    
    @Override
    public String cadastrar(String nome, String matricula, String curso) {
        int res = this.conn.executeUpdate("INSERT INTO Aluno (nome,matricula,curso) VALUES ('" + nome + "','" + matricula + "','" + curso + "')");
        return (res >= 1) ? "Inserção realizada!" : "Inserção NÃO realizada!";
    }


    @Override
    public ArrayList<String> listar() throws Exception, RemoteException{
        ArrayList<String> alunos = new ArrayList<>();
        Aluno aluno = null;
        ResultSet consulta = this.conn.executeQuery("SELECT * FROM Aluno");
        while (consulta.next()) {
           aluno = new Aluno(consulta.getString("nome"), consulta.getString("matricula"), consulta.getString("curso"));
           alunos.add(aluno.toString());
        }
        return alunos;
}

    @Override
    public String excluir(String matricula) throws Exception, RemoteException {
        this.conn.executeUpdate("DELETE FROM Aluno WHERE matricula='"+matricula+"'");
        return "Excluido com sucesso!";
    }

    @Override
    public String buscarAluno(String matricula) throws Exception, RemoteException {
        try{
            Aluno aluno = null;
            ResultSet consulta = this.conn.executeQuery("SELECT nome, matricula, curso FROM Aluno WHERE matricula = '"+matricula+"'");
            if(consulta.next()){
                aluno = new Aluno(consulta.getString("nome"), consulta.getString("matricula"), consulta.getString("curso"));
                return aluno.toString();
            }
            return "ALUNO NÃO CADASTRADO NA BASE!!";
        } catch(SQLException erro){
            return null;
        }
    }

}
