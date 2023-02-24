package edu.eci.arep;

import java.io.IOException;

public class ClassGetter {
    public static String getInstance() throws IOException {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Reflective ChatGPT</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Parcial Santiago Fetecua</h1>"
                + "<h2>Ingrese el nombre de la clase</h2>"
                + "<input class='box' type='Text' id='consulta' placeholder='Nombre de la clase'>"
                + "<button  class='box' onclick='consultar()' class='btn'>Consultar</button>"
                + "<script>"
                + "function consultar(){"
                + "var consulta = document.getElementById('consulta').value;"
                + "var url = 'http://localhost:35000/consulta?comando=' + consulta;"
                + "var xhttp = new XMLHttpRequest();"
                + "xhttp.onreadystatechange = function() {"
                + "if (this.readyState == 4 && this.status == 200) {"
                + "document.getElementById('resultado').innerHTML = this.responseText;"
                + "}"
                + "};"
                + "xhttp.open('GET', url, true);"
                + "xhttp.send();"
                + "}"
                + "</script>"
                + "<div id='resultado'></div>"
                + "</body>\n"
                + "</html>\n";
    }

    public static String consultarClase(String url) throws IOException {
        String clase = url.split("=")[1];
        String resultado = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Reflective ChatGPT</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<script>"
                + "function consultar(){"
                + "var consulta = document.getElementById('consulta').value;"
                + "var url = 'http://localhost:35000/consulta?comando=' + consulta;"
                + "var xhttp = new XMLHttpRequest();"
                + "xhttp.onreadystatechange = function() {"
                + "if (this.readyState == 4 && this.status == 200) {"
                + "document.getElementById('resultado').innerHTML = this.responseText;"
                + "}"
                + "};"
                + "xhttp.open('GET', url, true);"
                + "xhttp.send();"
                + "}"
                + "</script>"
                + "<div id='resultado'>"
                + "<h2>Clase: " + clase + "</h2>"
                + "<h2>Metodos: </h2>"
                + "<ul>";
        try {
            Class<?> c = Class.forName(clase);
            for (java.lang.reflect.Method m : c.getDeclaredMethods()) {
                resultado += "<li>" + m.getName() + "</li>";
            }
            resultado += "</ul>"
                    + "</div>"
                    + "</body>\n"
                    + "</html>\n";
        } catch (ClassNotFoundException e) {
            resultado = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "<title>Reflective ChatGPT</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<script>"
                    + "function consultar(){"
                    + "var consulta = document.getElementById('consulta').value;"
                    + "var url = 'http://localhost:35000/consulta?comando=' + consulta;"
                    + "var xhttp = new XMLHttpRequest();"
                    + "xhttp.onreadystatechange = function() {"
                    + "if (this.readyState == 4 && this.status == 200) {"
                    + "document.getElementById('resultado').innerHTML = this.responseText;"
                    + "}"
                    + "};"
                    + "xhttp.open('GET', url, true);"
                    + "xhttp.send();"
                    + "}"
                    + "</script>"
                    + "<div id='resultado'>"
                    + "<h2>Clase: " + clase + "</h2>"
                    + "<h2>Metodos: </h2>"
                    + "<ul>"
                    + "<li>La clase no existe</li>"
                    + "</ul>"
                    + "</div>"
                    + "</body>\n"
                    + "</html>\n";
        }
        return resultado;

    }
}
