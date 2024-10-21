package scr.gui.frames;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JList;
import scr.escola.Escola;
import scr.escola.anos.Anos;
import scr.gui.JanelaAbstrata;
/** 
* Janela que exibe uma lista selecionável de anos (turmas) da escola
* @author Daniel Campos Galdez Monteiro
*/
public class ListaAnos extends JanelaAbstrata {
    /** 
    * escola que vão ser exibidas as suas turmas e passada para o diálogo
    */
    Escola escola;
    /** 
    * componente lista que exibirá os anos
    */
    JList<String> lista_anos;
    /** 
    * lista de anos que será utilizada para gerar o componente lista
    */
    ArrayList<Anos> lista_de_anos;
    /** 
    * por padrão recebe e armazena a escola cujo os anos serão exibidos, como também define a lista de nomes das turmas
    * @param escola escola a ser armazenada e passada para os diálogos
    */
    public ListaAnos(Escola escola){
        this.escola = escola;
        ArrayList<String> nomes_coletados = new ArrayList<>();
        lista_de_anos = new ArrayList<>();

        for(Anos percorrer_anos: escola.getAnos()){
            nomes_coletados.add(String.format("%dº ano", percorrer_anos.getNumeroAno()));
            lista_de_anos.add(percorrer_anos);
        }

        String[] nomes_anos = nomes_coletados.toArray(new String[nomes_coletados.size()]);
        lista_anos = criar_Lista(nomes_anos);

        inicializarJanela("Lista de turmas da escola","Visualizar turmas",380,550);
    }
    @Override
    protected void definirConteudo() {
        panel_meio.add(criarPanelLista("Lista de turmas do Ensino Fundamental", lista_anos,300,550));
    }
    @Override
    protected void definirBotoes() {
        JButton bInfo = criarBotao("DETALHES");

        bInfo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lista_anos.getSelectedIndex()>-1){
                    Anos ano_selecionado = lista_de_anos.get(lista_anos.getSelectedIndex());
                    JanelaAbstrata nova_janela = new InfoAno(escola,ano_selecionado);
                    passarFrame(nova_janela);
                }
            }
        });

        panel_menu.add(bInfo);

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
    
}
