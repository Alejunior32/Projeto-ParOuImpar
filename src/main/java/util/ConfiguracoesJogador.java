package util;

import java.io.Serializable;

/**
 * Classe que representa as configurações de um jogador.
 */
public class ConfiguracoesJogador implements Serializable {


    ImparOuPar imparOuPar;
    int numero;


    /**
     * Construtor da classe ConfiguracoesJogador.
     *
     * @param imparOuPar a escolha ímpar ou par do jogador.
     * @param numero     o número escolhido pelo jogador.
     */
    public ConfiguracoesJogador(ImparOuPar imparOuPar, int numero) {
        this.imparOuPar = imparOuPar;
        this.numero = numero;
    }

    /**
     * Obtém a escolha ímpar ou par da ConfiguracoesJogador.
     *
     * @return a escolha ímpar ou par do jogador.
     */
    public ImparOuPar getImparOuPar() {
        return imparOuPar;
    }

    /**
     * Obtém o número escolhido da ConfiguracoesJogador.
     *
     * @return o número escolhido pelo jogador.
     */
    public int getNumero() {
        return numero;
    }


}
