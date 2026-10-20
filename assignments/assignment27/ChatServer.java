import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ChatServer {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        System.out.println("chat server starting on port " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("server waiting for client connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("client connected from: " + clientSocket.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.equalsIgnoreCase("/quit")) {
                            System.out.println("client has requested to end the chat");
                            break;
                        }
                        System.out.println("client: " + msg);
                    }
                } catch (SocketException e) {
                    System.out.println("client connection closed");
                } catch (Exception e) {
                    System.out.println("error receiving message: " + e.getMessage());
                }
            });
            receiveThread.start();

            String sendMsg;
            while ((sendMsg = console.readLine()) != null) {
                out.println(sendMsg);
                if (sendMsg.equalsIgnoreCase("/quit")) {
                    System.out.println("terminating server chat session");
                    break;
                }
            }

            try {
                clientSocket.close();
            } catch (Exception ignored) {
            }
            receiveThread.join(1000);
            System.out.println("chat server stopped cleanly");
        } catch (Exception e) {
            System.out.println("server error: " + e.getMessage());
        }
    }
}
