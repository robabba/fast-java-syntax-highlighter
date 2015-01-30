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

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import JSyntaxHighlighter.*;


import javax.swing.*;

public class JSyntaxHighlighterTest {

	public static void main(String[] args) {
		
			JSyntaxHighlighter syntaxHighlighter = new JSyntaxHighlighter(Language.CSharp, Themes.VibrantInk);
			syntaxHighlighter.setText("public class HelloWorld{\n\n\tpublic static void main (String[] args){\n\t\tSystem.out.println(\"Hello, World\");\n\t}\n\n}");
			
			JFrame form = new JFrame("JSyntaxHighlighter");
			form.setLayout(new BorderLayout());
			JComboBox<String> comboBox = new JComboBox<String>();
			
			comboBox.addItem("-- Change Theme --");
			comboBox.addItem("Dusk");
			comboBox.addItem("Monokai");
			comboBox.addItem("Tomorrow");
			comboBox.addItem("Tomorrow Night");
			comboBox.addItem("Obsidian");
			comboBox.addItem("Solarized Dark");
			comboBox.addItem("Vibrant Ink");
			comboBox.addItem("Xcode");
			
			comboBox.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){
						Object item = e.getItem();
						if (item.toString() == "Monokai"){
							syntaxHighlighter.changeHighlightTheme(Themes.Monokai);
						}else if (item.toString() == "Tomorrow"){
							syntaxHighlighter.changeHighlightTheme(Themes.Tomorrow);
						}else if (item.toString() == "Tomorrow Night"){
							syntaxHighlighter.changeHighlightTheme(Themes.TomorrowNight);
						}else if (item.toString() == "Obsidian"){
							syntaxHighlighter.changeHighlightTheme(Themes.Obsidian);
						}else if (item.toString() == "Solarized Dark"){
							syntaxHighlighter.changeHighlightTheme(Themes.SolarizedDark);
						}else if (item.toString() == "Dusk"){
							syntaxHighlighter.changeHighlightTheme(Themes.Dusk);
						}else if (item.toString() == "Xcode"){
							syntaxHighlighter.changeHighlightTheme(Themes.Xcode);
						}else if (item.toString() == "Vibrant Ink"){
							syntaxHighlighter.changeHighlightTheme(Themes.VibrantInk);
						}
					}
				}
				
			});
			
			
			form.add(syntaxHighlighter, BorderLayout.CENTER);
			form.add(comboBox, BorderLayout.EAST );
			//form.add(sPane);
			form.setSize(800, 500);
			form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			form.setVisible(true);
	}

}
