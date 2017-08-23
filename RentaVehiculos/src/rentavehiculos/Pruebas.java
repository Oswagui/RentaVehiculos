/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import rentavehiculos.creators.AnyCreator;
import rentavehiculos.creators.ConsultarClienteCreator;
import rentavehiculos.creators.InfoClienteCreator;
import rentavehiculos.creators.ListarClientesCreator;
import rentavehiculos.creators.LoginCreator;
import rentavehiculos.entities.Cliente;
import rentavehiculos.entities.Proveedor;
import rentavehiculos.entities.Vehiculo;

/**
 *
 * @author Osw
 */
public class Pruebas extends Application {
    
    private static ObservableList<Cliente> listaClientes;
    private static ObservableList<Vehiculo> listaVehiculos;
    private static Pruebas instancia;
    private static Cliente clienteAMostrar;
    private static Vehiculo vehiculoAMostrar;
    
    public Pruebas(){
        instancia=this;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //stage = ConsultarClienteCreator.consultarClienteCreator();
        //stage=AnyCreator.anyCreator("src/rentavehiculos/screens/clientServices/SubmenuAtencion.fxml");
        stage=AnyCreator.anyCreator("src/rentavehiculos/screens/clientServices/ConsultarVehiculo.fxml");
        stage.setTitle("RentaVehiculo 2.0"); 
        stage.show();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //metodo para probar el funcionamiento de conservar datos
    public void mostrarNuevaVentana() throws IOException{
        Stage s=ListarClientesCreator.listarClientesCreator();
        s.setTitle("RentaVehiculo 2.0"); 
        s.show();
        
    }
    
     //metodo para probar el funcionamiento
    public void mostrarNuevaVentana2() throws IOException{
        Stage s=InfoClienteCreator.infoClienteCreator();
        s.setTitle("RentaVehiculo 2.0"); 
        s.show();
    }

    public static Pruebas getInstancia() {
        return instancia;
    }
    
    public void setListaClientes(ObservableList<Cliente> lc){       
        listaClientes=lc;
    }
    
    public ObservableList<Cliente> getListaClientes(){
        return listaClientes;
    }

    public Cliente getClienteAMostrar() {
        return clienteAMostrar;
    }

    public void setClienteAMostrar(Cliente c) {
        clienteAMostrar = c;
    }
    
    public void mostrarAnyVentana(String xmlSource) throws IOException{
        Stage s=AnyCreator.anyCreator(xmlSource);
        s.setTitle("RentaVehiculo 2.0"); 
        s.show();
        
    }
    
    public void setListaVehiculos(ObservableList<Vehiculo> lv){       
        listaVehiculos=lv;
    }
    
    public ObservableList<Vehiculo> getListaVehiculos(){
        return listaVehiculos;
    }

    public Vehiculo getVehiculoAMostrar() {
        return vehiculoAMostrar;
    }

    public void setVehiculoAMostrar(Vehiculo v) {
        vehiculoAMostrar = v;
    }
   
}
