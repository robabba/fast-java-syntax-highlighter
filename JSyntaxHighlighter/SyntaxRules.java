package JSyntaxHighlighter;

public class SyntaxRules {

	private String SYNTAX_KEYWORDS_RULE = "";
	private String SYNTAX_STRING_RULE = "\\\"(\\.|[^\\\"])*\\\"";
	private String SYNTAX_STRING_WITH_ESCAPE_RULE = "(\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\")";
	private String SYNTAX_NUMERIC_RULE = "\\b\\d+[\\.]?\\d*([eE]\\-?\\d+)?[lLdDfF]?\\b|\\b0x[a-fA-F\\d]+\\b";
	private String SYNTAX_CLASS_RULE = "\\b[A-Z][a-z]*([A-Z][a-z]*)*\\b";
	private String SYNTAX_MULTILINE_COMMENT_RULE = "/\\*(.|[\\r\\n])*?\\*/";
	private String SYNTAX_SINGLELINE_COMMENT_RULE = "//.*";
	private String SYNTAX_OPERATOR_RULE = "(<=|>=|!=|=|>|<)";
	private String SYNTAX_FUNCTION_RULE = "(\\w+\\()+([^\\)]*\\)+)*";

	public SyntaxRules() { }
	
	public void setKeywords(String[] keywords){
			// Start KEYWORDS_REGEX rule
			SYNTAX_KEYWORDS_RULE = "(";
			
			int max = keywords.length;
			int i = 1;
			
			for (String syntax : keywords){
				if (i < max){
					if (!syntax.isEmpty())
						SYNTAX_KEYWORDS_RULE += "\\b" + syntax + "\\b|";
				}else{
					if (syntax != " " || syntax != null)
						SYNTAX_KEYWORDS_RULE += "\\b" + syntax + "\\b";
				}
				i++;
			}
			
			// Close Regex Rule
			SYNTAX_KEYWORDS_RULE += ")";
	}
	
	public void overrideSingleCommentsRule(boolean on, String overrideComponents){
		if (on){
			SYNTAX_SINGLELINE_COMMENT_RULE = overrideComponents + ".*";
		}else{
			SYNTAX_SINGLELINE_COMMENT_RULE = "";
		}
	}

	public void overrideMultiCommentsRule(boolean on, String[] overrideComponents){
		if (on){			
			char[] startIdentifier = overrideComponents[0].replaceAll("\t", "").toCharArray();
			char[] endIdentifier = overrideComponents[1].replaceAll("\t", "").toCharArray();
			
			String startId = "";
			String endId = "";
			
			for (char c : startIdentifier){
				if (c == '[' || c == ']' || c == '*' || c == '.' || c == '|' || c == '$' || c == '?' || c == '+' || c == '"'){
					startId += "\\" + c; 
				}else{
					startId += c;
				}
			}
			
			for (char c : endIdentifier){
				if (c == '[' || c == ']' || c == '*' || c == '.' || c == '|' || c == '$' || c == '?' || c == '+' || c == '"'){
					endId += "\\" + c; 
				}else{
					endId += c;
				}
			}

			
			SYNTAX_MULTILINE_COMMENT_RULE = startId + "(.|[\\r\\n])*?\\" + endId;
		}
	}
	
	public void overrideStringRule(boolean on, String[] overrideComponents){
		if (on){			
			char[] startIdentifier = overrideComponents[0].replaceAll("\t", "").toCharArray();
			char[] endIdentifier = overrideComponents[1].replaceAll("\t", "").toCharArray();
			
			String startId = "";
			String endId = "";
			
			for (char c : startIdentifier){
				if (c == '[' || c == ']' || c == '*' || c == '.' || c == '|' || c == '$' || c == '?' || c == '+'){
					startId += "\\" + c; 
				}else{
					startId += c;
				}
			}
			
			for (char c : endIdentifier){
				if (c == '[' || c == ']' || c == '*' || c == '.' || c == '|' || c == '$' || c == '?' || c == '+'){
					endId += "\\" + c; 
				}else{
					endId += c;
				}
			}

			
			SYNTAX_STRING_RULE = startId + "(\\.|[^\\\"])*\\" + endId;
			SYNTAX_STRING_WITH_ESCAPE_RULE = "(" + startId + "[^" + startId +"\\\\]*(?:\\\\.[^" + endId + "\\\\]*)*" + endId + ")";
		}
	}
	
	public String getKeywordsRule(){
		return SYNTAX_KEYWORDS_RULE;
	}
	
	public String getSingleCommentRule(){
		return SYNTAX_SINGLELINE_COMMENT_RULE;
	}
	
	public String getMultiCommentRule(){
		return SYNTAX_MULTILINE_COMMENT_RULE;
	}

	public String getStringRule(){
		return SYNTAX_STRING_RULE;
	}
	
	public String getStringWithEscapeRule(){
		return SYNTAX_STRING_WITH_ESCAPE_RULE;
	}
	
	public String getNumericalRule(){
		return SYNTAX_NUMERIC_RULE;
	}
	
	public String getClassRule(){
		return SYNTAX_CLASS_RULE;
	}
	
	public String getOperatorRule(){
		return SYNTAX_OPERATOR_RULE;
	}
	
	public String getFunctionRule(){
		return SYNTAX_FUNCTION_RULE;
	}
}
