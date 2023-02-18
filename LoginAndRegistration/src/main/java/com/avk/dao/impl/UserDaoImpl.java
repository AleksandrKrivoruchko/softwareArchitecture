package com.avk.dao.impl;

import com.avk.connection.ConnectionManager;
import com.avk.dao.UserDao;
import com.avk.entity.User;
import org.springframework.util.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public User findByLogin(String login) {
        ConnectionManager cm = new ConnectionManager();
        Connection con = cm.getConnection();
        User user = null;
        if (con != null) {
            String sqlRequest = "SELECT * FROM user WHERE login=?";
            try {
                PreparedStatement pr = con.prepareStatement(sqlRequest);
                pr.setString(1, login);
                ResultSet resultSet = pr.executeQuery();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setLogin(login);
                    user.setPassword(resultSet.getString("password"));
                    pr.close();
                    con.close();
                    return user;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return user;
    }

    @Override
    public Boolean save(User user) {
        ConnectionManager cm = new ConnectionManager();
        Connection con = cm.getConnection();
        if (con != null) {
            String sqlRequest = "INSERT INTO user (name, surname, login, password)"
                    + " VALUES (?,?,?,?)";
            try {
                PreparedStatement pr = con.prepareStatement(sqlRequest);
                pr.setString(1, user.getName());
                pr.setString(2, user.getSurname());
                pr.setString(3, user.getLogin());
                pr.setString(4, DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                pr.executeUpdate();
                pr.close();
                con.close();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
