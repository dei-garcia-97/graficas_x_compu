package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 * Recordemos que un scene graph se compone de dos tipos de objetos que heredan 
 * de la clase Spatial, estos son: Node y Geometry, donde los primeros no se pueden visualizar.
 * Los segundos contienen toda la información para que el motor pueda mostrar el objeto en la 
 * pantalla: información del modelo(vértices y aristas), material.
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        /**
         * Los objetos Node podrías imaginarlos como cajas que contendrán otras cajas o modelos (esferas, 
         * personaje, etc.). Al igual que si mueves una caja, se mueve todo lo de su interior, si rotas 
         * o trasladas un Node, aplica lo mismo a los objetos que contiene. 
         **/
        Node pivot = new Node("nodo_pivote");      
        Node pivot1 =new Node("nodo_pivote1");     /**Privote para el nodo de Marte**/
        Node pivot2 =new Node("nodo_pivote2");     /**Privote para el nodo de Jupiter**/
        Node nodeSol = new Node("nodo_sol");
        Node nodeTierra = new Node("nodo_tierra");
        Node nodeMarte = new Node("nodo_marte");    /**Nodo de Marte**/
        Node nodeJupiter = new Node("nodo_jupiter");/**Nodo de Jupiter**/
        /**
         * Para poder visualizar un objeto en jMonkey se necesita definir un objeto Geometry, 
         * el cual requiere los vértices y aristas del objeto tridimensional, así como el 
         * material de este.
         */
        Sphere sol = new Sphere(30, 30, 2f);
        Geometry geomSol = new Geometry("Sol", sol);
        /**
         * Ya que utilizaremos una figura geométrica básica, la “esfera”, el motor proporción 
         * una clase la cual sólo debemos revisar en el API, para identificar cada parámetro de 
         * su constructor. En este caso, para la clase Sphere(int zSample, int radialSamples, 
         * float radius), zSample – El número de muestras a lo largo del eje z, 
         * radialSample -  El número de muestras a lo largo del radial, radius – El radio de la esfera.
         * 
         **/
        Sphere tierra = new Sphere(30, 30, 1f);
        Geometry geomTierra = new Geometry("Tierra", tierra);
        Sphere luna = new Sphere(30, 30, 0.5f);
        Geometry geomLuna = new Geometry("Luna", luna);
        Sphere marte = new Sphere(30, 30, 0.7f);           /**Creación de la esfera de Marte**/
        Geometry geomMarte = new Geometry("Marte", marte); 
        Sphere jupiter = new Sphere(30, 30, 0.8f);         /**Creación de la esfera de Jupiter**/
        Geometry geomJupiter = new Geometry("Jupiter", jupiter);
        
        /**
         * Por el momento utilizaremos materiales que en si son simples colores.  
         * Mas adelante tendrán otra apariencia.
         **/
        Material matY = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matY.setColor("Color", ColorRGBA.Yellow);
        geomSol.setMaterial(matY);
        
        Material matB = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matB.setColor("Color", ColorRGBA.Blue);        
        geomTierra.setMaterial(matB);
        
         Material matM = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matM.setColor("Color", ColorRGBA.Red); /**Asignacón de color para Marte**/
        geomMarte.setMaterial(matM);
        
        Material matJ = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matJ.setColor("Color", ColorRGBA.Gray); /**Asignacón de color para Jupiter**/
        geomJupiter.setMaterial(matJ);
        
        Material matW = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matW.setColor("Color", ColorRGBA.White);
        geomLuna.setMaterial(matW);
        
        geomLuna.setLocalTranslation(1.7f, 0f, 0f);
        
       
        
        /**
         * Los Nodes son un medio para agrupar otros nodos y/o objetos del tipo Geometry. Además, 
         * se utilizan comúnmente para aplicar transformaciones a los spatials que agrupan.
         * 
         * A través del comando “attachChild( Geometry)” estaremos organizando lo que cada 
         * Node tendrá, esto lo hacemos de acuerdo a una idea que definimos previamente, la 
         * cual nos permitirá generar la ilusión de rotación en forma de orbita para cada uno de 
         * nuestros planetas
         */
        nodeTierra.attachChild(geomTierra);
        nodeTierra.attachChild(geomLuna);
        
        
        nodeSol.attachChild(geomSol);
        
        nodeMarte.attachChild(geomMarte);
        
        nodeJupiter.attachChild(geomJupiter);
        
        nodeTierra.setLocalTranslation(4f, 0, 0f);
        
        nodeMarte.setLocalTranslation(7f, 0, 0f);  /**Rotación para Marte**/
        
        nodeJupiter.setLocalTranslation(8f, 0, 0f); /**Rotación para Jupiter**/
        
        
        pivot.attachChild(nodeSol);
        pivot.attachChild(nodeTierra);
        pivot1.attachChild(nodeMarte); /**Agrupación del nodoMarte a su pivote**/
        pivot2.attachChild(nodeJupiter);/**Agrupación del nodoJupiter a su pivote**/
        
        
        
        /**
         * Recuerda, para que se pueda visualizar algún Spatial(Node o Geometry) en el escenario, 
         * ecesita estar adjuntado al “rootNode”. Además, ya que se genera un relación de padre – hijo, 
         * si agregas al padre por lo tanto también agregas el hijo, regresando a la visualización de cajas, 
         * si agregas una caja, por transitividad, también lo que este adentro estará agregado. 
        **/
        rootNode.attachChild(pivot);
        rootNode.attachChild(pivot1); /**Visualización del pivot1 y por lo tanto también del nodoMarte, además se rota junto con el pivote**/
        rootNode.attachChild(pivot2);  /**Visualización del pivot2 y por lo tanto también del nodoJupiter, además se rota junto con el pivote**/
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        /**
         * Time per frames = tpf
         * tpf Indica el tiempo que toma al motor genera un frame(imagen renderizada en la pantalla) 
         * antes de entrar nuevamente al metodo simpleUpdate para generar un nuevo frame.
         * Esto indica que si utilizamos esta variable para determinar la cantidad de movimiento en 
         * cada frame, este movimiento será más o menos rápido, dependiendo de la máquina que se utilice, 
         * pero por el momento podremos utilizar esta variable para actualizar la rotación de cada objeto 
         * en cada ciclo de la función simpleUpdate
        **/
        rootNode.getChild("nodo_tierra").rotate(0,tpf, 0);
        // tpf/3 indica que se movera un tercion de la velocidad que lo haga el nodo tierra
        rootNode.getChild("nodo_pivote").rotate(0,tpf/3, 0);
        
        rootNode.getChild("nodo_marte").rotate(0,tpf, 0);
        rootNode.getChild("nodo_pivote1").rotate(0,tpf*2, 0); /** Velocidad en la que se va a desplazar**/
        rootNode.getChild("nodo_jupiter").rotate(0,tpf, 0);
        rootNode.getChild("nodo_pivote2").rotate(0,tpf/2, 0); /** Velocidad en la que se va a desplazar**/
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}