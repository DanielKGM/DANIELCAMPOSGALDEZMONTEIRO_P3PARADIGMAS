package scr.gui.dialogos;
import scr.escola.Escola;
import scr.escola.anos.Anos;
import scr.escola.anos.Disciplinas;
import scr.escola.anos.Horarios;
import scr.escola.participantes.funcionarios.Funcionarios;
import scr.escola.participantes.funcionarios.Professores;
import scr.gui.DialogoAbstrato;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JTextField;
/** 
* Dialogo que carrega os formulários necessários para as Janelas relacionadas às disciplinas (InfoAno)
* @author Daniel Campos Galdez Monteiro
*/
public class DialogoDisciplinas extends DialogoAbstrato{
    /**
    * lista de professores que será utilizada pelo formulário
    */
    ArrayList<Professores> lista_de_docentes;
    /**
    * lista de disciplinas que será utilizada pelo formulário (quando for pra selecionar alguma)
    */
    ArrayList<Disciplinas> lista_de_disciplinas;
    /**
    * disciplina que será armazenada e utilizada para algumas ações
    */
    Disciplinas disciplina;
    /**
    * escola que será armazenada e utilizada para algumas ações
    */
    Escola escola;
    /**
    * ano que será armazenado e utilizado para algumas ações
    */
    Anos ano;
    /**
    * define se o formulário assume forma de selecionar disciplinas do ano ou não
    */
    boolean selecionar_disciplinas;
    /**
    * lista de itens da lista que será utilizada pelo formulário (para listar professores ou disciplinas, depende do polimorfismo)
    */
    JList<String> lista_de_itens;
    JTextField campo_nome;
    
    /** 
    * Quando é passada uma disciplina para o Diálogo, ele entende que deve alterar uma disciplina
    * @param escola escola que vai ser armazenada e utilizada pelo formulário de alterar (caso altere o professor)
    * @param ano ano que será armazenado e utilizado (a disciplina deve ser atualizada no ano)
    * @param disciplina disciplina que será alterada
    */
    public DialogoDisciplinas(Escola escola, Anos ano, Disciplinas disciplina) { //ALTERAR UMA DISCIPLINA
        this.escola = escola;
        this.disciplina = disciplina;
        this.ano=ano;
        selecionar_disciplinas = false;

        inicializarJanela("Alterar disciplina",380,430);
    }
    /** 
    * Quando não é passada uma disciplina para o diálogo, ele entende que é para criar uma nova ou listar todas elas. Isso é controlado pelo boolean
    * @param escola escola que será utiliza (definir o professor)
    * @param ano ano que será utilizado (para listar disciplinas, por exemplo)
    * @param selecionar_disciplinas define se é para listar as disciplinas do ano ou apenas criar uma nova mesmo
    */
    public DialogoDisciplinas(Escola escola, Anos ano, boolean selecionar_disciplinas) { //CRIAR NOVA DISCIPLINA OU SELECIONAR UMA DISCIPLINA
        this.disciplina = null;
        this.escola = escola;
        this.ano = ano;
        this.selecionar_disciplinas = selecionar_disciplinas;
        inicializarJanela("Registrar nova disciplina",380,430);
    }
    @Override
    protected void definirConteudo(){
        ArrayList<String> nomes_coletados = new ArrayList<>();
        String texto_da_lista;

        if(!selecionar_disciplinas){
            PanelInformacoes formulario = new PanelInformacoes();
            lista_de_docentes = new ArrayList<>();
            for(Funcionarios percorrer_funcionarios: escola.getFuncionarios()){
                if(percorrer_funcionarios instanceof Professores){
                    lista_de_docentes.add((Professores) percorrer_funcionarios);
                    nomes_coletados.add(percorrer_funcionarios.getNome());
                }
            }

            formulario.adicionar(criarTexto(true, "Título da disciplina: "));
            if(disciplina!=null){
                campo_nome = criarCampo_Texto(disciplina.getNome());
                formulario.adicionar(campo_nome);
            } else {
                campo_nome = criarCampo_Texto(null);
                formulario.adicionar(campo_nome);
            }
            panel_meio.add(formulario.panel);
            texto_da_lista = "Atribua um professor:";
        } else {
            lista_de_disciplinas = new ArrayList<>();
            for(Disciplinas percorrer_disciplinas: ano.getListaDisciplinas()){
                lista_de_disciplinas.add(percorrer_disciplinas);
                nomes_coletados.add(percorrer_disciplinas.getNome()+" ("+percorrer_disciplinas.getDocente().getNome()+")");
            }
            texto_da_lista = "Escolha uma disciplina:";
        }

        String[] nomes_coletados_string = nomes_coletados.toArray(new String[nomes_coletados.size()]);
        lista_de_itens = criar_Lista(nomes_coletados_string);

        if((!selecionar_disciplinas)&&(disciplina!=null)){
            lista_de_itens.setSelectedIndex(lista_de_docentes.indexOf(disciplina.getDocente()));
        }

        panel_meio.add(criarPanelLista(texto_da_lista, lista_de_itens, 300, 350));
    }
    @Override
    protected void definirBotoes(){
        //
    }
    /** 
    * método que usa as informações do formulário para criar uma nova disciplina
    */
    public void criarDisciplina(){
        if(lista_de_itens.getSelectedIndex()==-1){
            mostrar_aviso("Disciplina não registrada", "É necessário um docente para ministrá-la!");
        } else {
            Professores docente_selecionado = lista_de_docentes.get(lista_de_itens.getSelectedIndex());
            Disciplinas disciplina_criada = new Disciplinas(campo_nome.getText(), ano.getNumeroAno(), docente_selecionado);
            ano.adicionarDisciplina(disciplina_criada);
            docente_selecionado.atribuirDisciplina(disciplina_criada);
            mostrar_aviso("Disciplina registrada com sucesso!", "Atribua a disciplina a algum horário");
        }
    }
    /** 
    * método que usa as informações do formulário para atualizar os valores de alguma disciplina
    */
    public void alterarDisciplina(){
        if(lista_de_itens.getSelectedIndex()==-1){
            mostrar_aviso("Disciplina não alterada", "É necessário um docente para ministrá-la!");
        } else {
        Professores docente_selecionado = lista_de_docentes.get(lista_de_itens.getSelectedIndex());
        disciplina.setNome(campo_nome.getText());
        disciplina.atribuirDocente(docente_selecionado);
        docente_selecionado.desatribuirDisciplina(disciplina);
        docente_selecionado.atribuirDisciplina(disciplina);
        }
    }
    /** 
    * método que usa as informações do formulário para atribuir uma disciplina a algum horário
    * @param horario_selecionado horário ao qual a disciplina será atribuida
    */
    public void atribuirDisciplina(Horarios horario_selecionado){
        if(lista_de_itens.getSelectedIndex()==-1){
            mostrar_aviso("Atribuição cancelada", "Não foi selecionada uma disciplina!");
        } else {
            Disciplinas disciplina_selecionada = lista_de_disciplinas.get(lista_de_itens.getSelectedIndex());
            disciplina_selecionada.desatribuirHorario(horario_selecionado);
            disciplina_selecionada.atribuirHorario(horario_selecionado);
        }
    }
}
