package scr.gui.dialogos;
import scr.escola.Escola;
import scr.escola.participantes.funcionarios.Funcionarios;
import scr.escola.participantes.funcionarios.Professores;
import scr.gui.DialogoAbstrato;
import javax.swing.JComboBox;
import javax.swing.JTextField;
/** 
* Dialogo que carrega os formulários necessários para as Janelas relacionadas aos funcionários
* @author Daniel Campos Galdez Monteiro
*/
public class DialogoFuncionario extends DialogoAbstrato{
    /** 
    * funcionário utilizado para os formulários de "edição" de funcionários
    */
    Funcionarios funcionario;
    /** 
    * escola utilizada para os formulários de "criação" de funcionário (principalmente)
    */
    Escola escola;
    /** 
    * caixa seletora para selecionar a função exercida pelo funcionário
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
    * Quando o diálogo recebe um funcionário, ele entende que deve assumir a forma de edição de funcionário
    * @param escola escola que será atualizada com a atualização do funcionário, também usada em alguma operações
    * @param funcionario funcionário que será alvo do formulário do diálogo
    */
    public DialogoFuncionario (Escola escola, Funcionarios funcionario) {
        this.escola = escola;
        this.funcionario = funcionario;
        inicializarJanela("Alterar dados do funcionario",380,430);
    }
    /** 
    * Quando o diálogo não recebe um funcionário, ele entende que deve assumir a forma de criação de funcionários
    * @param escola escola que será atualizada com o novo funcionário, também usada em alguma operações
    */
    public DialogoFuncionario (Escola escola) {
        funcionario = null;
        this.escola = escola;
        inicializarJanela("Registrar novo funcionario",380,430);
    }
    @Override
    protected void definirConteudo(){
        caixas_de_texto = new JTextField[7];
        inputs_campos = new String[7];
        valor_caixa_seletora = 0;

        String[] infos_func = new String[7];
        String[] titulos_infos_func = {"Nome:","Email pessoal:","Telefone:","Dia de nascimento:","Mês de nascimento:","Ano de nascimento:","Remuneração:"};
        
        if(funcionario != null){
            infos_func[0]=funcionario.getNome();
            infos_func[1]=funcionario.getEmail();
            infos_func[2]=funcionario.getTelefone();
            infos_func[3]=funcionario.getDataNascimento()[0];
            infos_func[4]=funcionario.getDataNascimento()[1];
            infos_func[5]=funcionario.getDataNascimento()[2];
            infos_func[6]=funcionario.lerRemuneracao();
            for(String percorrer_funcoes: Funcionarios.FUNCOES){
                if(funcionario.getFuncao().compareTo(percorrer_funcoes)==0){
                    break;
                }
                valor_caixa_seletora++;
            }
        }

        caixa_seletora = criarCaixa_Seleção(Funcionarios.FUNCOES,valor_caixa_seletora);

        PanelInformacoes formulario = new PanelInformacoes();

        int contador_nova_coluna=0;
        for(int x=0;x<caixas_de_texto.length;x++){
            if(contador_nova_coluna>4){
                formulario.novaColuna();
                contador_nova_coluna=0;
            } else {
                contador_nova_coluna++;
            }
            caixas_de_texto[x] = criarCampo_Texto(infos_func[x]);
            formulario.adicionar(criarTexto(true, titulos_infos_func[x]));
            formulario.adicionar(caixas_de_texto[x]);
        }

        if(!(funcionario instanceof Professores)){
            formulario.adicionar(criarTexto(true, "Função exercida: "));
            formulario.adicionar(caixa_seletora);
        }
        panel_meio.add(formulario.panel);
    }
    @Override
    protected void definirBotoes(){
        //
    }
    /** 
    * método que usa as informações do formulário para alterar o funcionário alvo armazenado
    */
    public void alterarFuncionario(){
        for(int x=0;x<caixas_de_texto.length;x++){
            inputs_campos[x] = caixas_de_texto[x].getText();
        }
        if(caixa_seletora.getSelectedIndex()!=0){
            valor_caixa_seletora = caixa_seletora.getSelectedIndex();
        } else if (!(funcionario instanceof Professores)){
            mostrar_aviso("Função não alterada", "Professores precisam ser criados do zero!");
        }
        inputs_campos[6]=inputs_campos[6].replace(',','.');
        try{
            double double_teste = Double.parseDouble(inputs_campos[6]);
            for(double_teste+=double_teste;double_teste<-9999;double_teste++);
        } catch (java.lang.NumberFormatException e) {
            mostrar_aviso("Remuneração não atualizada", "É necessário um número válido! Tente novamente");
            inputs_campos[6] = "0";
        } finally {
            
        }
        funcionario.alterarDados(valor_caixa_seletora, inputs_campos);
    }
    /** 
    * método que usa as informações do formulário para criar um novo funcionário na escola alvo armazenada
    */
    public void criarFuncionario(){
        for(int x=0;x<caixas_de_texto.length;x++){
            inputs_campos[x] = caixas_de_texto[x].getText();
        }
        inputs_campos[6]=inputs_campos[6].replace(',','.');
        try{
            double double_teste = Double.parseDouble(inputs_campos[6]);
            for(double_teste+=double_teste;double_teste<-9999;double_teste++);
        } catch (java.lang.NumberFormatException e) {
            mostrar_aviso("Remuneração não registrada", "É necessário um número válido! Altere em DETALHES");
            inputs_campos[6] = "0";
        } finally {
            
        }
        valor_caixa_seletora = caixa_seletora.getSelectedIndex();
        if(valor_caixa_seletora==0){
            escola.adicionarFuncionario(new Professores(inputs_campos));
        } else {
            escola.adicionarFuncionario(new Funcionarios(valor_caixa_seletora, inputs_campos));
        }
    }
}
