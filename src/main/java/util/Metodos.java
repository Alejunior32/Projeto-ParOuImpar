package util;

import java.util.Scanner;

/**
 * Esta classe contém todos os métodos utilizados pelas classes Jogador e JogadorDois. São importantes para deixar o código mais legível.
 */
public class Metodos {

    /**
     * Escolhe o modo de jogo.
     *
     * @param scanner o objeto Scanner para leitura da informação passada pelo usuário.
     * @return um enum do tipo Modo representando o modo de jogo escolhido.
     */
    public Modo escolhaModoJogo(Scanner scanner){

        String escolha = scanner.nextLine();
        if (escolha.equals("1"))
            return Modo.JOGADOR_VS_CPU;
        else if (escolha.equals("2"))
            return Modo.JOGADOR_Vs_JOGADOR;

        return Modo.ESCOLHA_INVALIDA;
    }


    /**
     * Escolhe ímpar ou par.
     *
     * @param scanner o objeto Scanner para leitura da informação passada pelo usuário.
     * @return um enum do tipo ImparOuPar representando a escolha ímpar ou par.
     */
    public ImparOuPar escolherImparOuPar(Scanner scanner) {

        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("1"))
            return ImparOuPar.IMPAR;
        else if (resposta.equalsIgnoreCase("2"))
            return ImparOuPar.PAR;

        return ImparOuPar.ESCOLHA_INVALIDA;
    }

    /**
     * Escolhe ímpar ou par.
     *
     * @param imparOuParJogador1 a escolha do jogador 1 do tipo ImparOuPar.
     * @return um enum do tipo ImparOuPar representando a escolha ímpar ou par do jogador 2.
     */
    public ImparOuPar imparOuParJogador2(ImparOuPar imparOuParJogador1){

        if (imparOuParJogador1 == ImparOuPar.IMPAR)
            return ImparOuPar.PAR;

        return ImparOuPar.IMPAR;

    }

}
