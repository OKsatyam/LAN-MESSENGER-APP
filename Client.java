import java.io.*;
import  java.net.*;

class Client{

    private Socket s= null ;


    private  DataInputStream in = null ; // from server

    private DataOutputStream    out = null ; // from server

     private BufferedReader console = null; // from keyboard

    


    public Client(String addr, int port){

            try {

                // connecting to the server
                s = new Socket(addr,port);
                System.out.println("Connected");
                
                // reading and sending data / reading data from serve
                console = new BufferedReader(new InputStreamReader(System.in));
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());


                
            } catch (Exception e) {

                System.out.println(e);
            }


            String msgToSend = "";
            String msgFromServer = "";

            

            while (!msgToSend.equals("Over") && !msgFromServer.equals("Over")) {
            try {
                // sending data
                System.out.print("Client: ");
                msgToSend = console.readLine();
                out.writeUTF(msgToSend);
                out.flush();
                // recieving data
                msgFromServer = in.readUTF();
                System.out.println("Server: " + msgFromServer);
            }
            catch (Exception i) {
                System.out.println(i);
            }

            
            }

            try {
            // closing all the connection 
            System.out.println("Closing connection");
            in.close();
            out.close();
            s.close();
            console.close();
            }
            catch (Exception i) {
                System.out.println(i);
            }
        

    }

      public static void main(String[] args) {
          Client c = new Client("127.0.0.1", 5000);
      }
}