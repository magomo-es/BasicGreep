// - - - - - - - - - - - - - - - - - - - - -
// Grep - Workfile
// PSP - UF2 Processos i fils: Exercici Grep
// MaGoMo - 03/02/2020 - CEP
// resultados por archivo
// - - - - - - - - - - - - - - - - - - - - -
package grep;

import static grep.Main.needlestr;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Workfile extends Thread 
{
    // - - - - - - - - - - Atributos
    private File filename;
    private int qtty;
    private ArrayList<Result> finded = new ArrayList<>();
    // - - - - - - - - - - Contructor
    public Workfile(File filename)
    {
        this.filename = filename;
	this.qtty = 0;
    }
    // - - - - - - - - - - Setters & Getters
    public String getFilename() { return this.filename.getAbsolutePath(); }
    public int getQtty() { return this.qtty; }
    public ArrayList<Result> getText() { return this.finded; }
    // - - - - - - - - - - add Finded
    public void addFinded(int line, String text) 
    { 
	++this.qtty; 
        this.finded.add( new Result(line,text));
    }
    // - - - - - - - - - - toString
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
	for(Result r: this.finded) { sb.append("\n").append( getFilename() ).append( r.toString() ); }
	return sb.toString();
    }
    // - - - - - - - - - - 
    // - - - - - - - - - - RUN method: lee archivo y lo procesa por lineas en busqueda del string
    @Override
    public void run()
    {
	String slinea;
	try ( BufferedReader br = new BufferedReader( new FileReader( this.getFilename() ) ) ) 
	{
	    int nlinea = 0;
	    while ((slinea = br.readLine()) != null) 
	    { 
		++nlinea;
		if ( slinea.contains(needlestr) ) { addFinded(nlinea, slinea); } 
	    }
	} 
	catch (FileNotFoundException e) { System.out.println("Exeption " + e.toString()); } 
	catch (IOException ex) { Logger.getLogger(Workfile.class.getName()).log(Level.SEVERE, null, ex);
	}
    }	
}
