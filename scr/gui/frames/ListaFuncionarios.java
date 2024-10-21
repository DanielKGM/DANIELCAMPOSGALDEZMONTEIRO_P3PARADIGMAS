package scr.gui.frames;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JList;
import scr.escola.Escola;
import scr.escola.participantes.funcionarios.Funcionarios;
import scr.gui.JanelaAbstrata;
import scr.gui.dialogos.DialogoFuncionario;
/** 
* Janela que exibe uma lista selecionável de funcionários da escola
* @author Daniel Campos Galdez Monteiro
*/
public class ListaFuncionarios extends JanelaAbstrata implements ActionListener {
    /** 
    * escola que vão ser exibidos seus funcionários, e passada para o diálogo
    */
    Escola escola;
    /** 
    * lista de funcionários da escola que será utilizada pelo componente lista
    */
    ArrayList<Funcionarios> lista_de_funcionarios;
    /** 
    * componente lista que exibirá os funcionários e poderá ser selecionado
    */
    JList<String> lista_funcionarios;
    /** 
    * botão de OK que será implementado no diálogo e ouvido por essa janela
    */
    JButton bOk;
    /** 
    * diálogo relacionado a essa janela (para criação de novos funcionários)
    */
    DialogoFuncionario dialogo;
    /** 
    * por padrão recebe e armazena a escola cujo os funcionários serão exibidos, como também define a lista de nomes dos funcionários
    * @param escola escola a ser armazenada e passada para os diálogos
    */
    public ListaFuncionarios(Escola escola){
        lista_de_funcionarios = new ArrayList<>();
        ArrayList<String> nomes_coletados = new ArrayList<>();
        this.escola = escola;

        for(Funcionarios percorrer_funcionarios: escola.getFuncionarios()){
            nomes_coletados.add(percorrer_funcionarios.getNome()+" ("+percorrer_funcionarios.getFuncao()+")");
            lista_de_funcionarios.add(percorrer_funcionarios);
        }

        String[] nomes_funcionarios = nomes_coletados.toArray(new String[nomes_coletados.size()]);
        lista_funcionarios = criar_Lista(nomes_funcionarios);

        inicializarJanela("Lista de funcionários da escola","Visualizar funcionários",390,550);
    }
    @Override
    protected void definirConteudo() {
        panel_meio.add(criarPanelLista("Lista de funcionarios", lista_funcionarios,300,550));
    }
    @Override
    protected void definirBotoes() {
        bOk = criarBotao("OK");
        bOk.addActionListener(this);
        JButton bRegistrarNovo = criarBotao("NOVO");
        JButton bInfo = criarBotao("DETALHES");
        JButton bRemover = criarBotao("REMOVER",VERMELHO);

        bRegistrarNovo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo = new DialogoFuncionario(escola);
                dialogo.panel_menu.add(bOk);
                dialogo.setVisible(true);
            }
        });

        bInfo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lista_funcionarios.getSelectedIndex()>-1){
                    Funcionarios funcionario_selecionado = lista_de_funcionarios.get(lista_funcionarios.getSelectedIndex());
                    JanelaAbstrata nova_janela = new InfoFuncionario(escola,funcionario_selecionado);
                    passarFrame(nova_janela);
                }
            }
        });
        
        bRemover.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lista_funcionarios.getSelectedIndex()>-1){
                    Funcionarios funcionario_selecionado = lista_de_funcionarios.get(lista_funcionarios.getSelectedIndex());
                    escola.removerFuncionarioEscola(funcionario_selecionado);
                    JanelaAbstrata janela_atualizada = new ListaFuncionarios(escola);
                    passarFrame(janela_atualizada);
                }
            }
        });

        panel_menu.add(bRegistrarNovo);
        panel_menu.add(bInfo);
        panel_menu.add(bRemover);
    }

    @Override
    protected void definirBotaoVoltar() {
        botao_de_voltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAbstrata frame_voltar = new MenuInicial(escola);
                passarFrame(frame_voltar);
                
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bOk){
            dialogo.criarFuncionario();
            dialogo.dispose();
            JanelaAbstrata frame_atualizado = new ListaFuncionarios(escola);
            passarFrame(frame_atualizado);
        }
    }
}
