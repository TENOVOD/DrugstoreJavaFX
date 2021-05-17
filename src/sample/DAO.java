package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

interface DAO_Client<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://CourseWork.db");
        return result;
    }

    T create(T entity);

    boolean update(T entity);

    boolean delete(int ID);

    List<Client> find(String login, String password);

    List<Client> foundForId(int id);

    List<T> findAll();

}

interface DAO_Password<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://CourseWork.db");
        return result;
    }

    T create(T entity);
}

interface DAO_ID<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://CourseWork.db");
        return result;
    }

    T create(T entity);

    List<SaveID> find();

    boolean delete();
}

interface DAO_Admin<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://CourseWork.db");
        return result;
    }

    List<Admin> find();
}

interface DAO_Order<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://CourseWork.db");
        return result;
    }

    T create(T entity);

    List<Order> findAll();

}

interface DAO_Production<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:sqlite:e://CourseWork.db");
        return result;
    }

    T create(T entity);

    List<Production> findAll();

    List<Production> find(int num);

    boolean update(T entity);

}

class CLDAO_Password implements DAO_Password<SingUp> {
    Connection connect;

    @Override
    public SingUp create(SingUp entity) {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO PasswordClient (loginClient,passwordClient) VALUES (?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, entity.login);
            preparedStatement.setString(2, entity.password);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

}

class CLDAO_Client implements DAO_Client<Client> {

    Connection connect;

    @Override
    public Client create(Client entity) {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO Clients (Surname,Name,MiddleName,Number,Address,Login,Password) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, entity.getSurname());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getMiddleName());
            preparedStatement.setInt(4, entity.getPhoneNumber());
            preparedStatement.setString(5, entity.getAddress());
            preparedStatement.setString(6, entity.getLogin());
            preparedStatement.setString(7, entity.getPassword());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            entity.setId(key);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public boolean update(Client entity) {
        return false;
    }

    @Override
    public boolean delete(int ID) {
        return false;
    }

    @Override
    public List<Client> find(String login, String password) {
        List<Client> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM Clients WHERE Login LIKE '%" + login + "%' OR Password LIKE '%" + password + "%'";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String middleName = resultSet.getString(4);
                int number = resultSet.getInt(5);
                String address = resultSet.getString(6);
                String tempLogin = resultSet.getString(7);
                String tempPassword = resultSet.getString(8);
                Client client = new Client(name, surname, middleName, number, address, tempLogin, tempPassword);
                result.add(client);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Client> foundForId(int id) {
        List<Client> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM Clients WHERE  ID=" + String.valueOf(id);
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int tempId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String middleName = resultSet.getString(4);
                int number = resultSet.getInt(5);
                String address = resultSet.getString(6);
                String tempLogin = resultSet.getString(7);
                String tempPassword = resultSet.getString(8);
                Client client = new Client(name, surname, middleName, number, address, tempLogin, tempPassword);
                client.setId(tempId);
                result.add(client);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Client> findAll() {
        List<Client> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM Clients";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String middleName = resultSet.getString(4);
                int number = resultSet.getInt(5);
                String address = resultSet.getString(6);
                String Login = resultSet.getString(7);
                String Password = resultSet.getString(8);
                Client tempClient = new Client(name, surname, middleName, number, address, Login, Password);
                tempClient.setId(id);
                result.add(tempClient);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        Client client = new Client("Karpenko", "Mas2ha", "Ptrenko", 123123231, "St.Shevchenko", "ramara20121", "king2003");
        CLDAO_Client cldao_client = new CLDAO_Client();
        cldao_client.create(client);
        //System.out.println(cldao_client.findAll());
        //System.out.println(cldao_client.findAll());
        //System.out.println(cldao_client.foundForId(1));
    }
}

class CLDAO_Id implements DAO_ID<SaveID> {
    Connection connect;

    @Override
    public SaveID create(SaveID entity) {

        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO SavedID (ID) VALUES (?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<SaveID> find() {
        List<SaveID> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT *FROM SavedID";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                SaveID saveID = new SaveID(id);
                saveID.setId(id);
                result.add(saveID);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete() {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "DELETE FROM SavedID";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}

class CLDAO_Admin implements DAO_Admin<Admin> {
    Connection connect;

    @Override
    public List<Admin> find() {
        List<Admin> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM PasswordAdmin";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tempLogin = resultSet.getString(1);
                String tempPassword = resultSet.getString(2);
                Admin admin = new Admin(tempLogin, tempPassword);
                result.add(admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}

class CLDAO_Order implements DAO_Order<Order> {
    Connection connect;

    @Override
    public Order create(Order entity) {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO orders (date,sum,nameProducts,address,idClient) VALUES ( ?,?,?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, entity.getDate());
            preparedStatement.setDouble(2, entity.getSum());
            preparedStatement.setString(3, entity.getNameProducts());
            preparedStatement.setString(4, entity.getAddress());
            preparedStatement.setInt(5, entity.getIdClient());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM orders";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int num = resultSet.getInt(2);
                double sum = resultSet.getDouble(3);
                String nameProducts = resultSet.getString(4);
                String address = resultSet.getString(5);
                int idClient = resultSet.getInt(6);
                Order order = new Order(name, num, sum, nameProducts, address, idClient);
                order.setNumber(num);
                result.add(order);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}

class CLDAO_Production implements DAO_Production<Production> {
    Connection connect;

    @Override
    public Production create(Production entity) {
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "INSERT INTO production (name,price,quantity,manufactured,producingCountry) VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setString(4, entity.getManufactured());
            preparedStatement.setString(5, entity.getProducingCountry());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            entity.setNum(key);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<Production> findAll() {
        List<Production> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM production";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int num = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int quantity = resultSet.getInt(4);
                String manufactured = resultSet.getString(5);
                String producingCountry = resultSet.getString(6);
                Production production = new Production(num, name, price, quantity, manufactured, producingCountry);
                production.setNum(num);
                result.add(production);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Production> find(int num) {
        List<Production> result = new ArrayList<>();
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "SELECT * FROM production WHERE  num=" + String.valueOf(num);
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int tempNum = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int quantity = resultSet.getInt(4);
                String manufactured = resultSet.getString(5);
                String producingCountry = resultSet.getString(6);
                Production production = new Production(tempNum, name, price, quantity, manufactured, producingCountry);
                production.setNum(num);
                result.add(production);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Production entity) {
        boolean result = false;
        try {
            if (connect == null) {
                connect = getConnection();
            }
            String sql = "UPDATE production SET quantity=? WHERE num=?";
            PreparedStatement prepareStatement = connect.prepareStatement(sql);
            prepareStatement.setInt(1, entity.getQuantity());
            prepareStatement.setInt(2, entity.getNum());
            result = prepareStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        CLDAO_Production cldao_production = new CLDAO_Production();
        Production production = new Production("Солпадеїн", 50, 130, "Ukraine", "India"); //Ukraine Israel
        cldao_production.create(production);

    }
}
