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

import java.util.ArrayList;

public class Syntax {

	private static ArrayList<String> KEYWORDS = new ArrayList<String>();
	private static ArrayList<String> CLASS = new ArrayList<String>();
	private static ArrayList<String> FUNCTIONS = new ArrayList<String>();
	
	private static String KEYWORDS_REGEX;
	
	public Syntax(){  }
	
	/**
	 * Adds a keyword to the ArrayList
	 * @param keyword
	 */
	public void addKeyword(String keyword){
		KEYWORDS.add(keyword);
	}
	
	/**
	 * Adds a array of keywords to the ArrayList
	 * @param keywords
	 */
	public void addKeywords(String[] keywords){
		for (String keyword : keywords){
			KEYWORDS.add(keyword);
		}
	}
	
	/**
	 * Adds a class to the ArrayList
	 * @param _class
	 */
	public void addClass(String _class){
		CLASS.add(_class);
	}
	
	/**
	 * Adds an array of class' to the ArrayList
	 * @param _classes
	 */
	public void addClasses(String[] _classes){
		for (String _class : _classes){
			CLASS.add(_class);
		}
	}
	
	/**
	 * Adds a function to the ArrayList
	 * @param function
	 */
	public void addFunction(String function){
		FUNCTIONS.add(function);
	}
	
	/**
	 * Adds a array of functions to the ArrayList
	 * @param functions
	 */
	public void addFunctions(String[] functions){
		for (String function : functions){
			FUNCTIONS.add(function);
		}
	}
	
	/**
	 * Returns the list of keywords
	 * @return
	 */
	public ArrayList<String> getKeywordList(){
		return KEYWORDS;
	}
	
	/**
	 * Returns the list of class'
	 * @return
	 */
	public ArrayList<String> getClassList(){
		return CLASS;
	}
	
	
	/**
	 * Returns the list of functions
	 * @return
	 */
	public ArrayList<String> getFunctionList(){
		return FUNCTIONS;
	}
	
	public void buildSyntaxHighlighter(){
		
		// Start KEYWORDS_REGEX rule
		KEYWORDS_REGEX = "(";
		
		int max = KEYWORDS.size();
		int i = 1;
		
		for (String syntax : KEYWORDS){
			if (i < max){
			KEYWORDS_REGEX += "\\b" + syntax + "\\b|";
			}else{
				KEYWORDS_REGEX += "\\b" + syntax + "\\b";
			}
			i++;
		}
		
		// Close Regex Rule
		KEYWORDS_REGEX += ")";
	}
	
	public String getKeywordsRegexRule(){
		return KEYWORDS_REGEX;
	}
	
}
