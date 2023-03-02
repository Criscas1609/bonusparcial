package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Boolean b = validator();
        if(Boolean.TRUE.equals(b)) {
            Socket clientSocket = new Socket("localhost", 5050);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String message = userInput.readLine();
                out.println(message);
                System.out.println(in.readLine());
            }
        }else System.out.println("Error datos incorrectos");
    }
    public static Boolean validator(){
        Scanner lectura = new Scanner (System.in);
        System.out.println("Ingrese su nombre: ");
        String host = lectura.next();
        System.out.println("Ingrese el puerto: ");
        String port = lectura.next();
        if (host.equals("localhost") && port.equals("5050")) {
            System.out.println("Host y puerto correctos");
            return true;
        }
        return null;
    }
}
