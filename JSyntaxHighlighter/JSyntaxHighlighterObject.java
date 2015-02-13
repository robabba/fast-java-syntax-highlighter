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

	Keep moving forward ~ Monty Oum (1981 - 2015)
*/

package JSyntaxHighlighter;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

import JSyntaxHighlighter.Languages.LanguageBuilder;

public class JSyntaxHighlighterObject extends JTextPane{

	private StyledDocument styledDoc;
	private static final long serialVersionUID = -2995700771349481427L;
	
	private SyntaxStyle syntaxStyle = new SyntaxStyle();
	private static SyntaxRules syntaxRules = new SyntaxRules();
	
	private ThemeModifier themeMod;

	/**
	 * <u><b>Default Constructor</b></u>
	 * Creates a new instance of the Controller Object for
	 * JSyntaxHighlighter.
	 */
	public JSyntaxHighlighterObject(){				
		this.setTabSpacing();
		
		styledDoc = this.getStyledDocument();

		this.getStyledDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
		
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
		
		this.setForeground(syntaxStyle.DEFAULT);
		
		AbstractDocument doc = (AbstractDocument)this.getDocument();
		doc.setDocumentFilter(new AutoTab());
		
		//this.addKeyListener(highlight());
	}
	
	/**
	 * Create a new JHighlighter Object with a predefined language and theme.
	 * @param language
	 * @param theme
	 */
	public JSyntaxHighlighterObject(Language language, Themes theme){				
		this.setMargin(new Insets(10,5,0,0));
		this.setTabSpacing();
		
		styledDoc = this.getStyledDocument();

		this.getStyledDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
		
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
		
		AbstractDocument doc = (AbstractDocument)this.getDocument();
		doc.setDocumentFilter(new AutoTab());
		
		this.setFont(Meslo);
		this.changeLanguage(language);
		this.changeSyntaxHighlightingTheme(theme);
	
		this.addKeyListener(highlight());
	}
	
	public JSyntaxHighlighterObject(File language, File theme){				
		this.setMargin(new Insets(10,5,0,0));
		this.setTabSpacing();
		
		styledDoc = this.getStyledDocument();

		this.getStyledDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
		
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
		
		AbstractDocument doc = (AbstractDocument)this.getDocument();
		doc.setDocumentFilter(new AutoTab());
		
		LanguageBuilder lb = new LanguageBuilder(language);
		syntaxRules = new SyntaxRules();
		syntaxRules = lb.getRules();
		
		this.changeSyntaxHighlightingTheme(theme);
		this.addKeyListener(highlight());
		
	}
	
	public JSyntaxHighlighterObject(Language language, File theme){		
		this.setMargin(new Insets(10,5,0,0));
		this.setTabSpacing();
		
		styledDoc = this.getStyledDocument();

		this.getStyledDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
		
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
		
		AbstractDocument doc = (AbstractDocument)this.getDocument();
		doc.setDocumentFilter(new AutoTab());
		
		this.setFont(Meslo);
		
		this.changeLanguage(language);
		this.changeSyntaxHighlightingTheme(theme);
		this.addKeyListener(highlight());
		
		
	}
	
	public JSyntaxHighlighterObject(File language, Themes theme){				
		this.setMargin(new Insets(10,5,0,0));
		this.setTabSpacing();
		
		styledDoc = this.getStyledDocument();

		this.getStyledDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
		
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
		
		AbstractDocument doc = (AbstractDocument)this.getDocument();
		doc.setDocumentFilter(new AutoTab());
		
		this.setFont(Meslo);
		
		LanguageBuilder lb = new LanguageBuilder(language);
		syntaxRules = new SyntaxRules();
		syntaxRules = lb.getRules();
		
		this.changeSyntaxHighlightingTheme(theme);
		this.addKeyListener(highlight());
	}
	
	public void changeSyntaxHighlightingTheme(Themes theme){
		ThemeModifier tm = new ThemeModifier(theme);
		applyTheme(tm);
	}
	
	public void changeSyntaxHighlightingTheme(File theme){
		ThemeModifier tm = new ThemeModifier(theme);
		applyTheme(tm);
	}
	
	private void applyTheme(ThemeModifier themeMod){
		syntaxStyle = themeMod.getNewStyle();
		
		@SuppressWarnings("unused")
		LinePainter ln = new LinePainter(this, themeMod.getCurrentLineColor());
		
		try{
			highlightSyntax();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		this.addKeyListener(highlight());
		
		this.setBackground(themeMod.getBackground());
		this.setForeground(themeMod.getForeground());
		this.setCaretColor(themeMod.getCaretColor());
		this.setSelectionColor(themeMod.getSelectionBackColor());
		 
		this.highlightSyntax();
	}
	
	public void changeLanguage(Language language){

		switch (language){
		case Java:
			syntaxRules.setKeywords(Keywords.getJavaKeywords());
			syntaxRules.overrideSingleCommentsRule(true, "//");
			syntaxRules.overrideMultiCommentsRule(true, new String[]{"/*", "*/"});
			break;
		case C:
			syntaxRules.setKeywords(Keywords.getCKeywords());
			syntaxRules.overrideSingleCommentsRule(true, "//");
			syntaxRules.overrideMultiCommentsRule(true, new String[]{"/*", "*/"});
			break;
		case CPP:
			syntaxRules.setKeywords(Keywords.getCPPKeywords());
			syntaxRules.overrideSingleCommentsRule(true, "//");
			syntaxRules.overrideMultiCommentsRule(true, new String[]{"/*", "*/"});
			break;
		case CSharp:
			syntaxRules.setKeywords(Keywords.getCSKeywords());
			syntaxRules.overrideSingleCommentsRule(true, "//");
			syntaxRules.overrideMultiCommentsRule(true, new String[]{"/*", "*/"});
			break;
		case VisualBasic:
			syntaxRules.setKeywords(Keywords.getVBKeywords());
			syntaxRules.overrideSingleCommentsRule(true,  "'"); 
			syntaxRules.overrideMultiCommentsRule(false, null);
			break;
		default:
			break;
		}
		
		this.highlightSyntax();
		this.addKeyListener(highlight());
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
			break;
		default:
			asetF = sc.addAttribute(aset, StyleConstants.Bold, true);
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
			public void keyTyped(KeyEvent e) { 
				if (e.getKeyChar() == ' ' || e.getKeyChar() == 10 || e.getKeyChar() == '(' || e.getKeyChar() == ';' || e.getKeyChar() == '.' || e.getKeyChar() == '{' || e.getKeyChar() == '"')
					highlightSyntax();
			}

			@Override
			public void keyPressed(KeyEvent e) { }

			@Override
			public void keyReleased(KeyEvent e) { }
		};
	}
	// END OF KEY LISTENER
	
	public void highlightSyntax(){
		
		clearSyntaxColors();
		
		// Functions
		Pattern patternFun = Pattern.compile(syntaxRules.getFunctionRule());
		Matcher matchFun = patternFun.matcher(this.getText());
		while (matchFun.find()){
			updateSyntaxColor(matchFun.start(), matchFun.end() - matchFun.start(), syntaxStyle.FUNCTION, 3);
		}
		
		// Class
		Pattern patternClass = Pattern.compile(syntaxRules.getClassRule());
		Matcher matchClass = patternClass.matcher(this.getText());
		while (matchClass.find()){
			updateSyntaxColor(matchClass.start(), matchClass.end() - matchClass.start(), syntaxStyle.CLASS, 3);
		}
		
		// Keywords
		Pattern patternKey = Pattern.compile(syntaxRules.getKeywordsRule());
		Matcher matchKey = patternKey.matcher(this.getText());
		while (matchKey.find()){
			updateSyntaxColor(matchKey.start(), matchKey.end() - matchKey.start(), syntaxStyle.KEYWORDS, Font.BOLD);
		}

		// Operators
		Pattern patternOp = Pattern.compile(syntaxRules.getOperatorRule());
		Matcher matchOp = patternOp.matcher(this.getText());
		while (matchOp.find()){
			updateSyntaxColor(matchOp.start(), matchOp.end() - matchOp.start(), syntaxStyle.KEYWORDS, Font.BOLD);
		}
			
		// Numbers
		Pattern patternNum = Pattern.compile(syntaxRules.getNumericalRule());
		Matcher matchNum = patternNum.matcher(this.getText());
		while (matchNum.find()){
			updateSyntaxColor(matchNum.start(), matchNum.end() - matchNum.start(), syntaxStyle.NUMERICAL, Font.BOLD);
		}
		
		// Generic String
		Pattern patternStr = Pattern.compile(syntaxRules.getStringRule());
		Matcher matchStr = patternStr.matcher(this.getText());
		while (matchStr.find()){
			updateSyntaxColor(matchStr.start(), matchStr.end() - matchStr.start(), syntaxStyle.STRINGS,Font.BOLD);
		}
		
		
		// String with escape character
		Pattern patternStrE = Pattern.compile(syntaxRules.getStringWithEscapeRule());
		Matcher matchStrE = patternStrE.matcher(this.getText());
		while (matchStrE.find()){
			updateSyntaxColor(matchStrE.start(), matchStrE.end() - matchStrE.start(), syntaxStyle.STRINGS, Font.BOLD);
		}
		
		// Comments (Single)
		Pattern patternC = Pattern.compile(syntaxRules.getSingleCommentRule());
		Matcher matchC = patternC.matcher(this.getText());
		while (matchC.find()){
			updateSyntaxColor(matchC.start(), matchC.end() - matchC.start(), syntaxStyle.COMMENTS, Font.ITALIC);
		}
				
		
		// Comments (Multilined)
		Pattern patternCML = Pattern.compile(syntaxRules.getMultiCommentRule());
		Matcher matchCML = patternCML.matcher(this.getText());
		while (matchCML.find()){
			updateSyntaxColor(matchCML.start(), matchCML.end() - matchCML.start(), syntaxStyle.COMMENTS, Font.ITALIC);
		}

	}
	
	// End of Syntax Highlighting methods 
	
	public ThemeModifier getThemeModifer() {
		return themeMod;
	}

	public void setThemeModifer(ThemeModifier themeMod) {
		this.themeMod = themeMod;
	}
}