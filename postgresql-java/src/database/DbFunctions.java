package database;


import java.sql.*;

public class DbFunctions {
    public Connection connecToDb(String dbname,String user,String password){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,password);
            if(conn!=null){
                System.out.println("Conexão estabelecida");
            }else{
                System.out.println("Conexão falhou");
            }

        }catch (Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
        return  conn;
    }

    public void createTable(Connection conn,String tableName){
        Statement statement;
        try{
            String query="create table IF NOT EXISTS "+tableName+"(id SERIAL PRIMARY KEY,name varchar(200),address varchar(200));";
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tabela criada");
        }catch (Exception e){
            System.out.println("Erro: "+ e.getMessage());
        }
    }

    public void insertRow(Connection conn, String name, String address) {
        String query = "INSERT INTO employee (name, address) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.executeUpdate();
            System.out.println("Linha inserida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }


    public void updateName(Connection conn, String oldName, String newName) {
        String query = "UPDATE employee SET name = ? WHERE name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, oldName);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println(rowsUpdated + " linha(s) atualizada(s).");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar nome: " + e.getMessage());
        }
    }

    public void deleteData(Connection conn, int id) {
        String query = "DELETE FROM EMPLOYEE where id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuário com id: "+ id +" Excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }



    public void searchByName(Connection conn, String name) {
        String query = "SELECT * FROM employee WHERE name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + ": " + rs.getObject(i) + " | ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }




    public void readData(Connection conn, String tableName) {
        ResultSet rs = null;
        String query = "SELECT * FROM " + tableName;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            rs = statement.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + ": " + rs.getObject(i) + " | ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("Erro ao ler dados: " + e.getMessage());
        }



    }



}
