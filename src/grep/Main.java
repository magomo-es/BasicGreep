// - - - - - - - - - - - - - - - - - - - - -
// Grep - Main
// PSP - UF2 Processos i fils: Exercici Grep Extended
// MaGoMo - 03/02/2020 - CEP
// - - - - - - - - - - - - - - - - - - - - -
package grep;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
/*
Per cada arxiu o directori a explorar, el programa crearà un procés, de tal manera que, si el
procés està tractant un arxiu, buscarà les ocurrències de la cadena de cerca i les mostrarà
per pantalla. En canvi, si es tracta de un directori, el programa llençarà un procés per cada
arxiu o directori que hi pengi. I així, de manera recursiva.
Per cada ocurrència trobada, el programa mostrarà una línia de text, que inclourà:
- La ruta de l’arxiu on s’ha trobat l’ocurrència.
- El número de la línia de l’ocurrència dins de l’arxiu.
- La línia sencera que conté la ocurrència.
*/
public class Main 
{
    public static ArrayList<Workfile> searchResults = new ArrayList<>();
    public static String pathstr="", needlestr="";
    public static void main(String[] args) throws IOException, InterruptedException 
    {
	boolean isOK = false;
        Scanner scanner = new Scanner(System.in);
        // - - - - - - - - - - - - - - - - - - - get the data
	System.out.print("Search string: ");
	needlestr = scanner.nextLine();
	if ( needlestr.length()>0 ) 
	{ 
	    System.out.print("Path file: ");
	    pathstr = scanner.nextLine();
	    if ( needlestr.length()>0 ) 
	    { isOK = true; }
	}
        // - - - - - - - - - - - - - - - - - - - id data Ok, execute process
	if (isOK)
	{
	    if ( pathstr.contentEquals(".")) { pathstr = new File(".").getAbsoluteFile().getParent(); }
	    Workdir thedir = new Workdir( new File(pathstr) );
	    thedir.start();
	    thedir.join();
	    // - - - - - Visualizacion de resultados
	    if (searchResults.isEmpty()) 
	    { 
		System.out.println("\nNo se ha encontrado " + needlestr + " en " + pathstr); 
	    }
	    else
	    { 
		int totalFounded = 0; // acumulador de resultados
		for(Workfile w:searchResults) { System.out.print( w.toString() ); totalFounded+=w.getQtty(); } 
		// - - - - - Visualizacion de totales
		System.out.println("\nAnalizados " + thedir.getTotalScanned() + " directorios/archivos y encontrados " + totalFounded + " coincidencias" );
	    }
	    // - - - - - - - - - - /
	} 
	else  { System.out.println("... debe insertar una cadena y un path de busqueda !!"); } 
    }
    // - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - - - - - - - - - - - -
}
