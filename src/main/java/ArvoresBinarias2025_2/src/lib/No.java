package ArvoresBinarias2025_2.src.lib;

public class No<T> {
    private T valor;
    private No<T> filhoEsquerdo;
    private No<T> filhoDireito;

    public No(T valor){
        this.valor = valor;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }

    public T getValor() {
        return valor;
    }

    public No<T> getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public No<T> getFilhoDireito() {
        return filhoDireito;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public void setFilhoEsquerdo(No<T> esquerdo) {
        this.filhoEsquerdo = esquerdo;
    }

    public void setFilhoDireito(No<T> filhoDireito) {
        this.filhoDireito = filhoDireito;
    }
}
