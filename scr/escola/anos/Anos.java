package scr.escola.anos;

import java.util.ArrayList;

import scr.escola.participantes.alunos.Alunos;
/** 
* Representa um ano(turma) da escola.
* @author Daniel Campos Galdez Monteiro
*/
public class Anos {
    /** 
    * representação númerica do ano escolar
    */
    private int numero_ano_escolar;
    /** 
    * lista de horários do ano escolar
    */
    private ArrayList<Horarios> horarios;
    /** 
    * lista de disciplinas do ano escolar
    */
    private ArrayList<Disciplinas> disciplinas;
    /** 
    * lista de alunos do ano escolar
    */
    private ArrayList<Alunos> alunos;
    /** 
    * por padrão, atribui a representação do ano escolar ao ano, como também cria os horários e as listas de disciplinas e alunos
    */
    public Anos(int numero_ano_escolar){
        horarios = new ArrayList<>();
        disciplinas = new ArrayList<>();
        alunos = new ArrayList<>();

        Horarios horario_auxiliar;
        this.numero_ano_escolar = numero_ano_escolar;
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                horario_auxiliar = new Horarios();
                horario_auxiliar.dia_da_semana = Horarios.DIAS_SEMANA[x];
                horario_auxiliar.horario_de_inicio = Horarios.HORARIOS_INICIO[y];
                horario_auxiliar.horario_de_termino = Horarios.HORARIOS_TERMINO[y];
                horarios.add(horario_auxiliar);
            }
        }
    }
    /** 
    * adiciona uma disciplina ao ano
    * @param disciplina disciplina a ser adicionada
    */
    public void adicionarDisciplina(Disciplinas disciplina){
        disciplinas.add(disciplina);
    }
    /** 
    * remove uma disciplina do ano
    * @param disciplina disciplina a ser removida
    */
    public void removerDisciplina(Disciplinas disciplina){
        disciplinas.remove(disciplina);
    }
    /** 
    * remove algum aluno do ano
    * @param aluno aluno a ser removido
    */
    public void removerAlunoAno(Alunos aluno){
        alunos.remove(aluno);
    }
    /** 
    * adiciona algum aluno ao ano
    * @param aluno aluno a ser adicionado
    */
    public void adicionarAlunoAno(Alunos aluno){
        alunos.add(aluno);
    }
    /** 
    * retorna a representação numérica do ano escolar
    * @return número do ano retornado
    */
    public int getNumeroAno(){
        return numero_ano_escolar;
    }
    /** 
    * retorna a lista de horários do ano escolar
    * @return horário retornado
    */
    public ArrayList<Horarios> getListaHorarios(){
        return horarios;
    }
    /** 
    * retorna a lista de disciplinas do ano escolar
    * @return horário retornado
    */
    public ArrayList<Disciplinas> getListaDisciplinas(){
        return disciplinas;
    }
    /** 
    * retorna a lista de alunos do ano escolar
    * @return lista de alunos retornada
    */
    public ArrayList<Alunos> getListaAlunos(){
        return alunos;
    }
}