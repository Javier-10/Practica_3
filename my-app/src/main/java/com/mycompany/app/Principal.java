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
//package pr2.aplicacion;

package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        onePathFindsAPath();
    }
    
    @Test
    
    public static void onePathFindsAPath(){
        System.out.println("\nTest onePathFindsAPath");
        System.out.println("----------------------");
        // Se construye el grafo.
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        // Se construye el camino esperado.
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);
        
        List<Integer> lista = g.onePath(1, 4);
        if (lista.equals(expectedPath))
            System.out.println("\nEs igual");
        else
            System.out.println("\nNo es igual");

        //Se comprueba si el camino devuelto es igual al esperado.
    
    }
}
