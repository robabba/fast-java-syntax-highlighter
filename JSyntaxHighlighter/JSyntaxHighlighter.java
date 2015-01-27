package JSyntaxHighlighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;

import JSyntaxHighlighter.LineNumber.*;

public class JSyntaxHighlighter extends JScrollPane {

	private static final long serialVersionUID = 8111955883531380120L;
	private JSyntaxHighlighterObject sho = null;
	private LineNumbers line = null;
	
	
	public JSyntaxHighlighter(Language language){
		sho = new JSyntaxHighlighterObject(language, null);
		this.setViewportView(sho);
		
		applyFont();
	}
	
	public JSyntaxHighlighter(Language language, Theme theme){
		sho = new JSyntaxHighlighterObject(language, theme);
		this.setViewportView(sho);
		
		switch (theme){
		case Monokai:
			this.setBackground(Color.decode("#272822"));
			this.setForeground(Color.decode("#F8F8F2"));
			break;
		default:
			break;
		}

		applyFont();
	}
	
	public JSyntaxHighlighter(Language language, Theme theme, boolean showLineNumbers){
		sho = new JSyntaxHighlighterObject(language, theme);
		this.setViewportView(sho);
		
		switch (theme){
		case Monokai:
			this.setBackground(Color.decode("#272822"));
			this.setForeground(Color.decode("#F8F8F2"));
			
			if (showLineNumbers){
				line = new LineNumbers(sho, Color.decode("#F8F8F2"), Color.decode("#272822"));
				setRowHeaderView(line);
			}
			
			break;
		default:
			break;
		}
		
		applyFont();
	}
	
	private void applyFont(){
		Font Meslo = null;
		
		try{
		    Meslo = Font.createFont(Font.TRUETYPE_FONT, new File("src/JHighlighter/Fonts/Menlo-Regular.ttf")).deriveFont(Font.BOLD, 12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/JHighlighter/Fonts/Menlo-Regular.ttf")));
		}catch(IOException e){
			e.printStackTrace();
		}catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		this.setFont(Meslo);
	}
}
