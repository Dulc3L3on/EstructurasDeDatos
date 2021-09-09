/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

/**
 *
 * @author phily
 */
public class Kit {    
    
    //teniendo este método ya será posible hacer las comparaciones del tipo >, ahora de lo que te debes preocupar es de
    //envolver a los tipos primitivos en la envoltura que corresponfa cuando vayas a add, buscar y eliminar... solo que aún 
    //no se como hacerle, porque en todo caso debería hacerse justo en los paréntesis del métood add, eli o buscar, para que 
    //Ahí llege como OBJECT... deplano que será otro método que agregar a este Kit xD
    public boolean esMayor(Object objetoBase, Object objetoAComparar){
    
        if(objetoBase instanceof Double && objetoAComparar instanceof Double){//puse double para inclu ahí de una vez decimales y enteros...
            double numeroAComparar = (Double) objetoAComparar;
            double numeroBase = (Double) objetoBase;//creo que es con mayúscula, ya no me acuerdo :,v
            
            return (numeroAComparar > numeroBase);
        }
        if(objetoBase instanceof Integer && objetoAComparar instanceof Integer){//puse double para inclu ahí de una vez decimales y enteros...
            double numeroAComparar = (Integer) objetoAComparar;
            double numeroBase = (Integer) objetoBase;//creo que es con mayúscula, ya no me acuerdo :,v
            
            return (numeroAComparar > numeroBase);
        }
        if(objetoBase instanceof  Character && objetoAComparar instanceof Character){
            char caracterAComparar = (char) objetoAComparar;
            char caracterBase = (char) objetoBase;
            
            return (caracterAComparar > caracterBase);//recuerda que los char pueden ser tratados como números...
        }if(objetoBase instanceof String && objetoAComparar instanceof String){
            String cadenaAComparar = (String) objetoAComparar;
            String cadenaBase = (String) objetoBase;

            return (cadenaAComparar.compareTo(cadenaBase) == 1);
        }       
        
        return false;
        //***LEEME!!!***
        //Aquí se tendrá que llamar el método esMayor del objeto creado por tí correspondiente, el cual lo que realizará es         
        //revisar que tipo de objeto le enviaron al objetoAComparar, y así enviar a este método que invocará desde su propia clase
        //el objeto que debe comparar y ahora sí el valor del atrib que corresponde al tipo o cumple con los requisitos
        //[digo ahora sí el valorBase, porque al usar el método add, lo que se envía al param de objBase es el obj creado por tí]
        
        //entonces el flujo para un objeto creado por tí sera
        //1. llamada de este método en el método add
        //2. llamar al método mayorQue del obj corresp, por el hecho que no es ninguno de los tipos que se encuentran en los if de arribita xD
        //3. en la clase del obj, comparar que tipo de atrib se quiere comparar y según eso, escoger el valor a enviar como base, de los atrib que posea
        //3.1 usar este método de mayor qué para hacer las comparaciones que se deban según el tipo, puesto que ya se posee la pareja a comparar
        
        //También tendría que poseer un caso para saber si el objetoBase y el objetoAComparar [más que todo por este]
        //son del mismo tipo, puesto que al no ser así [a menos que el base sea un obj "propio"] existiría un error
        //creo que para evitar tener que estar poneindo condis que digan algo así como, si el objBase no es obj "propio"
        //y el obj a comparar no es igual al tipo del base, hay un error, quizá por la extendión de tipos de obj propios que
        //pueden existir, debería manejarse con una EXCEPCIÓN...
        
    }
    
    public boolean esIgual(Object objetoBase, Object objetoAComparar){    
        if(objetoBase instanceof Double && objetoAComparar instanceof Double){//puse double para inclu ahí de una vez decimales y enteros...
            double numeroAComparar = (Double) objetoAComparar;
            double numeroBase = (Double) objetoBase;//creo que es con mayúscula, ya no me acuerdo :,v
            
            return (numeroAComparar == numeroBase);
        }if(objetoBase instanceof  Character && objetoAComparar instanceof Character){
            char caracterAComparar = (char) objetoAComparar;
            char caracterBase = (char) objetoBase;
            
            return (caracterAComparar == caracterBase);//recuerda que los char pueden ser tratados como números...
        }if(objetoBase instanceof String && objetoAComparar instanceof String){
            String cadenaAComparar = (String) objetoAComparar;
            String cadenaBase = (String) objetoBase;

            return (cadenaAComparar.compareTo(cadenaBase) ==0);
        }       
        
        return false;//lamas el método esIgual de cualquiera de los objetos propios que existan, pueso que cada uno tendría su propia vrs, de no tener nada en común con otro... xD
        
        //***LEEME***
        //Ni siquiera deberás sobreescribir el método equasl :v, puesto que podría ser más de un atributo que deberías comparar
        //entonces igual que en el caso del mayor qué, determinarías el tipo para así saber a que atrib del obj guardado se refieren
        //y luego mandarías a llamar el método de esIgual de esta clase, para hacer la comparación como corresponde, es decir
        //debes [o puedes xD] ejecutar el mismo procedimiento que para el igual que... entonces lo de la sobreescripción dle equals
        //es más apto cuando es un solo atributo el posible a comparar y/o no se tenga que usar en un lado donde pueda haber más de 
        //un tipo de objeto, es decir que si fuera fijo el hecho que solo un tipo de obj puede estar como "elemento" resultaría mejor
        //hacer la sobreescripción del método equals aunque se tuviera que escoger el atrib a xD
        //pues aunque sobreescribieras el método equals, ahí tendrías que mandar a llamar el igualQue de aquí para que se pudiera hacer
        //la comparación según el tipo, y además tendrías que sobreescribir el método hashcode :v, en cambio si creas otro igualQUe en el obj
        //te ahorras el paso de sobreescribir el hasCode xD
    }
    
    /**
     * OJO: Este método fue creado para ser empleado por las clases de objetos creadas por tí
     * de tal manera que puedan saber que tipo de dato le enviaron y asó pueda dar el 
     * valor base [que provendrá de uno de sus atrib] y así proceder a llamar al método
     * esMayor o esIgual y hacer la respectiva comparación xD
     * esMayor de aquí xD
     * @param elObjeto
     * @return
     */
    public String darTipoInstancia(Object elObjeto){
        if(elObjeto instanceof Double){
            return "Double";
        }if(elObjeto instanceof Character){
            return "Character";
        }if(elObjeto instanceof  Boolean){
            return "Boolean";
        }if(elObjeto instanceof String){
            return "String";
        }
        
        return "Objeto";//puesto que podría tener atribs de objetos propios...
    }
    //NOTA: NO será necesario tener un método para envolver a los tipos primitivos, puesto que se envuelven automáticamente :,)
  
}
