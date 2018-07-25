
package dominio;


public class Aluno{
    private String nome;
    private String matricula;
    private String curso;
    
    public Aluno (String nome, String matricula, String curso){
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }
    
    @Override
    public String toString(){
        return "NOME : "+this.nome+ "\nMATRICULA : "+this.matricula+ "\nCURSO : "+this.curso;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public String getCurso() {
        return this.curso;
    }
    
}
