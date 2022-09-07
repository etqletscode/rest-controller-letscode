package org.acme.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.enterprise.context.RequestScoped;

import io.quarkus.logging.Log;

@RequestScoped
public class GreetingService {

    public String hello() {
        Log.info("Endpoint '/hello' foi chamado");
        return "Hello!";
    }

    public String helloNome(String nome) {
        Log.info("Endpoint '/hello/{nome}' foi chamado");
        String texto = "Hello, " + nome + "!";
        return texto;
    }

    public String helloNomeHorario(String nome) {
        Log.info("Endpoint '/hello/{nome}/horario' foi chamado");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        String horario = LocalDateTime.now().format(df);
        String texto = String.format("Hello, %s!\nAgora são %s, não se esqueça!", nome, horario);
        return texto;
    }
}
