package scr.gui.frames;
import java.awt.event.*;
import javax.swing.JButton;
import scr.escola.Escola;
import scr.escola.anos.Anos;
import scr.escola.participantes.alunos.Alunos;
import scr.gui.JanelaAbstrata;
import scr.gui.dialogos.DialogoAluno;
/** 
* Janela que exibe as informações sobre um dado aluno.Ela será um ActionListener dos botões de seus diálogos.
* @author Daniel Campos Galdez Monteiro
*/
public class InfoAluno extends JanelaAbstrata implements ActionListener {
    /** 
    * aluno cujo as informações estão sendo exibidas
    */
    Alunos aluno;
    /** 
    * escola do aluno, que será passada para o diálogo
    */
    Escola escola;
    /** 
    * ano do aluno, que será passada para o diálogo
    */
    Anos ano_do_aluno;
    /** 
    * botão de ok que será "injetado" no diálogo e escutado por essa janela
    */
    JButton bOk;
    /** 
    * diálogo relacionado à janela
    */
    DialogoAluno dialogo;
    /** 
    * por padrão armazena a escola, o ano e o aluno sendo exibido
    * @param escola escola a ser armazenada
    * @param ano a ser armazenado
    * @param aluno aluno a ser exibido e armazenado
    */
    public InfoAluno(Escola escola, Anos ano, Alunos aluno){
        this.escola = escola;
        this.aluno = aluno;
        ano_do_aluno = ano;
        inicializarJanela(aluno.getNome(),"Detalhes sobre o aluno",380,550);
    }
    @Override
    protected void definirConteudo() {
        PanelInformacoes infos_aluno = new PanelInformacoes();

        infos_aluno.adicionar(criarTexto(true,"Nome completo: "));
        infos_aluno.adicionar(criarTexto(false,aluno.getNome()));
        infos_aluno.adicionar(criarTexto(true,"Data de nascimento: "));
        infos_aluno.adicionar(criarTexto(false,aluno.getDataNascimento()[0]+"/"+aluno.getDataNascimento()[1]+"/"+aluno.getDataNascimento()[2]));
        infos_aluno.adicionar(criarTexto(true,"Email: "));
        infos_aluno.adicionar(criarTexto(false,aluno.getEmail()));
        infos_aluno.adicionar(criarTexto(true,"Telefone: "));
        infos_aluno.adicionar(criarTexto(false,aluno.getTelefone()));
        infos_aluno.adicionar(criarTexto(true,"Ano escolar: "));
        infos_aluno.adicionar(criarTexto(false,String.format("%dº ano do ensino fundamental",aluno.getAno().getNumeroAno())));
        infos_aluno.adicionar(criarTexto(true,"Nome do responsável: "));
        infos_aluno.adicionar(criarTexto(false,aluno.getResponsavel()[0]));
        infos_aluno.adicionar(criarTexto(true,"Email do responsável: "));
        infos_aluno.adicionar(criarTexto(false,aluno.getResponsavel()[1]));
        infos_aluno.adicionar(criarTexto(true,"Telefone do responsável"));
        infos_aluno.adicionar(criarTexto(false,aluno.getResponsavel()[2]));
        panel_meio.add(infos_aluno.panel);

    }
    @Override
    protected void definirBotoes() {
        bOk = criarBotao("OK");
        bOk.addActionListener(this);

        JButton bAlterarAluno = criarBotao("ALTERAR DADOS");
        JButton bRemoverAluno = criarBotao("REMOVER ALUNO",VERMELHO);

        bAlterarAluno.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo = new DialogoAluno(escola,aluno);
                dialogo.panel_menu.add(bOk);
                dialogo.setVisible(true);
            }
        });

        bRemoverAluno.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                aluno.getAno().removerAlunoAno(aluno);
                
                if(ano_do_aluno!=null){
                    JanelaAbstrata janela_atualizada = new ListaAlunos(escola,ano_do_aluno);
                    passarFrame(janela_atualizada);
                } else {
                    JanelaAbstrata janela_atualizada = new ListaAlunos(escola);
                    passarFrame(janela_atualizada);
                }
            }
        });

        panel_menu.add(bAlterarAluno);
        panel_menu.add(bRemoverAluno);
    }

    @Override
    protected void definirBotaoVoltar() {
        botao_de_voltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ano_do_aluno!=null){
                    JanelaAbstrata frame_voltar = new ListaAlunos(escola,ano_do_aluno);
                    passarFrame(frame_voltar);
                } else {
                    JanelaAbstrata frame_voltar = new ListaAlunos(escola);
                    passarFrame(frame_voltar);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bOk){
            dialogo.alterarAluno();
            dialogo.dispose();
            JanelaAbstrata frame_atualizado = new InfoAluno(escola,ano_do_aluno,aluno);
            passarFrame(frame_atualizado);
        }
    }
}
