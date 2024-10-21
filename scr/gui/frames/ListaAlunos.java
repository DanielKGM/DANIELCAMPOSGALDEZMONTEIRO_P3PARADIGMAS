package scr.gui.frames;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JList;

import scr.escola.Escola;
import scr.escola.anos.Anos;
import scr.escola.participantes.alunos.Alunos;
import scr.gui.JanelaAbstrata;
import scr.gui.dialogos.DialogoAluno;
/** 
* Janela que exibe a lista de alunos da escola
* @author Daniel Campos Galdez Monteiro
*/
public class ListaAlunos extends JanelaAbstrata implements ActionListener {
    /** 
    * ano escolar, ele é armazenado porque a lista também pode assumir a forma de exibir alunos apenas de um determinado ano
    */
    Anos ano;
    /** 
    * escola que vão ser exibidos todos seus alunos
    */
    Escola escola;
    /** 
    * componente lista selecionável de alunos
    */
    JList<String> lista_alunos;
    /** 
    * lista de alunos que será utilizada pelo componente de lista
    */
    ArrayList<Alunos> lista_de_alunos;
    /** 
    * diálogo relacionado a essa janela (para criação de novos alunos)
    */
    DialogoAluno dialogo;
    /** 
    * botão de OK que será implementado no diálogo e ouvido por essa janela
    */
    JButton bOk;
    /** 
    * foi passada apenas a escola, ele entende que é uma lista de alunos da escola
    * @param escola escola a ser armazenada e passada para os diálogos
    */
    public ListaAlunos(Escola escola){
        super();
        ano = null;
        this.escola = escola;

        ArrayList<String> nomes_coletados = new ArrayList<>();

        lista_de_alunos = new ArrayList<>();

        for(Anos percorrer_anos: escola.getAnos()){
            for(Alunos percorrer_alunos: percorrer_anos.getListaAlunos()){
                nomes_coletados.add(percorrer_alunos.getNome() + String.format(" (%dº ano)",percorrer_anos.getNumeroAno()));
                lista_de_alunos.add(percorrer_alunos);
            }
        }

        String[] nomes_alunos = nomes_coletados.toArray(new String[nomes_coletados.size()]);
        lista_alunos = criar_Lista(nomes_alunos);
        inicializarJanela("Lista de alunos da escola","Visualizar alunos",380,550);
    }
    /** 
    * foi passado um ano, ele entende que é uma lista de alunos desse ano
    * @param escola escola a ser armazenada e passada para os diálogos
    * @param ano ano a ser armazenado e repassado para o diálogo
    */
    public ListaAlunos(Escola escola, Anos ano){
        this.ano = ano;
        this.escola = escola;
        lista_de_alunos = new ArrayList<>();
        ArrayList<String> nomes_coletados = new ArrayList<>();

        for(Alunos percorrer_alunos: ano.getListaAlunos()){
            nomes_coletados.add(percorrer_alunos.getNome());
            lista_de_alunos.add(percorrer_alunos);
        }

        String[] nomes_alunos = nomes_coletados.toArray(new String[nomes_coletados.size()]);
        lista_alunos = criar_Lista(nomes_alunos);
        inicializarJanela("Lista de alunos da turma","Visualizar Alunos",380,550);
    }
    @Override
    protected void definirConteudo() {
        panel_meio.add(criarPanelLista("Lista de alunos", lista_alunos,300,400));
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
                dialogo = new DialogoAluno(escola);
                dialogo.panel_menu.add(bOk);
                dialogo.setVisible(true);
            }
        });

        bInfo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int index_selecionado = lista_alunos.getSelectedIndex();
                if(index_selecionado>-1){
                    Alunos aluno_selecionado = lista_de_alunos.get(index_selecionado);
                    JanelaAbstrata nova_janela = new InfoAluno(escola,ano,aluno_selecionado);
                    passarFrame(nova_janela);
                }
            }
        });

        bRemover.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lista_alunos.getSelectedIndex()>-1){
                    lista_alunos.remove(lista_alunos.getSelectedIndex());
                    if(ano==null){
                        Alunos aluno_selecionado = lista_de_alunos.get(lista_alunos.getSelectedIndex());
                        Anos ano_do_selecionado = lista_de_alunos.get(lista_alunos.getSelectedIndex()).getAno();
                        escola.removerAlunoEscola(ano_do_selecionado,aluno_selecionado);

                        JanelaAbstrata janela_atualizada = new ListaAlunos(escola);
                        passarFrame(janela_atualizada);

                    } else {
                        Alunos aluno_selecionado = lista_de_alunos.get(lista_alunos.getSelectedIndex());
                        ano.removerAlunoAno(aluno_selecionado);

                        JanelaAbstrata janela_atualizada = new ListaAlunos(escola,ano);
                        passarFrame(janela_atualizada);
                    }
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
                if(ano!=null){
                    JanelaAbstrata frame_voltar = new InfoAno(escola,ano);
                    passarFrame(frame_voltar);
                } else {
                    JanelaAbstrata frame_voltar = new MenuInicial(escola);
                    passarFrame(frame_voltar);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bOk){
            dialogo.criarAluno();
            dialogo.dispose();
            if(ano!=null){
                JanelaAbstrata frame_atualizado = new ListaAlunos(escola,ano);
                passarFrame(frame_atualizado);
            } else {
                JanelaAbstrata frame_atualizado = new ListaAlunos(escola);
                passarFrame(frame_atualizado);
            }
        }
    }
}
