package login;

// Importação das bibliotecas
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    // Método para estabelecer conexão com o banco de dados
    public Connection conectarBD(){
        Connection conn = null;
        try{
            // Carrega o driver JDBC
            Class.forName("com.mysql.Driver.Manager").newInstance();
            // Define a URL de conexão ao banco
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            // Estabelece a conexão e atribui à variável 'conn'
            conn = DriverManager.getConnection(url);
        }catch (Exception e) { 
            // Em caso de falha, não faz nada
        }
        
        // Retorna a conexão criada (ou null se falhou)
        return conn;
    }
    // Atributo para armazenar o nome do usuário autenticado
    public String nome="";
    // Atributo booleano que indica se a autenticação foi bem-sucedida
    public boolean result = false;
    // Método que verifica se o usuário com login e senha fornecidos existe no banco
    public boolean verificarUsuario(String login, String senha){
        String sql = "";
        Connection conn = conectarBD();
        // Monta manualmente a instrução SQL com as credenciais recebidas
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "'";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            // Se encontrou um usuário, define resultado como true e armazena o nome
            if(rs.next()){
                result = true;
                nome = rs.getString("nome");
            }
        }catch (Exception e) { // Erros também são ignorados silenciosamente
            }
        // Retorna o resultado da verificação    
        return result;
    }
}
