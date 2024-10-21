package scr.escola;
import java.util.ArrayList;
import scr.escola.anos.Anos;
import scr.escola.participantes.alunos.Alunos;
import scr.escola.participantes.funcionarios.Funcionarios;

/** 
* Representa uma escola
* @author Daniel Campos Galdez Monteiro
*/
public class Escola{
    /** 
    * Nome da escola.
    */
    private String nome;
    /** 
    * O atual bimestre da escola
    */
    private int bimestre;
    /** 
    * Lista os anos dessa escola
    */
    private ArrayList<Anos> anos;
    /** 
    * Lista os funcionários dessa escola
    */
    private ArrayList<Funcionarios> funcionarios;
    
    /**
    * Por padrão, atribui um nome e um bimestre à nova escola instanciada, também define apenas nove anos para a escola.
    * @param nome nome da escola
    * @param bimestre bimestre da escola
    */
    public Escola(String nome, int bimestre){
        anos = new ArrayList<>();
        funcionarios = new ArrayList<>();
        
        this.nome = nome;

        for(int x=0;x<9;x++){
            anos.add(new Anos(x+1));
        }
        if ((bimestre<1) || (bimestre>4)){
            bimestre = 1;
        } else {
            this.bimestre = bimestre;
        }
    }
    /**
    * Altera o nome da escola
    * @param novo_nome novo nome da escola
    */
    public void setNome(String novo_nome){
        nome = novo_nome;
    }
    /**
    * Passa o bimestre atual da escola para o próximo bimestre
    */
    public void passarBimestre(){
        if (bimestre<4){
            bimestre++;
        } else {
            bimestre = 1;
        }
    }
    /**
    * Remove um aluno de um dado ano da escola
    * @param ano ano do aluno a ser removido
    * @param aluno aluno a ser removido
    */
    public void removerAlunoEscola(Anos ano, Alunos aluno){
        for(Anos percorrer_anos: anos){
            if(percorrer_anos == ano){
                percorrer_anos.removerAlunoAno(aluno);
            }
        }
    }
    /**
    * Remove um funcionário da escola
    * @param funcionario funcionario a ser removido
    */
    public void removerFuncionarioEscola(Funcionarios funcionario){
        funcionarios.remove(funcionario);
    }
    /**
    * Adiciona um aluno a um dado ano
    * @param aluno aluno a ser adicionado
    * @param index_ano_escolar posição do ano na lista de anos da escola
    */
    public void adicionarAlunoAno(Alunos aluno, int index_ano_escolar){
        getAno(index_ano_escolar).adicionarAlunoAno(aluno);
    }
    /**
    * Retorna um ano da lista de anos, para um dado index 
    * @param index_ano_escolar posição do ano na lista de anos da escola
    * @return o ano retornado
    */
    public Anos getAno(int index_ano_escolar){
        return anos.get(index_ano_escolar);
    }
    /**
    * Adiciona um funcionário à lista de funcionários da escola
    * @param funcionario funcionário a ser adicionado
    */
    public void adicionarFuncionario(Funcionarios funcionario){
        funcionarios.add(funcionario);
    }
    /**
    * Retorna a lista de anos da escola
    * @return a lista de anos da escola retornada
    */
    public ArrayList<Anos> getAnos(){
        return anos;
    }
    /**
    * Retorna a lista de de funcionários da escola
    * @return a lista de funcionários retornada
    */
    public ArrayList<Funcionarios> getFuncionarios(){
        return funcionarios;
    }
    /**
    * Retorna o nome da escola
    * @return o nome retornado
    */
    public String getNome(){
        return nome;
    }
    /**
    * Retorna o atual bimestre da escola
    * @return o bimestre retornado
    */
    public int getBimestre(){
        return bimestre;
    }
}