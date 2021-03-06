package com.cursos.vista.login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cursos.archivo.Archivo;
import com.cursos.dao.Base;
import com.cursos.dao.Consultas;
import demo.data.pojo.Alumnos;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author TapiaSoftware
 */
public class eliminarAlumno extends GenericForwardComposer {
// @Inject

    Consultas consu = new Consultas();
    Base dao = new Base();
    Textbox cedula, nombre, apellido, telefono, direccion, correo;

    Combobox combo;
    Button cerrar, grabar;
    String codigo = "";
    int rep = 0;
    Integer op = 0;
    String mensaje = "";
    ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
    ArrayList<String> validar = new ArrayList<String>();

    String id_alumno;


    @Wire  // anotacion que apunta a la ventana
    Window venprofesor;

    public void onCreate$venprofesor() {
        init();

    }

    public void init() {
        String clav = Archivo.leerArchivo("curso");
        id_alumno = clav;
        lista = consu.ConsultaEstudiante("cursos", "select * from alumnos where id_alumno='" + clav + "' and estado='" + "ACTIVO" + "'");
        System.out.println(lista.size() + " VEREMOS   select * from profesor where id_profesor=" + clav);
        cedula.setText(lista.get(0).getCedula());
        nombre.setText(lista.get(0).getNombre());
        apellido.setText(lista.get(0).getApellido());
        telefono.setText(lista.get(0).getTelefono());
        direccion.setText(lista.get(0).getDireccion());
        correo.setText(lista.get(0).getCorreo());
    }

    public void onChange$nombre() {
        nombre.setText(nombre.getText().toUpperCase());
    }

    public void onChange$apellido() {
        apellido.setText(apellido.getText().toUpperCase());
    }

    public void onChange$direccion() {
        direccion.setText(direccion.getText().toUpperCase());
    }

    public void inHabilitar(boolean valor) {
        nombre.setDisabled(valor);
        apellido.setDisabled(valor);
        telefono.setDisabled(valor);
        direccion.setDisabled(valor);
        correo.setDisabled(valor);;
    }

    //////////////////////
    public void onClick$cerrar() {
        venprofesor.detach();
    }

    public void onClick$grabar() {
        delete();

    }

    public void delete() {

        try {
            String camp = "nombre,apellido,telefono,direccion,estado";
            String val = "'" + nombre.getText() + "','" + apellido.getText()
                    + "','" + telefono.getText() + "','" + direccion.getText() + "','" + "INACTIVO" + "'";
            dao.Editar("cursos", camp, val, "alumnos", "id_alumno", id_alumno);
        } catch (Exception ex) {
            Logger.getLogger(editProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }

        grabar.setDisabled(true);
    }

}
