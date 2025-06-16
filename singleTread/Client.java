import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toSocket.println("Hello from client");

        System.out.println("response from socket");
        while (true) {
            String str = fromSocket.readLine();
            if (str.equals("exit")) {
                System.out.println("Connection end");
                break;
            }
            System.out.println(str);
        }
        
        // toSocket.close();
        // fromSocket.close();
        // socket.close();
    }

    public static void main(String args[]) {
        try {
            Client c = new Client();
            c.run();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
