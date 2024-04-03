package service;

import db.DBConn;
import dto.MenuDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    public int insertMenu(MenuDto dto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "insert into menu(name, price, stock) values(?,?,?)";
            psmt = conn.prepareStatement(query);
            psmt.setString(1,dto.name());
            psmt.setInt(2,dto.price());
            psmt.setInt(3,dto.stock());
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<MenuDto> getListAll(){
        List<MenuDto> dtoList = new ArrayList<>();
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            String query = "select * from menu";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                dtoList.add(MenuDto.allOf(
                        rs.getInt("menu_id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                ));
            }
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dtoList;
    }

    public int deleteMenu(int id) {
        int result = 0;
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        try {
            String query = "delete from menu where menu_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,id);
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void updateMenu(MenuDto dto,int id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;
        try {
            String query = "update menu set name=?, price=?, stock=? where menu_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1,dto.name());
            psmt.setInt(2,dto.price());
            psmt.setInt(3,dto.stock());
            psmt.setInt(4,id);
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public MenuDto searchOne(int id) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        MenuDto dto = null;
        try {
            String query = "select*from menu where menu_id=?";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1,id);
            rs = psmt.executeQuery();
            while (rs.next()){
                dto = MenuDto.allOf(
                        rs.getInt("menu_id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dto;
    }

    public List<MenuDto> menuList() {
        List<MenuDto> dtoList = new ArrayList<>();
        MenuDto dto = null;
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            String query ="select *from menu";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();
            while(rs.next()){
                dto = MenuDto.allOf(
                        rs.getInt("menu_id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                );
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dtoList;
    }


}
