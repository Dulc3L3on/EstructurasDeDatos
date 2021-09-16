/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulceingenio.edd;

import Listas.ListaDoblementeEnlazada;
import Listas.NodoDoble;
import Árboles.ArbolAVL;

/**
 *
 * @author phily
 */
public class Main {
    /*
       ******SUPER OJO!!!!!!*******
     * Esto te recuerda que, al envíar un objeto en el parám de un método, si utilizas métodos para hacer cambios por
       medio de la varParám, por el hecho que el parám contiene la ref de un obj, al llamar al método, se estará llamando 
       al método del objeto al cual refiere, puesto que en sí no es un obj lo que almacena sino una ref, entonces eso sí 
       afectará el contenido enviado, PERO si se hace una igualación de esa varParamétrica a otro obj (es decir otra ref) 
       solo estarás modificando el espacio (o contenido) en sí de esa var paramétrica, y por ello no resultará afectado el 
       obj original...
     */    
    public static void probarActualizacionEnRecursividad(){
        ListaDoblementeEnlazada<String> listadoStrings = new ListaDoblementeEnlazada<>();
        
        listadoStrings.agregarSiguiente("A");
        listadoStrings.agregarSiguiente("B");
        listadoStrings.agregarSiguiente("C");
        listadoStrings.agregarSiguiente("D");
        listadoStrings.agregarSiguiente("E");
        
        metodoParaVerificarActualizacionEnMetodosRecursivos(listadoStrings.darPrimerElemento());
        listadoStrings.mostrarElementosLista("primeroAlUltimo");
        System.out.println(listadoStrings.darTamanio());
    }
    
     private static String metodoParaVerificarActualizacionEnMetodosRecursivos(NodoDoble<String> nodoPrueba){
        String elString;
        
        if(nodoPrueba.obtenerContenido().equals("D")){
            nodoPrueba.reestablecerContenido("L");
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
        //probarActualizacionEnRecursividad();
        
        pruebaAVL();      
    }
    
    public static void pruebaAVL(){
        ArbolAVL avl = new ArbolAVL();
        
        System.out.println("o-> PRUEBA DE AVL INICIADA <-o\n");
        avl.insertar(15);
        avl.insertar(13);
        avl.insertar(12);
        avl.insertar(10);
        avl.insertar(7);
        avl.insertar(5);
        avl.insertar(4);
        avl.insertar(3);
        avl.insertar(0);
        avl.insertar(1);
//        avl.insertar(0);//duplicado, no debería addse, funciona bien, sea que se acepten o no claves repetidas
        System.out.println("se ha terminado la inserción\n");
        
        avl.borrar(12);
        avl.borrar(13);
        avl.borrar(5);
        avl.borrar(5);//intento eli dato inexistente, todo debería quedar como antes de intentarlo
        avl.borrar(4);
        System.out.println("se ha terminado la eliminación\n");
        
        avl.buscar(3);
        avl.buscar(12);//buscar un dato eliminado
        //avl.buscar(13);//buscar un dato eliminado y en este caso que además era la raíz
        //avl.buscar(18);//buscar un dato que nunca se add al árbol
        System.out.println("se ha terminado la búsqueda\n");
        System.out.println("o-> PRUEBA DE AVL FINALIZADA <-o\n");
        
    }
}

