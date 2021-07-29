import java.io.*;
import java.net.*;

class TCPServer {

    public static void main(String argv[]) throws Exception
    {

        SQL sql =new SQL();

        ServerSocket s = null;
        try {
            s = new ServerSocket(10000);
            sql.ConectingToSQL();
            //sql.select_query();
            //db.connect();

        } catch(IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        while (true) {
            Socket incoming = null;

            try {
                incoming = s.accept();


            } catch(IOException e) {
                System.out.println(e);
                continue;
            }


            //if(incoming.equals(null)) break;

            new socketHandler(incoming,sql).start();
            //System.out.println(incoming);


        }
    }
}
