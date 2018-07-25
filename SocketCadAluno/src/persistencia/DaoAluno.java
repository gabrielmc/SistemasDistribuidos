
package persistencia;

import conexao.Conexao;
import dominio.Aluno;
import dominio.IAluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoAluno implements IAluno{

    private Connection conn;
    private PreparedStatement stmt;
    static final String INSERT_ALUNO = "INSERT INTO Aluno (nome, matricula, curso) values (?, ?, ?) ";
    static final String DELETE_ALUNO = "DELETE Aluno WHERE matricula = ?";
    static final String BUSCA_ALUNO = "SELECT nome, matricula, curso FROM Aluno WHERE matricula = ?";
    
    public void conect() throws ClassNotFoundException{
        this.conn = Conexao.getConnection();
    }
    
    @Override
    public String buscarMatricula(String matricula) throws SQLException, ClassNotFoundException{
        try{
            this.conect();
            Aluno aluno = null;
            this.stmt = conn.prepareStatement(BUSCA_ALUNO);
            this.stmt.setString(1, matricula);
            ResultSet rSet = stmt.executeQuery();
            if(rSet.next()){
                aluno = new Aluno(rSet.getString("nome"), rSet.getString("matricula"), rSet.getString("curso"));
                rSet.close();
                this.stmt.close();
                this.conn.close();
                return aluno.toString();
            }
            return "ALUNO NÃO CADASTRADO NA BASE!!";
        } catch(SQLException erro){
            return null;
        }
    }

    @Override
    public boolean cadastrarAluno(Aluno aluno) throws SQLException, ClassNotFoundException{
        try{
            this.conect();
            this.stmt = conn.prepareStatement(INSERT_ALUNO);
            this.stmt.setString(1, aluno.getNome());
            this.stmt.setString(2, aluno.getMatricula());
            this.stmt.setString(3, aluno.getCurso());
            this.stmt.close();
            this.conn.close();
            return true;
        } catch(SQLException erro){
            return false;
        }
    }

    @Override
    public boolean deletarAluno(String matricula) throws SQLException, ClassNotFoundException {
         try{
            this.conect();
            this.stmt = conn.prepareStatement(DELETE_ALUNO);
            this.stmt.setString(1, matricula);
            this.stmt.execute();
            this.stmt.close();
            this.conn.close();
            return true;
        } catch(SQLException erro){
            return false;
        }
    }
    
}
