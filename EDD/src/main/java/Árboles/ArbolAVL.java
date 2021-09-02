/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Árboles;

/**
 *
 * @author phily
 * @param <E>
 */
public class ArbolAVL<E> extends ÁrbolBinario{
    //buscar no está aquí porque no requiere de una modificación...
    
    @Override
    public void insertar(Object dato){
        super.insertar(dato);
        
        equilibrarArbol(raiz, null);//para que así se revise todo el árbol, aunque quizá lo mejor sería enviar el papá el subarbol izq o der hijo de la raíz, donde se inertó el dato, para no tener que revisar todo xD, pero eso implica tener que cambiar el método de insertar para saber a que hijo directo de la raíz se fue... si te da tiempo , hazo, porque al final de cuentas solo ese se debería arrehlar, y hasta donde se no provoca problemas con que no se encíe la raíz porque esta no cambia de lugar xD, mejor haz los métodos para hacer las rotaciones y luego decides si add el dar el árbol hijo directo de la raíz donde se almacenó el dato, claro si es que no da problemas y no requiere de cambios severos :v xD
    }
    
    @Override
    public NodoDeArbol<E> borrar(Object dato){
        NodoDeArbol<E> nodo = super.borrar(dato);        
        equilibrarArbol(raiz, null);//debe ser raíz porque si envías nodo provocarás un error, puesto que ese ya fue eliminado :v xD
        
        return nodo;
    }
    
    public int hallarFactorDeEquilibrio(NodoDeArbol<E> nodo){
        if(nodo != null){
            return hallarAltura(nodo.darHijoDerecho())-hallarAltura(nodo.darHijoIzquierdo());
        }
        
        System.out.println("El nodo recibido no se encuentra en el árbol\n");//este msje no se mostrará al ser empleado este método en el de equilibrar árbol, por el hecho que se verfica que el nodod que intenta utilizar esto no sea null xD
        return 0;        
    }
    
    public void equilibrarArbol(NodoDeArbol<E> nodo, NodoDeArbol<E> padreDeNodo){//el null nunca se utilizará porque en la raíz al revisar el FE, nunca se encontrará un FE anormal, porque el desequilibrio se habrá arreglado (Si es que eixstiera xD) antes de llegar ahi xD
        if(nodo != null){
            equilibrarArbol(nodo.darHijoDerecho(), nodo);
            equilibrarArbol(nodo.darHijoIzquierdo(), nodo);
            
            int factorDeEquilibro = hallarFactorDeEquilibrio(nodo);
            
            if(Math.abs(factorDeEquilibro) > 1){
                //las condiciones para ejecutar la rotación que corresponde según sea el caso... 
                hallarTipoRotacion(factorDeEquilibro, nodo, padreDeNodo);//el anterior es el padre del desequilibrado...
            }
        }
        //no se si colocar un return provoque que todas las llamadas recursivas previas se cancelen [de alguna manera pienso que puede suceder esto, a pesar de que los métodos de recursión que se han usado siempre han devuelto algo :v xD9
        //o si el no colocarlo provocará que no se regrese bien al método previo (aunque sabes que cuando termina un método le devuelve el mando a aquel quein lo llaamó y por ese motivdo no debería dar problemas :V xD)
    }          
    
    private void hallarTipoRotacion(int factorDeEquilibrio, NodoDeArbol<E> nodo, NodoDeArbol<E> nodoPadre){                
        if(factorDeEquilibrio == -2){
            if(hallarFactorDeEquilibrio(nodo.darHijoIzquierdo()) == -1){//se requiere simple a la derecha...
                rotarSimplementeALaDerecha(nodo, nodoPadre);
            }else{//Es decir que es +1, no puede ser otro número por la naturaleza del problema, pues si fuera 0, entonces no se habría provocado un desequilibrio en el padre de este... son normas xD
                rotarDoblementeALaIzquierda(nodo, nodoPadre);//Si no entras a este lo más seguro es que e lFE debía colocarse solo como + porque no es necesariamente +1, aunque yo pienso que sí, porque se ha estado arreglado el desequilibrio desde la parte infereior del aárbol, por lo cual no tendría que obtenerese un FE mayor a |1| en el hijo correspondiente del desequilibrado actual...
            }            
        }else{//Es decir es igual a +2, no puede ser otro número porque el desequilibrio se arregla cada vez que un nodo lo provoca y de manera obligatoria al usar este método es porque el FE no es normal, es decir no es ni 0, 1 o .1...
            if(hallarFactorDeEquilibrio(nodo.darHijoDerecho()) == 1){//se requiere simple a la izquierda
                rotarSimplementeALaIzquierda(nodo, nodoPadre);
            }else{//es decir que es -1, por el hecho que solo 1 y -1 podrían provocar un desequilibrio...
                rotarDoblementeALaDerecha(nodo, nodoPadre);
            }   
        }
    }//sale mejor que estar haciendo un método para dar true or false por cada tipo de rotación... pues si se tuviera que hacer la última, se tendrían que hacer más llamadas del métood para hallar FE, que en este xD
    
    private void rotarSimplementeALaIzquierda(NodoDeArbol<E> nodoDesequilibrado, NodoDeArbol<E> padreDelDesequilibrado){
        NodoDeArbol<E> nodoAuxiliar = nodoDesequilibrado;//se hace una copia del que pasará a ser hijo xD
        
        nodoDesequilibrado =  nodoDesequilibrado.darHijoDerecho();//se hace el cambio del hijo por el padre para reequilibrar
        nodoAuxiliar.reestablecerHijoDerecho(nodoDesequilibrado.darHijoIzquierdo());//aquí el nodo desequilibrado ya fue reemplazado por su hijo derecho, quien permitirá reequilibrar el árbol xD
        nodoDesequilibrado.reestablecerHijoIzquierdo(nodoAuxiliar);//se establece como hijo izquierdo al que era padre del que ahora ocupa su lugar        
        
        padreDelDesequilibrado.reestablecerHijoDerecho(nodoDesequilibrado);//se hace actualiza el hijo del que era padre del desequilibrado        
    }//funcional (o eso pienso xD)  
    
    private void rotarSimplementeALaDerecha(NodoDeArbol<E> nodoDesequilibrado, NodoDeArbol<E> padreDelDesequilibrado){
        NodoDeArbol<E> nodoAuxiliar = nodoDesequilibrado;//se hace una copia del que pasará a ser hijo xD
        
        nodoDesequilibrado =  nodoDesequilibrado.darHijoIzquierdo();//se hace el cambio del hijo por el padre para reequilibrar
        nodoAuxiliar.reestablecerHijoIzquierdo(nodoDesequilibrado.darHijoDerecho());//aquí el nodo desequilibrado ya fue reemplazado por su hijo derecho, quien permitirá reequilibrar el árbol xD
        nodoDesequilibrado.reestablecerHijoDerecho(nodoAuxiliar);//se establece como hijo izquierdo al que era padre del que ahora ocupa su lugar        
        
        padreDelDesequilibrado.reestablecerHijoIzquierdo(nodoDesequilibrado);//se hace actualiza el hijo del que era padre del desequilibrado
    }//funcional (o eso pienso xD)   
    
    private void rotarDoblementeALaIzquierda(NodoDeArbol<E> nodoDesequilibrado, NodoDeArbol<E> padreDelDesequilibrado){
        rotarSimplementeALaIzquierda(nodoDesequilibrado.darHijoIzquierdo(), nodoDesequilibrado);
        rotarSimplementeALaDerecha(nodoDesequilibrado, padreDelDesequilibrado);
    }//si no funciona es porque las simples no están bien

    private void rotarDoblementeALaDerecha(NodoDeArbol<E> nodoDesequilibrado, NodoDeArbol<E> padreDelDesequilibrado){
        rotarSimplementeALaDerecha(nodoDesequilibrado.darHijoDerecho(), nodoDesequilibrado);//pues se debe arreglar primero el hijo y luego al padre xD
        rotarSimplementeALaIzquierda(nodoDesequilibrado, padreDelDesequilibrado);
    }//si no funciona es porque las simples no están bien
}
