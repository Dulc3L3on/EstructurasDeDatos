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
public class NodoDeArbol<E> {//pienso que en el caso de los nodos si podría existir herencia, puesto que le agregan caracterśticas al Nodo funamental
    //ya no será necesario el identificador, por la manera de comparar que se adoptará xD, la cual admito está genial xD
    //si el elemento llegara a ser un objeto que poseyera este atributo entonces en el lugar donde se debe asignar se emplearía el método para que el objeto de el número directamente y así no haya problemas con esto xD
    //eso si va a resultar algo tedioso si el elemento a guardar es un número, pero sería mehor dejarlo así por la existencia de los obj y que estos tengan designado cierto orden de jerarquí de manera manual
    private E elemento;    
    private NodoDeArbol<E> hijoIzquierdo;
    private NodoDeArbol<E> hijoDerecho;
      
    public NodoDeArbol(E elElemento){
        this(elElemento, null, null);//Es decir que cuando el padre sea null, el nodo que se está inspeccionando será la raíz...
    }
    
    public NodoDeArbol(E elElemento, NodoDeArbol elIzquierdo, NodoDeArbol elDerecho){//coloco al padre para que así se puede hacer "regresiones" como en el caso de las listas enlazadas, pero no se si estas regresiones (a niveles de mayor jerarquí o más arriba, para el caso de los árboles) se logran con la recursividad, según lo que estba pensando, sí es aśi, cuadno estés en el proceso de la búsqueda inténtal        
        elemento = elElemento;
        
        hijoIzquierdo = elIzquierdo;
        hijoDerecho = elDerecho;             
    }
    
    public void establecerHijoDerecho(NodoDeArbol elHijoDerecho){
        hijoDerecho = elHijoDerecho;
    }
    
    public void establecerHijoIzquierdo(NodoDeArbol elHijoIzquierdo){
        hijoIzquierdo = elHijoIzquierdo;
    }
    
   /* public void establecerHijoDerecho(E elElemento, int elIdentificador){
        hijoDerecho = new Nodo<>(elElemento, elIdentificador, null, null, this);               
    }
    
    public void establecerHijoIzquierdo(E elElemento, int elIdentificador){
        hijoIzquierdo = new Nodo<>(elElemento, elIdentificador, null, null, this);               
    }*/
    
    /*Estos métodos para reestableces los cree por la eliminación, puesto que al eli un nodo que contenga hijos, el orden que poseía el árbol cb*/
      
    public void reestablecerHijoIzquierdo(NodoDeArbol nuevoHijoIzquierdo){
        hijoIzquierdo = nuevoHijoIzquierdo;
    }

    public void reestablecerHijoDerecho(NodoDeArbol nuevoHijoDerecho){
        hijoDerecho = nuevoHijoDerecho;
    }    
    
    public void reestablecerElemento(E elElemento){
        elemento = elElemento;
    }
       
    public NodoDeArbol darHijoIzquierdo(){
        return hijoIzquierdo;
    }
    
    public NodoDeArbol darHijoDerecho(){
        return hijoDerecho;
    }
    
    public E darElemento(){
        return elemento;
    }
    
    public int darNumeroHijos(){//Estaba pensando que quizá sería útil para obtener el número total de hijos... xD
        if(hijoDerecho != null && hijoIzquierdo !=null){
            return 2;
        }else if(hijoDerecho == null && hijoIzquierdo == null){
            return 0;
        }
        return 1;
    }
    
    
    //SOLUCIÓN AL RPOBLEMA DE LAS COMPARACIONES, debido a que el contenido de los nodos pueden variar
    
    //en primer lugar se utilizará el concepto, divide y vencerás, específicamente para la sobreescripció
    //del método equals, según las necesidades
    
    //Con esto quiero decir que, en esta clase lo que haré es sobreescribir el método equals para que
    //se emplee el == o el equals xD, dependiendo del tipo de objeto que sea el contenido; es decir que
    //el dato a buscar o que requeira de la utilización del método equals debe ser un parám del tipo obj
    //para así hacer una conversión y conocer el tipo de meodo de comparación a utilizar, tenieno en cuenta
    //el tipo de obj que sea el contenido
        //si el contenido es un número Y el dato es un número -> utilizarás ==
        //si el contenido es un obj o string y el dato es igual que el tipo del contenido -> utilizarás equals
    
        //para el caso de los objetos que tú crees, tendrás que colocar una sobreescripción del método equals
        //[so existe herencia, esto se colocará en el padre y dependiendo del tipo de hijos tendrás que sobreescribirlo en ellos :v]
        //para que ahí se tenga el atrib o atribs y las características a revisar, para saber que atributo 
        //vas a comparar, y así no tener problemas xD, puesto que solo debes devolver true, entonces, esto será posible
    
    //AHora lo que no sé es si dará problema el estar sobreescribiendo tanto el método :v xD, no creo xd
    //porque la llamada de los métodos se hará en contextos diferentes, por ello no habrá pierde xD            
}
