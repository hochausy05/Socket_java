import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 12345;

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in))) {

            String messageToServer;
            String messageFromServer;

            while (true) {
                // Nhập dữ liệu từ bàn phím và gửi tới server
                System.out.print("Client: ");
                messageToServer = inputFromUser.readLine();
                outputToServer.println(messageToServer);

                // Đọc dữ liệu từ server
                messageFromServer = inputFromServer.readLine();
                System.out.println("Server: " + messageFromServer);

                if (messageToServer.equals("bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


