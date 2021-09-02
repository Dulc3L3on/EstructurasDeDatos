/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulceingenio.edd;

import Listas.ListaDoblementeEnlazada;
import Listas.NodoDoble;

/**
 *
 * @author phily
 */
public class Main {

     public static String metodoParaVerificarActualizacionEnMetodosRecursivos(NodoDoble<String> nodoPrueba){
        String elString;
        
        if(nodoPrueba.obtenerContenido().equals("D")){
            nodoPrueba.eliminar();
            return "D";
        }else{
            elString = metodoParaVerificarActualizacionEnMetodosRecursivos(nodoPrueba.obtenerSiguiente());
            System.out.println(nodoPrueba.obtenerContenido()+"\n");
        }            
        
        return elString;
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //recuerda no tendrás problemas por tener como tipo de param de los elementos a insertar en los árboles un Object, porque se envuelven automáticamente y siguen conservando su naturaleza xD
        
      ListaDoblementeEnlazada<String> listadoStrings = new ListaDoblementeEnlazada<>();
        
        listadoStrings.agregarSiguiente("A");
        listadoStrings.agregarSiguiente("B");
        listadoStrings.agregarSiguiente("C");
        listadoStrings.agregarSiguiente("D");
        listadoStrings.agregarSiguiente("E");
        
        metodoParaVerificarActualizacionEnMetodosRecursivos(listadoStrings.darPrimerElemento());
        listadoStrings.mostrarElementosLista("primeroAlUltimo");
        System.out.println(listadoStrings.darTamanio());
    }//Esto demuestra que la forma de actuar de los objetso con respecto a los cambios, funciona como de normalidad sin importar que el método sea recursi o no xD....era obvi ._. :v xD
    
    
}

