package ArvoresBinarias2025_2.src.app;
import ArvoresBinarias2025_2.src.lib.Aluno;
import ArvoresBinarias2025_2.src.lib.ArvoreBinaria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int escolha, balanceamento; //Iniciar variáveis de escolha
        ArvoreBinaria<Aluno> arv; // Instanciar Arvore binaria

        Scanner s = new Scanner(System.in);
        String menu = """
            Aplicativo de árvore binária
            1) Adicionar Aluno
            2) Pesquisar Aluno
            3) Pesquisar Aluno com Comparator
            4) Remover Aluno
            5) Caminha em ordem
            0) Sair""";

        String ordenamento = """ 
                Como deseja ordenar a Arvore?
                1) Matricula
                2) Nome"""; // Escolher qual parametro será usado para balancear a árvore
        System.out.println(ordenamento);
        balanceamento = s.nextInt();

        if (balanceamento == 1){
            arv = new ArvoreBinaria<>(new ComparadorAlunoPorMatricula()); // Balancear por matrícula
        } else{
            arv = new ArvoreBinaria<>(new ComparadorAlunoPorNome()); // Balancear por nome
        }


        do{
            System.out.println(menu);
            escolha = s.nextInt();
            
            switch (escolha){
                case 1 ->{
                    System.out.println("Digite a matrícula do Aluno: ");
                    int matricula = s.nextInt();
                    System.out.println("Digite o nome do Aluno: ");
                    String nome = s.nextLine();
                    Aluno aluno = new Aluno(matricula, nome);
                    arv.adicionar(aluno);
                }
                case 2 -> {
                    System.out.println("Escreva o valor que deseja pesquisar: ");


                }
                case 3-> {
                    System.out.println("Qual valor deseja usar na pesquisa? ");
                    System.out.println("1) Matricula");
                    System.out.println("2) Nome");
                    int comparatorPesquisa = s.nextInt();
                    if (comparatorPesquisa == 1){
                        System.out.println("Escreva o valor da matrícula: ");
                        int matricula = s.nextInt();
                        System.out.println(arv.pesquisar(, new ComparadorAlunoPorMatricula()));
                    }
                }
                case 4-> {
                    System.out.println("Qual valor deseja usar pra remover o aluno");
                    System.out.println("1) Matricula");
                    System.out.println("2) Nome");
                    int comparatorRemocao = s.nextInt();
                    if (comparatorRemocao == 1){
                        System.out.println("Escreva o valor da matrícula: ");
                        int matricula = s.nextInt();
                        arv.remover();
                    }
                    else{
                        System.out.println("Escreva o nome do aluno: ");
                        String nome = s.nextLine();
                        arv.remover();
                    }
                }
                case 5-> {
                    // Nao entendi esse aqui nao
                }
            }
        } while (escolha != 0);
    }


}
