package service;

import db.DBConn;
import dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public static String userID;
    public static String password;
    public static int money;
    public int insertData(UserDto dto) {

        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
//        PreparedStatement psmt1 = null;
//        ResultSet rs = null;
        int result=0;
        try {
            String query = "insert into user(user_id, password, user_name, telnum) values(?,?,?,?)";
//            String query1 = "select user_id from user where user_id = ?";
            psmt = conn.prepareStatement(query);
//            psmt1 = conn.prepareStatement(query1);
            psmt.setString(1, dto.id());
            psmt.setString(2,dto.pw());
            psmt.setString(3, dto.name());
            psmt.setString(4, dto.tel());
//            psmt1.setString(1, dto.id());
            result = psmt.executeUpdate();
//            rs = psmt1.executeQuery();
//            if(rs.next()){
//                if(rs.getString(1).equals(dto.id())){
//                    // 아이디가 이미 존재할시
//                    return 3;
//                }
//            }
            psmt.close();
//            psmt1.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int loginData(String id, String pw){
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
//            String query = "select password from user where user_id=?";
            String query = "SELECT*from user where user_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            if(rs.next()){
                userID=rs.getString("user_id");
                money = rs.getInt("money");
                if(rs.getString("password").equals(pw)) {
                    // 로그인 성공시
                    return 1;

                }else {
                    return 2;
                }
            }
            psmt.close();

            return 3;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void updateMoney(int money, String id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        try {
            String query = "update user set money=money+? where user_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,money);
            psmt.setString(2,id);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateUserData(UserDto dto,String id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "update user set password=?, user_name=?, telnum=?, money=? where user_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, dto.pw());
            psmt.setString(2, dto.name());
            psmt.setString(3, dto.tel());
            psmt.setInt(4, dto.money());
            psmt.setString(5,id);
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public UserDto searchOne(String id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        UserDto dto = null;
        try {
            String query = "select * from user where user_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1,id);
            rs = psmt.executeQuery();
            while(rs.next()){
                dto = UserDto.allOf(
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("user_name"),
                        rs.getString("telnum"),
                        rs.getInt("money")
                );
            }
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dto;
    }

    public int deleteData(String id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result=0;
        try {
            String query = "delete from user where user_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1,id);
            result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void buyMenu(int price, int id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        try {
            String query = "update user set money=money-? where user_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,price);
            psmt.setString(2,userID);
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            String query = "update menu set stock=stock-1 where menu_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
