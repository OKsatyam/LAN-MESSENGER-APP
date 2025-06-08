import  java.io.*;
import  java.net.*;

class  Server {

    private Socket s= null ; 

    private  ServerSocket ss = null;

    private DataInputStream in = null;

    private DataOutputStream out = null;

    public Server(int port){
        try {
            
            ss = new ServerSocket(port); // getting the port number to establish conncection 
            System.out.println("Server Started... ");
            System.out.println("Waiting for the client to connect...");
            s=ss.accept();
            System.out.println("Connected");


            in = new DataInputStream(new BufferedInputStream(s.getInputStream())); // reading data
            out = new DataOutputStream(s.getOutputStream()); // sending data 


            String msgFromClient = "";
            String msgToClient = "";
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            while(!msgFromClient.equals("Over") && !msgToClient.equals("Over")){
                try {
                  // receving msg from client  
                    msgFromClient = in.readUTF();
                    System.out.println("Client: " + msgFromClient);

                if (msgFromClient.equals("Over"))
                    break;

                // 2. Send message to client
                System.out.print("Server: ");
                msgToClient = console.readLine();
                out.writeUTF(msgToClient);
                out.flush();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            System.out.println("Closing connection");
            s.close();
            in.close();
            out.close();
            ss.close();



        } catch (Exception e) {
            System.out.println(e);
        }
    }

     public static void main(String[] args) {
         Server s = new Server(5000);
     }


}