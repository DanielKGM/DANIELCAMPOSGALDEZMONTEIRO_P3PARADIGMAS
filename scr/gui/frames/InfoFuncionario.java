package scr.gui.frames;
import java.awt.event.*;
import javax.swing.JButton;

import scr.escola.Escola;
import scr.escola.anos.Disciplinas;
import scr.escola.participantes.funcionarios.Funcionarios;
import scr.escola.participantes.funcionarios.Professores;
import scr.gui.JanelaAbstrata;
import scr.gui.dialogos.DialogoFuncionario;
/** 
* Janela que exibe as informações sobre um dado funcionário.Ela será um ActionListener dos botões de seus diálogos.
* @author Daniel Campos Galdez Monteiro
*/
public class InfoFuncionario extends JanelaAbstrata implements ActionListener {
    /** 
    * diálogo relacionado à janela
    */
    DialogoFuncionario dialogo;
    /** 
    * botão de ok que será "injetado" no diálogo e escutado por essa janela
    */
    JButton bOk;
    /** 
    * funcionário cujo as informações estão sendo exibidas
    */
    Funcionarios funcionario;
    /** 
    * escola do aluno, que será passada para o diálogo
    */
    Escola escola;
    /** 
    * por padrão armazena a escola do funcionário que será exibido, e o próprio ano
    * @param escola escola a ser armazenada
    * @param funcionario a ser armazenado
    */
    public InfoFuncionario(Escola escola, Funcionarios funcionario){
        this.funcionario = funcionario;
        this.escola = escola;
        if(funcionario instanceof Professores){
            inicializarJanela(funcionario.getNome(),"Sobre o funcionario",500,550);
        } else {
            inicializarJanela(funcionario.getNome(),"Sobre o funcionario",380,550);
        }
    }
    @Override
    protected void definirConteudo() {
        PanelInformacoes infos_funcionario = new PanelInformacoes();

        infos_funcionario.adicionar(criarTexto(true,"Nome completo: "));
        infos_funcionario.adicionar(criarTexto(false,funcionario.getNome()));
        infos_funcionario.adicionar(criarTexto(true,"Data de nascimento: "));
        infos_funcionario.adicionar(criarTexto(false,funcionario.getDataNascimento()[0]+"/"+funcionario.getDataNascimento()[1]+"/"+funcionario.getDataNascimento()[2]));
        infos_funcionario.adicionar(criarTexto(true,"Email: "));
        infos_funcionario.adicionar(criarTexto(false,funcionario.getEmail()));
        infos_funcionario.adicionar(criarTexto(true,"Telefone: "));
        infos_funcionario.adicionar(criarTexto(false,funcionario.getTelefone()));
        infos_funcionario.adicionar(criarTexto(true,"Função exercida: "));
        infos_funcionario.adicionar(criarTexto(false,funcionario.getFuncao()));
        infos_funcionario.adicionar(criarTexto(true,"Remuneracao: "));
        infos_funcionario.adicionar(criarTexto(false,"R$ "+funcionario.lerRemuneracao()));

        if(funcionario instanceof Professores){
            infos_funcionario.novaColuna();
            infos_funcionario.adicionar(criarTexto(true,"Disciplinas ministradas: "));
            Professores professor = (Professores) funcionario;
            if (professor.getDisciplinas()!=null){
                for(Disciplinas percorrer_disciplinas: professor.getDisciplinas()){
                    infos_funcionario.adicionar(criarTexto(false,percorrer_disciplinas.getNome()+String.format("(%dº ano)", percorrer_disciplinas.getAno())));
                }
            } else {
                infos_funcionario.adicionar(criarTexto(false,"Nenhuma disciplina ministrada"));
            }
        }

        panel_meio.add(infos_funcionario.panel);

    }
    @Override
    protected void definirBotoes() {
        bOk = criarBotao("OK");
        bOk.addActionListener(this);
        JButton bAlterar = criarBotao("ALTERAR DADOS");
        JButton bRemover = criarBotao("REMOVER FUNCIONÁRIO",VERMELHO);

        bAlterar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo = new DialogoFuncionario(escola, funcionario);
                dialogo.panel_menu.add(bOk);
                dialogo.setVisible(true);
            }
        });

        bRemover.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                escola.removerFuncionarioEscola(funcionario);
                JanelaAbstrata frame_atualizado = new ListaFuncionarios(escola);
                passarFrame(frame_atualizado);
            }
        });

        panel_menu.add(bAlterar);
        panel_menu.add(bRemover);
    }

    @Override
    protected void definirBotaoVoltar() {
        botao_de_voltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAbstrata frame_voltar = new ListaFuncionarios(escola);
                passarFrame(frame_voltar);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bOk){
            dialogo.alterarFuncionario();
            dialogo.dispose();
            JanelaAbstrata frame_atualizado = new InfoFuncionario(escola,funcionario);
            passarFrame(frame_atualizado);
        }
    }
}
