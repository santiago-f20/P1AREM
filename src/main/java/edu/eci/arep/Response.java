package edu.eci.arep;

import java.io.IOException;

import java.io.PrintWriter;

import java.net.Socket;

public class Response {

    public static void response(Socket clientSocket, String input) {
        String url;
        if (input.contains("GET")) {
            url = input.split(" ")[1].substring(1);
            try {
                if (url.contains("consulta?comando=")) {
                    consulta(clientSocket, ClassGetter.consultarClase(url));
                } else {
                    System.out.println("Opcion Invalida.");
                    consulta(clientSocket, ClassGetter.getInstance());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void consulta(Socket clientSocket, String url) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(url);
        out.close();
    }
}
