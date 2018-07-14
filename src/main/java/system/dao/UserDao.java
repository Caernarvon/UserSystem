package system.dao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import system.model.User;
import javax.activation.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    //TODO Autowired database, work w. connection pool

    @Autowired
    private static UserDao userDao;

    private BasicDataSource dataSource;
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String PASSWORD = "root";
    private final String URL = "jdbc:postgresql://localhost:5432/users";

    private BasicDataSource getDataSource() {
        if (dataSource == null) {
            synchronized (this) {
                if (dataSource == null) {
                    dataSource = new BasicDataSource();
                    dataSource.setUrl(URL);
                    dataSource.setDriverClassName(DRIVER);
                    dataSource.setUsername(USER);
                    dataSource.setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() throws SQLException {
        try {
            Connection connection = getDataSource().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM public.\"Users\"");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2));
                users.add(user);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(User user) {
        try {
            Connection connection = getDataSource().getConnection();
            PreparedStatement pstmt =
                    connection.prepareStatement("INSERT INTO public.\"Users\"(username, password) VALUES (?,?)");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
