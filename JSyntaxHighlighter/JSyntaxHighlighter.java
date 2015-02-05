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

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;

public class JSyntaxHighlighter extends JScrollPane {

	private static final long serialVersionUID = 8111955883531380120L;
	private JSyntaxHighlighterObject sho = null;
	
	/**
	 * <b><u>JSyntaxHighlighter Default Constructor</u></b>
	 * <br/>Creates a new instance of the JSyntaxHighlighter View
	 */
	public JSyntaxHighlighter(){
		sho = new JSyntaxHighlighterObject();
		this.setViewportView(sho);
		
		applyFont();
	}
	
	public JSyntaxHighlighter(Language language){
		sho = new JSyntaxHighlighterObject(language, null);
		this.setViewportView(sho);
		
		applyFont();
	}
	
	public JSyntaxHighlighter(Language language, Themes theme){
		sho = new JSyntaxHighlighterObject(language, theme);
		this.setViewportView(sho);
		
		sho.changeSyntaxHighlightingTheme(theme);
		applyFont();
	}
	
	public JSyntaxHighlighter(File language, Themes theme){
		sho = new JSyntaxHighlighterObject(language, theme);
		this.setViewportView(sho);

		applyFont();
	}
	
	/**
	 * Change the current Theme of the Syntax
	 * @see Themes
	 * @param theme
	 */
	public void changeHighlightTheme(Themes theme){
		
		sho.changeSyntaxHighlightingTheme(theme);
		
	}
	
	public void changeLanguage(Language language){
		sho.changeLanguage(language);
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
	
	public void setText(String text){
		sho.setText(text);
		sho.highlightSyntax();
	}
}
