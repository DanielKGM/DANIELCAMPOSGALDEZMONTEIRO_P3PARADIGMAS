package scr.gui;
import javax.swing.*;

/** 
* "Esqueleto abstrato" dos diálogos (pop ups geralmente com formulários) da interface gráfica da escola. Os diálogos geralmente vão estar ligados a alguma janela principal.
* <br> essa classe é basicamente igual à "JanelaAbstrata" apenas com a diferença que não tem título interno, não precisa de alguns componentes e precisa ser modal.
* @author Daniel Campos Galdez Monteiro
*/
public abstract class DialogoAbstrato extends JDialog{
    ////////// DECLARAÇÃO DE VARIAVEIS                  
    protected JPanel panel_meio;
    public JPanel panel_menu;
    ///////// <FIM>

    /////// CORES QUE ESCOLHI PROS BOTOES
    protected static final int VERMELHO = 0;
    protected static final int BRANCO = 1;
    protected static final int AMARELO = 2;
    protected static final int VERDE = 3;
    // QUALQUER OUTRO NUMERO = ROXO
    /////// <FIM>

    ////////// CONSTRUTOR
    protected DialogoAbstrato(){
    }
    ///////// <FIM>
    public void mostrar_aviso(String titulo, String texto){
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.PLAIN_MESSAGE);
    }
    /////////// MÉTODOS ABSTRATOS PARA DEFINIR O CONTEÚDO DENTRO DA MINHA JANELA, DEVE SER IMPLEMENTADA EM CADA
    protected abstract void definirConteudo();
    protected abstract void definirBotoes();
    ///////////  <FIM>

    /////////// METODOS PARA CRIAR OS MEUS PANEIS - DE INFORMAÇÕES, DE TABELA DE HORARIOS OU DE LISTA
    protected class PanelInformacoes{
        public JPanel panel;
        int grid_x = 0;
        int grid_y=0;
        java.awt.GridBagConstraints gb_constraint;

        public PanelInformacoes(){
            gb_constraint = new java.awt.GridBagConstraints();
            panel = new JPanel();
            panel.setBackground(new java.awt.Color(248, 238, 231));
            panel.setLayout(new java.awt.GridBagLayout());
        }

        public void adicionar(JComponent componente){
            gb_constraint = new java.awt.GridBagConstraints();
            gb_constraint.gridx = grid_x;
            gb_constraint.gridy = grid_y;
            grid_y++;
            gb_constraint.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gb_constraint.insets = new java.awt.Insets(0, 0, 6, 10);
            panel.add(componente, gb_constraint);
        }

        public void novaColuna(){
            grid_y=0;
            grid_x++;
        }
    }

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

    protected JComboBox<String> criarCaixa_Seleção(String[] opcoes){
        JComboBox<String> caixa_selecao = new JComboBox<>();
        caixa_selecao.setBackground(new java.awt.Color(244, 222, 203));
        caixa_selecao.setFont(new java.awt.Font("Helvetica", 0, 14));
        caixa_selecao.setModel(new javax.swing.DefaultComboBoxModel<>(opcoes));
        caixa_selecao.setMinimumSize(new java.awt.Dimension(72, 28));
        caixa_selecao.setPreferredSize(new java.awt.Dimension(200, 28));
        return caixa_selecao;
    }
    // CRIA UMA CAIXA TAMBÉM, MAS NO INDEX ESCOLHIDO!!! 
    protected JComboBox<String> criarCaixa_Seleção(String[] opcoes, int item){
        JComboBox<String> caixa_selecao = criarCaixa_Seleção(opcoes);
        caixa_selecao.setSelectedIndex(item);
        return caixa_selecao;
    }

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

    /////////// FUNCÃO PARA INICIALIZAR O LAYOUT DA MINHA JANELA :)
    protected void inicializarJanela(String titulo_janela, int largura, int altura){
        setModal(true);
        panel_meio = new JPanel();
        panel_menu = new JPanel();

        setTitle(titulo_janela);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(248, 238, 231));
        setPreferredSize(new java.awt.Dimension(largura, altura));
        setMinimumSize(new java.awt.Dimension(480, 340));
        setMaximumSize(new java.awt.Dimension(largura, altura));
        getContentPane().setLayout(new java.awt.BorderLayout(6, 6));
            
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
    }
    /////////// <FIM>

}
