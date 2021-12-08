package exceptions;

import java.awt.TextArea;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CustomOutputStream extends OutputStream {
	
    private TextArea textArea;

    public CustomOutputStream(TextArea entrada) {
        this.textArea = entrada;
    }

    @Override
    public void write(int b) throws IOException {
        
        textArea.append(String.valueOf((char)b));
        
        textArea.setCaretPosition(textArea.getText().length());
        
        textArea.update(textArea.getGraphics());
    }

}
