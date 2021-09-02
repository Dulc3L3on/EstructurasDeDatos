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
public class NodoDoble<E> {
    private E contenido;    
    private NodoDoble<E> nodoSiguiente;
    private NodoDoble<E> nodoAnterior;
    
    /**
    *ctrctor para 1er elemento es decir, cabeza
    * @param elemento
    */
    public NodoDoble(E elemento){
        this(null, elemento, null);//mando a llamar al ctrctor que recibe 2 parámetros
    }
    /**
    *ctrctor para nodos addi a la cabeza
    * @param anterior
    * @param elemento
    * @param siguiente 
    */
    public NodoDoble(NodoDoble<E> anterior, E elemento, NodoDoble<E> siguiente){//a ver si no te da problema el nodo por no estar especigicando su tipo... puesto que esta clase es genérica y aquí estas creando uno sin saber, asi que creo que debería de  especificarselo
        contenido = elemento;
        nodoSiguiente = siguiente;
        nodoAnterior = anterior;
    }

    public void reestablecerContenido(E contenidoNuevo){
        contenido = contenidoNuevo;                      
    }//no creo que se aplique aquí en los árboles pero... xD

    /**
    *Añade un nodo si importar [Es decir que tipo de nodo era [null o no]
    *la posición, el conteido antiguo, el nuevo. Por lo tanto hace crecer la lista!
    * @param contenido
    */
    public void establecerSiguiente(E contenido){            
        NodoDoble<E> antiguoSiguiente = nodoSiguiente;
        nodoSiguiente = new NodoDoble<>(this, contenido,antiguoSiguiente);
        if(antiguoSiguiente != null){
            antiguoSiguiente.nodoAnterior = nodoSiguiente;
        }
    }

    public void establecerAnterior(E contenido){
        NodoDoble<E> antiguoAnterior = nodoAnterior;
        nodoAnterior = new NodoDoble<>(antiguoAnterior, contenido, this);//se ve más lógico al colocarlo en la lista, pero sin importar donde esté, daría el mismo resultado... lo que cb es que aquí solo se tendría que igualar puesto que en otra parte se hizo los establecimientos... xD
        if(antiguoAnterior != null){
            antiguoAnterior.nodoSiguiente = nodoAnterior;
        }
    }       
    
    public void reestablecerSiguiente(NodoDoble<E> nuevoSiguiente){
        nodoSiguiente = nuevoSiguiente;
    }
    
    public void reestablecerAnterior(NodoDoble<E> nuevoAnterior){
        nodoAnterior = nuevoAnterior;
    }
    
    public void eliminar(){
        if(nodoAnterior!= null){
            nodoAnterior.reestablecerSiguiente(nodoSiguiente);
        }
        if(nodoSiguiente != null){
            nodoSiguiente.reestablecerAnterior(nodoAnterior);
        }              
        ListaDoblementeEnlazada.tamanioLista--;//aaah ._. ahora sí ya se reduce el tamaño, por el momento solo eso se me ocurre, para seguir preservando el método de este lado y no tener que estar acarreando la lista para realizar la eliminación...
        //no me deja hacer esta instancia = null, pero pensandolo bien quizá no sea necesario porque ya hice que se perdiera la referencia... hice que se quedara en el olvido...
    }

    public E obtenerContenido(){//no será necesario el índice?? para hacer ref a uno específico y obtener sus respect datos??
        return contenido;
    }

    public NodoDoble<E> obtenerSiguiente(){//Aqupi estas refiriendote al nodo, mas no al objeto que dentro de él está contenido
        return nodoSiguiente;
    }          
        
    public NodoDoble<E> obtenerAnterior(){
        return nodoAnterior;
    }
}
