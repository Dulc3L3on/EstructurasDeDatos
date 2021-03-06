/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Árboles;

import Herramientas.Kit;

/**
 *
 * @author phily
 * @param <E>
 */
public class ÁrbolBinario<E> {
    private Kit herramientas = new Kit();    
    //no hice privado el atributo para que pueda ser heredado... no olvides, lo privado no se hereda... es razonable xD, porque solo le pertenece al que lo posee xD
    NodoDeArbol<E> raiz = null;//lo que deberás hacer es colocar el nodo con sus respectivas funciones, NO CAMBIES EL NODO uq creaste, en la parte de eliminación coloca ya sea la condi der !=null o izq == null o cb los contenidos de ese else iff cuando el dato es gual por el del else de abajito y que ese quede con el contenido de ese else if xD
       
    public void insertar(Object dato) {
        NodoDeArbol nuevoNodo = new NodoDeArbol(dato);
        
        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            NodoDeArbol nodoAuxiliar = raiz;
            NodoDeArbol nodoAnterior = null;
            
            while (nodoAuxiliar != null) {//se eexe al menos una vez, por el hecho que la raíz no es null... xD
                nodoAnterior = nodoAuxiliar;
                if (herramientas.esMayor(nodoAuxiliar.darElemento(), nuevoNodo.darElemento())){//aux = objeto Base; nuevoNodo  = objetoAComparar
                    nodoAuxiliar = nodoAuxiliar.darHijoDerecho();
                } else {
                    nodoAuxiliar = nodoAuxiliar.darHijoIzquierdo();
                }
            }
            
            //puesto que solo en el while se sabía si era mayor o menor, se debe hacer de nuevo aquí afuerita, la revisión
            if (herramientas.esMayor(nodoAnterior.darElemento(), nuevoNodo.darElemento())){///no hay problemas con un null pointer porque está el if para cuando la raiz es null... xD
                nodoAnterior.establecerHijoDerecho(nuevoNodo);
            } else {
                nodoAnterior.establecerHijoIzquierdo(nuevoNodo);
            }

        }
    }

    public void inorden() {
        inorden(raiz);
    }

    private void inorden(NodoDeArbol laRaiz) {
        if (laRaiz != null) {
            inorden(laRaiz.darHijoIzquierdo());
            System.out.println(" " + laRaiz.darElemento());
            inorden(laRaiz.darHijoDerecho());
        }
    }

    public void postorden() {
        postorden(raiz);
    }

    private void postorden(NodoDeArbol laRaiz) {
        if (laRaiz != null) {
            postorden(laRaiz.darHijoIzquierdo());
            postorden(laRaiz.darHijoDerecho());
            System.out.println(laRaiz.darElemento() + " ");
        }
    }

    public void preorden() {
        preorden(raiz);
    }

    private void preorden(NodoDeArbol laRaiz) {
        if (laRaiz != null) {
            System.out.println(laRaiz.darElemento() + " ");
            preorden(laRaiz.darHijoIzquierdo());
            preorden(laRaiz.darHijoDerecho());
        }
    }   
    
    public void darAltura(Object dato){
        NodoDeArbol nodo = encontrar(dato);
        if(nodo!=null){            
            System.out.println("El dato se encuentra a "+hallarAltura(nodo)+" de altura");
        }
    }
    
    public int hallarAltura(NodoDeArbol nodo){//no lo cambio a private porque debo utilizarlo en el AVL... xD
        if(nodo ==null){
            return 0;
        }
        
        return 1 + Math.max(hallarAltura(nodo.darHijoIzquierdo()), hallarAltura(nodo.darHijoDerecho()));        
    }

    public NodoDeArbol buscar(Object dato) {//colocqué un retorno de tipo Nodo, para que se pudiera emplear en el método de dar Altura... aunque de los dos qeu dan la altura, el más importante es el que halla la altura... xD
        NodoDeArbol nodo = encontrar(dato);
        if (nodo == null) {
            System.out.println("El dato \"" + dato + "\" NO se encuentra almacenado");//ya sea porquue no hanía nada en el árbol, o de verdad no estaba xD
        } else {
            System.out.println("El datos \"" + dato + "\" SI se encuentra almacenado");
        }
        return nodo;
    }
    
    private NodoDeArbol encontrar(Object dato) {
        return encontrar(raiz, dato);
    }
    
    private NodoDeArbol encontrar(NodoDeArbol laRaiz, Object dato) {//coloco object, puesto que cuando se emplee este método, éste rev¿cibirá como parám el tipo correspondiente... lo cual se hará realidad por medio de la interfaz... xD
        if (laRaiz == null || herramientas.esIgual(laRaiz.darElemento(), dato)) {//aquí se empleará el equals sobreescrito...            
            return raiz;
        }
        if (herramientas.esMayor(laRaiz.darElemento(), dato)) {//para evitar crear otro método, para el caso de z, se usará la equivalencia de raiz < dato, que sería dato > raíz...
            return encontrar(laRaiz.darHijoDerecho(), dato);
        }
        return encontrar(laRaiz.darHijoIzquierdo(), dato);
    }    
    
    public NodoDeArbol borrar(Object dato){
        NodoDeArbol nodoEliminado = eliminar(raiz, dato);
        
        if(nodoEliminado == null){
            System.out.println("El dato \""+ dato +"\" no se encontraba almacenado en el árbol");
        }else{
            System.out.println("El dato \""+ dato +"\" se ha eliminado correctamente");
        }//Solo para que sea más informativo xD
        return nodoEliminado;
    }
    
    private NodoDeArbol eliminar(NodoDeArbol laRaiz, Object dato){//no es necesario estar reestableciendo los hijos que se recorrieron para hacer la eliminación para actualizar si sucedió una modificación, puesto que independientemente de que el método sea o no recursivo, a menos que la var haga referencia específicamente a un estado de un objeto en particular, pues de esa manera no le serían percibidos los cambios que dicho obj pudiera recibir en cualquier línea del método en cuestión o de uno llamado allí en el interior                
        if (laRaiz != null) {                    
            if (herramientas.esMayor(laRaiz.darElemento(), dato)) {
                laRaiz.reestablecerHijoDerecho(eliminar(laRaiz.darHijoDerecho(),dato));
            }else if(herramientas.esMayor(dato, laRaiz.darElemento())){//equivalente a: dato < raiz
                laRaiz.reestablecerHijoIzquierdo(eliminar(laRaiz.darHijoIzquierdo(),dato));
            }else{//Es decir que es igual, se ha encontrado el dato...
                if (laRaiz.darHijoIzquierdo()==null && laRaiz.darHijoDerecho()==null) {
                    laRaiz = null;
                }else if(laRaiz.darHijoDerecho() != null){
                    laRaiz.reestablecerElemento(sucesor(laRaiz));
                    laRaiz.reestablecerHijoDerecho(eliminar(laRaiz.darHijoDerecho(),laRaiz.darElemento()));
                }else{
                    laRaiz.reestablecerElemento(predecesor(laRaiz));
                    laRaiz.reestablecerHijoIzquierdo(eliminar(laRaiz.darHijoIzquierdo(),laRaiz.darElemento()));
                }                                    
            }                                    
        }
        return laRaiz;
    }//se creará un bloque nuevo de eliminar, por así decirlo, cada vez que una llamada encuentre un resultado, [con subloque me refiero a una nueva serie de llamadas recursivas que están en busca de un dato en cuentión, en decir que por cada dato diferente que busquen [a partir de la llamada original] se forma otro nuevo bloque...] y después de dar un resultado para este[es decir el respectivo sustituto, se hace el reemplazo del lado derecho o izquierdo con el nodo que que con el cambio, ocupa el respectivo lugar...            
    
    private E sucesor(NodoDeArbol laRaiz){//QUé tendré que colocar, object o E? dependerá de dónde se empleará este valor... si es para reemplezar, entonces E, si es para comparar, entocnes object... creo xD
        laRaiz = laRaiz.darHijoDerecho();
        while(laRaiz.darHijoIzquierdo() != null){
            laRaiz = laRaiz.darHijoIzquierdo();
        }
        return (E) laRaiz.darElemento();
    }//***OJO*** coloco E, porque el nodo recibe E's y además pienso que si envío Object, depués tendré problemas para tratar al elemento contenido, como se debe...
    
    private E predecesor(NodoDeArbol laRaiz){
        laRaiz = laRaiz.darHijoIzquierdo();
        while(laRaiz.darHijoDerecho() != null){
            laRaiz = laRaiz.darHijoDerecho();
        }
        return (E) laRaiz.darElemento();
    }   
}

//**ACUERDO**
//Cuando un dato se requiera comparar con uno que ya está almacenado [como en el caso de insertar y buscar]
//el tipo de parámetro para este dato será OBJECT, a diferencia de cuando el dato que reciba el método sea para 
//re o establecer, el parámetro y el tipo de return será E xD


//**DEDUCCIÓN**
//bien hubiera podido utilizarse en lugar de los nodos solamente los árboles, pue en lugar de colocar hijoIzq o der sería subArbolIzq o der, aquí estaría de una vez
//la var para el contenido y el identificador, también tendría "raíz" o padre para así hacer los reajustes debido a eliminación, es decir todo lo que tiene la clase
//nodo estaría aquí, además de tener las acciones que se pueden realizar en este tipo de árboles :v xD

//por lo cual con todo eso llego a la conclusión que teniendo el nodo, no debo crear un atrib o estructura tal y como un subárbol, puesto que sería redundante y en vano
//porque el nodo cumple con la función de un subárbol, por lo cual cuando te toque recorrer el árbol, deberás hacerlo utilizando los atribs de los nodos, es decir, si el
//dato es menor que el del nodo actual, entonces te vas a hijo izquierdo sino al derecho, eso quiere decir que el método de búsqueda en lugar de recibir un "subárbol" de-
//bería tener como parámetro un nodo, para que así este método pueda utilizarse en recursividad xD

//Entonces en resumen, o es subárbol o es nodo xD, para orden mejor crear el nodo, pero para  que se mire más impresionante el código xD, usa subárbol como atrib del árbol xD