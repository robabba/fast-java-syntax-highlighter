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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import JSyntaxHighlighter.Languages.*;
import JSyntaxHighlighter.*;

import javax.swing.*;

public class JSyntaxHighlighterTest {

	public static void main(String[] args) {
		
			// Create a new instance of JSyntaxHighlighter
			JSyntaxHighlighter syntaxHighlighter = new JSyntaxHighlighter(new File("src/lua.lang"), Themes.Monokai);
			// Create a new JFrame
			JFrame form = new JFrame("JSyntaxHighlighter - Alpha Build");
			
			// Set the size of the JFrame for demo purposes
			form.setSize(800, 500);
			
			// Setting the size of the Syntax Highlighter
			syntaxHighlighter.setSize(form.getSize().width , form.getSize().height - 100);
		
			// No layout
			form.setLayout(null);
			
			// Create the JComboBox's that modify the look and language of the Syntax Highlighter
			JComboBox<String> comboBox = new JComboBox<String>();
			JComboBox<String> langBox = new JComboBox<String>();
			
			// Add Themes to the combo box
			comboBox.addItem("-- Change Theme --");
			comboBox.addItem("Dusk");
			comboBox.addItem("Monokai");
			comboBox.addItem("Tomorrow");
			comboBox.addItem("Tomorrow Night");
			comboBox.addItem("Obsidian");
			comboBox.addItem("Solarized Dark");
			comboBox.addItem("Vibrant Ink");
			comboBox.addItem("Xcode");
			
			// Add Languages to the combo box
			langBox.addItem("-- Change Language --");
			langBox.addItem("C");
			langBox.addItem("C++");
			langBox.addItem("C#");
			langBox.addItem("Java");
			langBox.addItem("Visual Basic");
	
			// Add Item Listeners
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
			
			langBox.addItemListener(new ItemListener(){
				
				@Override
				public void itemStateChanged(ItemEvent e){
					Object item = e.getItem();
					
					if (item.toString() == "C"){
						syntaxHighlighter.changeLanguage(Language.C);
					}else if (item.toString() == "C++"){
						syntaxHighlighter.changeLanguage(Language.CPP);
					}else if (item.toString() == "C#"){
						syntaxHighlighter.changeLanguage(Language.CSharp);
					}else if (item.toString() == "Java"){
						syntaxHighlighter.changeLanguage(Language.Java);
					}else if (item.toString() == "Visual Basic"){
						syntaxHighlighter.changeLanguage(Language.VisualBasic);
					}
				}
				
			});
			
			comboBox.setSize(300, 20);
			comboBox.setLocation(330, 410);
			
			langBox.setSize(300, 20);
			langBox.setLocation(10, 410);
			
			form.add(syntaxHighlighter);
			form.add(langBox);
			form.add(comboBox);
			//form.add(sPane);
			form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			form.setVisible(true);
			
			
	}

}
