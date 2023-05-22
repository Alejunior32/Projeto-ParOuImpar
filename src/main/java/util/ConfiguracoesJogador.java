package util;

import java.io.Serializable;

public class ConfiguracoesJogador implements Serializable {


    ImparOuPar imparOuPar;
    int numero;

    public ConfiguracoesJogador(ImparOuPar imparOuPar, int numero) {
        this.imparOuPar = imparOuPar;
        this.numero = numero;
    }

    public ImparOuPar getImparOuPar() {
        return imparOuPar;
    }

    public int getNumero() {
        return numero;
    }


}
