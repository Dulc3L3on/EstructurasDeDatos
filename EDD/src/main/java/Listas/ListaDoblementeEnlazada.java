/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author phily
 */
public class ListaDoblementeEnlazada<E> {
    private NodoDoble<E> primerNodo;
    private NodoDoble<E> ultimoNodo;
    static int tamanioLista;
    
    public ListaDoblementeEnlazada(){
        vaciarLista();
    }
    
    private boolean inicializarLista(E contenido){
        if(tamanioLista == 0){
            primerNodo = ultimoNodo = new NodoDoble<>(null, contenido, null);
            return true;
        }        
        return false;
    }
    
    /**
     * O de otra manera, agregarAlFinal xD
     * @param contenido
     */
    public void agregarSiguiente(E contenido){
        if(!inicializarLista(contenido)){                   
            //método antiguo y funcional [en el que el nodo no exe el establecmiento, solo igualación de los obj]
            //NodoDoble<E> nodoAuxiliar = ultimoNodo;
            // ultimoNodo = new NodoDble<E>(nodoAuxiliar, contenido, null);
                        
            ultimoNodo.establecerSiguiente(contenido);
            ultimoNodo = ultimoNodo.obtenerSiguiente();            
        }        
        tamanioLista++;
    }
    
    /**
     * Mejor dicho es agregarAlInicio :v
     * @param contenido
     */
    public void agregarAnterior(E contenido){
        if(!inicializarLista(contenido)){
             //método antiguo y funcional [en el que el nodo no exe el establecmiento, solo igualación de los obj]
            //NodoDoble<E> nodoAuxiliar = primerNodo;
            // primerNodo = new NodoDble<E>(null, contenido, nodoAuxiliar);
            
            primerNodo.establecerAnterior(contenido);
            primerNodo = primerNodo.obtenerAnterior();
        }        
        tamanioLista++;
    }
    
    public void vaciarLista(){
        primerNodo = ultimoNodo = null;        
        tamanioLista = 0;      
    }
      
    public NodoDoble<E> darPrimerElemento(){
        return primerNodo;//quizá después también sea últil un método para dar el último... todo dependerá del comportamiento de los árboles...
    }
    
    public NodoDoble<E> darUltimoElemento(){
        return ultimoNodo;
    }
    
    public boolean estaVacia(){
        return tamanioLista==0;
    }      
    
    public int darTamanio(){
        return tamanioLista;
    }
    
    public void mostrarElementosLista(String tipoOrden){               
        if(tipoOrden.equalsIgnoreCase("primeroAlUltimo")){
            NodoDoble<E> nodoAuxiliar = primerNodo;
            
            while(nodoAuxiliar != null){
                System.out.println(nodoAuxiliar.obtenerContenido());
                nodoAuxiliar = nodoAuxiliar.obtenerSiguiente();
            }        
        }else{//puesto que yo estableceré el tipo xD
            NodoDoble<E> nodoAuxiliar = ultimoNodo;
            
            while(nodoAuxiliar != null){
                System.out.println(nodoAuxiliar.obtenerContenido());
                nodoAuxiliar = nodoAuxiliar.obtenerAnterior();
            }        
        }              
    }
}
