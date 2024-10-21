package scr.gui.dialogos;
import scr.escola.Escola;
import scr.escola.participantes.alunos.Alunos;
import scr.gui.DialogoAbstrato;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/** 
* Dialogo que carrega os formulários necessários para as Janelas relacionadas aos alunos (InfoAlunos e ListaAlunos)
* @author Daniel Campos Galdez Monteiro
*/
public class DialogoAluno extends DialogoAbstrato{
    /** 
    * aluno utilizado para os formulários de "edição" de alunos
    */
    Alunos aluno;
    /** 
    * escola utilizada para os formulários de "criação" de alunos (principalmente)
    */
    Escola escola;
    /** 
    * caixa seletora para selecionar o ano do aluno
    */
    JComboBox<String> caixa_seletora;
    /** 
    * index do valor selecionado pela caixa seletora
    */
    int valor_caixa_seletora;
    /** 
    * lista com as caixas de texto do formulário
    */
    JTextField[] caixas_de_texto;
    /** 
    * lista com os valores das caixas de texto do formulário
    */
    String[] inputs_campos;

    /** 
    * Quando é passado para o diálogo apenas a escola, ele entende que é o registro de um novo aluno
    * @param escola escola passada ao diálogo
    */
    public DialogoAluno(Escola escola) {
        this.escola = escola;
        aluno = null;
        inicializarJanela("Registrar novo aluno",380,430);
    }
    
    /** 
    * Quando é passado para o diálogo a escola e um aluno, ele entende que é alteração de algum aluno
    * @param escola escola passada ao diálogo
    * @param aluno que vai ser alterado pelo formulário do diálogo
    */
    
    public DialogoAluno(Escola escola, Alunos aluno) {
        this.escola = escola;
        this.aluno = aluno;
        inicializarJanela("Alterar registros do aluno",380,430);
    }
    @Override
    protected void definirConteudo(){
        caixas_de_texto = new JTextField[9];
        inputs_campos = new String[9];
        valor_caixa_seletora = 0;

        String[] infos_aluno = new String[9];
        String[] titulos_infos_aluno = {"Nome:","Email pessoal:","Telefone:","Dia de nascimento:","Mês de nascimento:","Ano de nascimento:","Nome do responsável:","Email do responsável:","Telefone do responsável:"};
        
        if(aluno != null){
            infos_aluno[0]=aluno.getNome();
            infos_aluno[1]=aluno.getEmail();
            infos_aluno[2]=aluno.getTelefone();
            infos_aluno[3]=aluno.getDataNascimento()[0];
            infos_aluno[4]=aluno.getDataNascimento()[1];
            infos_aluno[5]=aluno.getDataNascimento()[2];
            infos_aluno[6]=aluno.getResponsavel()[0];
            infos_aluno[7]=aluno.getResponsavel()[1];
            infos_aluno[8]=aluno.getResponsavel()[2];
            valor_caixa_seletora = aluno.getAno().getNumeroAno()-1;
        }

        String[] anos_escolares = {"1º ano","2º ano","3º ano","4º ano","5º ano","6º ano","7º ano","8º ano","9º ano"};

        caixa_seletora = criarCaixa_Seleção(anos_escolares,valor_caixa_seletora);

        PanelInformacoes formulario = new PanelInformacoes();
        int contador_nova_coluna=0;
        for(int x=0;x<caixas_de_texto.length;x++){
            if(contador_nova_coluna>4){
                formulario.novaColuna();
                contador_nova_coluna=0;
            } else {
                contador_nova_coluna++;
            }
            caixas_de_texto[x] = criarCampo_Texto(infos_aluno[x]);
            formulario.adicionar(criarTexto(true, titulos_infos_aluno[x]));
            formulario.adicionar(caixas_de_texto[x]);
        }

        formulario.adicionar(criarTexto(true, "Ano escolar: "));
        formulario.adicionar(caixa_seletora);
        panel_meio.add(formulario.panel);

    }
    @Override
    protected void definirBotoes(){
       //
    }
    /** 
    * Método que usa as informações do formulário para alterar as informações do aluno armazenado pelo diálogo
    */
    public void alterarAluno(){
        for(int x=0;x<caixas_de_texto.length;x++){
            inputs_campos[x] = caixas_de_texto[x].getText();
        }
        valor_caixa_seletora = caixa_seletora.getSelectedIndex();
        aluno.alterarDados(escola.getAno(valor_caixa_seletora), inputs_campos);
    }
    /** 
    * Método que usa as informações do formulário para criar um novo aluno na escola armazenada por ele
    */
    public void criarAluno(){
        for(int x=0;x<caixas_de_texto.length;x++){
            inputs_campos[x] = caixas_de_texto[x].getText();
        }
        valor_caixa_seletora = caixa_seletora.getSelectedIndex();
        escola.adicionarAlunoAno(new Alunos(escola.getAno(valor_caixa_seletora),inputs_campos), valor_caixa_seletora);
    }
}
