package dominio;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IAluno {
    public String buscarMatricula(String matricula) throws SQLException, ClassNotFoundException;
    public ArrayList<String> listar() throws Exception;
    public String cadastrarAluno(String nome, String matricula, String curso) throws SQLException, ClassNotFoundException;
    public String deletarAluno(String matricula) throws SQLException, ClassNotFoundException;
}
