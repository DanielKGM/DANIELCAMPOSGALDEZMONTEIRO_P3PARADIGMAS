package scr.escola.participantes.funcionarios;
import java.util.ArrayList;
import scr.escola.anos.Disciplinas;
/** 
* Representa um funcionário professor participante de uma escola
* @author Daniel Campos Galdez Monteiro
*/
public class Professores extends Funcionarios {
    /** 
    * lista com as disciplinas ministradas pelo professor
    */
    private ArrayList<Disciplinas> disciplinas_ministradas;
    /** 
    * por padrão, recebe uma string que representa as informações sobre o funcionário e passa para a superclasse "Funcionários"
    * @param inputs lista que representa as informações sobre o professor, cada índice é uma informação específica
    */
    public Professores(String[] inputs){
        super(0,inputs);
        disciplinas_ministradas = new ArrayList<>();
    }
    /** 
    * adiciona uma disciplina à lista de disciplinas do professor
    * @param disciplina a disciplina que vai ser atribuída ao professor
    */
    public void atribuirDisciplina(Disciplinas disciplina){
        disciplinas_ministradas.add(disciplina);
    }
    /** 
    * remove uma disciplina da lista de disciplinas ministradas pelo professor
    * @param disciplina a disciplina que vai ser removida
    */
    public void desatribuirDisciplina(Disciplinas disciplina){
        disciplinas_ministradas.remove(disciplina);
    }
    /** 
    * retorna a lista de disciplinas ministradas pelo professor
    * @return lista de disciplinas ministradas pelo professor
    */
    public ArrayList<Disciplinas> getDisciplinas(){
        return disciplinas_ministradas;
    }
}
