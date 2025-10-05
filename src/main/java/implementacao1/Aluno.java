package implementacao1;

public class Aluno {
    private int matricula;
    private String nome;

    public Aluno(int matricula, String nome){
        this.matricula = matricula;
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public String toString(){
        return Integer.toString(matricula) + " - " + nome;
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof Aluno)
            return this.matricula == ((Aluno)a).matricula;
        else
            return false;
    }
}

