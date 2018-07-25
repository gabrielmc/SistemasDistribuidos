
package run;

import conexao.Conexao;
import dominio.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProcessClass implements IAluno,Runnable{
   
    private Conexao conn;
    private PreparedStatement stmt;
    private Socket cliente;
    private ArrayList<String> alunos;
        
    public ProcessClass(Socket socket) throws IOException{
        this.alunos = new ArrayList<>();
        this.cliente = socket;
        this.conn = new Conexao();
    }
    
    @Override
    public String buscarMatricula(String matricula) throws SQLException, ClassNotFoundException{
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

    @Override
    public String cadastrarAluno(String nome, String matricula, String curso) throws SQLException, ClassNotFoundException{  
        int res = this.conn.executeUpdate("INSERT INTO Aluno (nome,matricula,curso) VALUES ('" + nome + "','" + matricula + "','" + curso + "')");
        return (res >= 1) ? "Inserção realizada!" : "Inserção NÃO realizada!";   
    }

    @Override
    public String deletarAluno(String matricula) throws SQLException, ClassNotFoundException {
       this.conn.executeUpdate("DELETE FROM Aluno WHERE matricula='"+matricula+"'");
        return "Excluido com sucesso!";
    }

    @Override
    public ArrayList<String> listar() throws Exception {
        ResultSet consulta = this.conn.executeQuery("SELECT * FROM Aluno");
        while (consulta.next()) {
           alunos.add(new Aluno(consulta.getString("nome"), consulta.getString("matricula"), consulta.getString("curso")).toString() );
        }
        return alunos;
    }
    @Override
    public void run() {
        while (true) {
            try {
                DataOutputStream mandaParaCliente = new DataOutputStream(this.cliente.getOutputStream());
                DataInputStream lerDoCliente = new DataInputStream(this.cliente.getInputStream());
                int op = lerDoCliente.readInt();
                if (op == 1) {
                    String nome = lerDoCliente.readUTF();
                    String matricula = lerDoCliente.readUTF();
                    String curso = lerDoCliente.readUTF();
                    cadastrarAluno(nome, matricula, curso);
                    mandaParaCliente.writeUTF("Inserção realizada com sucesso!");
                    mandaParaCliente.flush();
                }
                if (op == 2) {
                    ArrayList<String> alunos = listar();
                    String alunoList = "";
                    for(int x=0; x<alunos.size(); x++){
                        alunoList += x+1+" - ";
                        alunoList += alunos.get(x);
                        alunoList += "\n\n";
                    }
                    mandaParaCliente.writeUTF(alunoList);
                    mandaParaCliente.flush();
                }
                if (op == 3) {
                    String matricula = lerDoCliente.readUTF();
                    mandaParaCliente.writeUTF(this.buscarMatricula(matricula));
                    mandaParaCliente.flush();
                }
                if (op == 4) {
                    String matricula = lerDoCliente.readUTF();
                    this.deletarAluno(matricula);
                    mandaParaCliente.writeUTF("Exclusão realizada com sucesso!");
                    mandaParaCliente.flush();
                }
            } catch (Exception erro) {
                Logger.getLogger(ProcessClass.class.getName()).log(Level.SEVERE, null, erro);
            }
        }
    }
}
