package ArvoresBinarias2025_2.src.lib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...
    private int altura(No<T> no){ //poderia usar o método de altura que já tem na ArvoreBinaria, mas preferi fazer um específico para o nó, ele guardando a própria altura facilitaria, pq não precisaria recalcular a altura toda hora que fosse verificar o balanceamento da árvore
        if (no == null){
            return -1;
        }
        return 1 + Math.max(altura(no.getFilhoEsquerdo()), altura(no.getFilhoDireito()));
    }

    private int fatorBalanceamento(No<T> no){ //balanceamento: altura(esquerdo) - altura(direito) -> pra saber se tá desbalanceado
        if (no == null){
            return 0;
        }
        return altura(no.getFilhoEsquerdo()) - altura(no.getFilhoDireito());
    }

    @Override
    public void adicionar(T novoValor) {
        this.raiz = adicionarRecursivo(this.raiz, novoValor);
    }

    private No<T> adicionarRecursivo(No<T> noAtual, T novoValor){
        if (noAtual == null) { //se atual for nulo, cria um novo nó com o valor
            return new No<>(novoValor);
        }
        //mesma lógica do adicionar da ArvoreBinaria, vai mudar só a parte do balanceamento lá embaixo
        int comp = comparador.compare(novoValor, noAtual.getValor()); //comparador do ArvoreBinaria
        if (comp < 0) { //se for menor, vai para a esquerda
            noAtual.setFilhoEsquerdo(adicionarRecursivo(noAtual.getFilhoEsquerdo(), novoValor));
        } else if(comp > 0){ //se for maior, vai para a direita
            noAtual.setFilhoDireito(adicionarRecursivo(noAtual.getFilhoDireito(), novoValor));
        } else{ //se for igual, não adiciona
            System.out.println("Valor já tá na árvore.");
            return noAtual; //não precisa balancear se não adicionou nada
        }

        //https://www.freecodecamp.org/portuguese/news/insercao-rotacao-e-fator-de-balanceamento-da-arvore-avl-explicados/
        //https://joaoarthurbm.github.io/eda/posts/avl/
        //pode acontecer 4 casos de desbalanceamento, cada um com sua rotação específica, leiam os artigos pra entenderem melhor
        //parte AVL: verifica o balanceamento a cada adição para ver se precisa fazer alguma rotação
        int balanceamento = fatorBalanceamento(noAtual);

        //caso 1: Left Left
        //se o for maior que 1, tá desbalanceada pra esquerda, ou seja, tem que fazer rotação pra >DIREITA<, é ao contrário, pq tá "caindo" pra esquerda
        if (balanceamento > 1 && comparador.compare(novoValor, noAtual.getFilhoEsquerdo().getValor()) < 0){
            return rotacaoDireita(noAtual);
        }

        //Caso2: Right Right
        //se for menor que -1, tá desbalanceada pra direita, tem que fazer rotação pra >ESQUERDA<
        if (balanceamento < -1 && comparador.compare(novoValor, noAtual.getFilhoDireito().getValor()) > 0){
            return rotacaoEsquerda(noAtual);
        }

        //Caso 3: Left Right
        //esse caso é meio "composto", pq o desbalanceamento é pra esquerda, mas o novo valor foi adicionado na subárvore direita do filho esquerdo, então precisa fazer uma rotação dupla: primeiro rotaciona o filho esquerdo pra esquerda, depois rotaciona o nó atual pra direita, é meio confuso
        if (balanceamento > 1 && comparador.compare(novoValor, noAtual.getFilhoEsquerdo().getValor()) > 0){
            noAtual.setFilhoEsquerdo(rotacaoEsquerda(noAtual.getFilhoEsquerdo()));
            return rotacaoDireita(noAtual);
        }

        //Caso 4: Right Left
        //mesma lógica do caso 3, mas invertida, primeiro rotaciona o filho direito pra direita, depois o nó atual pra esquerda
        if (balanceamento < -1 && comparador.compare(novoValor, noAtual.getFilhoDireito().getValor()) < 0){
            noAtual.setFilhoDireito(rotacaoDireita(noAtual.getFilhoDireito()));
            return rotacaoEsquerda(noAtual);
        }

        return noAtual;
    }

    private No<T> rotacaoDireita(No<T> noDesbalanceado){
        No<T> novaRaiz = noDesbalanceado.getFilhoEsquerdo(); //a nova raiz vai ser o filho esquerdo do nó desbalanceado
        No<T> temp = novaRaiz.getFilhoDireito(); //armazena o filho direito da nova raiz temporariamente, vai servir como uma subárvore

        //aqui faz a rotação, vai pegar o nó desbalanceado e colocar como filho direito da nova raiz, e o filho direito da nova raiz vai virar o filho esquerdo do nó desbalanceado
        novaRaiz.setFilhoDireito(noDesbalanceado);
        noDesbalanceado.setFilhoEsquerdo(temp);

        return novaRaiz;
    }

    private No<T> rotacaoEsquerda(No<T> noDesbalanceado){
        No<T> novaRaiz = noDesbalanceado.getFilhoDireito(); //a nova raiz vai ser o filho direito do nó desbalanceado
        No<T> temp = novaRaiz.getFilhoEsquerdo(); //armazena o filho esquerdo da nova raiz temporariamente, vai servir como uma subárvore

        //aqui faz a rotação, vai pegar o nó desbalanceado e colocar como filho esquerdo da nova raiz, e o filho esquerdo da nova raiz vai virar o filho direito do nó desbalanceado
        novaRaiz.setFilhoEsquerdo(noDesbalanceado);
        noDesbalanceado.setFilhoDireito(temp);

        return novaRaiz;
    }

}
