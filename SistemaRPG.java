package org.example;

import java.util.Random;
import java.util.Scanner;

public class SistemaRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        Guerreiro guerreiro = new Guerreiro("Thor", 5);
        Mago mago = new Mago("Merlin", 5);
        Arqueiro arqueiro = new Arqueiro("Legolas", 5);


        System.out.println("Status Inicial dos Personagens:");
        guerreiro.exibirStatus();
        mago.exibirStatus();
        arqueiro.exibirStatus();
        System.out.println();


        Personagem[] personagens = {guerreiro, mago, arqueiro};


        while (personagensVivos(personagens) > 1) {
            for (Personagem atacante : personagens) {
                if (atacante.estaVivo()) {
                    Personagem alvo = selecionarInimigo(personagens, atacante);
                    if (alvo != null) {
                        int escolha = random.nextInt(2);
                        if (escolha == 0) {
                            atacante.atacar(alvo);
                        } else {
                            atacante.habilidadeEspecial(alvo);
                        }

                        System.out.println();
                        if (!alvo.estaVivo()) {
                            System.out.println(alvo.nome + " foi derrotado!");
                        }

                        if (personagensVivos(personagens) <= 1) {
                            break;
                        }
                    }
                }
            }
        }


        for (Personagem personagem : personagens) {
            if (personagem.estaVivo()) {
                System.out.println(personagem.nome + " é o vencedor!");
            }
        }

        scanner.close();
    }


    private static int personagensVivos(Personagem[] personagens) {
        int vivos = 0;
        for (Personagem p : personagens) {
            if (p.estaVivo()) {
                vivos++;
            }
        }
        return vivos;
    }


    private static Personagem selecionarInimigo(Personagem[] personagens, Personagem atacante) {
        Random random = new Random();
        Personagem inimigo;
        do {
            inimigo = personagens[random.nextInt(personagens.length)];
        } while (!inimigo.estaVivo() || inimigo == atacante);
        return inimigo;
    }
}
