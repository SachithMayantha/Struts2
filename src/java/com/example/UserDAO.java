/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mayantha_f
 */
public class UserDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    // create variables to store JDBC urls
    private static final String INSERT_USERS_SQL = "insert into users"
            + "(id, username, password, department,valid) values" + "(?,?,?,?,?);";
    private static final String SELECT_USER_BY_ID = "select * from users where id=?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id =?;";
    private static final String UPDATE_USERS_SQL = "update users set username = ?,password = ?,department = ?,valid = ? where id = ?;";

// login function
    public static User login(User bean) {
        EncodeDecode ed = new EncodeDecode();

        // preparing some objects for connection
        Statement stmt = null;

        String username = bean.getUsername();

        String searchQuery = "select * from users where username='" + username + "';";

        try {
            // connect to DB
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            // Check the result set is empty or not
            rs = stmt.executeQuery(searchQuery);
            //get data row
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false);
            } else {
                String dbPassword = rs.getString("password");
                String userEnteredPassword = bean.getPassword();

                String decryptedPassword = ed.decode(dbPassword);
                bean.setValid(true);
                if (!userEnteredPassword.equals(decryptedPassword)) {
                    System.out.println("Incorrect password!");
                    bean.setValid(false);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        } // some exception handling
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {
                }

                currentCon = null;
            }
        }

        return bean;
    }

    public List<User> selectAllUsers() throws SQLException {
        System.out.println("UserDAO selectAllUsers() calleds");
        List<User> users = new ArrayList<>();
        // Establishing the connection
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement prepareStatement = currentCon.prepareStatement(SELECT_ALL_USERS);
            // execute the query or update the query
            ResultSet rs = prepareStatement.executeQuery();

            // Process the result set object
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String department = rs.getString("department");
                users.add(new User(id, username, password, department));
            }
        } catch (SQLException e) {
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        Boolean rowDeleted = null;
        try {
            currentCon = ConnectionManager.getConnection();
            // create a statement using connection object
            PreparedStatement statement = currentCon.prepareStatement(DELETE_USERS_SQL);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return rowDeleted;
    }

    public void insertUser(User user) throws SQLException {
        EncodeDecode ed = new EncodeDecode();
        String encoded;
        // Establishing a connection
        try {
            currentCon = ConnectionManager.getConnection();
            // create a statement using connection object
            PreparedStatement prepareStatement = currentCon.prepareStatement(INSERT_USERS_SQL);
            encoded = ed.encode(user.getPassword());
            prepareStatement.setInt(1, user.getId());
            prepareStatement.setString(2, user.getUsername());
            prepareStatement.setString(3, encoded);
            prepareStatement.setString(4, user.getDepartment());
            prepareStatement.setBoolean(5, true);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement statement = null;
        // Establishing a connection
        try {
            Connection connection = ConnectionManager.getConnection();
            // create a statement using connection object
            statement = connection.prepareStatement(UPDATE_USERS_SQL);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getDepartment());
            statement.setBoolean(4, true);
            statement.setInt(5, user.getId());
            // updating
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("updateUser() over");
    }
    public User selectUser(int id) {
		User user = null;
		// Establish the connection
		try {
			Connection connection = ConnectionManager.getConnection();
			// create a statement using connection object
			PreparedStatement prepareStatement = connection.prepareStatement(SELECT_USER_BY_ID);
			prepareStatement.setInt(1, id);
			System.out.println(prepareStatement);
			// execute the query
			ResultSet rs = prepareStatement.executeQuery();

			// Process the result set object
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String department = rs.getString("department");
				user = new User(id, username, password, department);
			}
		} catch (SQLException e) {
		}
		return user;

	}

}
