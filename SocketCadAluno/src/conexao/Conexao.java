
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexao {
    private static final String URI = "jdbc:postgresql://localhost:5432/rmi";
    private static final String USER = "postgres";
    private static final String PWD = "1234";
    
    private Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(URI, USER, PWD);
            return con;
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "O driver não foi encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
 
    public int executeUpdate(String query){
        try{
            Connection con = getConnection();
            Statement stm = con.createStatement();
            int res = stm.executeUpdate(query);
            con.close();
            return res;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
 
    public ResultSet executeQuery(String query){
        try{
            Connection con = getConnection();
            Statement stm = con.createStatement();
            return stm.executeQuery(query);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public PreparedStatement getPreparedStatement(String sql){
        Connection con = getConnection();
        try{
            return con.prepareStatement(sql);
        }catch(SQLException e){
            System.out.println("Erro de sql:"+e.getMessage());
            return null;
        }
    }
}