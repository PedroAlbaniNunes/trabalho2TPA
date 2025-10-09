package ArvoresBinarias2025_2.src.app;

import ArvoresBinarias2025_2.src.lib.Aluno;
import ArvoresBinarias2025_2.src.lib.ArvoreBinaria;

/**
 * Classe para visualizar os primeiros nomes gerados na árvore
 */
public class Testes {
    public static void main(String[] args) {
        GeradorDeArvores gerador = new GeradorDeArvores();
        ComparadorAlunoPorMatricula comparador = new ComparadorAlunoPorMatricula();
        ArvoreBinaria<Aluno> arv;

        arv = new ArvoreBinaria<>(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 20, arv);

        // Mostrar os alunos em ordem (da menor para maior matrícula)
        String resultado = arv.caminharEmOrdem();
        String[] alunos = resultado.split("\n");

        for (String aluno : alunos) {
                System.out.println(aluno);
        }
    }
}
