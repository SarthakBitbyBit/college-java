import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5001;

    public static void main(String[] args) {
        System.out.println("connecting to chat server at " + HOST + ":" + PORT + "...");
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("connected to chat server. type messages or /quit to exit");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        if (msg.equalsIgnoreCase("/quit")) {
                            System.out.println("server closed the chat session");
                            break;
                        }
                        System.out.println("server: " + msg);
                    }
                } catch (SocketException e) {
                    System.out.println("connection terminated");
                } catch (Exception e) {
                    System.out.println("error receiving message: " + e.getMessage());
                }
            });
            receiveThread.start();

            String sendMsg;
            while ((sendMsg = console.readLine()) != null) {
                out.println(sendMsg);
                if (sendMsg.equalsIgnoreCase("/quit")) {
                    System.out.println("exiting chat application");
                    break;
                }
            }

            try {
                socket.close();
            } catch (Exception ignored) {
            }
            receiveThread.join(1000);
            System.out.println("chat client disconnected cleanly");
        } catch (Exception e) {
            System.out.println("unable to connect to chat server: " + e.getMessage());
        }
    }
}
