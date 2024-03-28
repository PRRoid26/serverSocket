
package com.mycompany.serversocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ServerSocket serverSocket; //responsible for listeing to client and creating socket object

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    public void startServer(){
        
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept(); //client connection 
                System.out.println("New Client Has Entered!!!");
                ClientHandler cliendHandler = new ClientHandler(socket); 
                Thread thread = new Thread(cliendHandler); //this will make threads for clients enabling it to taken in multiple clients
                thread.start();
            }
        }
        
        catch(IOException e){
            
        }
    }
    
    public void closeServerSocket(){
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException{
      
            ServerSocket serverSocket = new ServerSocket(1234);
            Server server = new Server(serverSocket);
            server.startServer();
        
    }
}
