package implementacao1;
import ArvoresBinarias2025_2.src.lib.IArvoreBinaria;

import java.util.Comparator;

public class ArvoreBinaria <T extends Comparable<T>> implements IArvoreBinaria{
    private No<T> raiz;
    public ArvoreBinaria(){
        this.raiz = null;
    }

    @Override
    public void adicionar(Object novoValor) {

    }

    @Override
    public Object pesquisar(Object valor) {
        return null;
    }

    @Override
    public Object pesquisar(Object valor, Comparator comparador) {
        return null;
    }

    @Override
    public Object remover(Object valor) {
        return null;
    }

    @Override
    public int altura() {
        return 0;
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
