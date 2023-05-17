% Sopa de letras predefinida
%sopa([
%    [p, a, l, a, b, r, a],
%    [e, c, i, s, t, a, n],
%    [r, t, o, d, a, f, e],
%    [o, a, r, a, l, o, b],
%    [l, p, e, r, r, o, w]
%]).


%Leer los hechos de la sopa de letras
:- ensure_loaded('sopa_letras.pl').

% Lista de palabras predefinida
%palabras([lupita,pedro,sapo,dado,zorro,luis,sol]).
:- ensure_loaded('palabras.pl').

% Verificar si una palabra comienza en una posición específica
inicio_letra(Palabra, Fila, Col, Sopa) :-
    nth0(Fila, Sopa, FilaLista),
    nth0(Col, FilaLista, Letra),
    atom_chars(Palabra, [Letra|_]).

% Buscar palabra en la sopa de letras en diferentes direcciones
buscar_palabra(Palabra, Fila, Col, Sopa, Coordenadas) :-
    direccion(DFila, DCol),
    buscar_palabra_dir(Palabra, Fila, Col, DFila, DCol, Sopa, Coordenadas).

direccion(-1,0).
direccion(1,0).
direccion(0,1).
direccion(0,-1).
direccion(-1,1).
direccion(-1,-1).
direccion(1,1).
direccion(1,-1).

% Buscar palabra en una dirección específica
buscar_palabra_dir(Palabra, Fila, Col, DFila, DCol, Sopa, Coordenadas) :-
    atom_chars(Palabra, Letras),
    buscar_letras(Letras, Fila, Col, DFila, DCol, Sopa, Coordenadas).

% Buscar letras en una dirección específica
buscar_letras([], _, _, _, _, _, []).
buscar_letras([Letra|Resto], Fila, Col, DFila, DCol, Sopa, [(Fila, Col)|CoordResto]) :-
    nth0(Fila, Sopa, FilaLista),
    nth0(Col, FilaLista, Letra),
    NFila is Fila + DFila,
    NCol is Col + DCol,
    buscar_letras(Resto, NFila, NCol, DFila, DCol, Sopa, CoordResto).

% Encontrar todas las palabras en la sopa de letras y devolver sus coordenadas completas
ubicaciones_palabras(Ubicaciones) :-
    palabras(Palabras),
    sopa(Sopa),
    findall(
        (Palabra, Coordenadas),
        (   member(Palabra, Palabras),
            inicio_letra(Palabra, Fila, Col, Sopa),
            buscar_palabra(Palabra, Fila, Col,Sopa, Coordenadas)
        ),
        Ubicaciones
    ).
