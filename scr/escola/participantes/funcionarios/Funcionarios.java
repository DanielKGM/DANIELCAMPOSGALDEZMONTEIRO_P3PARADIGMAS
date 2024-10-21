package scr.escola.participantes.funcionarios;

import scr.escola.participantes.Participantes;
/** 
* Representa um participante funcionário da escola
* @author Daniel Campos Galdez Monteiro
*/
public class Funcionarios extends Participantes{
    /** 
    * número que representa a função "professor"
    */
    protected static final int PROFESSOR = 0;
    /** 
    * número que representa a função "merendeiro"
    */
    protected static final int MERENDEIRO = 1;
    /** 
    * número que representa a função "faxineiro"
    */
    protected static final int FAXINEIRO = 2;
    /** 
    * número que representa a função "zelador"
    */
    protected static final int ZELADOR = 3;
    /** 
    * número que representa a função "porteiro"
    */
    protected static final int PORTEIRO = 4;
    /** 
    * string com os nomes das profissões em que cada profissão está no índice correspondente ao número que a representa
    */
    public static final String FUNCOES[]={"Professor","Merendeiro","Faxineiro","Zelador","Porteiro"};
    /** 
    * nome da função exercida pelo funcionário
    */
    private String funcao_exercida;
    /** 
    * salário (ou remuneração) do funcionário
    */
    private double remuneracao;
    /** 
    * por padrão, recebe o número que representa a função do funcionário e uma lista de strings com as outras informações restantes
    * @param numero_funcao que representa a função da nova instância
    * @param inputs lista de strings com as informações sobre o funcionário
    */
    public Funcionarios(int numero_funcao,String[] inputs){
        super(inputs[0], inputs[3], inputs[4], inputs[5], inputs[1], inputs[2]);
        remuneracao = Double.parseDouble(inputs[6]);
        setFuncao(numero_funcao);
        setRemuneracao(remuneracao);
        setFuncao(numero_funcao);
    }
    /** 
    * altera o valor da remuneração do funcionário
    * @param nova_remuneracao valor da nova remuneração do funcionário
    */
    private void setRemuneracao(double nova_remuneracao){
        remuneracao = nova_remuneracao;
    }
    /** 
    * retorna uma string com o valor da remuneração do funcionário
    * @return valor da remuneração em string
    */
    public String lerRemuneracao(){
        return String.format("%.2f",remuneracao);
    }
    /** 
    * altera de uma vez os dados sobre o funcionário
    * @param numero_funcao número que representa a função do funcionário
    * @param inputs lista de strings que representam as informações sobre o funcionário
    */
    public void alterarDados(int numero_funcao, String[] inputs){
        nome = inputs[0];
        email_pessoal = inputs[1];
        telefone_pessoal = inputs[2];
        data_nascimento[0] = inputs[3]; 
        data_nascimento[1] = inputs[4];
        data_nascimento[2] = inputs[5];
        remuneracao = Double.parseDouble(inputs[6]);
        setFuncao(numero_funcao);
    }
    /** 
    * altera a função exercida pelo funcionário. Os números estão declarados estaticamente
    * @param index_funcao número que representa a nova função exercida pelo funcionário
    */
    private void setFuncao(int index_funcao){
        if((index_funcao > -1)&&(index_funcao< FUNCOES.length)){
            funcao_exercida = FUNCOES[index_funcao];
        } else {
            funcao_exercida = "Indefinida";
        }
    }
    /** 
    * retorna a função exercida pelo funcionário
    * @return função retornada, em string
    */
    public String getFuncao(){
        return funcao_exercida;
    }
}
