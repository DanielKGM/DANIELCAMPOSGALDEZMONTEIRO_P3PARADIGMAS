package scr.escola.participantes.alunos;
import scr.escola.anos.Anos;
import scr.escola.participantes.Participantes;
/** 
* Representa um participante aluno da escola
* @author Daniel Campos Galdez Monteiro
*/
public class Alunos extends Participantes{
    /** 
    * ano escolar do aluno
    */
    private Anos ano_escolar;
    /** 
    * string com o nome[0], email[1] e telefone[2] do responsável pelo aluno
    */
    private String responsavel[];
    /** 
    * Por padrão, recebe o ano escolar do aluno e uma lista de strings em que cada índice representa uma inforamção básica do aluno, sendo os três ultimos índices as informações do responsável pelo aluno. As informações são passadas como string para facilitar na hora do registro
    * @param ano_escolar ano escolar do aluno
    * @param informacoes strings com informações sobre o aluno: 0= nome; 1= email; 2=telefone pessoal; 3 a 5 = dia, mês e ano de nascimento; 6 a 8 = nome, email e telefone do responsável
    */
    public Alunos(Anos ano_escolar, String[] informacoes){
        super(informacoes[0], informacoes[3],informacoes[4], informacoes[5], informacoes[1], informacoes[2]);
        responsavel = new String[3];
        this.ano_escolar = ano_escolar;
        responsavel[0] = informacoes[6];
        responsavel[1] = informacoes[7];
        responsavel[2] = informacoes[8];
    }
    /** 
    * Altera os dados do aluno de uma vez
    * @param ano_escolar novo ano escolar do aluno
    *@param informacoes strings com informações sobre o aluno: 0= nome; 1= email; 2=telefone pessoal; 3 a 5 = dia, mês e ano de nascimento; 6 a 8 = nome, email e telefone do responsável
    */
    public void alterarDados(Anos ano_escolar, String[] informacoes){
        this.ano_escolar.removerAlunoAno(this);
        this.ano_escolar = ano_escolar;
        ano_escolar.adicionarAlunoAno(this);

        nome = informacoes[0];
        email_pessoal = informacoes[1];
        telefone_pessoal = informacoes[2];
        data_nascimento[0]= informacoes[3];
        data_nascimento[1]=informacoes[4];
        data_nascimento[2]=informacoes[5];
        responsavel[0] = informacoes[6];
        responsavel[1] = informacoes[7];
        responsavel[2] = informacoes[8];
    }
    /** 
    * Retorna o ano escolar do aluno
    * @return o ano retornado
    */
    public Anos getAno(){
        return ano_escolar;
    }
    /** 
    * Retorna as informações sobre o responsável pelo aluno
    * @return lista de strings com as informações sobre o responsável
    */
    public String[] getResponsavel(){
        return responsavel;
    }
}
