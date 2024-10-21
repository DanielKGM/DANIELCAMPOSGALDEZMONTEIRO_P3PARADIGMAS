package scr.escola.anos;
/** 
* Representa um horário de algum ano escolar
* @author Daniel Campos Galdez Monteiro
*/
public class Horarios {
    /** 
    * Nome dos dias da semana que a escola funciona
    */
    public static final String[] DIAS_SEMANA= {"Segunda-feira","Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira"};
    /** 
    * Horários de início dos horários dos anos da escola
    */
    public static final String[] HORARIOS_INICIO= {"7:30","8:20","9:20","10:10","11:10"};
    /** 
    * Horários de término dos horários dos anos da escola
    */
    static final String[] HORARIOS_TERMINO= {"8:20","9:10","10:10","11:00","12:00"};
    /** 
    * Disciplina à qual o horário foi atribuído
    */
    Disciplinas disciplina;
    /** 
    * Dia da semana do horário
    */
    String dia_da_semana;
    /** 
    * Horário de início do horário
    */
    String horario_de_inicio;
    /** 
    * Horário de término do horário
    */
    String horario_de_termino;
    /** 
    * Atribui alguma disciplina ao horário
    */
   void atribuirDisciplina(Disciplinas disciplina){
     this.disciplina = disciplina;
   }
    /** 
    * Retorna o dia da semana do horário
    * @return dia da semana retornado
    */
   public String getDia(){
     return dia_da_semana;
   }
    /** 
    * Retorna o horário de início do horário
    * @return horário de início do horário
    */
   public String getInicio(){
     return horario_de_inicio;
   }

}
