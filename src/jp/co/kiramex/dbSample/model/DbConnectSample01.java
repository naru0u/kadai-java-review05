package jp.co.kiramex.dbSample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample01 {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "UnoUsesql248"
                    );

            stmt = con.createStatement();

            String sql = "SELECT * FROM country LIMIT 50";
            rs = stmt.executeQuery(sql);

            while( rs.next()) {
                String name = rs.getString("Name");
                int population = rs.getInt("Population");

                System.out.println(name);
                System.out.println(population);
            }



        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }finally {
            if( rs != null) {
                try {
                    rs.close();
                }catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }
            if( stmt != null) {
                try {
                    stmt.close();
                }catch (SQLException e) {
                    System.err.println("Statementを閉じるときににエラーが発生しました。");
                    e.printStackTrace();
                }

            if( con != null) {
                try {
                    con.close();
                }catch (SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }



                }
            }





    }


