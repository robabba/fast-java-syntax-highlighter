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

package JSyntaxHighlighter.Languages;

import java.io.File;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import JSyntaxHighlighter.SyntaxRules;

public class LanguageBuilder {

	private SyntaxRules syntaxRules = new SyntaxRules();
	
	public LanguageBuilder(String file){
		buildLanguage(new File(file));
	}
	
	public LanguageBuilder(File file){
		buildLanguage(file);
	}
	
	private void buildLanguage(File f){
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);
			
			// normalize text
			doc.getDocumentElement().normalize();
			
			if (doc.getDocumentElement().getNodeName() != "JSyntaxHighlighter"){
				System.out.println("Error: Language file is not of JSyntaxHiglighter! Please make sure you've spelt everything correctly, and try again.");
				return;
			}else{
				// Get keywords
				NodeList keywordTag = doc.getElementsByTagName("Keywords");
				Node node = keywordTag.item(0);
				
				String getNodeList = node.getTextContent().replaceAll("\t", "");
				String[] keywordList = getNodeList.replaceAll("\t", "").split("\n");
				syntaxRules.setKeywords(keywordList);
				
				// Check for overrides
				if (doc.getElementsByTagName("Overrides") != null){
					
					// Get override occurrences
					NodeList overrides = doc.getElementsByTagName("Overrides");
					int overrideOccurrences = overrides.getLength();
					
					for (int i = 0; i < overrideOccurrences; i++){
						String[] overrideList = overrides.item(i).getTextContent().split("\n");
						
						String ruleOverride = overrides.item(i).getAttributes().getNamedItem("rule").getNodeValue();
	
						if (ruleOverride.equals("s-comments")){
							
							String str = overrideList[1].replaceAll("\t", "");
							syntaxRules.overrideSingleCommentsRule(true, str);
							
						}else if (ruleOverride.equals("m-comments")){
							
							syntaxRules.overrideMultiCommentsRule(true, overrideList);
							
						}else if (ruleOverride.equals("strings")){
							syntaxRules.overrideStringRule(true, overrideList);
						}
						
					}
					
				}
			}
			
		}catch (SAXParseException e){
			e.printStackTrace();
		}catch (Throwable t){
			t.printStackTrace();
		}
		
	}
	
	public SyntaxRules getRules(){
		return syntaxRules;
	}

}
