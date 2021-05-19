/*
Copyright 2021 Javier Garcia Carbia
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing,
software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied.
See the License for the specific language governing permissions
and
limitations under the License.
*/

package com.mycompany.app;

import java.util.*;

public class Graph<V>{
//Lista de adyacencia.
private Map<V, Set<V>> adjacencyList = new HashMap<>();
private Set<V> vertices = new HashSet<>();

/******************************************************************
* Añade el vertice ‘v‘ al grafo.
*
* @param v vertice a añadir.
* @return ‘true‘ si no estaba anteriormente y ‘false‘ en caso
CAPITULO 2. PRACTICAS 10
* contrario.
******************************************************************/
public boolean addVertex(V v){
    return vertices.add(v);
}
/******************************************************************
* Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo. En
* caso de que no exista alguno de los vértices, lo añade
* tambien.
*
* @param v1 el origen del arco.
* @param v2 el destino del arco.
* @return ‘true‘ si no existia el arco y ‘false‘ en caso
contrario.
******************************************************************/
public boolean addEdge(V v1, V v2){
    if(!containsVertex(v1)){
        vertices.add(v1);
    }
    if(!containsVertex(v2)){
        vertices.add(v2);
    }
    if(!adjacencyList.containsKey(v1)){
        Set<V> verts = new HashSet<>();
        verts.add(v2);
        adjacencyList.put(v1, verts);
        return true;
    } else{
        try{
            Set<V> verts = obtainAdjacents(v1);
            verts.add(v2);
        } catch(Exception e){

        }
        return false;
    }
}
/******************************************************************
* Obtiene el conjunto de vertices adyacentes a ‘v‘.
*
* @param v vertice del que se obtienen los adyacentes.
* @return conjunto de vertices adyacentes.
******************************************************************/
public Set<V> obtainAdjacents(V v) throws Exception{
    return adjacencyList.get(v);
}
/******************************************************************
* Comprueba si el grafo contiene el vertice dado.
*
* @param v vertice para el que se realiza la comprobación.
* @return ‘true‘ si ‘v‘ es un vertice del grafo.
******************************************************************/
public boolean containsVertex(V v){
    return vertices.contains(v);
}
/******************************************************************
* Metodo ‘toString()‘ reescrito para la clase ‘Grafo.java‘.
* @return una cadena de caracteres con la lista de adyacencia
.
******************************************************************/
@Override
public String toString(){
    return adjacencyList.toString(); //Este código hay que modificarlo.
}
/******************************************************************
* Obtiene, en caso de que exista, un camino entre ‘v1‘ y ‘v2
‘. En
* caso contrario, devuelve ‘null‘.
*
* @param v1 el vertice origen.
* @param v2 el vértice destino.
* @return lista con la secuencia de vértices desde ‘v1‘ hasta
‘v2‘
* pasando por arcos del grafo.
******************************************************************/
public List<V> onePath(V v1, V v2){
    Map<V, V> traza = new HashMap<>();
    Deque<V> abierta = new ArrayDeque<>();
    abierta.push(v1);
    traza.put(v1, null);
    boolean encontrado = false;
    while (!abierta.isEmpty() && !encontrado){
        V v = abierta.pop();
        if(v == v2){
            encontrado = true;
        }
        if(!encontrado){
            try{
                Set<V> adjacents = obtainAdjacents(v);
                for (V siguiente: adjacents){
                    abierta.add(siguiente);
                    traza.put(siguiente, v);
                }
            } catch(Exception e){

            }
        }
    }
    if (encontrado)
        {
            List<V> verticesFinal = new ArrayList<>();            
            encontrado = false;

            //buscamos la ruta de puntos definitiva entre los arcos de la traza
            //pero empezamos por el final para evitar posible bifurcaciones
            //y también rellenando la lista definitiva al revés
            V padre = v2;
            verticesFinal.add(padre);

            while(true)
            {
                for (Map.Entry<V, V> entry : traza.entrySet())
                {
                    V hijo = entry.getKey();
                    if (padre == v1){
                        encontrado = true;
                        break;
                    }
                    else if (hijo == padre){
                        padre = entry.getValue();
                        verticesFinal.add(0, padre);
                    }
                } if (encontrado)
                    break;
            } return verticesFinal;
        } else
            return null;
    }
}
