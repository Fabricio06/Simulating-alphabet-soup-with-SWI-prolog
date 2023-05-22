# Simulating alphabet soup with SWI prolog connected with java jkframe

### Las palabras son enviadas en un .txt
![image](https://github.com/Fabricio06/Simulating-alphabet-soup-with-SWI-prolog/assets/82431338/3324f900-e3e5-4338-8e88-dc698e49db7c)

Al ejecutar el codigo, java toma las palabras y genera la matriz con las palabras en ella y pasa la matriz y las palabras a archivos .pl

### Ejemplo de la sopa en el archivo auto generado:
![image](https://github.com/Fabricio06/Simulating-alphabet-soup-with-SWI-prolog/assets/82431338/3f5ffe04-b595-42c6-b59d-c222c42653cb)

### Ejemplo de las palabras en el archivo auto generado:
![image](https://github.com/Fabricio06/Simulating-alphabet-soup-with-SWI-prolog/assets/82431338/993ca9f8-d563-4168-bd10-5f53a1cf198c)

En el archivo principal de prolog se encarga de tomar estos archivos como hechos de la siguiente forma

### Carga de los archivos como hechos
![image](https://github.com/Fabricio06/Simulating-alphabet-soup-with-SWI-prolog/assets/82431338/a298f71d-836b-4a09-a8cf-3da8bf786d0a)

Luego el resto del archivo de prolog realiza una busqueda en profundidad de cada una de las palabras
y devuelve la palabra con las ubicaciones

### Ejemplo de salida:
![image](https://github.com/Fabricio06/Simulating-alphabet-soup-with-SWI-prolog/assets/82431338/51057434-1c41-42bf-80b9-187d2387cf31)


## Implementacion con java para mostrar la sopa de letras graficamente

En el codigo se hace la conexion con prolog y se realiza la consulta de la imagen anterior, se obtienen los datos y se procesan para obtenerlo como lista con sublistas

### Ejemplo del resultado final:
![image](https://github.com/Fabricio06/Simulating-alphabet-soup-with-SWI-prolog/assets/82431338/3211d73e-a90f-4f2b-862c-8b282fe3c420)

* Los cubos negros significan espacios utilizados por 2 palabras o mas
* Si hay dos palabras iguales en la sopa, se pintan del mismo color
* Todas las palabras se pintan de diferentes colores (Max 10 palabras)
