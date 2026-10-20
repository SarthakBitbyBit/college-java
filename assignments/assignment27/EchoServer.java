import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
    private static final int PORT = 5002;

    public static void main(String[] args) {
        System.out.println("echo server listening on port " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("waiting for incoming client connection...");
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("client connected from: " + clientSocket.getRemoteSocketAddress());
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("received: " + line);
                        if (line.equalsIgnoreCase("/quit") || line.equalsIgnoreCase("exit")) {
                            out.println("goodbye");
                            break;
                        }
                        out.println("echo: " + line);
                    }
                    System.out.println("client session ended cleanly");
                } catch (SocketException e) {
                    System.out.println("client disconnected abruptly");
                }
                break;
            }
        } catch (Exception e) {
            System.out.println("echo server error: " + e.getMessage());
        }
        System.out.println("echo server shutdown complete");
    }
}
