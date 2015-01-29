import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import JSyntaxHighlighter.*;
import JSyntaxHighlighter.LineNumber.LineNumbers;

import javax.swing.*;

public class JHighlighterTest {

	public static void main(String[] args) {

			JSyntaxHighlighter syntaxHighlighter = new JSyntaxHighlighter(Language.Java, Themes.TomorrowNight, false);
			
			JFrame form = new JFrame("JSyntaxHighlighter");
			form.setLayout(new BorderLayout());
			JComboBox<String> comboBox = new JComboBox<String>();
			
			comboBox.addItem("-- Change Theme --");
			comboBox.addItem("Monokai");
			comboBox.addItem("Tomorrow Night");
			comboBox.addItem("Obsidian");
			comboBox.addItem("Solarized Dark");
			
			comboBox.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){
						Object item = e.getItem();
						if (item.toString() == "Monokai"){
							syntaxHighlighter.changeHighlightTheme(Themes.Monokai);
						}else if (item.toString() == "Tomorrow Night"){
							syntaxHighlighter.changeHighlightTheme(Themes.TomorrowNight);
						}else if (item.toString() == "Obsidian"){
							syntaxHighlighter.changeHighlightTheme(Themes.Obsidian);
						}else if (item.toString() == "Solarized Dark"){
							syntaxHighlighter.changeHighlightTheme(Themes.SolarizedDark);
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
