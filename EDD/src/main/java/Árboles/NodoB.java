/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Árboles;

import Listas.ListaDoblementeEnlazada;

/**
 *
 * @author phily
 */
public class NodoB<E>{
    private E elemento;    
    private ListaDoblementeEnlazada<NodoB<E>> hijoIzquierdo;//con los hijos como listado, es posible emular la forma de trabajar de los árboles B, puesto que los hijos no son individuales, sino por montón (listado) :v
    private ListaDoblementeEnlazada<NodoB<E>> hijoDerecho;
      
    public NodoB(E elElemento){
        this(elElemento, new ListaDoblementeEnlazada(), new ListaDoblementeEnlazada());//Es decir que cuando el padre sea null, el nodo que se está inspeccionando será la raíz...
    }
    
    public NodoB(E elElemento, ListaDoblementeEnlazada<NodoB<E>> elIzquierdo, ListaDoblementeEnlazada<NodoB<E>> elDerecho){//coloco al padre para que así se puede hacer "regresiones" como en el caso de las listas enlazadas, pero no se si estas regresiones (a niveles de mayor jerarquí o más arriba, para el caso de los árboles) se logran con la recursividad, según lo que estba pensando, sí es aśi, cuadno estés en el proceso de la búsqueda inténtal        
        elemento = elElemento;
        
        hijoIzquierdo = elIzquierdo;
        hijoDerecho = elDerecho;             
    }
    
    public void establecerHijoIzquierdo(ListaDoblementeEnlazada<NodoB<E>> listadoIzquierdo){
        hijoIzquierdo = listadoIzquierdo;
    }

    public void establecerHijoDerecho(ListaDoblementeEnlazada<NodoB<E>> listadoDerecho){
        hijoDerecho = listadoDerecho;
    }   
    
    public void reestablecerHijoIzquierdo(ListaDoblementeEnlazada<NodoB<E>> nuevoListadoIzquierdo){
        hijoIzquierdo = nuevoListadoIzquierdo;
    }
    
    public void reestablecerHijoDerecho(ListaDoblementeEnlazada<NodoB<E>> nuevoListadoDerecho){
        hijoDerecho = nuevoListadoDerecho;
    }    
    //para agregar los elementos a los listados hijos, no deberás crear métodos por el hecho que eso se realiza en la clase ArbolB... y queda mejor que se haga ahí, sino (según lo que he pensado hasta ahora xD) tendrías que add más condiciones y bloques...
           
    /*public void reestablecerElemento(E elElemento){
        elemento = elElemento;
    }*///Hasta donde sé, este método no será útil, por la naturaleza del árbol...
    
    public boolean poseeHijos(){
        return (!hijoIzquierdo.estaVacia() && !hijoDerecho.estaVacia());//en realidad para el caso de los árboles B por la naturaleza de estos, los nodosB o tienen dos hijos o no tienen ninguno... xD
    }
    
    public ListaDoblementeEnlazada<NodoB<E>> darHijoIzquierdo(){
        return hijoIzquierdo;
    }
    
    public ListaDoblementeEnlazada<NodoB<E>> darHijoDerecho(){
        return hijoDerecho;
    }
    
    public E darElemento(){
        return elemento;
    }       
}
