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
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

public class ThemeModifier {

	private SyntaxStyle syntaxStyle = new SyntaxStyle();
	private Color background;
	private Color foreground;
	private Color currentLine;
	private Color caretColor;
	private Color selectionBackColor;
	private Color selectionForeColor;
	private Color lineNumbers;

	public ThemeModifier(Themes theme) {
		switch (theme) {
		case Dusk:
			setAsDusk();
			break;
		case Monokai:
			setAsMonokaiExtended();
			break;
		case Tomorrow:
			setAsTomorrow();
			break;
		case TomorrowNight:
			setAsTomorrowNight();
			break;
		case Obsidian:
			setAsObsidian();
			break;
		case SolarizedDark:
			setAsSolarizedDark();
			break;
		case VibrantInk:
			setAsVibrantInk();
			break;
		case Xcode:
			setAsXcode();
			break;
		default:
			break;
		}
	}

	public ThemeModifier(File theme) {
		setCustomThemeFromFile(theme);
	}

	public SyntaxStyle getNewStyle() {
		return syntaxStyle;
	}

	public Color getBackground() {
		return background;
	}

	public Color getForeground() {
		System.out.println(foreground.toString());
		return foreground;
	}

	public Color getCurrentLineColor() {
		return currentLine;
	}

	public Color getCaretColor() {
		return caretColor;
	}

	public Color getSelectionBackColor() {
		return selectionBackColor;
	}

	public Color getSelectionForeColor() {
		return selectionForeColor;
	}

	public Color getLineNumbersColor() {
		return lineNumbers;
	}

	// Monokai - Extended
	private void setAsMonokaiExtended() {
		// Syntax Colors
		this.background = Color.decode("#272822");
		this.foreground = Color.decode("#CFBFAD");
		this.currentLine = Color.decode("#5B5A4E");
		this.selectionBackColor = Color.decode("#586E75");
		this.selectionForeColor = Color.decode("#A6E22E");
		this.caretColor = Color.white;

		syntaxStyle.DEFAULT = Color.decode("#272822");
		syntaxStyle.KEYWORDS = Color.decode("#F92672");
		syntaxStyle.STRINGS = Color.decode("#E6DB74");
		syntaxStyle.NUMERICAL = Color.decode("#AE81FF");
		syntaxStyle.CLASS = Color.decode("#66d9EF");
		syntaxStyle.FUNCTION = Color.decode("#A6E22E");
		syntaxStyle.COMMENTS = Color.decode("#75715E");
	}

	// Tomorrow Night
	private void setAsTomorrowNight() {
		// Syntax Colors
		this.background = Color.decode("#1D1F21");
		this.foreground = Color.decode("#C5C8C6");
		this.currentLine = Color.decode("#282A2E");
		this.selectionBackColor = Color.decode("#373B41");
		this.selectionForeColor = Color.decode("#C5C8C6");
		this.caretColor = Color.white;

		syntaxStyle.DEFAULT = Color.decode("#1D1F21");
		syntaxStyle.KEYWORDS = Color.decode("#81A2BE");
		syntaxStyle.STRINGS = Color.decode("#B5BD68");
		syntaxStyle.NUMERICAL = Color.decode("#DE935F");
		syntaxStyle.CLASS = Color.decode("#F0C674");
		syntaxStyle.FUNCTION = Color.decode("#81A2BE");
		syntaxStyle.COMMENTS = Color.decode("#969896");
	}

	public void setAsTomorrow() {
		// Syntax Colors for Tomorrow
		this.background = Color.decode("#FFFFFF");
		this.foreground = Color.decode("#4D4D4C");
		this.currentLine = Color.decode("#EFEFEF");
		this.selectionBackColor = Color.decode("#D6D6D6");
		this.selectionForeColor = Color.decode("#4D4D4C");
		this.caretColor = Color.decode("#4D4D4C");

		syntaxStyle.DEFAULT = Color.decode("#FFFFFF");
		syntaxStyle.KEYWORDS = Color.decode("#8959A8");
		syntaxStyle.STRINGS = Color.decode("#718C00");
		syntaxStyle.NUMERICAL = Color.decode("#718C00");
		syntaxStyle.CLASS = Color.decode("#EAB700");
		syntaxStyle.FUNCTION = Color.decode("#4271AE");
		syntaxStyle.COMMENTS = Color.decode("#8E908C");
	}

	// Obsidian
	private void setAsObsidian() {
		// Syntax Colors
		this.background = Color.decode("#293134");
		this.foreground = Color.decode("#E0E2E4");
		this.currentLine = Color.decode("#2F393C");
		this.selectionBackColor = Color.decode("#804000");
		this.selectionForeColor = Color.decode("#E0E2E4");
		this.caretColor = Color.white;

		syntaxStyle.DEFAULT = Color.decode("#E0E2E4");
		syntaxStyle.KEYWORDS = Color.decode("#93C763");
		syntaxStyle.STRINGS = Color.decode("#EC7600");
		syntaxStyle.NUMERICAL = Color.decode("#FFCD22");
		syntaxStyle.CLASS = Color.decode("#678CB1");
		syntaxStyle.FUNCTION = Color.decode("#678CB1");
		syntaxStyle.COMMENTS = Color.decode("#7D8C93");
	}

	// Solarized Dark
	private void setAsSolarizedDark() {
		// Syntax Colors
		this.background = Color.decode("#002B36");
		this.foreground = Color.decode("#839496");
		this.currentLine = Color.decode("#002B36");
		this.selectionBackColor = Color.decode("#073642");
		this.selectionForeColor = Color.decode("#93A1A1");
		this.caretColor = Color.white;

		syntaxStyle.DEFAULT = Color.decode("#002B36");
		syntaxStyle.KEYWORDS = Color.decode("#B58900");
		syntaxStyle.STRINGS = Color.decode("#2AA198");
		syntaxStyle.NUMERICAL = Color.decode("#2AA198");
		syntaxStyle.CLASS = Color.decode("#678CB1");
		syntaxStyle.FUNCTION = Color.decode("#009DBE");
		syntaxStyle.COMMENTS = Color.decode("#586E75");
	}

	// Dusk
	private void setAsDusk() {
		// Syntax Colors
		this.background = Color.decode("#1A1B20");
		this.foreground = Color.decode("#FFFFFF");
		this.currentLine = Color.decode("#2F393C");
		this.selectionBackColor = Color.decode("#7F7F7F");
		this.selectionForeColor = Color.decode("#333E48");
		this.caretColor = Color.white;

		syntaxStyle.DEFAULT = Color.decode("#1A1B20");
		syntaxStyle.KEYWORDS = Color.decode("#B11888");
		syntaxStyle.STRINGS = Color.decode("#DB2B37");
		syntaxStyle.NUMERICAL = Color.decode("#776CC4");
		syntaxStyle.CLASS = Color.decode("#009DBE");
		syntaxStyle.FUNCTION = Color.decode("#009DBE");
		syntaxStyle.COMMENTS = Color.decode("#41B644");
	}

	// Xcode
	private void setAsXcode() {
		// Syntax Colors
		this.background = Color.decode("#FFFFFF");
		this.foreground = Color.decode("#000000");
		this.currentLine = Color.decode("#FFFFFF");
		this.selectionBackColor = Color.decode("#B5D5FF");
		this.selectionForeColor = Color.decode("#000000");
		this.caretColor = Color.black;

		syntaxStyle.DEFAULT = Color.decode("#FFFFFF");
		syntaxStyle.KEYWORDS = Color.decode("#C800A4");
		syntaxStyle.STRINGS = Color.decode("#DF0002");
		syntaxStyle.NUMERICAL = Color.decode("#3A00DC");
		syntaxStyle.CLASS = Color.decode("#438288");
		syntaxStyle.FUNCTION = Color.decode("#438288");
		syntaxStyle.COMMENTS = Color.decode("#008E00");
	}

	// Vibrant Ink
	private void setAsVibrantInk() {
		// Syntax Colors
		this.background = Color.decode("#191919");
		this.foreground = Color.decode("#FFFFFF");
		this.currentLine = Color.decode("#222200");
		this.selectionBackColor = Color.decode("#414C3B");
		this.selectionForeColor = Color.decode("#FFFFFF");
		this.caretColor = Color.white;

		syntaxStyle.DEFAULT = Color.decode("#191919");
		syntaxStyle.KEYWORDS = Color.decode("#EC691E");
		syntaxStyle.STRINGS = Color.decode("#477488");
		syntaxStyle.NUMERICAL = Color.decode("#477488");
		syntaxStyle.CLASS = Color.decode("#9CF828");
		syntaxStyle.FUNCTION = Color.decode("#F7C527");
		syntaxStyle.COMMENTS = Color.decode("#8146A2");
	}

	public void setCustomThemeFromFile(File theme) {
		if (theme != null) {
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(theme);

				// normalize text
				doc.getDocumentElement().normalize();

				if (doc.getDocumentElement().getNodeName() != "JSyntaxHighlighter") {
					System.out.println("Error: Theme file is not of JSyntaxHiglighter! Please make sure you've spelt everything correctly, and try again.");
					return;
				} else {
					// Get keywords
					NodeList nodeList = doc.getElementsByTagName("Color");
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node n = nodeList.item(i);

						if (n.getNodeType() == Node.ELEMENT_NODE) {
							// Get attribute
							String attr = n.getAttributes().getNamedItem("type").getNodeValue();

							if (attr.equals("foreground")) {
								foreground = Color.decode(n.getTextContent());
								syntaxStyle.DEFAULT = Color.decode(n.getTextContent());
							} else if (attr.equals("background")) {
								background = Color.decode(n.getTextContent());
							} else if (attr.equals("selection.fore")) {
								selectionForeColor = Color.decode(n.getTextContent());
							} else if (attr.equals("selection.back")) {
								selectionBackColor = Color.decode(n.getTextContent());
							} else if (attr.equals("caret")) {
								caretColor = Color.decode(n.getTextContent());
							} else if (attr.equals("line.numbers")) {
								lineNumbers = Color.decode(n.getTextContent());
							} else if (attr.equals("comments")) {
								syntaxStyle.COMMENTS = Color.decode(n.getTextContent());
							} else if (attr.equals("class")) {
								syntaxStyle.CLASS = Color.decode(n.getTextContent());
							} else if (attr.equals("functions")) {
								syntaxStyle.FUNCTION = Color.decode(n.getTextContent());
							} else if (attr.equals("numbers")) {
								syntaxStyle.NUMERICAL = Color.decode(n.getTextContent());
							} else if (attr.equals("keywords")) {
								syntaxStyle.KEYWORDS = Color.decode(n.getTextContent());
							} else if (attr.equals("strings")) {
								syntaxStyle.STRINGS = Color.decode(n.getTextContent());
							}
						}
					}

				}

			} catch (SAXParseException e) {
				e.printStackTrace();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
