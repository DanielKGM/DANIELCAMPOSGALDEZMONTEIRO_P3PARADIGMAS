package scr.escola.participantes;
/** 
* "Esqueleto abstrato" que representa um participante da escola
* @author Daniel Campos Galdez Monteiro
*/
public abstract class Participantes {
    /** 
    * Nome do participante da escola
    */
    protected String nome;
    /** 
    * Data de nascimento do participante: 0=dia; 1=mês; 2=ano
    */
    protected String[] data_nascimento;
    /** 
    * Email do participante da escola
    */
    protected String email_pessoal;
    /** 
    * Telefone pessoal do participante da escola
    */
    protected String telefone_pessoal;
    /** 
    * Construtor que por padrão atribui todos os atributos básicos de um participante
    */
    protected Participantes(String nome, String dia_nascimento, String mes_nascimento, String ano_nascimento, String email_pessoal, String telefone_pessoal){
        this.nome = nome;
        data_nascimento = new String[3];
        this.data_nascimento[0]= dia_nascimento;
        this.data_nascimento[1]= mes_nascimento;
        this.data_nascimento[2]= ano_nascimento;
        this.email_pessoal = email_pessoal;
        this.telefone_pessoal = telefone_pessoal;
    }
    /** 
    * Retorna o nome do participante
    * @return nome retornado
    */
    public String getNome(){
        return nome;
    }
    /** 
    * Retorna a data de nascimento do participante, em lista de strings.
    * @return retorna a data de nascimento: 0=dia; 1=mês; 2=ano
    */
    public String[] getDataNascimento(){
        return data_nascimento;
    }
    /** 
    * Altera a data de nascimento do participante
    * @param data nova data de nascimento, em formato de lista de strings: 0=dia; 1=mês; 2=ano
    * @param indice qual item da data de nascimento alterar: 0=dia; 1=mês; 2=ano
    */
    public void setDataNascimento(String data, int indice){
        data_nascimento[indice]=data;
    }
    /** 
    * Retorna o email pessoal do participante
    * @return email retornado
    */
    public String getEmail(){
        return email_pessoal;
    }
    /** 
    * Retorna o telefone pessoal do participante
    * @return telefone retornado
    */
    public String getTelefone(){
        return telefone_pessoal;
    }

}
