package Logical;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Matriz {

    private final String rutaArchivo = "C:/Users/fapor/OneDrive/Desktop/Universidad/5 semestre/Lenguajes de programacion/Semana 11/proyecto3-lenguajes-de-programacion/GUI/palabras.txt";
    public  List<String> palabras = new ArrayList<>();
    public char[][] superMatriz;
    
    public void cargarPalabras() {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                palabras.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  int palabraMasLarga() {
        palabras.sort(Comparator.comparingInt(String::length).reversed());
        return palabras.get(0).length();
    }

    public  final int[][] DIRECCIONES = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public void generateSopaDeLetras() {
        int n = palabraMasLarga();
        char[][] matriz = new char[n][n];
        Random random = new Random();
        if(palabras.size() > 10){
            System.out.println("Demasiadas palabras, no se permiten mas de 10");
            return;
        }
        for (String palabra : palabras) {
            boolean encontrada = false;
            while (!encontrada) {
                int fila = random.nextInt(n);
                int columna = random.nextInt(n);
                int dx = random.nextInt(3) - 1;
                int dy = random.nextInt(3) - 1;

                if (dx == 0 && dy == 0) {
                    continue;
                }

                boolean superpuesta = false;
                for (int i = 0; i < palabra.length(); i++) {
                    int nuevaFila = fila + i * dy;
                    int nuevaColumna = columna + i * dx;
                    if (nuevaFila < 0 || nuevaFila >= n || nuevaColumna < 0 || nuevaColumna >= n || (matriz[nuevaFila][nuevaColumna] != '\0' && matriz[nuevaFila][nuevaColumna] != palabra.charAt(i))) {
                        superpuesta = true;
                        break;
                    }
                }

                if (!superpuesta) {
                    for (int i = 0; i < palabra.length(); i++) {
                        matriz[fila + i * dy][columna + i * dx] = palabra.charAt(i);
                    }
                    encontrada = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == '\0') {
                    matriz[i][j] = (char) (random.nextInt('z' - 'a' + 1) + 'a');
                }
            }
        }
        superMatriz = matriz;
        String path = Paths.get("").toAbsolutePath().toString() + "/sopa_letras.pl";
        try (BufferedWriter sw = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    sw.write("sopa([");
                }
                sw.write("[");
                for (int j = 0; j < n; j++) {
                    sw.write(matriz[i][j]);
                    if (j < n - 1) {
                        sw.write(",");
                    }
                }
                
                if (i == n - 1) {
                    sw.write("]]).");
                }
                else{
                    sw.write("],");
                }
                sw.newLine();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        
        path = Paths.get("").toAbsolutePath().toString() + "/palabras.pl";
        try (BufferedWriter sw = new BufferedWriter(new FileWriter(path))) {
            sw.write("palabras("+palabras.toString()+").");
        } catch (IOException e) {
                e.printStackTrace();
       }
  
        }
    
    public List<String> getPalabras() {
        return palabras;
    }
    
    public char[][] getMatriz(){
        return superMatriz;
    }

}