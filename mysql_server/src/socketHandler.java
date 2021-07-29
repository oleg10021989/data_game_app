import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.*;

public class socketHandler extends Thread {
    Socket incoming;
    private SQL sql;


    socketHandler(Socket _in,SQL sql)
    {
        incoming=_in;
        this.sql =sql;


    }

    public void run()
    {
        String clientSentence;
        String capitalizedSentence = null;

        try
        {

            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(incoming.getInputStream()));


            DataOutputStream  outToClient =
                    new DataOutputStream (incoming.getOutputStream() );

            while(true) {

                boolean istrue = false;
                String new_str;
                clientSentence = inFromClient.readLine();

                //System.out.println(clientSentence);

                //System.out.println(inFromClient);
                // all the work from the client

                //sql.delete_statement(clientSentence);
                //sql.insert_statement(clientSentence);
                if(clientSentence==null)break;
                System.out.println("start---------------");
                System.out.println("---------------"+clientSentence);



                new_str=sql.select_query(clientSentence);
//	         istrue=sql.update_statement(clientSentence);
//	         istrue=sql.insert_statement(clientSentence);
//	           System.out.println(new_str);
                System.out.println("end---------------");

                capitalizedSentence = new_str+"\n"; //MUST BE \N !!!!!!!!!!!!




//	           System.out.println(capitalizedSentence);
//	           capitalizedSentence = istrue+"\n"; //MUST BE \N !!!!!!!!!!!!
//	           System.out.println(capitalizedSentence);

                outToClient.writeBytes(capitalizedSentence);
//                outToClient.writeUTF(capitalizedSentence);


            }
        }
        catch(IOException e)
        {

        }

    }
}
