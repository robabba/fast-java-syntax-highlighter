/* 	Copyright (c) 2015 Robert Abba

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
*/

package JSyntaxHighlighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
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
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

public class JSyntaxHighlighterObject extends JTextPane{

	private StyledDocument styledDoc;
	private static final long serialVersionUID = -2995700771349481427L;
	
	private SyntaxStyle SYNTAX_STYLE = new SyntaxStyle();
	private static String SYNTAX_KEYWORDS_RULE;
	
	private static final String SYNTAX_STRING_RULE = "\\\"(\\.|[^\\\"])*\\\"";
	private static final String SYNTAX_STRING_WITH_ESCAPE_RULE = "(\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\")";
	private static final String SYNTAX_NUMERIC_RULE = "\\b\\d+[\\.]?\\d*([eE]\\-?\\d+)?[lLdDfF]?\\b|\\b0x[a-fA-F\\d]+\\b";
	private static final String SYNTAX_CLASS_RULE = "\\b[A-Z][a-z]*([A-Z][a-z]*)*\\b";
	private static final String SYNTAX_MULTILINE_COMMENT_RULE = "/\\*(.|[\\r\\n])*?\\*/";
	private static final String SYNTAX_SINGLELINE_COMMENT_RULE = "//.*";
	private static final String SYNTAX_OPERATOR_RULE = "(<=|>=|!=|=|>|<)";
	private static final String SYNTAX_FUNCTION_RULE = "(\\w+\\()+([^\\)]*\\)+)*";
	
	private ThemeModifier themeMod;

	/**
	 * Create a new JHighlighter Object with a predefined language and theme.
	 * @param language
	 * @param theme
	 */
	public JSyntaxHighlighterObject(Language language, Themes theme){				
		this.setMargin(new Insets(10,15,0,0));
		this.setTabSpacing();
		
		styledDoc = this.getStyledDocument();

		this.getStyledDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
			
		switch (language){
		case Java:
			Syntax syntaxJava = new Syntax();
			syntaxJava.addKeywords(JavaSyntax());
			syntaxJava.buildSyntaxHighlighter();
			
			SYNTAX_KEYWORDS_RULE = syntaxJava.getKeywordsRegexRule();
			break;
		case C:
			Syntax syntaxC = new Syntax();
			syntaxC.addKeywords(CSyntax());
			syntaxC.buildSyntaxHighlighter();
			SYNTAX_KEYWORDS_RULE = syntaxC.getKeywordsRegexRule();
			break;
		case CPP:
			Syntax syntaxCPP = new Syntax();
			syntaxCPP.addKeywords(CPPSyntax());
			syntaxCPP.buildSyntaxHighlighter();
			SYNTAX_KEYWORDS_RULE = syntaxCPP.getKeywordsRegexRule();
			break;
		case CSharp:
			Syntax syntaxCS = new Syntax();
			syntaxCS.addKeywords(CSSyntax());
			syntaxCS.buildSyntaxHighlighter();
			SYNTAX_KEYWORDS_RULE = syntaxCS.getKeywordsRegexRule();
			break;
		default:
			break;
		}

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
		this.changeSyntaxHighlightingTheme(theme);
		this.addKeyListener(highlight());
	}
	
	public void changeSyntaxHighlightingTheme(Themes theme){
		themeMod = new ThemeModifier(theme);
		SYNTAX_STYLE = themeMod.getNewStyle();
		this.setBackground(themeMod.getBackground());
		this.setForeground(themeMod.getForeground());
		this.setCaretColor(themeMod.getCaretColor());
		this.setSelectionColor(themeMod.getSelectionBackColor());
		
		LinePainter ln = new LinePainter(this, themeMod.getCurrentLineColor());
		
		try{
			highlightSyntax();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void setTabSpacing(){
		FontMetrics fm = this.getFontMetrics( this.getFont() );
        int charWidth = fm.charWidth( 'w' );
        int tabWidth = charWidth * 4;

        TabStop[] tabs = new TabStop[10];

        for (int j = 0; j < tabs.length; j++)
        {
             int tab = j + 1;
             tabs[j] = new TabStop( tab * tabWidth );
        }

        TabSet tabSet = new TabSet(tabs);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setTabSet(attributes, tabSet);
        int length = this.getDocument().getLength();
        this.getStyledDocument().setParagraphAttributes(0, length, attributes, true);
	}
	
	// Make keyword Syntax
	private String[] CSyntax(){
		return new String[] {"auto", "break", "case","const", "continue", "default", "do", 
							"double", "else","enum", "extern","float", "goto","if", "int", 
							"long", "regster", "return", "short", "signed", "sizeof", "static",
							"struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};
	}
	
	private String[] CPPSyntax(){
		return new String[] {"alginas", "alignof", "and", "and_eq", "asm", "auto", "bitand", "bitor", "bool",
							"break", "case", "catch", "char", "char16_t", "char32_t", "class", "compl", "concept",
							"const", "constexpr", "const_cast", "continue", "decltype", "default", "delete", "do","double",
							"dynamic_cast", "else", "enum", "explicit", "export", "extern", "false", "float", "for", "friend",
							"goto", "if", "inline", "int", "long", "mutable", "namespace", "new", "noexcept", "not", "not_eq", 
							"nullptr", "operator", "or", "or_eq", "private", "protected", "public", "register", "reinterpret_cast",
							"requires", "return", "short", "signed", "sizeof", "static", "static_assert", "static_cast", "struct",
							"switch", "template", "this", "thread_local", "throw", "true", "try", "typedef", "typeid", "typename", "union",
							"unsigned", "usign","virtual", "void", "volatile", "wchar_t", "while", "xor", "xor_eq"};
	}
	
	public String[] CSSyntax(){
		return new String[] {"abstract", "as", "base", "bool", "break", "byte", "case", "catch", "char", "checked", "class" ,"const",
							"continue", "decimal", "default", "delegate", "do", "double", "else", "enum", "event", "explicit", "extern",
							"false", "finally", "fixed", "float", "for", "foreach", "goto", "if", "implicit", "in", "int", "interface",
							"internal", "is", "lock", "long", "namespace", "new", "null", "object", "operator", "out", "override", "params",
							"private", "protected", "public", "readonly", "ref", "return", "sbyte", "sealed", "short", "sizeof", "stackalloc",
							"static", "string", "struct", "switch", "this", "throw", "true", "try", "typeof", "uint", "ulong", "unchecked",
							"unsafe", "ushort", "using", "virtual", "void", "volatile", "while" };
	}
	
	private String[] JavaSyntax(){
		return new String[]{ "abstract", "assert", "boolean", "break", "byte", "case", "catch" ,
				"char", "class", "continue", "default", "do", "double", "else", "enum", "extends",
				"final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", 
				"int", "long", "native", "new", "package", "private", "protected", "public", "return", 
				"short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", 
				"transient", "try", "void", "volatile", "while", "false", "null", "true"};
	}

	// Syntax highlighting methods
	
	private void updateSyntaxColor(int offset, int length, Color c, int style){
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		AttributeSet asetF = null;
		AttributeSet asetF2 = null;
		
		switch(style){
		case Font.BOLD:
			asetF = sc.addAttribute(aset, StyleConstants.Bold, true);
			break;
		case Font.ITALIC:
			asetF = sc.addAttribute(aset, StyleConstants.Italic, true);
			break;
		case 3:
			asetF = sc.addAttribute(aset, StyleConstants.Bold, true);
			asetF2 = sc.addAttribute(asetF, StyleConstants.Italic, true);
		}
			
		
		if (asetF2 != null)
			styledDoc.setCharacterAttributes(offset, length, asetF2, true);
		else if (asetF != null)
			styledDoc.setCharacterAttributes(offset, length, asetF, true);
		else
			styledDoc.setCharacterAttributes(offset, length, aset, true);
	}
	
	private void clearSyntaxColors(){
		updateSyntaxColor(0, this.getText().length(), this.getForeground(), Font.BOLD);
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
				
				// Functions
				Pattern patternFun = Pattern.compile(SYNTAX_FUNCTION_RULE);
				Matcher matchFun = patternFun.matcher(((JTextPane) e.getSource()).getText());
				while (matchFun.find()){
					updateSyntaxColor(matchFun.start(), matchFun.end() - matchFun.start(), SYNTAX_STYLE.FUNCTION, 3);
				}
							
				// Class
				Pattern patternClass = Pattern.compile(SYNTAX_CLASS_RULE);
				Matcher matchClass = patternClass.matcher(((JTextPane) e.getSource()).getText());
				while (matchClass.find()){
					updateSyntaxColor(matchClass.start(), matchClass.end() - matchClass.start(), SYNTAX_STYLE.CLASS, 3);
				}
								
				// Keywords
				Pattern patternKey = Pattern.compile(SYNTAX_KEYWORDS_RULE);
				Matcher matchKey = patternKey.matcher(((JTextPane) e.getSource()).getText());
				while (matchKey.find()){
					updateSyntaxColor(matchKey.start(), matchKey.end() - matchKey.start(), SYNTAX_STYLE.KEYWORDS, Font.BOLD);
				}
				
				// Operators
				Pattern patternOp = Pattern.compile(SYNTAX_OPERATOR_RULE);
				Matcher matchOp = patternOp.matcher(((JTextPane) e.getSource()).getText());
				while (matchOp.find()){
					updateSyntaxColor(matchOp.start(), matchOp.end() - matchOp.start(), SYNTAX_STYLE.KEYWORDS, Font.BOLD);
				}
				
				// Generic String
				Pattern patternStr = Pattern.compile(SYNTAX_STRING_RULE);
				Matcher matchStr = patternStr.matcher(((JTextPane) e.getSource()).getText());
				while (matchStr.find()){
					updateSyntaxColor(matchStr.start(), matchStr.end() - matchStr.start(), SYNTAX_STYLE.STRINGS,Font.BOLD);
				}
				
				
				// String with escape character
				Pattern patternStrE = Pattern.compile(SYNTAX_STRING_WITH_ESCAPE_RULE);
				Matcher matchStrE = patternStrE.matcher(((JTextPane) e.getSource()).getText());
				while (matchStrE.find()){
					updateSyntaxColor(matchStrE.start(), matchStrE.end() - matchStrE.start(), SYNTAX_STYLE.STRINGS, Font.BOLD);
				}
				
				// Numbers
				Pattern patternNum = Pattern.compile(SYNTAX_NUMERIC_RULE);
				Matcher matchNum = patternNum.matcher(((JTextPane) e.getSource()).getText());
				while (matchNum.find()){
					updateSyntaxColor(matchNum.start(), matchNum.end() - matchNum.start(), SYNTAX_STYLE.NUMERICAL, Font.BOLD);
				}
				
				// Comments (Multilined)
				Pattern patternCML = Pattern.compile(SYNTAX_MULTILINE_COMMENT_RULE);
				Matcher matchCML = patternCML.matcher(((JTextPane) e.getSource()).getText());
				while (matchCML.find()){
					updateSyntaxColor(matchCML.start(), matchCML.end() - matchCML.start(), SYNTAX_STYLE.COMMENTS, Font.ITALIC);
				}
				
				// Comments (Single)
				Pattern patternC = Pattern.compile(SYNTAX_SINGLELINE_COMMENT_RULE);
				Matcher matchC = patternC.matcher(((JTextPane) e.getSource()).getText());
				while (matchC.find()){
					updateSyntaxColor(matchC.start(), matchC.end() - matchC.start(), SYNTAX_STYLE.COMMENTS, Font.ITALIC);
				}

			}
		};
	}
	// END OF KEY LISTENER
	
	public void highlightSyntax(){
		
		clearSyntaxColors();
		
		// Functions
		Pattern patternFun = Pattern.compile(SYNTAX_FUNCTION_RULE);
		Matcher matchFun = patternFun.matcher(this.getText());
		while (matchFun.find()){
			updateSyntaxColor(matchFun.start(), matchFun.end() - matchFun.start(), SYNTAX_STYLE.KEYWORDS, 3);
		}
		
		// Class
		Pattern patternClass = Pattern.compile(SYNTAX_CLASS_RULE);
		Matcher matchClass = patternClass.matcher(this.getText());
		while (matchClass.find()){
			updateSyntaxColor(matchClass.start(), matchClass.end() - matchClass.start(), SYNTAX_STYLE.CLASS, 3);
		}
		
		// Keywords
		Pattern patternKey = Pattern.compile(SYNTAX_KEYWORDS_RULE);
		Matcher matchKey = patternKey.matcher(this.getText());
		while (matchKey.find()){
			updateSyntaxColor(matchKey.start(), matchKey.end() - matchKey.start(), SYNTAX_STYLE.KEYWORDS, Font.BOLD);
		}
		
		// Operators
		Pattern patternOp = Pattern.compile(SYNTAX_KEYWORDS_RULE);
		Matcher matchOp = patternOp.matcher(this.getText());
		while (matchOp.find()){
			updateSyntaxColor(matchOp.start(), matchOp.end() - matchOp.start(), SYNTAX_STYLE.KEYWORDS, Font.BOLD);
		}
			
		// Numbers
		Pattern patternNum = Pattern.compile(SYNTAX_NUMERIC_RULE);
		Matcher matchNum = patternNum.matcher(this.getText());
		while (matchNum.find()){
			updateSyntaxColor(matchNum.start(), matchNum.end() - matchNum.start(), SYNTAX_STYLE.NUMERICAL, Font.BOLD);
		}
		
		// Generic String
		Pattern patternStr = Pattern.compile(SYNTAX_STRING_RULE);
		Matcher matchStr = patternStr.matcher(this.getText());
		while (matchStr.find()){
			updateSyntaxColor(matchStr.start(), matchStr.end() - matchStr.start(), SYNTAX_STYLE.STRINGS,Font.BOLD);
		}
		
		
		// String with escape character
		Pattern patternStrE = Pattern.compile(SYNTAX_STRING_WITH_ESCAPE_RULE);
		Matcher matchStrE = patternStrE.matcher(this.getText());
		while (matchStrE.find()){
			updateSyntaxColor(matchStrE.start(), matchStrE.end() - matchStrE.start(), SYNTAX_STYLE.STRINGS, Font.BOLD);
		}
		
		// Comments (Multilined)
		Pattern patternCML = Pattern.compile(SYNTAX_MULTILINE_COMMENT_RULE);
		Matcher matchCML = patternCML.matcher(this.getText());
		while (matchCML.find()){
			updateSyntaxColor(matchCML.start(), matchCML.end() - matchCML.start(), SYNTAX_STYLE.COMMENTS, Font.ITALIC);
		}
		
		// Comments (Single)
		Pattern patternC = Pattern.compile(SYNTAX_SINGLELINE_COMMENT_RULE);
		Matcher matchC = patternC.matcher(this.getText());
		while (matchC.find()){
			updateSyntaxColor(matchC.start(), matchC.end() - matchC.start(), SYNTAX_STYLE.COMMENTS, Font.ITALIC);
		}
				
		
	}
	
	// End of Syntax Highlighting methods 
}