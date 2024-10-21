package scr.gui.frames;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import scr.escola.Escola;
import scr.gui.JanelaAbstrata;
/** 
* Janela de menu inial, exibe as janelas iniciais e permite configurar algumas coisas da classe "Escola"
* @author Daniel Campos Galdez Monteiro
*/
public class MenuInicial extends JanelaAbstrata {
    Escola escola;
    /** 
    * Recebe alguma escola para então começar a exibir a interface gráfica 
    * @param escola escola a ser navegada
    */
    public MenuInicial(Escola escola){
        this.escola = escola;
        inicializarJanela(String.format("(%dº bimestre) %s",escola.getBimestre(), escola.getNome()),"BEM-VINDO(A)!",480,340);
    }
    @Override
    protected void definirConteudo() {
        JButton bAnos = criarBotao("TURMAS",VERDE,true);
        JButton bAlunos = criarBotao("ALUNOS",VERMELHO,true);
        JButton bFuncionarios = criarBotao("FUNCIONÁRIOS",AMARELO,true);
        JButton bNome = criarBotao("NOME",BRANCO);
        JButton bBimestre = criarBotao("+ BIMESTRE",BRANCO);
        PanelInformacoes botoes = new PanelInformacoes();

        bAnos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAbstrata nova_janela = new ListaAnos(escola);
                passarFrame(nova_janela);
            }
        });

        bAlunos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAbstrata nova_janela = new ListaAlunos(escola);
                passarFrame(nova_janela);
            }
        });

        bFuncionarios.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAbstrata nova_janela = new ListaFuncionarios(escola);
                passarFrame(nova_janela);
            }
        });

        bNome.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog(null, "Insira o novo nome");
                if(nome!=null){
                    escola.setNome(nome);
                    JanelaAbstrata janela_atualizada = new MenuInicial(escola);
                    passarFrame(janela_atualizada);
                }
            }
        });
        bBimestre.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                escola.passarBimestre();
                JOptionPane.showConfirmDialog(null,String.format("A escola agora está no %dº bimestre!", escola.getBimestre()),"Bimestre alterado",JOptionPane.DEFAULT_OPTION);
                JanelaAbstrata janela_atualizada = new MenuInicial(escola);
                passarFrame(janela_atualizada);
            }
        });

        botoes.adicionar(bAnos);
        botoes.adicionar(bAlunos);
        botoes.adicionar(bFuncionarios);
        botoes.adicionar(bNome);
        botoes.adicionar(bBimestre);
        panel_meio.add(botoes.panel);
    }
    @Override
    protected void definirBotoes() {
        panel_menu.add(criarTexto(true,"Por Daniel Campos Galdez Monteiro"));
    }

    @Override
    protected void definirBotaoVoltar() {
        botao_de_voltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
