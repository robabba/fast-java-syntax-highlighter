/* 	Copyright (c) 2015 Robert Abba
    Code based from Mathew Reeder.

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


import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static java.lang.Math.max;
import static java.lang.Math.min;


/**
 * Custom widget for line numbers, meant to be the row header for a JScrollPane
 * that scrolls a JEditorPane.
 *
 * @author Matthew Reeder (original)
 */

public class LineNumbers extends JPanel implements DocumentListener, MouseListener, MouseMotionListener,
            CaretListener {

		private static final long serialVersionUID = -7761522385042376659L;
		private final JEditorPane editorPane;
		private int currentLines, lineWidth, anchor, lastIndex, offset, textWidth;
		private int currentLinePadding = 20;

		public LineNumbers(JEditorPane editorPane) {
	        this.editorPane = editorPane;
	        editorPane.getDocument().addDocumentListener(this);
	        setForeground(editorPane.getForeground());
	        setBackground(editorPane.getBackground());
	        currentLines = 1;
	        lastIndex = -1;
	        anchor = -1;
	        addMouseListener(this);
	        addMouseMotionListener(this);
	        editorPane.addMouseListener(this);
	        setPreferredSize(new Dimension(24, 17));
	        editorPane.addCaretListener(this);
		  }
		
		  /**
		   * Listens for changes on the Document in its associated text pane.
		   * <p/>
		   * If the number of lines has changed, it updates its view.
		   */
		   public void changedUpdate(DocumentEvent e) {
		        anchor = lastIndex = -1;
		        try {
		              checkLines(e.getDocument().getText(0, e.getDocument().getLength()));
		        } catch (BadLocationException ex) {
		              ex.printStackTrace();
		        }
		  }
		
		  /**
		   * Listens for changes on the Document in its associated text pane.
		   * <p/>
		   * If the number of lines has changed, it updates its view.
		   */
		   public void insertUpdate(DocumentEvent e) {
		        anchor = lastIndex = -1;
		        try {
		              checkLines(e.getDocument().getText(0, e.getDocument().getLength()));
		        } catch (BadLocationException ex) {
		              ex.printStackTrace();
		        }
		  }
		
		  /**
		   * Listens for changes on the Document in its associated text pane.
		   * <p/>
		   * If the number of lines has changed, it updates its view.
		   */
		   public void removeUpdate(DocumentEvent e) {
		        anchor = lastIndex = -1;
		        try {
		              checkLines(e.getDocument().getText(0, e.getDocument().getLength()));
		        } catch (BadLocationException ex) {
		              ex.printStackTrace();
		        }
		  }
		
		  /**
		   * Called by the DocumentListener methods to check if the number of lines
		   * has changed, and if it has, it updates the display.
		   *
		   * @param text the text to compare to the current text
		   */
		   protected void checkLines(String text) {
		        int lines = 0;
		        int index = -1;
		
		        do {
		              lines++;
		              index = text.indexOf('\n', index + 1);
		        } while (index >= 0);
		        if (lines != currentLines) {
		              currentLines = lines;
		              repaint();
		        }
		  }
		
		  /**
		   * Draws the line numbers.
		   */
		  @Override
		  public void paint(Graphics g) {
			   // this.setMargin(new Insets(10,0,0,0));
		checkLines(editorPane.getText());
		g.setFont(editorPane.getFont());
		FontMetrics fm = g.getFontMetrics(g.getFont().deriveFont(12f));
		
		if (lineWidth == 0) {
		      lineWidth = fm.getHeight();
		      offset = fm.getAscent() - lineWidth / 2;
		}
		g.setFont(editorPane.getFont().deriveFont(Font.PLAIN, 11f));
		g.setColor(editorPane.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(editorPane.getSelectionColor());
		int start = max(1, min(anchor, lastIndex));
		int end = min(currentLines, max(anchor, lastIndex));
		
		g.fillRect(0, (start - 1) * lineWidth - offset, textWidth, (end - start + 1) * lineWidth);
		int maxwidth = max(textWidth, fm.stringWidth("0000"));
		
		        for (int i = 1; i <= currentLines; i++) {
		              String str = Integer.toString(i);
		
		              maxwidth = max(maxwidth, fm.stringWidth(str));
		              if (i >= start && i <= end) {
		                    g.setColor(editorPane.getSelectedTextColor());
		              } else {
		                    g.setColor(editorPane.getForeground());
		              }
		              
		              g.drawString(str, textWidth - fm.stringWidth(str), i * lineWidth + offset); 
		        }
		        textWidth = maxwidth;
		        g.setColor(editorPane.getForeground());
		        
		        g.drawLine(maxwidth + 1, 0, maxwidth + 1, getHeight());
		        Dimension dim = getPreferredSize();
		
		        if (dim.height != lineWidth * currentLines || dim.width != maxwidth + 8) {
		              setPreferredSize(new Dimension(maxwidth + 8, lineWidth * currentLines));
		              setMinimumSize(new Dimension(maxwidth + 8, lineWidth * currentLines));
		              repaint();
		        }
		  }
		
		  /**
		   * Handles selection in the text pane due to gestures on the line numbers.
		   */
		   protected void doSelection() {
		        int first = min(anchor, lastIndex);
		        int last = max(anchor, lastIndex);
		
		        try {
		              String text = editorPane.getDocument().getText(0, editorPane.getDocument().getLength());
		              int index = -1, lines = 1;
		
		              while (lines < first) {
		                    index = text.indexOf('\n', index + 1);
		            lines++;
		      }
		      int firstindex = index + 1;
		
		      do {
		            index = text.indexOf('\n', index + 1);
		                    lines++;
		              } while (lines <= last && index > 0);
		              int lastindex;
		
		              if (index < 0) {
		                    lastindex = editorPane.getDocument().getLength();
		              } else {
		                    lastindex = index + 1;
		              }
		              editorPane.setSelectionStart(firstindex);
		              editorPane.setSelectionEnd(lastindex);
		        } catch (BadLocationException ignored) {}
		  }
		
		  /**
		   * Selects the number that was clicked on and sets it as an "anchor" for
		   * dragging.
		   */
		   public void mousePressed(MouseEvent e) {
		        if (e.getSource() == this) {
		              if (e.getX() < textWidth) {
		                    anchor = e.getY() / lineWidth + 1;
		                    lastIndex = anchor;
		                    doSelection();
		                    repaint();
		                    editorPane.requestFocus();
		              }
		        } else {
		              anchor = lastIndex = -1;
		              repaint();
		        }
		  }
		
		  /**
		   * Sets the end anchor and updates the state of the widget to reflect that
		   * the click-and-drag gesture has ended.
		   */
		   public void mouseReleased(MouseEvent e) {
		        if (e.getSource() == this) {
		              if (e.getX() < textWidth) {
		                    if (lastIndex != e.getY() / lineWidth + 1) {
		                          lastIndex = e.getY() / lineWidth + 1;
		                          doSelection();
		                          repaint();
		                    }
		                    editorPane.requestFocus();
		              }
		        } else {
		              anchor = lastIndex = -1;
		              repaint();
		        }
		  }
		
		  /**
		   * Temporarily moves the end anchor of the current selection.
		   */
		   public void mouseDragged(MouseEvent e) {
		        if (lastIndex != e.getY() / lineWidth + 1) {
		              if (e.getX() < textWidth) {
		                    lastIndex = e.getY() / lineWidth + 1;
		                    doSelection();
		                    repaint();
		              }
		        }
		        editorPane.requestFocus();
		  }
		
		  /**
		   * Empty - part of the MouseMotionListener interface.
		   */
		   public void mouseMoved(MouseEvent e) {}
		
		  /**
		   * Empty - part of the MouseListener interface.
		   */
		   public void mouseEntered(MouseEvent e) {}
		
		  /**
		   * Empty - part of the MouseListener interface.
		   */
		   public void mouseExited(MouseEvent e) {}
		
		  /**
		   * Empty - part of the MouseListener interface.
		   */
		   public void mouseClicked(MouseEvent e) {}
		
		  /**
		   * Listens for changes in caret position on the text pane.
		   * <p/>
		   * Updates the code block display and stuff
		   */
		   public void caretUpdate(CaretEvent e) {
		        repaint();
		  }  
}