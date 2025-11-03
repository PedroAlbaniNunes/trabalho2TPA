/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArvoresBinarias2025_2.src.app;
//Lembre-se de ajustar os imports!!!!!


import ArvoresBinarias2025_2.src.lib.Aluno;
import ArvoresBinarias2025_2.src.lib.ArvoreAVL;
import ArvoresBinarias2025_2.src.lib.ArvoreBinaria;
import ArvoresBinarias2025_2.src.lib.IArvoreBinaria;

/**
 *
 * @author victoriocarvalho
 * 
 * Classe principal do aplicativo a ser utilizado para fazer o relatório do trabalho 
 * de árvore AVL
 */
public class AppRelatorioAVL {
    public static void main(String[] args) {

        GeradorDeArvores gerador = new GeradorDeArvores();
        ComparadorAlunoPorMatricula comparador = new ComparadorAlunoPorMatricula();
        IArvoreBinaria<Aluno> arv;

        arv = new ArvoreAVL(comparador);
        gerador.geraArvoreDegenerada(100, arv);
        System.out.println("Árvore AVL Criada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());
        arv = new ArvoreBinaria(comparador);
        gerador.geraArvoreDegenerada(100, arv);
        System.out.println("Árvore Degenerada Criada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());
        arv = new ArvoreBinaria(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 100, arv);
        System.out.println("Árvore Perfeitamente Balanceada gerada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());

        arv = new ArvoreAVL(comparador);
        gerador.geraArvoreDegenerada(1000, arv);
        System.out.println("Árvore AVL Criada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());
        arv = new ArvoreBinaria(comparador);
        gerador.geraArvoreDegenerada(1000, arv);
        System.out.println("Árvore Degenerada Criada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());
        arv = new ArvoreBinaria(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 1000, arv);
        System.out.println("Árvore Perfeitamente Balanceada gerada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());
        
        arv = new ArvoreAVL(comparador);
        gerador.geraArvoreDegenerada(10000, arv);
        System.out.println("Árvore AVL Criada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());
        arv = new ArvoreBinaria(comparador);
        gerador.geraArvoreDegenerada(10000, arv);
        System.out.println("Árvore Degenerada Criada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());
        arv = new ArvoreBinaria(comparador);
        gerador.geraArvorePerfeitamenteBalanceada(1, 10000, arv);
        System.out.println("Árvore Perfeitamente Balanceada gerada");
        System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ "\n Altura: " + arv.altura());


    }
}
