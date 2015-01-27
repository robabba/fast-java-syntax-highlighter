package JSyntaxHighlighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class JSyntaxHighlighterObject extends JTextPane {

	private StyledDocument styledDoc;
	private static final long serialVersionUID = -2995700771349481427L;
	
	private SyntaxStyle SYNTAX_STYLE = new SyntaxStyle();
	private static String SYNTAX_KEYWORDS_RULE;
	private static final String SYNTAX_STRING_RULE = "\\\"(\\.|[^\\\"])*\\\"";
	private static final String SYNTAX_NUMERIC_RULE = "\\b\\d+[\\.]?\\d*([eE]\\-?\\d+)?[lLdDfF]?\\b|\\b0x[a-fA-F\\d]+\\b";
		
	/**
	 * Create a new JHighlighter Object with a predefined language and theme.
	 * @param language
	 * @param theme
	 */
	public JSyntaxHighlighterObject(Language language, Theme theme){				
		this.setMargin(new Insets(2,20,0,0));
		this.setTheme(theme);
		
		styledDoc = this.getStyledDocument();

		this.getStyledDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
			
		switch (language){
		case Java:
			Syntax syntax = new Syntax();
			syntax.addKeywords(javaSyntax());
			syntax.buildSyntaxHighlighter();
			
			SYNTAX_KEYWORDS_RULE = syntax.getKeywordsRegexRule();
			break;
		case C:
			break;
		case CPP:
			break;
		case CSS:
			break;
		case CSharp:
			break;
		case HTML:
			break;
		case JavaScript:
			break;
		case ObjectiveC:
			break;
		case PHP:
			break;
		case SASS:
			break;
		case SCSS:
			break;
		case XML:
			break;
		case YAML:
			break;
		default:
			break;
		}

		// Add action listener
		this.addKeyListener(highlight());
		
		// Add Meslo font
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
	
	
	private void setTheme(Theme theme){
		switch(theme){
		case Monokai:
			this.setAsMonokai();
			break;
		case Tomorrow:
			break;
		case TomorrowNight:
			break;
		default:
			break;
		}
	}
	
	// SET MONOKAI THEME
	private void setAsMonokai(){
		this.setBackground(Color.decode("#272822"));
		this.setForeground(Color.decode("#F8F8F2"));
		this.setBackground(this.getBackground());
		this.setForeground(this.getForeground());
		
		// Syntax Colors
		SYNTAX_STYLE.KEYWORDS = Color.decode("#F92672");
		SYNTAX_STYLE.STRINGS = Color.decode("#E6DB74");
		SYNTAX_STYLE.NUMERICAL = Color.decode("#AE81FF");
	}
	
	private String[] javaSyntax(){
		return new String[]{ "abstract", "assert", "boolean", "break", "byte", "case", "catch" ,
				"char", "class", "continue", "default", "do", "double", "else", "enum", "extends",
				"final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", 
				"int", "long", "native", "new", "package", "private", "protected", "public", "return", 
				"short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", 
				"transient", "try", "void", "volatile", "while", "false", "null", "true"};
	}

	// Syntax highlighting methods
	private void updateSyntaxColor(int offset, int length, Color c){
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		
		styledDoc.setCharacterAttributes(offset, length, aset, true);
	}
	
	private void clearSyntaxColors(){
		updateSyntaxColor(0, this.getText().length(), this.getForeground());
	}
	
	// KEY LISTENER
	private KeyListener highlight(){
		return new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) { /* NOTHING DO DO */ }

			@Override
			public void keyPressed(KeyEvent e) { /* NOTHING TO DO */ }

			@Override
			public void keyReleased(KeyEvent e) {
				clearSyntaxColors();
				Pattern patternKey = Pattern.compile(SYNTAX_KEYWORDS_RULE);
				Matcher matchKey = patternKey.matcher(((JTextPane) e.getSource()).getText());
				while (matchKey.find()){
					updateSyntaxColor(matchKey.start(), matchKey.end() - matchKey.start(), SYNTAX_STYLE.KEYWORDS);
				}
				
				Pattern patternStr = Pattern.compile(SYNTAX_STRING_RULE);
				Matcher matchStr = patternStr.matcher(((JTextPane) e.getSource()).getText());
				while (matchStr.find()){
					updateSyntaxColor(matchStr.start(), matchStr.end() - matchStr.start(), SYNTAX_STYLE.STRINGS);
				}
				
				Pattern patternNum = Pattern.compile(SYNTAX_NUMERIC_RULE);
				Matcher matchNum = patternNum.matcher(((JTextPane) e.getSource()).getText());
				while (matchNum.find()){
					updateSyntaxColor(matchNum.start(), matchNum.end() - matchNum.start(), SYNTAX_STYLE.NUMERICAL);
				}
			}
		};
	}
	// END OF KEY LISTENER
	
	// End of Syntax Highlighting methods 
}