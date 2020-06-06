package DAO;
import Utils.DB;
import Entities.Usuario;
import Enums.Permissao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    public static boolean checkCredentials(String cpf, String password) throws SQLException, ClassNotFoundException {
        boolean credentials = false;

        //Inicializa conexão ao banco de dados
        DB connection = new DB();
        //Realiza consulta
        Statement stmt = connection.getConnection().createStatement();
        ResultSet rs=stmt.executeQuery("select * from user where cpf='"+cpf+"' and password='"+password+"'");

        if(rs.next())
            credentials = true;

        //Finaliza a conexão
        connection.closeConnection();

        return credentials;
    }
    public static Usuario getByCredentials(String cpf, String password) throws Exception {
        try
        {
            Usuario user = new Usuario();
            //Inicializo Conexão
            DB Connection = new DB();
            //Realiza consulta
            Statement stmt = Connection.getConnection().createStatement();
            String sql = "select * from user where cpf='"+cpf+"' and password='"+password+"'";
            ResultSet rs=stmt.executeQuery(sql);
            if(rs != null && rs.next()) {
                //Instancia atributos do Usuário que está logando
                user.setCpf(rs.getString("cpf"));
                user.setAdress(rs.getString("adress"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                deparaPermission(rs.getString("permission_id"), user);
                user.setWallet(null);
            }

            //Finaliza a conexão
            Connection.closeConnection();

            return user;
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    public static void add(Usuario usuario){
        try{
            DB connection = new DB();
            String sql = "INSERT INTO user (cpf, name, adress, password, birth, sex) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getName());
            stmt.setString(3, usuario.getAdress());
            stmt.setString(4, usuario.getPassword());
            stmt.setString(5, usuario.getBirth());
            stmt.setString(6, usuario.getSex().toString());
            stmt.execute();
            stmt.close();
            connection.closeConnection();
        } catch (SQLException | ClassNotFoundException e ) {
            System.out.println(e.getMessage());
        }
    }

    public static void deparaPermission(String permissionId, Usuario user)
    {
        if(permissionId == "1")
            user.setPermissao(Permissao.FULLACESS);
        if(permissionId == "2")
            user.setPermissao(Permissao.CADASTRO);
        if(permissionId == "3")
            user.setPermissao(Permissao.FINANCEIRO);
        if(permissionId == "4")
            user.setPermissao(Permissao.USUARIO);
    }
}
