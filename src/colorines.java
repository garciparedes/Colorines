//Colorines 1.0

import java.util.Scanner;

public class colorines {

	//Este método es el main del programa, que es quien toma los datos del usuario y llama a los métodos.
	public static void main(String[] args) {
		
		//a es la ficha aleatoria, que se insertará en el siguiente movimiento.
		//x es la columna del tablero.
		//y es la fila del tablero.
		int a, x, y, filas, columnas;

		filas = 10;
		columnas = 6;
		
		//Tablero es el array donde están contenidas todas las fichas.
		int tablero [][] = new int [filas] [columnas];
		
		
		
		System.out.println("¡Bienvenido al juego Colorines!");
		System.out.println();
		
		Scanner in = new Scanner(System.in);

		//Si x vale 0 o hay una columna llena termina el juego.
		do {
			
			printTablero(tablero, columnas);
			
			//a es un valor aleatorio entre 1 y 6
			a = (int) (Math.random()*6+1);
			
			System.out.println();
			System.out.println ("Nueva ficha: " + a);
			
			//Pregunta al usuario en qué columna insertar la ficha
			do {
				System.out.println ("¿Columna? ");
				x = in.nextInt();
			} while (x < 0 || x > columnas);
			
			//Se decrementa x, ya que los arrays empiezan a contar desde 0
			x--;
			
			//Si x vale -1 (0) esta parte se salta y termina el juego.
			if (x != 0-1){
				
				//y toma el valor de la primera fila libre empezando por abajo de la columna en la que se va a colocar la ficha.
				y = casillaLibre(tablero, x, filas);
				
				//Coloca la ficha en la posición adecuada.
				tablero [y][x] = a;
				
				//Modifica el color de la ficha cuando proceda.
				modColor(tablero, x, y, filas);
				
				//Elimina la fila en la cual se ha colocado la ficha en el caso de que todos los colores sean iguales.
				eliminaFila(tablero, x, y, columnas);
				
				//Si no está en la última fila comprueba si la anterior también es igual.
				if (y != (filas-1))
					eliminaFila(tablero, x, y+1, columnas);
			}
				
		} while (x != 0-1 && columnaLlena(tablero, x) == false);
		
		in.close();
		
		//Se imprime el resultado final.
		printTablero(tablero, columnas);

		System.out.println("El juego ha terminado.");
		
	}
	
	//Este procedimiento modifica el color de la ficha que se acaba de insertar y de la que se encuentra debajo, según las especificaciones que se piden en el guíon..
	public static void modColor(int tablero[][], int x, int y, int filas){
		
		//Si la ficha que acaba de colocarse se encuentra en la última fila, este procedimiento no actua, ya que no tiene una ficha debajo con quien conbinarse.
		if (y < (filas-1)){
			
			// Rojo + Amarillo = Naranja (1 + 2 = 3)
			if ((tablero[y][x] == 1 && tablero[y+1][x] == 2) || (tablero[y][x] == 2 && tablero[y+1][x] == 1)){
				tablero[y][x] = 0;
				tablero[y+1][x] = 3;
			}

			// Rojo + Azul = Morado (1 + 4 = 5)
			else if ((tablero[y][x] == 1 && tablero[y+1][x] == 4) || (tablero[y][x] == 4 && tablero[y+1][x] == 1)){
				tablero[y][x] = 0;
				tablero[y+1][x] = 5;
			}

			// Amarillo + Azul = Verde (2 + 4 = 6)
			else if ((tablero[y][x] == 2 && tablero[y+1][x] == 4) || (tablero[y][x] == 4 && tablero[y+1][x] == 2)){
				tablero[y][x] = 0;
				tablero[y+1][x] = 6;
			}

			// Naranja + Morado = Rojo (3 + 5 = 1)
			else if ((tablero[y][x] == 3 && tablero[y+1][x] == 5) || (tablero[y][x] == 5 && tablero[y+1][x] == 3)){
				tablero[y][x] = 0;
				tablero[y+1][x] = 1;
			}

			// Naranja + Verde = Amarillo (3 + 6 = 2)
			else if ((tablero[y][x] == 3 && tablero[y+1][x] == 6) || (tablero[y][x] == 6 && tablero[y+1][x] == 3)){
				tablero[y][x] = 0;
				tablero[y+1][x] = 2;
			}

			// Morado + Verde = Azul (5 + 6 = 4)
			else if ((tablero[y][x] == 5 && tablero[y+1][x] == 6) || (tablero[y][x] == 6 && tablero[y+1][x] == 5)){
				tablero[y][x] = 0;
				tablero[y+1][x] = 4;
			}
			
		}
	}
	
	//Esta función busca la primera casilla libre empezando por debajo en la columna que el usuario a elegido para colocar la ficha.
	public static int casillaLibre (int tablero[][], int x, int filas){
		
		//Empieza a contar desde la última fila
		int y = (filas-1);
		
		//Si está ocupada, busca la posición superior hasta que esta esté libre.
		while (tablero [y][x] != 0)
			y--;
		
		return y;
	}
	
	//Este procedimiento Imprime en pantalla el tablero con las fichas.
	public static void printTablero (int tablero[][], int columnas){
		
		//Muestra el número de columna y deja una linea libre con el tablero.
		numColumna(tablero, columnas);
		
		//Recorre las filas
		for (int fila=0; fila < tablero.length; fila++) {
			
			//Recorre las columnas
			for (int columna=0; columna < tablero[fila].length; columna++){
				
				//En las posiciones en las que hay un cero muestra un guión.
				if(tablero [fila][columna] == 0){
					System.out.print("-  ");
				}
				
				//En el resto muestra la ficha que contenga.
				else{	
				System.out.print (tablero[fila][columna] + "  ");
				}
			}
			System.out.println();
		}
	}

	//Este procedimiento escribe en pantalla el número de columna donde se insertará la ficha
	public static void numColumna (int tablero[][], int columnas){
		
		//Imprime el número de columna desde 1 hasta el número de columnas que haya.
		for(int col = 1; col <= columnas; col++){
			System.out.print(col + "  ");
		}
		
		//Deja una línea libre para separan los números de columna del tablero.
		System.out.println();
		System.out.println();
	}
	
	//Esta función booleana comprueba si una fila está llena.
	public static boolean filaIgual (int tablero[][], int x,int y, int columnas ){
		
		// Recoge la posición de la primera ficha de la fila.
		int valor = tablero[y][0];
		
		//Recorre la columna. 
		for (int columna=0; columna < columnas; columna++) {
			
			//Si encuentra una ficha distinta de la primera de la fila devuelve false.
			if (tablero[y][columna] != valor)
				return false;		
		}
		//Si no ha encontrado una ficha distinta en la fila devuelve true.
		return true;
	}
	
	//Este procedimiento elimina una fila en el caso de estar llena.
	public static void eliminaFila (int tablero[][], int x,int y, int columnas){
		
		//Si una fila está llena elimina la fila. 
		if (filaIgual(tablero,x,y, columnas) == true)
			
			//Recorre las filas desde la fila llena hasta arriba.
			for (int fila = y; fila > 0; fila--)
				
				//Recorre las columnas.
				for (int columna = 0; columna < columnas ; columna++)
					
					//Cambia el valor de la casilla por el de la inmediata superior.
					tablero [fila][columna] = tablero [fila-1][columna];	
		}
	
	//Esta función boleana dice si está llena una columna
	public static boolean columnaLlena (int tablero[][], int x){
		
		//Si la fila más alta está libre devuelve false, sino devuelve true
		if (tablero [0][x] == 0)
			return false;
		
		else
			return true;
	}
}