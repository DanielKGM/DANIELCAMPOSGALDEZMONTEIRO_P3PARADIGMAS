package scr;
//SE NÃO RODAR, TENTE ABRIR DE NOVO OU ABRIR OUTRA PASTA
// A PASTA ABERTA DEVE SER "ABRA ESSA PASTA VS"
import javax.swing.JOptionPane;

import scr.escola.Escola;
import scr.gui.frames.MenuInicial;
/** 
* Classe inicial<br>
* Pede o nome e bimestre iniciais da escola, e chama o menu inicial "{@link scr.gui.frames.MenuInicial}".
* @author Daniel Campos Galdez Monteiro
*/
public class Main {
    public static void main(String[] args) {
        String nome_escola = JOptionPane.showInputDialog(null, "Nome da escola: ");
        int bimestre = 1;
        try{
            bimestre = Integer.parseInt(JOptionPane.showInputDialog(null, "Bimestre da escola: "));
        }
        catch(java.lang.NumberFormatException e){
            JOptionPane.showMessageDialog(null,"O bimestre deve ser um número!","Bimestre não registrado",JOptionPane.ERROR_MESSAGE);
        }
        Escola escola_teste = new Escola(nome_escola,bimestre);
        new MenuInicial(escola_teste).setVisible(true);
    }
}
