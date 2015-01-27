import JSyntaxHighlighter.*;

import javax.swing.*;

public class JHighlighterTest {

	public static void main(String[] args) {

			JSyntaxHighlighter syntaxHighlighter = new JSyntaxHighlighter(Language.Java, Theme.Monokai, true);
			
			JFrame form = new JFrame("test");
			
			form.add(syntaxHighlighter);
			form.setSize(500, 500);
			form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			form.setVisible(true);

	}

}
