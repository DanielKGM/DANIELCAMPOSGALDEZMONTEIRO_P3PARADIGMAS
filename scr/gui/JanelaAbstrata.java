package scr.gui;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
/** 
* "Esqueleto abstrato" dos frames (ou "páginas") que vão ser visitadas e navegadas pelo usuário na interface gráfica
* @author Daniel Campos Galdez Monteiro
*/
public abstract class JanelaAbstrata extends JFrame {
    ////////// DECLARAÇÃO DE VARIAVEIS  
    /** 
    * label do título do cabeçalho da janela
    */
    private JLabel label_titulo;
    /** 
    * panel que será o pedaço central da janela
    */
    protected JPanel panel_meio;
    /** 
    * panel que será a parte inferior da janela, o menu de rodapé
    */
    protected JPanel panel_menu;
    /** 
    * panel que será o cabeçalho da janela, a parte de cima, o título
    */
    protected JPanel panel_titulo;
    /** 
    * botão de "voltar" que ficará no cabeçalho de todas as janelas
    */
    protected JButton botao_de_voltar;
    ///////// <FIM>

    /////// CORES QUE ESCOLHI PROS BOTOES
    protected static final int VERMELHO = 0;
    protected static final int BRANCO = 1;
    protected static final int AMARELO = 2;
    protected static final int VERDE = 3;
    // QUALQUER OUTRO NUMERO = ROXO

    ////////// CONSTRUTOR
    public JanelaAbstrata(){
        //
    }
    ///////// <FIM>

    /** 
    * método que invoca as mensagens que serão enviadas ao usuário durante a execução do programa
    */
    public void mostrar_aviso(String titulo, String texto){
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.PLAIN_MESSAGE);
    }
    
    /////////// MÉTODOS ABSTRATOS PARA DEFINIR O CONTEÚDO DENTRO DA MINHA JANELA, DEVE SER IMPLEMENTADA EM CADA
    /** 
    * metodo para adicionar componentes ao panel do meio (ou corpo) da janela
    */
    protected abstract void definirConteudo();
    /** 
    * método para adicionar componentes ao rodapé, menu, panel que fica em baixo das janelas
    */
    protected abstract void definirBotoes();
    /** 
    * método chamado logo após a criação do "botão de voltar" que fica no cabeçalho da janela, serve para definir o que ele vai fazer, ou para onde vai levar, em uma janela específica 
    */
    protected abstract void definirBotaoVoltar();
    ///////////  <FIM>
    
    ////////// MUDAR DE FRAME
    /** 
    * metodo invocado pela janela "A" para ir para a janela "B", passada por parâmetro.Se a janela "A" passar uma instância dela mesma, serve como atualização da janela
    * @param frame nova janela a ser acessada
    */
    public void passarFrame(JanelaAbstrata frame){
        this.dispose();
        frame.setLocation(this.getLocation());
        frame.setVisible(true);
        frame.toFront();
    }

    /////////// <FIM>

    /////////// METODOS PARA CRIAR OS MEUS PANEIS - DE INFORMAÇÕES, DE TABELA DE HORARIOS OU DE LISTA
    /** 
    * essa classe aninhada serve para criar um Panel de informações/componentes de forma organizada, ela guarda um Panel em Gridbaglayout e tem métodos para gerenciar os componentes dentro desse panel 
    */
    protected class PanelInformacoes{
        /** 
        * Panel que guardará os componentes de forma organizada
        */
        public JPanel panel;
        /** 
        * posição x, ou coluna do panel
        */
        int grid_x = 0;
        /** 
        * posição y, ou linha do panel
        */
        int grid_y=0;
        java.awt.GridBagConstraints gb_constraint;
        /** 
        * por padrão, cria um novo panel em gridbaglayout
        */
        public PanelInformacoes(){
            gb_constraint = new java.awt.GridBagConstraints();
            panel = new JPanel();
            panel.setBackground(new java.awt.Color(248, 238, 231));
            panel.setPreferredSize(null);
            panel.setLayout(new java.awt.GridBagLayout());
        }
        /** 
        * adiciona algum componente ao panel guardado pela instância da classe
        * @param componente a ser adicionado no panel organizado
        */
        public void adicionar(JComponent componente){
            gb_constraint = new java.awt.GridBagConstraints();
            gb_constraint.gridx = grid_x;
            gb_constraint.gridy = grid_y;
            grid_y++;
            gb_constraint.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gb_constraint.insets = new java.awt.Insets(0, 0, 6, 20);
            panel.add(componente, gb_constraint);
        }
        /** 
        * avança uma coluna no panel de componentes
        */
        public void novaColuna(){
            grid_y=0;
            grid_x++;
        }
        /** 
        * avança uma linha no panel de componentes
        */
        public void novaLinha(){
            grid_y++;
            grid_x=0;
        }
    }
    /** 
    * cria um scroll pane para uma tabela de horários (essa tabela é padrão e gerada para os anos da escola)
    * @param tabela_horarios tabela recebida
    * @return scroll pane gerado para a tabela
    */
    protected JScrollPane criarPanelHorarios(JTable tabela_horarios){

        JScrollPane scroll_panel_horarios = new JScrollPane();
        scroll_panel_horarios.setBackground(new java.awt.Color(244, 222, 203));
        scroll_panel_horarios.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_panel_horarios.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scroll_panel_horarios.setPreferredSize(new java.awt.Dimension(620, 110));
        scroll_panel_horarios.setViewportView(tabela_horarios);
        Border semborda = new EmptyBorder(0,0,0,0);
        scroll_panel_horarios.setBorder(semborda);
        return scroll_panel_horarios;
    }
    /** 
    * criar um scroll pane para uma determinada lista e coloca em um Panel de informações (Panel organizado de componentes)
    * @param titulo título da lista, label que fica no início do panel organizado
    * @param lista lista que será adicionada ao panel organizado
    * @param largura largura do panel
    * @param altura altura do panel
    * @return panel organizado "formatado" para exibir a lista recebida
    */
    protected JPanel criarPanelLista(String titulo, JList<String> lista, int largura, int altura){
        JScrollPane scroll_panel_lista = new JScrollPane();
        scroll_panel_lista.setBorder(null);
        scroll_panel_lista.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_panel_lista.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        scroll_panel_lista.setPreferredSize(new java.awt.Dimension(largura, altura));
        scroll_panel_lista.setViewportView(lista);

        PanelInformacoes p1 = new PanelInformacoes();
        p1.adicionar(criarTexto(true, titulo));
        p1.adicionar(scroll_panel_lista);
        return p1.panel;
    }
    ///////// <FIM>
    /** 
    * cria um componente de label(texto)
    * @param titulo define se é em negrito (texto de título) ou não
    * @param mensagem texto do label
    * @return texto gerado de acordo com os parâmetros e "design" do programa
    */
    /////////// METODOS PARA CRIAR OS MEUS COMPONENTES (QUE FICARÃO DENTRO DOS PANEIS)
    /////////// TEXTO (TITULO OU CORPO), CAMPO DE TEXTO, CAIXA SELETORA, TABELA, LISTA, BOTÃO
    protected JLabel criarTexto(boolean titulo, String mensagem){
        JLabel texto = new JLabel();
        if(titulo){
            texto.setFont(new java.awt.Font("Helvetica", 1, 14));
        } else {
            texto.setFont(new java.awt.Font("Helvetica", 0, 14));
        }
        texto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        texto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        texto.setText(mensagem);

        return texto;
    }
    /** 
    * cria um campo de texto para ser preenchido pelo usuário
    * @param texto_inicial texto padrão que aparecerá nele assim que gerado
    * @return componente do campo de texto gerado
    */
    protected JTextField criarCampo_Texto(String texto_inicial){
        JTextField campo_texto = new JTextField();
        campo_texto.setBackground(new java.awt.Color(244, 222, 203));
        campo_texto.setFont(new java.awt.Font("Helvetica", 0, 14));
        campo_texto.setText(texto_inicial);
        campo_texto.setMinimumSize(new java.awt.Dimension(64, 28));
        campo_texto.setPreferredSize(new java.awt.Dimension(200, 30));
        campo_texto.setSelectionColor(new java.awt.Color(148, 97, 142));
        return campo_texto;
    }
    /** 
    * cria uma caixa de seleção de uma lista de itens
    * @param opcoes lista que será a lista de itens da caixa de seleção
    * @return caixa de seleção gerada
    */
    protected JComboBox<String> criarCaixa_Seleção(String[] opcoes){
        JComboBox<String> caixa_selecao = new JComboBox<>();
        caixa_selecao.setBackground(new java.awt.Color(244, 222, 203));
        caixa_selecao.setFont(new java.awt.Font("Helvetica", 0, 14));
        caixa_selecao.setModel(new javax.swing.DefaultComboBoxModel<>(opcoes));
        caixa_selecao.setMinimumSize(new java.awt.Dimension(72, 28));
        caixa_selecao.setPreferredSize(new java.awt.Dimension(200, 28));
        return caixa_selecao;
    }
    /** 
    * cria uma tabela de horários selecionável e pré definida da escola
    * @return tabela de horários gerável
    */
    protected JTable criarTabela_Horarios(){
        JTable tabela_horarios = new JTable();
        tabela_horarios.setBackground(new java.awt.Color(248, 238, 231));
        tabela_horarios.setFont(new java.awt.Font("Helvetica", 0, 14));
        tabela_horarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"7:30 - 8:20", null, null, null, null, null},
                {"8:20 - 9:10", null, null, null, null, null},
                {"9:20 - 10:10", null, null, null, null, null},
                {"10:10 - 11:00", null, null, null, null, null},
                {"11:10 - 12:00", null, null, null, null, null}
            },
            new String [] {
                "HORARIOS", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        tabela_horarios.setCellSelectionEnabled(true);
        tabela_horarios.setGridColor(new java.awt.Color(244, 222, 203));
        tabela_horarios.setPreferredSize(new java.awt.Dimension(620, 100));
        tabela_horarios.setSelectionBackground(new java.awt.Color(148, 97, 142));
        tabela_horarios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabela_horarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabela_horarios.setShowGrid(true);


        return tabela_horarios;
    }
    /** 
    * cria uma lista de itens selecionáveis pelo usuário (útil para exibir lista de instâncias das classes da escola)
    * @param itens itens da lista
    * @return lista gerada, de acordo com o "design" e cores do programa
    */
    protected JList<String> criar_Lista(String[] itens){
        JList<String> lista_itens = new javax.swing.JList<>();
        lista_itens.setBackground(new java.awt.Color(244, 222, 203));
        lista_itens.setBorder(null);
        lista_itens.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        lista_itens.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = itens;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista_itens.setSelectionBackground(new java.awt.Color(148, 97, 142));
        lista_itens.setSelectionForeground(new java.awt.Color(255, 255, 255));

        return lista_itens;
    }
    /** 
    * cria um botão padrão, existem outras formas para esse mesmo método (sobrecarga) que geram outros tipos de botão não-padrão
    * @param texto texto do botão
    * @return botão gerado
    */
    protected JButton criarBotao(String texto){
        JButton botao = new JButton();
        botao.setForeground(new java.awt.Color(255, 255, 255));
        botao.setBackground(new java.awt.Color(73, 39, 74));
        botao.setFont(new java.awt.Font("Helvetica", 0, 12)); 
        botao.setText(texto);
        botao.setMaximumSize(new java.awt.Dimension(250, 30));
        botao.setMinimumSize(new java.awt.Dimension(95, 30));
        botao.setFocusPainted(false);
        return botao;
    }

    // TAMBÉM CRIA O BOTÃO, ***SÓ QUE COM UMA COR (por sobrecarga)**
    protected JButton criarBotao(String texto, int cor){
        JButton botao = criarBotao(texto);
        switch(cor){
            case VERMELHO -> {
                botao.setBackground(new java.awt.Color(205, 83, 96));
                break;
            }
            case BRANCO -> {
                botao.setBackground(new java.awt.Color(242, 235, 233));
                botao.setForeground(new java.awt.Color(0, 0, 0));
                break;
            }
            case AMARELO -> {
                botao.setBackground(new java.awt.Color(216, 179, 16));
                botao.setForeground(new java.awt.Color(0, 0, 0));
                break;
            }
            case VERDE ->{
                botao.setBackground(new java.awt.Color(109, 183, 133));
                break;
            }
            default->{
                botao.setBackground(new java.awt.Color(73, 39, 74));
                break;
            }
        }
        return botao;
    }
    ///////// <FIM>

    // TAMBÉM CRIA O BOTÃO, ***SÓ QUE COM UMA COR E UM TAMANHO MAIOR (para o menu inical)**
    protected JButton criarBotao(String texto, int cor,boolean botao_grande){
        JButton botao = criarBotao(texto,cor);
        if(botao_grande){
            botao.setMaximumSize(new java.awt.Dimension(250, 50));
            botao.setMinimumSize(new java.awt.Dimension(95, 50));
            botao.setFont(new java.awt.Font("Helvetica", 0, 20)); 
        }
        return botao;
    }
    ///////// <FIM>
    /** 
    * inicializa a janela, isto é, define as características básicas da janela como layout, cores, posição dos paneis, chamada dos métodos abstratos, etc
    * @param titulo_janela titulo externo da janela, do sistema operacional
    * @param titulo_interno título do label de texto que será gerado no cabeçalho da janela
    * @param largura largura da janela gerada
    * @param altura altura da janela gerada
    */
    /////////// FUNCÃO PARA INICIALIZAR O LAYOUT DA MINHA JANELA :)
    protected void inicializarJanela(String titulo_janela, String titulo_interno, int largura, int altura){
        label_titulo = new JLabel();
        panel_meio = new JPanel();
        panel_menu = new JPanel();
        panel_titulo = new JPanel();
        botao_de_voltar = new JButton();

        setTitle(titulo_janela);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(248, 238, 231));
        setPreferredSize(new Dimension(largura, altura));
        setMinimumSize(getPreferredSize());
        getContentPane().setLayout(new java.awt.BorderLayout(6, 6));

        /// CRIANDO O TITULO DAS PAGINAS E O BOTAO DE VOLTAR
        panel_titulo.setBackground(new java.awt.Color(148, 97, 142));
        panel_titulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_titulo.setLayout(new java.awt.BorderLayout());
        label_titulo.setFont(new java.awt.Font("Helvetica", 1, 20)); 
        label_titulo.setForeground(new java.awt.Color(255, 255, 255));
        label_titulo.setText(titulo_interno);
        label_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_titulo.add(label_titulo);
        panel_titulo.setPreferredSize(new java.awt.Dimension(188, 45));

        botao_de_voltar.setFocusPainted(false);
        botao_de_voltar.setBackground(new java.awt.Color(73, 39, 74));
        botao_de_voltar.setFont(new java.awt.Font("Helvetica", 0, 18)); 
        botao_de_voltar.setForeground(new java.awt.Color(255, 255, 255));
        botao_de_voltar.setText("<");
        botao_de_voltar.setBorderPainted(false);
        botao_de_voltar.setPreferredSize(new java.awt.Dimension(50, 30));
        botao_de_voltar.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        panel_titulo.add(botao_de_voltar, java.awt.BorderLayout.LINE_START);
        
        definirBotaoVoltar();

        JButton botao_para_alinhar = new JButton();
        botao_para_alinhar.setContentAreaFilled(false);
        botao_para_alinhar.setPreferredSize(botao_de_voltar.getPreferredSize());
        botao_para_alinhar.setOpaque(false);
        botao_para_alinhar.setBorderPainted(false);
        panel_titulo.add(botao_para_alinhar, java.awt.BorderLayout.LINE_END);

        getContentPane().add(panel_titulo, java.awt.BorderLayout.PAGE_START);

        /// <FIM>

        /// O MENU DO RODAPÉ DAS MINHAS JANELAS
        panel_menu.setBackground(new java.awt.Color(148, 97, 142));
        panel_menu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_menu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        definirBotoes();
        getContentPane().add(panel_menu, java.awt.BorderLayout.PAGE_END);
        /// <FIM>

        /// FINALMENTE, O CONTEUDO DA JANELA QUE CHAMA A FUNCAO QUE VAI SER SOBRE-ESCRITA PELAS JANELAS EM ESPECÍFICO
        panel_meio.setBackground(new java.awt.Color(248, 238, 231));
        panel_meio.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 800, 5));
        definirConteudo();
        getContentPane().add(panel_meio, java.awt.BorderLayout.CENTER);
        /// <FIM>
        pack();
        setLocationRelativeTo(null);
        setLocation(getX(),getY()-100);
    }
    /////////// <FIM>

}
