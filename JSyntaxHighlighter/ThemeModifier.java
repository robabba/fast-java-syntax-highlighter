package JSyntaxHighlighter;

import java.awt.Color;

public class ThemeModifier {

	private SyntaxStyle syntaxStyle = new SyntaxStyle();
	private Color background;
	private Color foreground;
	private Color currentLine;
	
	public ThemeModifier(Themes theme) {
		switch(theme){
		case Monokai:
			setAsMonokaiExtended();
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
		default:
			break;
		}
	}

	public SyntaxStyle getNewStyle(){
		return syntaxStyle;
	}
	
	public Color getBackground(){
		return background;
	}
	
	public Color getForeground(){
		return foreground;
	}
	
	public Color getCurrentLineColor(){
		return currentLine;
	}
	
	// Monokai - Extended
	private void setAsMonokaiExtended(){
		// Syntax Colors
		this.background = Color.decode("#272822");
		this.foreground = Color.decode("#CFBFAD");
		this.currentLine = Color.decode("#5B5A4E");
		
		syntaxStyle.KEYWORDS = Color.decode("#F92672");
		syntaxStyle.STRINGS = Color.decode("#E6DB74");
		syntaxStyle.NUMERICAL = Color.decode("#AE81FF");
		syntaxStyle.CLASS = Color.decode("#66d9ef");
		syntaxStyle.COMMENTS = Color.decode("#75715E");
	}

	// Tomorrow Night
	private void setAsTomorrowNight(){
		// Syntax Colors
		this.background = Color.decode("#1D1F21");
		this.foreground = Color.decode("#C5C8C6");
		this.currentLine = Color.decode("#282A2E");
		
		syntaxStyle.KEYWORDS = Color.decode("#81A2BE");
		syntaxStyle.STRINGS = Color.decode("#B5BD68");
		syntaxStyle.NUMERICAL = Color.decode("#DE935F");
		syntaxStyle.CLASS = Color.decode("#F0C674");
		syntaxStyle.COMMENTS = Color.decode("#969896");
	}
	
	// Obsidian
	private void setAsObsidian(){
		// Syntax Colors
		this.background = Color.decode("#293134");
		this.foreground = Color.decode("#E0E2E4");
		this.currentLine = Color.decode("#2F393C");
		
		syntaxStyle.KEYWORDS = Color.decode("#93C763");
		syntaxStyle.STRINGS = Color.decode("#EC7600");
		syntaxStyle.NUMERICAL = Color.decode("#FFCD22");
		syntaxStyle.CLASS = Color.decode("#678CB1");
		syntaxStyle.COMMENTS = Color.decode("#7D8C93");
	}
	
	// Solarized Dark
	private void setAsSolarizedDark(){
		// Syntax Colors
		this.background = Color.decode("#002B36");
		this.foreground = Color.decode("#839496");
		this.currentLine = Color.decode("#002B36");
		
		syntaxStyle.KEYWORDS = Color.decode("#B58900");
		syntaxStyle.STRINGS = Color.decode("#2AA198");
		syntaxStyle.NUMERICAL = Color.decode("#2AA198");
		syntaxStyle.CLASS = Color.decode("#678CB1");
		syntaxStyle.COMMENTS = Color.decode("#586E75");
	}
}
