package com.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sound.sampled.Port;

import org.json.JSONObject;

import com.sun.net.httpserver.*;

public class App {
       
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        HttpContext context = server.createContext("/example");
        HttpContext pj = server.createContext("/producer/join");
        HttpContext pl = server.createContext("/producer/leave");
        HttpContext cj = server.createContext("/consumer/join");
        HttpContext cc = server.createContext("/consumer/consume");
        HttpContext cl = server.createContext("/consumer/leave");
        pj.setHandler(App::handlepj);
        pl.setHandler(App::handlepl);
        cj.setHandler(App::handlecj);
        cc.setHandler(App::handlecc);
        cl.setHandler(App::handlecl);
        context.setHandler(App::handleRequest);
        server.start();
    }

    public  String teste(){
        return "Está a funcionar";
    }

    private static void handlepj(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        printRequestInfo(exchange);
        String response = "{\"result\": {\"code\": \"1\",\"log\": \"Producer failed to register: exception.......\"} }";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handlepl(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        printRequestInfo(exchange);
        String response = "{\"result\": {\"code\": \"0\",\"log\": \"Producer de-register with success\"} }";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handlecj(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        printRequestInfo(exchange);
        String response = "{\"result\": {\"code\": \"0\",\"log\": \"Consumer joined with success. Endpoints retrieved: 1.2.3.4:33\"} }";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handlecc(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        printRequestInfo(exchange);
        String response = "{\"result\": {\"code\": \"1\",\"log\": \"Failed to consume service npcf: no endpoints available\"} }";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handlecl(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        printRequestInfo(exchange);
        String response = "{\"result\": {\"code\": \"0\",\"log\": \"\"} }";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        printRequestInfo(exchange);
        String response = "This is the response at " + requestURI;
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    private static void printRequestInfo(HttpExchange exchange) {
        System.out.println("-- headers --");
        Headers requestHeaders = exchange.getRequestHeaders();
        requestHeaders.entrySet().forEach(System.out::println);
  
        System.out.println("-- principle --");
        HttpPrincipal principal = exchange.getPrincipal();
        System.out.println(principal);
  
        System.out.println("-- HTTP method --");
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);
  
        System.out.println("-- query --");
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        System.out.println(query);
    }
}
