import java.io.*;
import java.net.*;
import java.util.Scanner;
class TCPClient {

    public static void main(String argv[])
    {
        String sentence;
        String modifiedSentence;

        try {

            Scanner inFromUser =  new Scanner (System.in) ;

            Socket clientSocket = new Socket("192.168.24.190", 10000);


            DataOutputStream outToServer =
                    new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));

            while(true)
            {

                System.out.println("plz insert something");

                sentence = inFromUser.nextLine();

                outToServer.writeBytes(sentence + '\n');

                modifiedSentence = inFromServer.readLine();

                String[] token= modifiedSentence.split("~`");

                for (int i =0;i<token.length;i++) {

                    System.out.println("FROM SERVER: " + token[i]);
                }

                //System.out.println("FROM SERVER: " + modifiedSentence);

                if(sentence.toLowerCase().equals("bye"))
                    break;

            }
            clientSocket.close();


        }catch ( ConnectException e)
        {
            System.out.println( " 404 C'ant connect to the Server");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

