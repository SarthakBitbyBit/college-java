import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5002;

    public static void main(String[] args) {
        System.out.println("connecting to echo server at " + HOST + ":" + PORT + "...");
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("connected to echo server. enter messages (type /quit or exit to finish):");
            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();
                System.out.println("server replied -> " + response);
                if (userInput.equalsIgnoreCase("/quit") || userInput.equalsIgnoreCase("exit")) {
                    break;
                }
            }
            System.out.println("echo client disconnected cleanly");
        } catch (Exception e) {
            System.out.println("echo client connection error: " + e.getMessage());
        }
    }
}
