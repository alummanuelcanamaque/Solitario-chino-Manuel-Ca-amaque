

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaña
 */
public class ListaMovimientos {
    private ArrayList<Movimiento> listaMovimientos =  new ArrayList();
    private Movimiento movimiento;
    
    
    public void crearXML() {
        if(listaMovimientos.size()>0){
            try {
                DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
                DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
                Document documento = creadorDocumento.newDocument();
                Element elementoRaiz = documento.createElement("MOVIMIENTOS");
                documento.appendChild(elementoRaiz);

                for (int i = 0; i < listaMovimientos.size(); i++) {
                    Element elementoMovimiento = documento.createElement("MOVIMIENTO");
                    elementoRaiz.appendChild(elementoMovimiento);
                    
                    //Numero de movimiento
                    Element elementoNumMovimiento = documento.createElement("NUMEROMOVIMIENTO");
                    elementoMovimiento.appendChild(elementoNumMovimiento);
                    Text textoNumMovimiento = documento.createTextNode(""+(i+1));
                    elementoNumMovimiento.appendChild(textoNumMovimiento);

                    //Origen X
                    Element elementoOrigenX = documento.createElement("X_ORIGEN");
                    elementoMovimiento.appendChild(elementoOrigenX);
                    Text textoOrigenX = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getOrigenX()));
                    elementoOrigenX.appendChild(textoOrigenX);
                    

                    //Origen Y
                    Element elementoOrigenY = documento.createElement("Y_ORIGEN");
                    elementoMovimiento.appendChild(elementoOrigenY);
                    Text textoOrigenY = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getOrigenY()));
                    elementoOrigenY.appendChild(textoOrigenY);
                    

                    //Destino X
                    Element elementoDestinoX = documento.createElement("X_DESTINO");
                    elementoMovimiento.appendChild(elementoDestinoX);
                    Text textoDestinoX = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getDestinoX()));
                    elementoDestinoX.appendChild(textoDestinoX);

                    //Destino Y
                    Element elementoDestinoY = documento.createElement("Y_DESTINO");
                    elementoMovimiento.appendChild(elementoDestinoY);
                    Text textoDestinoY = documento.createTextNode(String.valueOf(listaMovimientos.get(i).getDestinoY()));
                    elementoDestinoY.appendChild(textoDestinoY);

                }

                //Generar el tranformador para obtener el documento XML en un fichero
                TransformerFactory fábricaTransformador = TransformerFactory.newInstance();
                Transformer transformador = fábricaTransformador.newTransformer();
                //Insertar saltos de línea al final de cada línea
                transformador.setOutputProperty(OutputKeys.INDENT, "yes");
                //Añadir 3 espacios delante, en función del nivel de cada nodo
                transformador.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
                Source origen = new DOMSource(documento);
                Result destino = new StreamResult("movimientos.xml");
                transformador.transform(origen, destino);

            } catch (ParserConfigurationException ex) {
                System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
                ex.printStackTrace();
            } catch (TransformerConfigurationException ex) {
                System.out.println("ERROR: No se ha podido crear el transformador del documento XML\n"+ex.getMessage());
                ex.printStackTrace();
            } catch (TransformerException ex) {
                System.out.println("ERROR: No se ha podido crear la salida del documento XML\n"+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    
    public void añadirMovimiento(int origenY, int origenX, int destinoY, int destinoX){
        movimiento = new Movimiento(origenY, origenX, destinoY, destinoX);
        listaMovimientos.add(movimiento);
    }
    
    public  Movimiento obtenerUltimoMovimiento(){        
        return listaMovimientos.get(listaMovimientos.size()-1);        
    }
    
    public boolean comprobarVacio(){
        boolean vacio=true;
        if(!listaMovimientos.isEmpty()){
            vacio=false;
        }
        return vacio;
    }
    public void borrarTodo(){
        listaMovimientos.clear();
    }

    public void borrarUltimoMovimiento(){
        listaMovimientos.remove(listaMovimientos.size()-1);
    }
}
