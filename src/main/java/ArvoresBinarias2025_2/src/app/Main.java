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
            \n-----------------------------
            Aplicativo de árvore binária
            1) Adicionar Aluno
            2) Pesquisar Aluno com a ordenação da árvore
            3) Pesquisar Aluno com Comparator
            4) Remover Aluno
            5) Caminhar em ordem
            6) Caminhar em nível
            0) Sair
            -----------------------------
            Escolha uma opção:""";

        String ordenamento = """ 
                ------------------------------
                Como deseja ordenar a Arvore?
                1) Matricula
                2) Nome
                ------------------------------
                Escolha uma opção:"""; // Escolher qual parametro será usado para balancear a árvore
        System.out.println(ordenamento);
        balanceamento = s.nextInt();
        s.nextLine();

        if (balanceamento == 1){
            arv = new ArvoreBinaria<>(new ComparadorAlunoPorMatricula()); // Balancear por matrícula
            System.out.println("Árvore será balanceada por MATRÍCULA");
        } else{
            arv = new ArvoreBinaria<>(new ComparadorAlunoPorNome()); // Balancear por nome
            System.out.println("Árvore será balanceada por NOME");
        }

        String tipoCriacaoArvore = """
                \n-----------------------------
                Qual tipo de árvore deseja criar?
                1) Árvore Degenerada
                2) Árvore Perfeitamente Balanceada
                3) Árvore vazia e inserir manualmente
                -----------------------------
                Escolha uma opção:""";
        System.out.println(tipoCriacaoArvore);
        int tipoArvore = s.nextInt(); //vou usar o gerador de árvore do victorio
        s.nextLine();
        if (tipoArvore == 1 || tipoArvore == 2){
            System.out.println("\nQuantos elementos deseja inserir na árvore?"); //os métodos precisam da quantidade pra passar como parâmetro
            int quantidade = s.nextInt();
            s.nextLine();
            GeradorDeArvores gerador = new GeradorDeArvores();
            if (tipoArvore == 1){
                gerador.geraArvoreDegenerada(quantidade, arv); //degenerada só recebe a quantidade e a árvore
                System.out.println("Árvore Degenerada Criada");
            } else{
                gerador.geraArvorePerfeitamenteBalanceada(1, quantidade, arv); //perfeitamente balanceada recebe a mesma coisa, mas o primeiro parâmetro é sempre 1 pra ser a rAIZ
                System.out.println("Árvore Perfeitamente Balanceada Criada");
            }
        } else{
            System.out.println("Árvore vazia criada. Insira os alunos manualmente.");
        }

        do {
            System.out.println(menu);
            escolha = s.nextInt();
            s.nextLine();

            switch (escolha){
                case 1: { //adicionar aluno
                    System.out.print("Digite a matrícula do Aluno: ");
                    int matricula = s.nextInt();
                    s.nextLine();

                    System.out.print("Digite o nome do Aluno: ");
                    String nome = s.nextLine();

                    Aluno aluno = new Aluno(matricula, nome);
                    arv.adicionar(aluno);
                    System.out.println("Aluno adicionado!");
                    break;
                }
                case 2: { //pesquisar pelo balanceamento da árvore
                    System.out.println("Pesquisando pelo balanceamento da árvore...");
                    Aluno alunoParaBusca;
                    if (balanceamento == 1) { //se o critério de balanceamento for a matrícula
                        System.out.print("Digite a MATRÍCULA para pesquisar: ");
                        int matricula = s.nextInt();
                        s.nextLine();
                        alunoParaBusca = new Aluno(matricula, "");
                    } else { //se o critério de balanceamento for o nome
                        System.out.print("Digite o NOME para pesquisar: ");
                        String nome = s.nextLine();
                        alunoParaBusca = new Aluno(0, nome);
                    }

                    Aluno alunoEncontrado = arv.pesquisar(alunoParaBusca);
                    if(alunoEncontrado != null) {
                        System.out.println("Encontrado: " + alunoEncontrado);
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                }
                case 3: { //pesquisar com 0 comparator
                    System.out.println("Qual critério deseja usar na pesquisa?");
                    System.out.println("1) Por Matrícula");
                    System.out.println("2) Por Nome");
                    int comparatorPesquisa = s.nextInt();
                    s.nextLine();

                    Aluno alunoEncontrado;
                    if (comparatorPesquisa == 1){
                        System.out.print("Digite o valor da matrícula: ");
                        int matricula = s.nextInt();
                        s.nextLine();
                        alunoEncontrado = arv.pesquisar(new Aluno(matricula, ""), new ComparadorAlunoPorMatricula());
                    } else {
                        System.out.print("Digite o nome: ");
                        String nome = s.nextLine();
                        alunoEncontrado = arv.pesquisar(new Aluno(0, nome), new ComparadorAlunoPorNome());
                    }

                    if(alunoEncontrado != null) {
                        System.out.println("Encontrado: " + alunoEncontrado);
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                }
                case 4: { //remover aluno
                    System.out.println("Removendo pelo critério principal da árvore...");
                    Aluno alunoParaRemover;
                    if (balanceamento == 1) {
                        System.out.print("Digite a MATRÍCULA do aluno a ser removido: ");
                        int matricula = s.nextInt();
                        s.nextLine();
                        alunoParaRemover = new Aluno(matricula, "");
                    } else {
                        System.out.print("Digite o NOME do aluno a ser removido: ");
                        String nome = s.nextLine();
                        alunoParaRemover = new Aluno(0, nome);
                    }
                    arv.remover(alunoParaRemover);
                    System.out.println("Solicitação de remoção enviada.");
                    break;
                }
                case 5: { //caminhar em ordem
                    System.out.println("Alunos na árvore (em ordem):");
                    String lista = arv.caminharEmOrdem();
                    if (lista.isEmpty()){
                        System.out.println("A árvore está vazia.");
                    } else {
                        System.out.println(lista);
                    }
                    break;
                }
                case 6: {
                    System.out.println("Quantidade de Nós: " + arv.quantidadeNos());
                    System.out.println("Altura da Árvore: " + arv.altura());
                    System.out.println("Caminhamento em Nível: " + arv.caminharEmNivel());
                    break;
                }
                            }
        } while (escolha != 0);

        System.out.println("Programa finalizado.");
        s.close();
    }
}
