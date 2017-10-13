/**
 * SMTP - Mail User Agent
 * EmailSender.java
 * Nischal Paudyal
 * October 13, 2017
 */

import java.io.*;
import java.net.*;

public class EmailSender
{
    public static void main(String[] args) throws Exception
    {
        // Establish a TCP connection with the Gmail server.
        Socket socket = new Socket("gmail-smtp-in.l.google.com", 25);

        // Create a BufferedReader to read a line at a time.
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // Read greeting from the server.
        String response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("220")) {
            throw new Exception("220 reply not received from server.");
        }

        // Get a reference to the socket's output stream.
        OutputStream os = socket.getOutputStream();

        // Send HELO command and get server response.
        String command = "HELO x\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send MAIL FROM command.
        command = "MAIL FROM: <email> x\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }



        // Send RCPT TO command.
        command = "RCPT TO: <email> x\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send DATA command.
        command = "DATA x\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
       if (!response.startsWith("354")) {
          throw new Exception("354 reply not received from server.");
        }
        //  To: From: Subject:
        // Send message data.
        String message = "To: znischal2000@gmail.com\n"
                        + "From: paudyaln@wit.edu\n"
                        + "Subject: Test SMTP\n"
                        + "This is the test program\n"
                        + "Name: Nischal Paudyal\n"
                        + "Section: 09\n"
                        + "Project: SMTP Mail User Agent\r\n";
        System.out.println(message);
        os.write(message.getBytes("US-ASCII"));



        // End with line with a single period.
        command = ".\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }
        // Send QUIT command.
        String quit = "QUIT x\r\n";
        System.out.print(quit);
        os.write(quit.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("221")) {
            throw new Exception("221 reply not received from server.");
        }
        socket.close();

    }

}

