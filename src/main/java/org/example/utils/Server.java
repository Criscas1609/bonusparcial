package org.example.utils;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server implements Runnable {

    private Socket socketDataBase;

    public Server(Socket socket) {
        this.socketDataBase = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socketDataBase.getInputStream()));
            PrintWriter out = new PrintWriter(socketDataBase.getOutputStream(), true);

            out.println("Conectado con éxito al servidor, ¿Desea ver la base de datos?");

            while (true) {
                String message = in.readLine();
                if (message == null || message.equals("No")||message.equals(" ")) {
                    System.out.println("El cliente no quiere ver la base de datos");
                    socketDataBase.close();
                    break;
                }
                if(message.equals("Si")) printData(out);
            }

            System.out.println("Se perdió la conexión con el cliente  " + socketDataBase.getInetAddress().getHostAddress());
            socketDataBase.close();

        } catch (IOException e) {
            System.out.println("Error inesperado " + e);
        }
    }

    private void printData(PrintWriter out) throws IOException {
        URL url = new URL("https://62a72da897b6156bff8967a6.mockapi.io/comida");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode !=200){
            throw new RuntimeException("Ocurrio un error al cargar la base de datos: "+responseCode);
        }else {
            StringBuilder info = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                info.append(scanner.next());
            }
            scanner.close();
            out.println(info);
        }
    }

}
