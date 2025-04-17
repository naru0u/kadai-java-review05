package jp.co.kiramex.dbSample.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DbConnectSample05 {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement spstmt = null;
        PreparedStatement ipstmt = null;
        ResultSet rs = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "UnoUsesql248"
                    );

            String selectSql = "SELECT * FROM city where CountryCode = ?";
            spstmt = con.prepareStatement(selectSql);

            System.out.print("CountryCodeを入力してください＞");
            String str1 = keyIn();

            spstmt.setString(1, str1);

            rs = spstmt.executeQuery();

            System.out.println("更新前＝＝＝＝＝＝＝＝＝＝＝＝＝");
            while( rs.next()) {
                String name = rs.getString("Name");
                String countryCode = rs.getString("CountryCode");
                String district = rs.getString("District");
                int population = rs.getInt("Population");

                System.out.println(name + "\t" + countryCode + "\t" + district + "\t" + population);
            }

            System.out.println("更新処理実行＝＝＝＝＝＝＝＝＝＝");

            String inserteSql = "INSERT INTO city (Name,CountryCode,District,Population)VALUES('Refah',?,'Refah',?)";
            ipstmt = con.prepareStatement(inserteSql);

            System.out.print("Populationを数字で入力してください＞");
            int num1 = keyInNum();

            ipstmt.setString(1,str1);
            ipstmt.setInt(2, num1);

            int count = ipstmt.executeUpdate();
            System.out.println("更新行数：" + count);

            rs.close();
            System.out.println("更新後＝＝＝＝＝＝＝＝＝＝＝＝＝");
            rs = spstmt.executeQuery();

            while( rs.next()) {
                String name = rs.getString("Name");
                String countryCode = rs.getString("CountryCode");
                String district = rs.getString("District");
                int population = rs.getInt("Population");

                System.out.println(name + "\t" + countryCode + "\t" + district + "\t" + population);

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
            if( ipstmt != null) {
                try {
                    ipstmt.close();
                }catch (SQLException e) {
                    System.err.println("PreparedStatementを閉じるときににエラーが発生しました。");
                    e.printStackTrace();
                }

                if( spstmt != null) {
                    try {
                        spstmt.close();
                    }catch (SQLException e) {
                        System.err.println("PreparedStatementを閉じるときににエラーが発生しました。");
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


            private static String keyIn() {
                String line = null;
                try {
                    BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
                    line = key.readLine();
                }catch (IOException e) {

                }
                return line ;
            }

            private static int keyInNum() {
                int result = 0;
                try {
                    result = Integer.parseInt(keyIn());
                }catch (NumberFormatException e) {

                }
                return result;
            }



    }


