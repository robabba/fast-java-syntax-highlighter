import java.awt.EventQueue;

import JHighlighter.*;

import javax.swing.*;

public class JHighlighterTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					JHighlighter syntaxHighlighter = new JHighlighter(Language.Java, Theme.Monokai);
					
					JFrame form = new JFrame("test");
					form.add(syntaxHighlighter);
					form.setSize(500,500);
					form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					form.setVisible(true);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

}
