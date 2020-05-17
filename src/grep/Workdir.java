// - - - - - - - - - - - - - - - - - - - - -
// Grep - Workdir
// PSP - UF2 Processos i fils: Exercici Grep
// MaGoMo - 03/02/2020 - CEP
// estuctura de directorios
// - - - - - - - - - - - - - - - - - - - - -
package grep;

import static grep.Main.searchResults;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Workdir extends Thread 
{
    // - - - - - - - - - - Atributos
    private static int totalScanned = 0;
    private File filedata;
    // - - - - - - - - - - Contructor
    public Workdir(File filedata) { this.filedata = filedata; }
    // - - - - - - - - - - Setters & Getters
    public String getFilename() { return this.filedata.getAbsolutePath(); }
    public int getTotalScanned() { return totalScanned; }
    // - - - - - - - - - - add Scanned
    public void addTotalSanned() { ++totalScanned; }
    // - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - - RUN method: recorrido de entradas de directorios
    @Override
    public void run()
    {
	ArrayList<Workdir> busquedas = new ArrayList<>(); 
	Workdir thedir;
	addTotalSanned();
	if (filedata.exists()) 
	{
	    if (filedata.isDirectory())
	    { 
		// la entrada es directorio
		for( File e: filedata.listFiles() ) 
		{ 
		    thedir = new Workdir( e );
		    thedir.start();
		    busquedas.add(thedir);
		}
		for(Workdir w: busquedas) 
		{ 
		    try { w.join(); } 
		    catch (InterruptedException ex) { Logger.getLogger(Workdir.class.getName()).log(Level.SEVERE, null, ex); }
		}
	    }
	    else
	    {
		// la entrada es archivo
		Workfile thefile = new Workfile( filedata );
		thefile.start();
		try { thefile.join(); } 
		catch (InterruptedException ex) { Logger.getLogger(Workdir.class.getName()).log(Level.SEVERE, null, ex); }
		searchResults.add(thefile); 
	    }
	}
    }
    // - - - - - - - - - - 
    // - - - - - - - - - - 
}
