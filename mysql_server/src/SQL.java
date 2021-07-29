import java.sql.*;
import java.util.Scanner;

import com.mysql.jdbc.Connection;


public class SQL {

    private static Connection connect;

    public static void delete_statement(String str) {

        String sqldelete = "delete from game_data.steam where email = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sqldelete);

            pst.setString(1, str);

            pst.execute();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static boolean update_statement(String str) {

        String sqlupdate = "UPDATE game_data.steam SET Name =? , password =? , URL =?  WHERE email =?";
        String[] tokens = str.split(" ");

        try {
            PreparedStatement pst = connect.prepareStatement(sqlupdate);

//			System.out.println();

            pst.setString(1, tokens[0]);
            pst.setString(2, tokens[1]);
            pst.setString(3, tokens[2]);
            pst.setString(4, tokens[3]);

            pst.executeUpdate();
            return true;


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    public static boolean insert_statement(String str) {

        String sqlInsert = "insert into game_data.steam VALUES (?,?,?,?)";

        String[] tokens = str.split(" ");

        try {
            PreparedStatement pst = connect.prepareStatement(sqlInsert);
            pst.setString(1, tokens[0]);
            pst.setString(2, tokens[1]);
            pst.setString(3, tokens[2]);
            pst.setString(4, tokens[3]);
            pst.execute();
            return true;


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    public static String select_query(String str) {
        boolean istrue = false;
        String new_str = "null";
        //System.out.println(str+"!!!");
        String[] token = str.split(" ");

        System.out.println(token.length);

        if (token.length > 2) {
            for (int j = 2; token.length > j; j++) {
                token[1] += " " + token[j];
                System.out.println(token[1]);

            }

        }


        //if() {System.out.println(str+"???");}


        if (token.length >= 2) {
            try {
//				PreparedStatement statement = connect.prepareStatement("select * from game_data.steam");
                PreparedStatement statement = connect.prepareStatement("SELECT * FROM game_data.steam where " + token[0] + " like" + "\"%" + token[1] + "%\"");


                ResultSet result = statement.executeQuery();


                int i = 1, j = 0;
                while (result.next() && i < 50) {


                    if (new_str.equals("null")) {
                        //System.out.println(result.getString(2)+" "+result.getString(3)+" "+result.getString(1));
                        new_str = (result.getString(1) + "`~" + result.getString(2) + "`~" + result.getString(3) + "`~" + result.getString(4) + "`~" + result.getString(5) + "`~" + result.getString(6) + "`~" + result.getString(7) + "`~" + result.getString(8) + "`~" + result.getString(9) + "`~" + result.getString(10) + "`~" + result.getString(11) + "`~" + result.getString(12) + "`~" + result.getString(13) + "`~" + result.getString(14) + "`~" + result.getString(15) + "~`");
//						istrue = true;
                        //break;
                        System.out.println(result.getString(1) + ":" + i + "::" + result.getString(4));
                        i++;

                    } else {
                        new_str += (result.getString(1) + "`~" + result.getString(2) + "`~" + result.getString(3) + "`~" + result.getString(4) + "`~" + result.getString(5) + "`~" + result.getString(6) + "`~" + result.getString(7) + "`~" + result.getString(8) + "`~" + result.getString(9) + "`~" + result.getString(10) + "`~" + result.getString(11) + "`~" + result.getString(12) + "`~" + result.getString(13) + "`~" + result.getString(14) + "`~" + result.getString(15) + "~`");
                        System.out.println(result.getString(1) + ":" + i + "::" + result.getString(4));
                        i++;

                    }


                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (new_str.equals("null")){
                return " `~ `~ `~ `~ `~ `~ `~ `~ `~ `~ `~ `~ `~ `~https://m.media-amazon.com/images/I/51QvPEHteIL._SS500_.jpg~`";
            }else {
                return new_str;
            }

        }


//
//		String [] tokens = str.split(" ");
//
//		try {
//			PreparedStatement statement = connect.prepareStatement("select * from game_data.steam");
//
//			ResultSet result = statement.executeQuery();
//
//			while(result.next())
//			{
//
//
//
//				if(result.getString(3).equals(tokens[0])) {
//					//System.out.println(result.getString(2)+" "+result.getString(3)+" "+result.getString(1));
//					new_str=(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4));
//					istrue = true;
//					break;
//				}
//
//
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return new_str;
    }

    public static void connection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Works");


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void ConectingToSQL() {

        connection();
        //String host = "jdbc:mysql://127.0.0.1:3306/test";
        String host2 = "jdbc:mysql://127.0.0.1:3306/game_data?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "asdzxc12";


        try {
            connect = (Connection) DriverManager.getConnection(host2, username, password);
            System.out.println("work");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}


//public static void main(String[] argv)
//{

//ConectingToSQL();
//	select_query();
//	insert_statement();
//	select_query();
//update_statement();
//	delete_statement();
//	select_query();
//}

