package implementacao1;
import ArvoresBinarias2025_2.src.app.ComparadorAlunoPorMatricula;
import ArvoresBinarias2025_2.src.lib.IArvoreBinaria;

import java.util.Comparator;


public class ArvoreBinaria <T extends Comparator<T>> implements IArvoreBinaria<T>{
    private No<T> raiz;
    private Comparator<T> comparador;

    public ArvoreBinaria(Comparator<T> comparador){
        this.comparador = comparador;
        this.raiz = null;
    }

    @Override
    public void adicionar(T novoValor) {
        adicionarRecursivo(raiz, novoValor);
    }
    public No<T> adicionarRecursivo(No<T> atual, T novoValor){
        if (atual == null) {
            return new No<>(novoValor);
        }
        int comp = comparador.compare(atual.getValor(), novoValor);
        if (comp < 0) {
            atual.setEsquerdo(adicionarRecursivo(atual.getFilhoEsquerdo(), novoValor));
        } else if(comp > 0){
            atual.setFilhoDireito(adicionarRecursivo(atual.getFilhoDireito(), novoValor));
        } else{
            System.out.println("Valor já na árvore.");
        }
        return atual;
    }

    @Override
    public T pesquisar(T valor) {
        return null;
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        return pesquisarRecursivo(this.raiz, valor, comparador);
    }

    public T pesquisarRecursivo(No<T> raiz, T valor, Comparator comp){
        T resEsq, resDir;
        if (raiz == null){
            return null;
        }
        if (comparador.compare(valor, raiz.getValor()) == 0){
            return raiz.getValor();
        } else{
            resEsq = pesquisarRecursivo(raiz.getFilhoEsquerdo(), valor, comp);
            resDir = pesquisarRecursivo(raiz.getFilhoDireito(), valor, comp);
            if (resEsq != null){
                return resEsq;
            } else {
                return resDir;
            }
        }
    }

    @Override
    public T remover(T valor) {
        return null;
    }

    @Override
    public int altura() {
        return alturaRecursiva(raiz);
    }

    public int alturaRecursiva(No<T> raiz){
        int alturaDireita, alturaEsquerda;

        if (raiz == null){
            return -1;
        }
        alturaDireita = alturaRecursiva(raiz.getFilhoDireito());
        alturaEsquerda = alturaRecursiva(raiz.getFilhoEsquerdo());
        if (alturaDireita > alturaEsquerda){
            return alturaDireita;
        } else {
            return alturaEsquerda;
        }
    }
    @Override
    public int quantidadeNos() {
        return 0;
    }

    @Override
    public String caminharEmNivel() {
        return "";
    }

    @Override
    public String caminharEmOrdem() {
        return "";
    }
}