import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public void run() throws IOException, SocketException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(100000);
        while (true) {
            try {
                System.out.println("Server is listening on port :" + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(),true);
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from the server");
                // toClient.close();
                // fromClient.close();
                // acceptedConnection.close();
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String str = scanner.nextLine();
                    toClient.println(str);
                    if (str.equals("exit")) {
                        System.out.println("connection end");
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                // socket.close();
            }
        }
    }

    public static void main(String args[]) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}