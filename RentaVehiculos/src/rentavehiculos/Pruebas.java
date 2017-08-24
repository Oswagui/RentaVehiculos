/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentavehiculos;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    private static Stage login = null;
    private static Stage submenu = null;
    private static Stage funcionalidad = null;
    private static Stage rentar = null;
    
    public Pruebas(){
        instancia=this;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //stage = ConsultarClienteCreator.consultarClienteCreator();
        //stage=AnyCreator.anyCreator("src/rentavehiculos/screens/clientServices/SubmenuAtencion.fxml");
        //stage=AnyCreator.anyCreator("src/rentavehiculos/screens/vehicles/ConsultarVehiculo.fxml");
        stage = AnyCreator.anyCreator("src/rentavehiculos/screens/login/Login.fxml");
        stage.setTitle("RentaVehiculo 2.0");
        Pruebas.getInstancia().setLogin(stage);
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
        s.setResizable(false);
        s.setMaximized(false);
        s.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("No puede cerrar la ventana asi");
                event.consume();}
        });
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

    public Stage getLogin() {
        return login;
    }

    public void setLogin(Stage login) {
        Pruebas.login = login;
    }

    public Stage getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Stage submenu) {
        Pruebas.submenu = submenu;
    }

    public Stage getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(Stage funcionalidad) {
        Pruebas.funcionalidad = funcionalidad;
    }

    public void setRentar(Stage rentar) {
        Pruebas.rentar = rentar;
    }
    public Stage getRentar() {
        return rentar;
    }

   
}
