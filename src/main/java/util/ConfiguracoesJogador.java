package util;

import java.io.Serializable;

public class ConfiguracoesJogador implements Serializable {

    Modo modo;
    ImparOuPar imparOuPar;
    int numero;

    public ConfiguracoesJogador(Modo modo, ImparOuPar imparOuPar, int numero) {
        this.modo = modo;
        this.imparOuPar = imparOuPar;
        this.numero = numero;
    }

    public ImparOuPar getImparOuPar() {
        return imparOuPar;
    }

    public int getNumero() {
        return numero;
    }

    public Modo getModo() {
        return modo;
    }
}
