// - - - - - - - - - - - - - - - - - - - - -
// Grep - Result.java
// PSP - UF2 Processos i fils: Exercici Grep
// MaGoMo - 03/02/2020 - CEP
// estuctura de directorios
// - - - - - - - - - - - - - - - - - - - - -
package grep;

public class Result {
    // - - - - - - - - - - Atributos
    private int line;
    private String text;
    // - - - - - - - - - - Contructor
    public Result(int line, String text)
    {
        this.line = line;
        this.text = text;
    }
    // - - - - - - - - - - Setters & Getters
    public void settLine(int line) { this.line=line; }
    public void setText(String text) { this.text=text; }
    public int getLine() { return this.line; }
    public String getText() { return this.text; }
    // - - - - - - - - - - toString
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( " :: " ).append( this.line ).append( " :: " ).append( this.text );
	return sb.toString();
    }
}
