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

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;

public class AutoTab extends DocumentFilter {

	public void insertString(FilterBypass filter, int offset, String str, AttributeSet attr) throws BadLocationException{
		if ("\n".equals(str))
			str = addTab(filter.getDocument(), offset);
	
		super.insertString(filter, offset, str, attr);
	}
	
	/**
	 * Adds tab to document where user presses enter if tab space already exists
	 * @param doc
	 * @param offset
	 * @return
	 * @throws BadLocationException
	 */
	public String addTab(Document doc, int offset) throws BadLocationException{
		StringBuilder tab = new StringBuilder("\n");
		Element root = doc.getDefaultRootElement();
		
		int l = root.getElementIndex(offset);
		int i = root.getElement(l).getStartOffset();
		
		while (true){
			
			String temp = doc.getText(i, 1);
			
			if (temp.equals(" ") || temp.equals("\t")){
				tab.append(temp);
				i++;
			}else{
				break;
			}
			
		}
		
		return tab.toString();
	}
	
	public void replace(FilterBypass filter, int offset, int length, String str, AttributeSet attr) throws BadLocationException{
		if ("\n".equals(str))
			str = addTab(filter.getDocument(), offset);
	
		super.replace(filter, offset, length, str, attr);
	}

}
