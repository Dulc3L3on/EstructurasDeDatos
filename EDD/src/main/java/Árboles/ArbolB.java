/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Árboles;

import Herramientas.AccionesComplementariasArbolB;
import Herramientas.Kit;
import Listas.ListaDoblementeEnlazada;
import Listas.NodoDoble;

/**
 *
 * @author phily
 */


//DEBES REVISAR EL MÉTODO DE BUSQUEDA, EL PROBELMA QUE TE PARECIÓ EXISTIR EN EL MÉTODO INSERTAR Y revisar nuevamente el método para dividir... y AGERGAR EL MÉTODO PARA ELIMINAR :V XD
    //MEJOR mira como lo programaron otros, pero antes cómo es en realidad que se comporta un árbl B, en la página de Trees, para resolver tu duda de insertar y así aclarar si has estado pensando como debe ser las axn de este árbol o no... mira repos! xD
public class ArbolB<E> {//no heredará de binario porque cambia bastante, pero el tipo de nodo que utiliza ese árbol sí xD
    private Kit herramientas = new Kit();
    private AccionesComplementariasArbolB accionesComplementarias;
    //si los árboles B+ y B* llegas a hacerlos, probablemente tengas que dejar la raíz public porque opdrían heredar de ellos...
    ListaDoblementeEnlazada<NodoB<E>> raiz = new ListaDoblementeEnlazada();    
    int M;//puesto que vas a hacer que hereden esto los árboles B+ Y B*...
    
    public ArbolB(int numeroElementos, Object elementoNuevo){
        M = numeroElementos;
        insertar(elementoNuevo);
        
        accionesComplementarias = new AccionesComplementariasArbolB(raiz, M);
    }
    
    public void insertar(Object elemento){
        //Esta condición también deberás addla al método público de buscar...
        if(raiz!=null){//no puse listaActual puesto que cuando ya no haya otro mayor que él (porue ya se acabaron los nodos) lo que sucederá es que se topará con el caso en el que no tiene hermano ni listado, por lo cual procederá a asignar el nodo como hermano...
            insertar(null, raiz, raiz.darPrimerElemento(), new NodoB(elemento));
        }else{
            raiz.agregarSiguiente(new NodoB(elemento));
        }        
    }    
    
    private void insertar(ListaDoblementeEnlazada<NodoB<E>> listaPadre, ListaDoblementeEnlazada<NodoB<E>> listaActual, NodoDoble<NodoB<E>> actual, NodoB<E> nuevo){
        NodoB<E> nodoBActual = (NodoB<E>) actual.obtenerContenido();
        
        if(herramientas.esMayor(nodoBActual.darElemento(), nuevo.darElemento())){
            if(actual.obtenerSiguiente() != null){
                insertar(listaPadre, listaActual, actual.obtenerSiguiente(), nuevo);
            }else if(!nodoBActual.darHijoDerecho().estaVacia()){//puesto que siempre se le enviará el elemento, para que posteriormente se le add elementos a sus listado shijos, entonces nunca esos listados serán null, la única forma para que los listados sean null, es haciendolos null con el cnstrc que recibe los lostados hijos, lo cual creo posos probable, más por el heho que yo soy quine escoge que método usar, y no creo que necesite emplear ese constrct :v xD
                //Si te da null pinter solo colocas != null y listo :v xD
                insertar(listaActual, nodoBActual.darHijoDerecho(), nodoBActual.darHijoDerecho().darPrimerElemento(), nuevo);
            }else{
                actual.establecerSiguiente(nuevo);//se le establece siguiente al nodoActual que es de tipo NodoDoble, por el hecho que la parte que crecerá es el listado, por lo cual, se añadirán hermando al listado de hijos hoja correspondientes...
                                                  //al hijoIzq o der (es decir el listado de hijos Izq o der) solo se le puede establecer pero el listado nuevo, y cuando se quiera add elementos, se debe hacer la axn directamente con el listado, no con el nodoB...
            }                       
        }else if(!nodoBActual.darHijoIzquierdo().estaVacia()){
            insertar(listaActual, nodoBActual.darHijoIzquierdo(), nodoBActual.darHijoIzquierdo().darPrimerElemento(), nuevo);
        }else{
            actual.establecerAnterior(nuevo);
        }
        
        accionesComplementarias.dividir(listaPadre, listaActual);
    }//el buscar en los hijos (o listados hijos, evita que se inserte en listados que no corresponden a nodos hoja...
    //aunque ahora que lo pienso en realidad no asegura que no se inserte en estos listado que no son nodos hoja... porque puede que justamente en el que no hallaste hijos esté niveles arriba de los hojas, pero por no encontrarllos pensaste que era hoja... debes revisar si de verdad no se puede add en listados No hoja, y si es así busca cómo se debería insertar si sucederiera el caso que de los listado que posee cad nodo no hoja (que si corresponden a nodos hoja) no cumple el requisito para entrar en ellos, pero si para estar a la par de los elementos del nodo no hoja, qué se debería hacer, se cometió un error o que?...
    
    //Funcional, solo hay que revisar si sí se puede add hermandos cuando el listado hoja del subárbol actual no posee hermanos ni hijos en el lado correspondiente...
    //SI está bien, por la naturaleza del comportamiento del árbol [aunque se hagan eliminaciones, supongo xD] no podrán haber desniveles, es decir cuando se esté llenando todos los niveles más arriba tendrán la misma cantidad de nodos hjos, de tal manera que cuando se inserte según sea > o < siempre se hará en el mismo nivel... es decir en el nodo hoja xD, entonces si no hya que add nada más a las condis, porque van conforme a la naturaleza del árbol, es decir que siempre que no hayan hijos ni más hermanos, cuando se inserte el dato como hermano, se estará haciendo esto a un nodo hoja, oo cual es lo que se solicita en esta axn xD
    //y la "naturaleza del árbol" la permite la axn de dividir puesto que al momento de agregar el nuevo hujo que surge a partir de la división, este se insertar al padre y por el hecho de poseer hijos [los que anteriormente fueron sus hermanos] todo sigue quedando nivelado, aún si el padre se sobrecargara, nueamente tendría hijos [que formarían otro nivel si este padre fuera la raiz o pasarían a otro de arriba si no fuera la raíz] y por ellos todo quedaría nivelado xD
        
    private Object buscar(Object elemento){
        Object elementoHallado= null;
        
        if(raiz !=null){
            elementoHallado = (E) buscar(elemento, raiz, raiz.darPrimerElemento());
        }else{
            System.out.println("\n**Arbol vacío nada que buscar**\n");
        }
        
        return elementoHallado;
    }
    
    public E buscar(Object elemento, ListaDoblementeEnlazada<NodoB<E>> listaActual, NodoDoble<NodoB<E>> actual){//no veo la necesidad de incluir al padre, pues solo se requiere revisar en el listado actual [que varía según la situación del valor]... por eso es que no lo haz estado usando :v xD
        NodoB<E> nodoBActual = (NodoB<E>) actual.obtenerContenido();
        E elementoBuscado;
        
        if(herramientas.esIgual(nodoBActual.darElemento(), elemento)){//se supone que aquí el elemento ya tendría que estar en el mismo tipo con el cual se guardó el dato en el árbol... xD
            return nodoBActual.darElemento();
            
        }else if(herramientas.esMayor(nodoBActual.darElemento(), elemento)){
            if(actual.obtenerSiguiente() != null){
                elementoBuscado = buscar(elemento, listaActual, actual.obtenerSiguiente());
            }else if(!nodoBActual.darHijoDerecho().estaVacia()){//puesto que siempre se le enviará el elemento, para que posteriormente se le add elementos a sus listado shijos, entonces nunca esos listados serán null, la única forma para que los listados sean null, es haciendolos null con el cnstrc que recibe los lostados hijos, lo cual creo posos probable, más por el heho que yo soy quine escoge que método usar, y no creo que necesite emplear ese constrct :v xD
                //Si te da null pinter solo colocas != null y listo :v xD
                elementoBuscado = (E) buscar(elemento, nodoBActual.darHijoDerecho(), nodoBActual.darHijoDerecho().darPrimerElemento());
            }else{
                System.out.println("El elemento no se encuentra almacenado en el arbol");
                return null;
            }                       
        }else if(!nodoBActual.darHijoIzquierdo().estaVacia()){
            elementoBuscado = (E) buscar(elemento, nodoBActual.darHijoIzquierdo(), nodoBActual.darHijoIzquierdo().darPrimerElemento());
        }else{
            System.out.println("El elemento no se encuentra almacenado en el arbol");
            return null;
        }
        
        return elementoBuscado;
    }
    
    public Object eliminar(Object elementoAEliminar){
        Object elementoEliminado = null;
        
        if(raiz!=null){
            elementoEliminado = eliminar(null, raiz, raiz.darPrimerElemento(), elementoAEliminar);
        }else{
            System.out.println("\n**Árbol vacío nada que eliminar**\n");
        }
        return elementoEliminado;
    }
    
    private NodoDoble<NodoB<E>> eliminar(NodoDoble<NodoB<E>> padre, ListaDoblementeEnlazada<NodoB<E>> listaActual, NodoDoble<NodoB<E>> actual, Object elementoAEliminar){//dejé al padre como nodoDOble, puesto que en la fusión que es una forma de redistribuir, necesito obtener los hermanos del padre, y la única manera de hacer esto es trabajando con él como nodoDoble... xD
        NodoB<E> nodoBActual = actual.obtenerContenido();
        NodoDoble<NodoB<E>> nodoAEliminar = null;//no creo que de problemas con el contenido que devolverán las otras llamadas... revisa xD si no da problemas entonces haz lo mismo en buscar, así te ahorrarías unas líneas xD
        
        if(herramientas.esIgual(nodoBActual.darElemento(), elementoAEliminar)){//se supone que aquí el elemento ya tendría que estar en el mismo tipo con el cual se guardó el dato en el árbol... xD
            accionesComplementarias.ejecutarCasoEliminacion(actual, listaActual, padre);                       
            //por el hecho que el método de eliminación es recursivo, se encarga de redistribuir a la lista con la cual empezó, es decir en donde se hizo la eliminación, por lo cual al regresar aquí el mando de exe no 
            //se va llamar al método de redistrib en este if donde revisa si es igual, porqu eesto ya se habrá hecho en la llamada de distribución que tiene el caso de eliminación, y por le hecho que cuando el listado
            //en el que se está revisando tenga hermanos, tampoco se hará puesto que el padre y la lista de revisión actual de esa llamada recursi en cuestión ser'inguales... entonces para evitar l aérdida de tiempo,
            //solo se mandará a llamar cuando ya no hayan hermanos por la razón que la revisión se encontrará en un nivel diferente al que ya se ha revisado xD
            
            return actual;            
        }else if(herramientas.esMayor(nodoBActual.darElemento(), elementoAEliminar)){
            if(actual.obtenerSiguiente() != null){
                nodoAEliminar = eliminar(padre, listaActual, actual.obtenerSiguiente(), elementoAEliminar);
            }else if(!nodoBActual.darHijoDerecho().estaVacia()){//puesto que siempre se le enviará el elemento, para que posteriormente se le add elementos a sus listado shijos, entonces nunca esos listados serán null, la única forma para que los listados sean null, es haciendolos null con el cnstrc que recibe los lostados hijos, lo cual creo posos probable, más por el heho que yo soy quine escoge que método usar, y no creo que necesite emplear ese constrct :v xD
                //Si te da null pinter solo colocas != null y listo :v xD
                nodoAEliminar = eliminar(actual, nodoBActual.darHijoDerecho(), nodoBActual.darHijoDerecho().darPrimerElemento(), elementoAEliminar);
                accionesComplementarias.redistribuir(padre, listaActual);//puesto que al entrar en este if, es porque se cambiará de nivel... por lo cual el parám padre será diferente al de la llamaada recursi que entró en los if cuando aún hay hermanos... xD
            }else{
                System.out.println("\nEl elemento no se encuentra almacenado en el arbol\n");        
            }                       
        }else if(!nodoBActual.darHijoIzquierdo().estaVacia()){
            nodoAEliminar = eliminar(actual, nodoBActual.darHijoIzquierdo(), nodoBActual.darHijoIzquierdo().darPrimerElemento(), elementoAEliminar);
            accionesComplementarias.redistribuir(padre, listaActual);//solo en el caos en que no se haya enontrado algo, estas llamadas serán en vano, creo que quizá debería colocar unnull porque sale ás fácil que apilar un bloque d emétodo de otra parte, lo cual sucedería al invocar en vano(En el caso que no se halla encontrado nada)  a este método de redsitribución xD
        }else{
            System.out.println("El elemento no se encuentra almacenado en el arbol");
            return null;
        }
        
        return nodoAEliminar;
    }//las redistribuciones hechas directamente en este método es para arreglar posibles problemas creados en niveles más arriba, por una fusión o transferencia realizada...
}

//NOTA: Creo que sería mejor tener un método que tenga el if para ver si la raíz es null y que mande a llamar a cualquiera de los métodos fundamentales, si el árbol tiene contenido...
