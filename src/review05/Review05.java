package review05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Review05 {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

             con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "UnoUsesql248");

             String sql = "SELECT * FROM person WHERE id = ?";
             pstm = con.prepareStatement(sql);

             System.out.print("検索キーワードを入力してください＞");
             String str = keyIn();
             int num = Integer.parseInt(str);

             pstm.setInt(1, num);

             rs = pstm.executeQuery();

             while (rs.next()) {
                 String name = rs.getString("name");
                 int age  = rs.getInt("age");

                 System.out.println(name);
                 System.out.println(age);
             }



        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                }catch(SQLException e) {
                    System.err.println("ResultSetを閉じる時にエラーが発生しました。");
                }
            }

            if(pstm != null) {
                try {
                    pstm.close();
                }catch(SQLException e){
                    System.err.println("PreparedStatementを閉じる時にエラーが発生しました。");

                }
            }
            if(con != null) {
                try {
                    con.close();
                    }catch(SQLException e) {
                        System.err.println("データベース切断時にエラーが発生しました。");
                        e.printStackTrace();
                    }
            }

        }

    }

    private static String keyIn() {
        String line =null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        }catch (IOException e) {

        }
        return line;
    }


}

