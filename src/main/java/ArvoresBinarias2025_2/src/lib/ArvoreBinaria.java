package ArvoresBinarias2025_2.src.lib;

import java.util.Comparator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T>{
    private No<T> raiz;
    private Comparator<T> comparador;

    public ArvoreBinaria(Comparator<T> comparador){
        this.comparador = comparador;
        this.raiz = null;
    }

    @Override
    public void adicionar(T novoValor) {
        this.raiz = adicionarRecursivo(raiz, novoValor);
    }
    private No<T> adicionarRecursivo(No<T> atual, T novoValor){
        if (atual == null) {
            return new No<>(novoValor);
        }
        int comp = comparador.compare(novoValor, atual.getValor());
        if (comp < 0) {
            atual.setFilhoEsquerdo(adicionarRecursivo(atual.getFilhoEsquerdo(), novoValor));
        } else if(comp > 0){
            atual.setFilhoDireito(adicionarRecursivo(atual.getFilhoDireito(), novoValor));
        } else{
            System.out.println("Valor já na árvore.");
        }
        return atual;
    }

    @Override
    public T pesquisar(T valorDesejado) {
        return pesquisarRecursivo(raiz, valorDesejado);
    }

    private T pesquisarRecursivo(No<T> noAtual, T valorDesejado){
        if (noAtual == null){ //se o nó atual for nulo, significa que o valor não foi encontrado na árvore
            return null;
        }

        int comp = comparador.compare(valorDesejado, noAtual.getValor()); //cria o comparador entre o valor do nó atual e o valor desejado

        if (comp == 0){ //se for igual, encontrou o valor, então retorna ele
            return noAtual.getValor();
        } else if (comp < 0){ //se for menor, vai para a esquerda
            return pesquisarRecursivo(noAtual.getFilhoEsquerdo(), valorDesejado);
        } else { //se for maior, vai para a direita
            return pesquisarRecursivo(noAtual.getFilhoDireito(), valorDesejado);
        }
    }

    @Override
    public T pesquisar(T valor, Comparator comp) {
        if(this.comparador.equals(comp)){ //dá pra usar o método normal de pesquisa se os comparadores forem iguais
            return pesquisar(valor);
        }

        return pesquisarRecursivoComparator(this.raiz, valor, comp);
    }

    private T pesquisarRecursivoComparator(No<T> noAtual, T valor, Comparator comp) {
        if (noAtual == null) { //se o nó atual for nulo, significa que o valor não foi encontrado na árvore
            return null;
        }

        if (comp.compare(valor, noAtual.getValor()) == 0) { //se for igual, encontrou o valor, então retorna ele
            return noAtual.getValor();
        }
        T valorNaEsquerda = pesquisarRecursivoComparator(noAtual.getFilhoEsquerdo(), valor, comp); //busca na subárvore esquerda
        if (valorNaEsquerda != null) { //se encontrou o valor na esquerda, retorna ele
            return valorNaEsquerda;
        }
        return pesquisarRecursivoComparator(noAtual.getFilhoDireito(), valor, comp); //se não encontrou na esquerda, busca na direita
    }

    @Override
    public T remover(T valor) {
        raiz = removerRecursivo(raiz, valor);
        return valor; //retorna o valor removido
    }

    private No<T> removerRecursivo(No<T> noAtual, T valor) {
        if (noAtual == null) { //se o nó atual for nulo, significa que o valor não foi encontrado na árvore
            return null;
        }

        //navega na árvore para encontrar o nó a ser removido
        int comparacao = comparador.compare(valor, noAtual.getValor());

        if (comparacao < 0) { //se for menor, vai para a esquerda
            noAtual.setFilhoEsquerdo(removerRecursivo(noAtual.getFilhoEsquerdo(), valor));
        } else if (comparacao > 0) { //se for maior, vai para a direita
            noAtual.setFilhoDireito(removerRecursivo(noAtual.getFilhoDireito(), valor));
        } else {
            //quando encontra o nó, pode ter 3 casos
            // Caso 1: O nó não ter nenhum filho em nenhum dos lados
            if (noAtual.getFilhoEsquerdo() == null && noAtual.getFilhoDireito() == null) {
                return null; //só remove ele
            }

            // Caso 2: O nó tem apenas um filho em um dos lados
            // basicamente, uma manipulação de 'ponteiros' entre cada nó
            if (noAtual.getFilhoDireito() == null) {
                return noAtual.getFilhoEsquerdo(); // O filho esquerdo "sobe" e substitui o nó removido
            }
            if (noAtual.getFilhoEsquerdo() == null) {
                return noAtual.getFilhoDireito(); // O filho direito "sobe" e substitui o nó removido
            }

            // Caso 3: O nó ter dois filhos
            // uma das soluções:
            // 1. Encontrar o menor valor na subárvore da direita
            // 2. Substituir o valor do nó atual pelo valor do sucessor
            // 3. Remover o nó sucessor da subárvore direita já que se tornou o nó atual
            T menorValor = encontrarMenorValor(noAtual.getFilhoDireito()); //encontra o menor valor na subárvore direita
            noAtual.setValor(menorValor); //seta ele o como o valor do nó atual
            noAtual.setFilhoDireito(removerRecursivo(noAtual.getFilhoDireito(), menorValor)); //chama a função de novo pra remover o nó que foi "movido" para a posição do nó atual
        }

        return noAtual;
    }

    // Método auxiliar para o Caso 3 do remover
    private T encontrarMenorValor(No<T> no) {
        No<T> noAtual = no; //ponteiro para a raiz da subárvore direita que estamos

        while (noAtual.getFilhoEsquerdo() != null) { //enquanto houver filho à esquerda, não chegou no menor valor ainda
            noAtual = noAtual.getFilhoEsquerdo(); //avança pro filho da esquerda
        }
        //quando sai do while, significa que chegou no menor valor dessa subárvore
        return noAtual.getValor();
    }

    @Override
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(No<T> raiz){
        int alturaDireita, alturaEsquerda;

        if (raiz == null){
            return -1;
        }
        alturaDireita = alturaRecursiva(raiz.getFilhoDireito());
        alturaEsquerda = alturaRecursiva(raiz.getFilhoEsquerdo());
        if (alturaDireita > alturaEsquerda){
            return alturaDireita + 1; // o +1 é para contar a raiz
        } else {
            return alturaEsquerda + 1; // o +1 é para contar a raiz
        }
    }
    @Override
    public int quantidadeNos() {
        return quantidadeNosRecursiva(raiz);
    }

    private int quantidadeNosRecursiva(No<T> raiz){
        if (raiz == null){
            return 0;
        }
        //o 1 representa o nó da raiz
        return 1 + quantidadeNosRecursiva(raiz.getFilhoDireito()) + quantidadeNosRecursiva(raiz.getFilhoEsquerdo());
    }

    @Override
    public String caminharEmNivel() {
        return "";
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder sb = new StringBuilder(); //usa o StringBuilder pra concatenar a string com os valores dos nós
        caminharEmOrdemRecursivo(raiz, sb);
        return sb.toString().trim(); //trim remove o espaço extra no final
    }

    private void caminharEmOrdemRecursivo(No<T> node, StringBuilder sb){
        if (node == null){
            return;
        }
        //primeiro visita o filho esquerdo até o fim pegando todos os valores
        caminharEmOrdemRecursivo(node.getFilhoEsquerdo(), sb);
        //depois visita a raiz (que é o nó atual)
        sb.append(node.getValor()).append(" ");
        //depois visita o filho direito até o fim, pega os valores e adiciona nos valores já coletados da esquerda
        caminharEmOrdemRecursivo(node.getFilhoDireito(), sb);
    }
}
