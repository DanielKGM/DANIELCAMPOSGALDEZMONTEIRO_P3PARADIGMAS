package scr.escola.anos;
import java.util.ArrayList;

import scr.escola.participantes.funcionarios.Professores;
/** 
* Representa uma disciplina de algum ano escolar
* @author Daniel Campos Galdez Monteiro
*/
public class Disciplinas {
    /** 
    * Nome da disciplina
    */
    private String nome;
    /** 
    * representação numérica do ano escolar que a disciplina participa
    */
    private int ano_escolar;
    /** 
    * Professor responsável por ministrar a disciplina
    */
    private Professores docente;
    /** 
    * Lista de horários da disciplina
    */
    private ArrayList<Horarios> horarios; 
    /** 
    * Por padrão, atribui um nome, um número do ano escolar e um professor para a disciplina, também cria a lista de horários
    * @param nome nome da disciplina
    * @param ano_escolar número do ano escolar da disciplina
    * @param docente professor responsável pela disciplina
    */
    public Disciplinas(String nome, int ano_escolar, Professores docente){
        horarios = new ArrayList<>();
        this.nome=nome;
        this.docente=docente;
        this.ano_escolar=ano_escolar;
    }
    /** 
    * atribui algum horário para disciplina
    */
    public void atribuirHorario(Horarios horario){
        horarios.add(horario);
        horario.atribuirDisciplina(this);
    }
    /** 
    * Remove algum horário da disciplina
    */
    public void desatribuirHorario(Horarios horario){
        horario.atribuirDisciplina(null);
        horarios.remove(horario);
    }
    /** 
    * Retorna se a disciplina tem algum horário em sua lista de horários
    * @return resposta da análise
    */
    public boolean temHorario(Horarios horario){
        return horarios.contains(horario);
    }
    /** 
    * Altera o professor responsável pela disciplina
    */
    public void atribuirDocente(Professores docente){
        this.docente = docente;
        docente.desatribuirDisciplina(this);
        docente.atribuirDisciplina(this);
    }
    /** 
    * Remove o professor responsável pela disciplina
    */
    public void desatribuirDocente(){
        docente.desatribuirDisciplina(this);
        this.docente = null;
    }
    /** 
    * Retorna o nome da disciplina
    * @return nome retornado
    */
    public String getNome(){
        return nome;
    }
    /** 
    * Altera o nome da disciplina
    */
    public void setNome(String novo_nome){
        nome = novo_nome;
    }
    /** 
    * Retorna a representação numérica da disciplina
    * @return o número retornado
    */
    public int getAno(){
        return ano_escolar;
    }
    /** 
    * Retorna o atual professor responsável pela disciplina
    * @return professor responsável pela disciplina
    */
    public Professores getDocente(){
        return docente;
    }
}
