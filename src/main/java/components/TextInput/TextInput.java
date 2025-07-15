package components.TextInput;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Dimension;

public class TextInput extends JTextArea {

    private String validationRegex;

    public TextInput() {
        this(1, 20); // 1 Fila y 20 Columnas por defecto
    }

    public TextInput(int rows, int columns) {
        super(rows, columns);
        //agrega texto arriba del input

        initialize();
    }
    
    private void initialize() {
        // Configura el tamaño preferido del área de texto.
        setPreferredSize(new Dimension(200, 30));
        // Agrega un borde simple.
        setBorder(BorderFactory.createEtchedBorder());
    }
    
    /**
     * Configura una expresión regular para validar el contenido del área de texto.
     */
    public void setValidationRegex(String regex) {
        this.validationRegex = regex;
    }
    
    /**
     * Valida el contenido actual del área de texto.
     * Si se ha establecido una expresión regular, la validación se hará contra ella.
     * De lo contrario, se verificará que el campo no esté vacío.
     * 
     * @return true si el contenido es válido; false de lo contrario.
     */
    public boolean isValidInput() {
        String input = getText();
        if (validationRegex != null) {
            return input.matches(validationRegex);
        }
        // Validación simple: no debe estar vacío (puedes ampliar esta lógica según tus necesidades).
        return input != null && !input.trim().isEmpty();
    }
    
    // Métodos adicionales reutilizables pueden agregarse aquí.
}