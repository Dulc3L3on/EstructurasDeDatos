/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import Listas.ListaDoblementeEnlazada;
import Listas.NodoDoble;
import Árboles.NodoB;

/**
 *
 * @author phily
 */
public class AccionesComplementariasArbolB<E>{
    //deberás revisar si no pierdas la referencia de un objeto, específicamente el de raíz, puesto que debe ser el mismo que este manejando en la clase Árbol B
    private ListaDoblementeEnlazada<NodoB<E>> raiz;
    private int M;
    private Kit herramientas = new Kit();
    private NodoDoble<NodoB<E>> nuevoNodoDobleDelPadre;//puesto que no se requiere hacer una revisión previa (si es !=null) para emplear esta var... xD
   
    
    public AccionesComplementariasArbolB(ListaDoblementeEnlazada<NodoB<E>> laRaiz, int gradoArbol){
        raiz = laRaiz;//recuerda que al hacer esto lo que haces es hacer referencia al contenido del dato que te enviaron, por ello al cb en la clase de arbolB, aquí tb se actualizará...
        M = gradoArbol;
    }
    
    public void dividir(ListaDoblementeEnlazada<NodoB<E>> padre, ListaDoblementeEnlazada<NodoB<E>> actual){//reordenar xD :v
        if(actual.darTamanio() == M){
           NodoB<E> nodoASubir = alistarHijos(actual);
            
           if(padre == null){//lo cual querría decir que la lista actual es la raíz...
               raiz = new ListaDoblementeEnlazada<>();
               raiz.agregarSiguiente(nodoASubir);//puesto que en ese caso, este nodo sería el que sustituiría al listado raíz que se llenó por completo...
           }else{
               NodoDoble<NodoB<E>> nodoAuxiliar = padre.darPrimerElemento();
               
               //con este for se busca la posición en que debería ir el nodo a subir...
               for (int elementoActual = 0; elementoActual < padre.darTamanio(); elementoActual++) {
                   if(herramientas.esMayor(nodoASubir.darElemento(), nodoAuxiliar.obtenerContenido().darElemento())){
                       nodoAuxiliar = nodoAuxiliar.obtenerSiguiente();
                   }else{//deberías poner una restricción para que no se acepten elementos con atribs de identificación iguales
                       //el proceso de hacer que el antiguo anterior actualice su nuevo siguiente y que el nodp nuevoAnterior referencie al anterior y siguiente respectivos y que el siguiente al nuevo actualice su referencia de ndo anterior a este nuevo, se hacce en el método de establecerAnterior de la clase NodoDoble, míralo y te darás cuenta xD
                       nodoAuxiliar.establecerAnterior(nodoASubir);   
                       
                       //se actualiza la referencia de los listado izq y der del siguiente y anterior al nodoSubido
                       nodoAuxiliar.obtenerAnterior().obtenerAnterior().obtenerContenido().reestablecerHijoDerecho(nodoASubir.darHijoIzquierdo());//se actualiza la referencia del hijo der al anterior del nodo agregado...
                       nodoAuxiliar.obtenerContenido().reestablecerHijoIzquierdo(nodoASubir.darHijoDerecho());//Se actualiza la referencia del hijo izquierdo al siguiente del nodo subido (es decir al nodo auxiliar)...
                       
                       break;//para así salir del for...
                   }
               }
           }
            
        }
    }//Funcional
    
    private NodoB<E> alistarHijos(ListaDoblementeEnlazada<NodoB<E>> actual){
        NodoDoble<NodoB<E>> nodoAuxiliar = actual.darPrimerElemento();
        ListaDoblementeEnlazada<NodoB<E>> listadoHijosIzquierdos = new ListaDoblementeEnlazada<>();
        ListaDoblementeEnlazada<NodoB<E>> listadoHijosDerechos = new ListaDoblementeEnlazada<>();
        NodoDoble<NodoB<E>> nodoASubir = null;
            
        for (int elementoActual = 1; elementoActual <= M/2; elementoActual++) {//el igual es por la existencia de los pares, porque quiero que el que se va a subir esté alejado una unidad de la mitad...
            listadoHijosIzquierdos.agregarSiguiente(nodoAuxiliar.obtenerContenido());
            nodoAuxiliar = nodoAuxiliar.obtenerSiguiente();
        }//se establece el listado de hijos Izquierdos
            
        nodoASubir = nodoAuxiliar;//se almacena el nodo a subir               
            
        while((nodoAuxiliar = nodoAuxiliar.obtenerSiguiente())!= null){//se hace la sustitución del nodo por el siguiente y de una vez se corobora si no es null xD
            listadoHijosDerechos.agregarSiguiente(nodoAuxiliar.obtenerContenido());
            nodoAuxiliar = nodoAuxiliar.obtenerSiguiente();
        }//Se establece listado de hijos derechos...
            
        //esta separación del renglón sobrecargado, podría hacerse en un solo for, que tenga que cuando sea <= M/2 se asigne al listado, sino (si es > M/2) que se revise si la var de nodo a subir es == null, que se asigne el nodoASubir sino que se add los hermanos derechos al listado de hijos derechos equivalente :v xD
        
        nodoASubir.obtenerContenido().establecerHijoIzquierdo(listadoHijosIzquierdos);//no habrá problema con perder referencia a posibles hijos anteriores porque es un hecho que, cuando posea hijos antes de ser subidos, la referencia será mantenida por los hermando quienes también poseían referencia de ellos, no la perderán por lo cual al colocar los nuevos hijos que le corresponderán a ese nodo a subir, todo se habrá hecho correctamente (esto sucederá sin importar cuantas veces se suba el mismo nodo [si es que casualmente queda cada vez en el espacio del nodo a subir xD, y también sucederá aunque el nodo siguiente a subir sea otro xd] y cuando no tenga hijos pues no hay preocupación de perder referencia (aunque por lo explicado anteriormente, no se pierde xD), sino que simplemnete se coloca una lista en cada hijo que ahora si teiene elementos xD
        nodoASubir.obtenerContenido().establecerHijoDerecho(listadoHijosDerechos);        
        
        return nodoASubir.obtenerContenido();
    }//Funcional
    
    public void ejecutarCasoEliminacion(NodoDoble<NodoB<E>> nodoAEliminar, ListaDoblementeEnlazada<NodoB<E>> listadoNodoAEliminar,
                NodoDoble<NodoB<E>> padreDelEliminado){
        if(nodoAEliminar.obtenerContenido().poseeHijos()){
            casoEliminacionInterno(nodoAEliminar, padreDelEliminado, listadoNodoAEliminar, nodoAEliminar);
        }else{
            casoEliminacionHoja(nodoAEliminar, listadoNodoAEliminar, padreDelEliminado);
        }
    }//terminado   
    
    /**
     * En este se ejecuta la eliminación cuando el nodo, tiene hijos
     * es decir es un nodo interno.El nodo de sustitución es el que 
       se ha escogido para reemplazar al que se eliminará, la lista
       del nodoSustitución se envía con el fin de que luego de haber
       obtenido el nodo hoja de sustitución, se haga la redistribución
       de cada listado por el que se pasó buscando, si es que llegó a 
       quedar una mala distribución...
     * @param nodoAEliminar
     * @param listadoPadreSustitucion
     * @param listaDeNodoSustitucion
     * @param nodoSustitucion     
     */
    private void casoEliminacionInterno(NodoDoble<NodoB<E>> nodoAEliminar, NodoDoble<NodoB<E>> padreListaNodoSustitucion, ListaDoblementeEnlazada<NodoB<E>> listaDeNodoSustitucion, NodoDoble<NodoB<E>> nodoSustitucion){//en este se hace transferencia hijo a padre
        if(!nodoSustitucion.obtenerContenido().poseeHijos()){//se ejecuta la transferencia de hijo a padre
            nodoSustitucion.obtenerContenido().establecerHijoIzquierdo(nodoAEliminar.obtenerContenido().darHijoIzquierdo());
            nodoSustitucion.obtenerContenido().establecerHijoIzquierdo(nodoAEliminar.obtenerContenido().darHijoDerecho());//puesto que este debe mantener todo lo que el eliminado poseía... por eso se llamó nodo de sustituión xD
            
            nodoAEliminar.reestablecerContenido(nodoSustitucion.obtenerContenido());
            nodoSustitucion.eliminar();//se elimina el nodo que funcionó como sustituto
            
            //no es necesario hacer una actualización en el padre del eleiminado, puesto que este hace referencia al LISTADO no a nodos en particular, y los nodos fueron los que tu cambiaste, aunque hallan cambiado los elementos del listado, el identificador de este sigue siendo el mismo, por ello no se requieren hacer actualizaciones al padre... xD
        }else{//se procede a buscar el nodo que sustituirá al que se eli [obligatoriamente debe ser uno hoja, pirque seno habría problemas con el acarreo de hijos xD]
            if(nodoSustitucion.obtenerContenido().darHijoDerecho().darTamanio()> nodoSustitucion.obtenerContenido().darHijoIzquierdo().darTamanio()){
                casoEliminacionInterno(nodoAEliminar, nodoSustitucion, nodoSustitucion.obtenerContenido().darHijoDerecho(), nodoSustitucion.obtenerContenido().darHijoDerecho().darPrimerElemento());//Recuerda que las reglas indican que en el caso de sustituir con el listado derecho, el nodo de sustiución debería ser el más pequeño de él, es decir el primer ele y viceversa para el caso en que el lsitado izq fuera el de sustitución
            }else{
                casoEliminacionInterno(nodoAEliminar, nodoSustitucion, nodoSustitucion.obtenerContenido().darHijoIzquierdo(), nodoSustitucion.obtenerContenido().darHijoIzquierdo().darUltimoElemento());
            }
        }
        
        //puesto que cada vez que se hace una invocación de este método "casoEliminación" se ha cambiado de nivel entoces estos parámetros si corresponden a los que se deberían enviar al método redistribuir... xD
        redistribuir(padreListaNodoSustitucion, listaDeNodoSustitucion);//También se exe para el listado en el que se hizo la eliminación, en última llamada apilada de este método recursivo (Es decir la primer llamada en espera)
    }//Terminado y Funcional
    
    private void casoEliminacionHoja(NodoDoble<NodoB<E>> nodoAEliminar, ListaDoblementeEnlazada<NodoB<E>> listaNodoAEliminar, NodoDoble<NodoB<E>> padreDelEliminado){               
        nodoAEliminar.eliminar();
        
        redistribuir(padreDelEliminado, listaNodoAEliminar);
    }//Terminado y funcional [si no funciona en todo caso sería la redistribución :v xD]    
    
    //si padre es igual a null, es decir que la lista necesitada sea la raíz, y esta no posee hijos no debería exe ninguno de estos tipos de redsitribución, si tuviera hijos, creo que habría que fusionar los hijos que tiene.. creo xD
    public void redistribuir(NodoDoble<NodoB<E>> padreDelPosibleNecesitado, ListaDoblementeEnlazada<NodoB<E>> posibleListaNecesitada){
        if(posibleListaNecesitada.darTamanio()<M/2 /*&& padreDelNecesitado!= null*/){//si es igual a null, es porque la lista es la raiz, pero podría ser que fuera null y tuviera hijos... fmmm ahí que habría que hacer...
            if(!transferir(padreDelPosibleNecesitado.obtenerContenido(), posibleListaNecesitada)){
                fusionar(padreDelPosibleNecesitado, posibleListaNecesitada);
            }
        }        
    }//puesto que por la revisión que hice, en el caso de Interno, al redistribuir el hijo que prestó o por el que se pasó, puede que no requiera de fusión porque su hermano posee suficiente como para no quedarse escazo... xD
    
    private boolean transferir(NodoB<E> padreDelNecesitado, ListaDoblementeEnlazada<NodoB<E>> listaNecesitada){
        if((padreDelNecesitado.darHijoDerecho().equals(listaNecesitada) && padreDelNecesitado.darHijoIzquierdo().darTamanio() > M/2) || (padreDelNecesitado.darHijoIzquierdo().equals(listaNecesitada) && padreDelNecesitado.darHijoDerecho().darTamanio() > M/2)){//Es decir que se hará si el hijo prestamista no se queda excazo...
            if(padreDelNecesitado.darHijoIzquierdo().darTamanio() > M/2){//es para saber que tipo de tranferencia se debe hacer
                //se hace la transferencia de padre a hijo y de hijo a padre, pero el nodo del hijo prestamista se deberá eliminar, por ello se hace el cambio de hijos antes del cambio de posición de nodos donados                
                transferirAHijoDerechoNecesitado(padreDelNecesitado.darHijoIzquierdo().darUltimoElemento(), padreDelNecesitado, listaNecesitada);
            }else{//quiere decir que el prestamista es el del lado derecho, lo aseguro puesto que al entrar aquí quiere decir que el que prestará será el derecho, es decir el hijo que tiene más de M/2 es este...                
                transferirAHijoIzquierdoNecesitado(padreDelNecesitado.darHijoDerecho().darPrimerElemento(), padreDelNecesitado, listaNecesitada);                                             
            }
            return true;
        }        
        return false;
    }//terminado y hasta donde sé funcional... xD
    
    private void transferirAHijoDerechoNecesitado(NodoDoble<NodoB<E>> nodoPrestadoDelHijo, NodoB<E> padreDelNecesitado, ListaDoblementeEnlazada<NodoB<E>> listaNecesitada){                      
        //se establecen los hijos del antiguo padre como los del nodo que dio el hijo izquierdo prestamista...
        nodoPrestadoDelHijo.obtenerContenido().reestablecerHijoIzquierdo(padreDelNecesitado.darHijoIzquierdo());
        nodoPrestadoDelHijo.obtenerContenido().reestablecerHijoDerecho(padreDelNecesitado.darHijoDerecho());
               
        //se restablece los hijos del padre [si no tienen hijos no habrá problema, es más hacen el favor de hacer de una vez null los hios del padre,es decir esto evita un paso, cuando los hijos del padre del listado necesitado no tienen hijos xD] además recuerad que estas listas nunca son null, entonces lo que estarías haciedno es reemplazar los hijos antiguos del padre, por una lista sin elementos... xD
        padreDelNecesitado.reestablecerHijoDerecho(listaNecesitada.darPrimerElemento().obtenerContenido().darHijoIzquierdo());
        padreDelNecesitado.reestablecerHijoIzquierdo(nodoPrestadoDelHijo.obtenerContenido().darHijoDerecho());//con el hijo izq del nodo prestado por el hijo no hay problema puesto que el hermano de este (por la condi de que debe tener más del mín, siempre tendrá un hermano) tiene la ref a él xD
             
        listaNecesitada.agregarAnterior(padreDelNecesitado);//se agergar el nodo donado dle padre al listado necesitado                
        padreDelNecesitado = nodoPrestadoDelHijo.obtenerContenido();//Se reestablece el lugar del padre necesitado por el nodo donado del hijo izquierdo
               
        //Se elimina el nodo que prestará el hijo prestamista
        nodoPrestadoDelHijo.eliminar();                
    }//terminado
    
    private void transferirAHijoIzquierdoNecesitado(NodoDoble<NodoB<E>> nodoPrestadoDelHijo, NodoB<E> padreDelNecesitado, ListaDoblementeEnlazada<NodoB<E>> listaNecesitada){
        //se establecen los hijos del antiguo padre como los del nodo que dio el hijo derecho prestamista...
        nodoPrestadoDelHijo.obtenerContenido().reestablecerHijoIzquierdo(padreDelNecesitado.darHijoIzquierdo());
        nodoPrestadoDelHijo.obtenerContenido().reestablecerHijoDerecho(padreDelNecesitado.darHijoDerecho());
                
        //se restablece los hijos del padre
        padreDelNecesitado.reestablecerHijoIzquierdo(listaNecesitada.darUltimoElemento().obtenerContenido().darHijoDerecho());
        padreDelNecesitado.reestablecerHijoDerecho(nodoPrestadoDelHijo.obtenerContenido().darHijoIzquierdo());
              
        listaNecesitada.agregarSiguiente(padreDelNecesitado);//se agergar el nodo donado dle padre al listado necesitado                
        padreDelNecesitado = nodoPrestadoDelHijo.obtenerContenido();//Se reestablece el lugar del padre necesitado por el nodo donado del hijo derecho
                
        //Se elimina el nodo que prestará el hijo prestamista
        nodoPrestadoDelHijo.eliminar();                
    }//terminado
    
    private void fusionar(NodoDoble<NodoB<E>> nodoPadreDelNecesitado, ListaDoblementeEnlazada<NodoB<E>> listaNecesitada){
        ListaDoblementeEnlazada<NodoB<E>> listaHermanoIzquierdoAFusionar;
        
        if((listaHermanoIzquierdoAFusionar = fusionarConHijoDerecho(nodoPadreDelNecesitado, listaNecesitada)) != null){
            fusionarConHijoIzquierdo(nodoPadreDelNecesitado, listaNecesitada, listaHermanoIzquierdoAFusionar);
        }    
        
        //se restablecen los hijos del padre (como nodoB) 
        nuevoNodoDobleDelPadre.obtenerContenido().reestablecerHijoIzquierdo(nuevoNodoDobleDelPadre.obtenerAnterior().obtenerContenido().darHijoDerecho());
        nuevoNodoDobleDelPadre.obtenerContenido().reestablecerHijoDerecho(nuevoNodoDobleDelPadre.obtenerSiguiente().obtenerContenido().darHijoIzquierdo());            
        //se elimina al padre del listado de sus antiguos hermanos
        nodoPadreDelNecesitado.eliminar(); 
    }
    
    private ListaDoblementeEnlazada<NodoB<E>> fusionarConHijoDerecho(NodoDoble<NodoB<E>> nodoPadreDelNecesitado, ListaDoblementeEnlazada<NodoB<E>> listaNecesitada){//Es decir que el necesitado es el hijo del LI del padre...
        if(nodoPadreDelNecesitado.obtenerContenido().darHijoIzquierdo().equals(listaNecesitada)){
            ListaDoblementeEnlazada<NodoB<E>> listaHijoDerechoAFusionar = nodoPadreDelNecesitado.obtenerContenido().darHijoDerecho();
            
            //se reestablece hijo der de hermano izq original del padre
            if(nodoPadreDelNecesitado.obtenerAnterior().obtenerContenido()!= null){//puesto que podría no tener hermano xD
                nodoPadreDelNecesitado.obtenerAnterior().obtenerContenido().reestablecerHijoDerecho(listaHijoDerechoAFusionar);                       
            }            
            //Se agrega el padre a la lista donde se hará la fusión
            listaHijoDerechoAFusionar.agregarAnterior(nodoPadreDelNecesitado.obtenerContenido());//Se add antes puesto que ese padre y sus datos con más peq que el padre siguiente y los datos del hijo der
            //Se obtiene el nodoDoble en el que ahora se encuentra almacenado el padre
            nuevoNodoDobleDelPadre = listaHijoDerechoAFusionar.darPrimerElemento();            
            
            //se agrega los elementos del listado necesitado a la lista donde se hará la fusión (hermano der de esta lista nec)
            NodoDoble<NodoB<E>> nodoAuxiliar = listaNecesitada.darUltimoElemento();
            for (int elemento = 0; elemento < listaNecesitada.darTamanio(); elemento++) {
                listaHijoDerechoAFusionar.agregarAnterior(nodoAuxiliar.obtenerContenido());
                nodoAuxiliar = nodoAuxiliar.obtenerAnterior();
            }          
            return null;//por esta condición debes revisar si cabe la posibilidad de que el padre sea null... 
        }
        return nodoPadreDelNecesitado.obtenerContenido().darHijoIzquierdo(); 
    }    
        
    private void fusionarConHijoIzquierdo(NodoDoble<NodoB<E>> nodoPadreDelNecesitado, ListaDoblementeEnlazada<NodoB<E>> listaNecesitada, ListaDoblementeEnlazada<NodoB<E>> hijoIzquierdoAFusionar){//Es decir que el hijo necesitado es el hijo del LD del padre...
        //se reestablece hijo izq de hermano der original del padre
        if(nodoPadreDelNecesitado.obtenerSiguiente().obtenerContenido()!= null){//puesto que podría no tener hermano xD
           nodoPadreDelNecesitado.obtenerSiguiente().obtenerContenido().reestablecerHijoIzquierdo(hijoIzquierdoAFusionar); 
        } 
        //Se agrega el padre a la lista donde se hará la fusión
        hijoIzquierdoAFusionar.agregarSiguiente(nodoPadreDelNecesitado.obtenerContenido());
        //Se obtiene el nodoDoble en el que ahora se encuentra almacenado el padre
        nuevoNodoDobleDelPadre = hijoIzquierdoAFusionar.darUltimoElemento();            
            
        //se agrega los elementos del listado necesitado a la lista donde se hará la fusión (es decir en el hermano izq de esta lista nec)
        NodoDoble<NodoB<E>> nodoAuxiliar = listaNecesitada.darPrimerElemento();
        for (int elemento = 0; elemento < listaNecesitada.darTamanio(); elemento++) {
            hijoIzquierdoAFusionar.agregarSiguiente(nodoAuxiliar.obtenerContenido());
            nodoAuxiliar = nodoAuxiliar.obtenerSiguiente();
        }        
    }      
}
