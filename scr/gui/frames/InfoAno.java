package scr.gui.frames;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import scr.escola.Escola;
import scr.escola.anos.Anos;
import scr.escola.anos.Disciplinas;
import scr.escola.anos.Horarios;
import scr.gui.JanelaAbstrata;
import scr.gui.dialogos.DialogoDisciplinas;
/** 
* Janela que exibe as informações sobre um dado ano(turma).Ela será um ActionListener dos botões de seus diálogos.
* @author Daniel Campos Galdez Monteiro
*/
public class InfoAno extends JanelaAbstrata implements ActionListener {
    /** 
    * botão de criar disciplina que será "injetado" em seu diálogo e ouvido pela Janela
    */
    JButton bOkCriarDisc;
    /** 
    * botão de alterar disciplina que será "injetado" em seu diálogo e ouvido pela Janela
    */
    JButton bOkAlterarDisc;
    /** 
    * botão de atribuir disciplina a algum horário que será "injetado" em seu diálogo e ouvido pela Janela
    */
    JButton bOkAtribuirDisc;
    /** 
    * diálogo relacionado a essa Janela
    */
    DialogoDisciplinas dialogo;
    /** 
    * ano que será armazenado e passado para o diálogo
    */
    Anos ano;
    /** 
    * tabela de horários selecionável que será gerada e exibida nas informações sobre o ano
    */
    JTable tabela_horarios;
    /** 
    * escola que será armazenada e passada para o diálogo
    */
    Escola escola;
    /** 
    * por padrão armazena a escola do ano que será exibido, e o próprio ano
    * @param escola escola a ser armazenada
    * @param ano a ser armazenado
    */
    public InfoAno(Escola escola, Anos ano){
        this.ano = ano;
        this.escola = escola;
        inicializarJanela(String.format("Detalhes sobre o %dº ano",ano.getNumeroAno()),"Detalhes sobre a turma",680,540);
    }
    @Override
    protected void definirConteudo() {
        JLabel nome_disciplina = criarTexto(true, null);
        JLabel professor_disciplina = criarTexto(false, null);

        PanelInformacoes infos_ano = new PanelInformacoes();
        PanelInformacoes opcoes_editar_disciplinas = new PanelInformacoes();
        JButton bLimparhorario = criarBotao("LIMPAR",BRANCO);
        JButton bAtribuirhorario = criarBotao("ATRIBUIR",BRANCO);

        bOkAtribuirDisc = criarBotao("ATRIBUIR");
        bOkAtribuirDisc.addActionListener(this);

        bAtribuirhorario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabela_horarios.getSelectedColumn()>0){
                    dialogo = new DialogoDisciplinas(escola, ano, true);
                    dialogo.panel_menu.add(bOkAtribuirDisc);
                    dialogo.setVisible(true);
                }
            }
        });

        bLimparhorario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Disciplinas disciplina_selecionada = getDisciplinaSelecionada(ano);
                if(disciplina_selecionada!=null){
                    disciplina_selecionada.desatribuirHorario(getHorarioSelecionado(ano));
                    JanelaAbstrata frame_atualizado = new InfoAno(escola, ano);
                    passarFrame(frame_atualizado);
                }
            }
        });

        tabela_horarios = criarTabela_Horarios();

        infos_ano.adicionar(criarTexto(true,"Turma: "));
        infos_ano.adicionar(criarTexto(false,String.format("%dº ano do Ensino Fundamental",ano.getNumeroAno())));
        infos_ano.adicionar(criarTexto(true,"Quantidade a alunos: "));
        infos_ano.adicionar(criarTexto(false,String.format("%d aluno(s)",ano.getListaAlunos().size())));
        infos_ano.adicionar(criarTexto(true,"Horários e disciplinas da turma: "));
        panel_meio.add(infos_ano.panel);

        for(int C=0;C<(tabela_horarios.getColumnCount()-1);C++){
            for(int R=0;R<tabela_horarios.getRowCount();R++){
                Disciplinas disciplina_selecionada = getDisciplinaSelecionada(ano,C,R);
                if(disciplina_selecionada!=null){
                    tabela_horarios.setValueAt(disciplina_selecionada.getNome(), R, C+1);
                }
            }
        }

        panel_meio.add(criarPanelHorarios(tabela_horarios));
        opcoes_editar_disciplinas.adicionar(criarTexto(true, "Horario selecionado "));
        opcoes_editar_disciplinas.adicionar(bAtribuirhorario);
        opcoes_editar_disciplinas.adicionar(bLimparhorario);
        opcoes_editar_disciplinas.adicionar(nome_disciplina);
        opcoes_editar_disciplinas.adicionar(professor_disciplina);
        panel_meio.add(opcoes_editar_disciplinas.panel);
    }
    @Override
    protected void definirBotoes() {
        bOkAlterarDisc = criarBotao("ALTERAR");
        bOkCriarDisc = criarBotao("CRIAR");
        bOkAlterarDisc.addActionListener(this);
        bOkCriarDisc.addActionListener(this);

        JButton bAlunos = criarBotao("VER ALUNOS");
        JButton bNova = criarBotao("NOVA DISCIPLINA");
        JButton bAlterar = criarBotao("ALTERAR SELECIONADA");
        JButton bApagar = criarBotao("APAGAR SELECIONADA",VERMELHO);

        bAlunos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAbstrata nova_janela = new ListaAlunos(escola,ano);
                passarFrame(nova_janela);
            }
        });

        bNova.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo = new DialogoDisciplinas(escola, ano, false);
                dialogo.panel_menu.add(bOkCriarDisc);
                dialogo.setVisible(true);
            }
        });

        bAlterar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Disciplinas disciplina_selecionada = getDisciplinaSelecionada(ano);
                if(disciplina_selecionada!=null){
                    dialogo = new DialogoDisciplinas(escola, ano, disciplina_selecionada);
                    dialogo.panel_menu.add(bOkAlterarDisc);
                    dialogo.setVisible(true);
                }
            }
        });

        bApagar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Disciplinas disciplina_selecionada = getDisciplinaSelecionada(ano);
                if(disciplina_selecionada!=null){
                    ano.removerDisciplina(disciplina_selecionada);
                    disciplina_selecionada.desatribuirDocente();
                    JanelaAbstrata janela_atualizada = new InfoAno(escola,ano);
                    passarFrame(janela_atualizada);
                }
            }
        });

        panel_menu.add(bAlunos);
        panel_menu.add(bNova);
        panel_menu.add(bAlterar);
        panel_menu.add(bApagar);
    }
    @Override
    protected void definirBotaoVoltar() {
        botao_de_voltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAbstrata frame_voltar = new ListaAnos(escola);
                passarFrame(frame_voltar);
            }
        });
    }
    /** 
    * retorna o horário selecionado de acordo com a tabela de horários selecionável
    * @param ano ano da tabela em questão
    * @return horário selecionado pela tabela
    */
    private Horarios getHorarioSelecionado(Anos ano){
        if(tabela_horarios.getSelectedColumn()<1){
            return null;
        }
        for(Horarios percorrer_horarios: ano.getListaHorarios()){
            if((percorrer_horarios.getDia()==Horarios.DIAS_SEMANA[tabela_horarios.getSelectedColumn()-1])&&(percorrer_horarios.getInicio()==Horarios.HORARIOS_INICIO[tabela_horarios.getSelectedRow()])){
                return percorrer_horarios;
            }
        }
        return null;
    }
    /** 
    * retorna a disciplina selecionada pela tabela de horários selecionável
    * @param ano ano da tabela em questão
    * @return disciplina selecionada pelo horário
    */
    private Disciplinas getDisciplinaSelecionada(Anos ano){
        Horarios horario_selecionado = getHorarioSelecionado(ano);
        for(Disciplinas percorrer: ano.getListaDisciplinas()){
            if(percorrer.temHorario(horario_selecionado)){
                return percorrer;
            }
        }
        return null;
    }
    /** 
    * retorna o horário selecionado de acordo com a tabela de horários selecionável
    * @param ano ano da tabela em questão
    * @return horário selecionado pela tabela
    */
    private Horarios getHorarioSelecionado(Anos ano,int coluna, int linha){
        for(Horarios percorrer_horarios: ano.getListaHorarios()){
            if((percorrer_horarios.getDia()==Horarios.DIAS_SEMANA[coluna])&&(percorrer_horarios.getInicio()==Horarios.HORARIOS_INICIO[linha])){
                return percorrer_horarios;
            }
        }
        return null;
    }
    /** 
    * retorna a disciplina selecionada de acordo com a tabela de horários selecionável
    * @param ano ano da tabela em questão
    * @return horário selecionado pela tabela
    */
    private Disciplinas getDisciplinaSelecionada(Anos ano, int coluna, int linha){
        Horarios horario_selecionado = getHorarioSelecionado(ano,coluna,linha);
        for(Disciplinas percorrer: ano.getListaDisciplinas()){
            if(percorrer.temHorario(horario_selecionado)){
                return percorrer;
            }
        }
        return null;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bOkCriarDisc){
            dialogo.criarDisciplina();
            dialogo.dispose();
        } else if (e.getSource()==bOkAlterarDisc){
            dialogo.alterarDisciplina();
            dialogo.dispose();
            JanelaAbstrata frame_atualizado = new InfoAno(escola, ano);
            passarFrame(frame_atualizado);

        } else if (e.getSource()==bOkAtribuirDisc){
            dialogo.atribuirDisciplina(getHorarioSelecionado(ano));
            JanelaAbstrata frame_atualizado = new InfoAno(escola, ano);
            passarFrame(frame_atualizado);
            dialogo.dispose();
        }
    }
}
