/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller4;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 *
 * @author adria
 */
public class Lista {

    Children cab;
    Children cola;
    int nChil;

    public Lista() {
        this.cab = null;
        this.cola = null;
        this.nChil = 0;
    }

    public Children getCab() {
        return cab;
    }

    public void setCab(Children cab) {
        this.cab = cab;
    }

    public Children getCola() {
        return cola;
    }

    public void setCola(Children cola) {
        this.cola = cola;
    }

    public int getnChil() {
        return nChil;
    }

    public void setnChil(int nChil) {
        this.nChil = nChil;
    }

    public void addFinal(String id, String name, String sexo, int edad, String grado) {
        if (this.Buscarid(id) != null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error; El id ya existe");
            a.show();
            return;
        }
        if (this.valEdad(edad) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error; El niño no Estan en el Rango de edad");
            a.show();
            return;
        }
        Children n = new Children(id.toUpperCase(), name.toUpperCase(), sexo.toUpperCase(), edad, grado.toUpperCase());
        if (cab == null) {
            cab = n;
            cola = n;
            cola.sig = n;
            this.nChil++;
        } else {
            cola.sig = n;
            n.sig = cab;
            this.cola = n;
            this.nChil++;
        }
    }

    public void addPrin(String id, String name, String sexo, int edad, String grado) {
        Children n = new Children(id.toUpperCase(), name.toUpperCase(), sexo.toUpperCase(), edad, grado.toUpperCase());
        if (this.Buscarid(id) != null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error; El id ya existe");
            a.show();
            return;
        }
        if (this.valEdad(edad) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error; El niño no Estan en el Rango de edad");
            a.show();
            return;
        }
        if (cab == null) {
            cab = n;
            cola = n;
            cola.sig = n;
            this.nChil++;
        } else {
            n.sig = cab;
            cola.sig = n;
            cab = cola.sig;
            this.nChil++;
        }
    }

    public void Eliminar(String id) {
        if (this.Buscarid(id) == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error: el id sumistrado no se encuentra en la base de datos");
            a.show();
            return;
        }
        if(this.nChil==1){
//            var res = new Lista();
            cab=null;
            cola=cab;
            this.nChil--;
            return;
        }
        if (cab.getId().equals(id)) {
            cola.sig = cab.sig;
            cab = cab.sig;
            this.nChil--;
            return;
        }

        var aux = cab;
//       var ant=cola;
        while (!aux.sig.getId().equals(id)) {
            aux = aux.sig;
//            ant=ant.sig;

        }
        var ant = aux;
        var sig = aux.sig.sig;
        ant.sig = sig;
        if (cola.getId().equals(id)) {
            cola = ant;
        }
        this.nChil--;
    }

    public void addDespid(String id, String name, String sexo, int edad, String grado, String idr) {
        if (this.Buscarid(id) != null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error; El id ya existe");
            a.show();
            return;
        }
        if (this.valEdad(edad) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error; El niño no Estan en el Rango de edad");
            a.show();
            return;
        }
        Children n = new Children(id.toUpperCase(), name.toUpperCase(), sexo.toUpperCase(), edad, grado.toUpperCase());
        Children aux = this.Buscarid(idr);

        if (aux != null) {
            Children sig = aux.sig;
            aux.sig = n;
            aux.sig.sig = sig;
            this.nChil++;
        }

    }

    public void Listar() {
        if (this.cab != null) {
            Children aux = cab;
            while (!aux.sig.getId().equals(cab.getId())) {
                System.out.println(aux.getId() + "while");
                aux = aux.sig;
            }
            System.out.println(aux.getId());
        }
    }

    public void guardarRegistro(String info) {
        try {
            PrintWriter writer = new PrintWriter(".\\filename.txt");
            writer.println(info);
//            writer.println("");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Lista LeerDoc() {
        Lista res = new Lista(); // lista auxiliar para recibir datos del documento
        File doc = new File(".\\filename.txt");// llamada del documento,
        try {
            Scanner obj = new Scanner(doc);// leectra del documento
            while (obj.hasNextLine()) { //contador de lineas del documento
                String[] item = obj.nextLine().split("\\s*,\\s*"); // Separa la linea del docuemnte por sus  comas, y las combierte en Arreglos de String
                res.addFinal(item[0], item[1], item[2], Integer.valueOf(item[3]), item[4]); // Asignacion de los elementos del la linea del documento y los agrega a la lista auxiliar.
            }
        } catch (Exception e) {
            
        }
        return res;  // Retorna la lista con los elementos encontrados
    }

    public String InfoLista() {
        String res = "";
        if (cab != null) {
            Children aux = cab;
            while (!aux.sig.getId().equals(cab.getId())) {
                res += aux.getId() + "," + aux.getNombre() + "," + aux.getSexo() + "," + aux.getEdad() + "," + aux.getGrado() + "\n";
                aux = aux.sig;
            }
            res += aux.getId() + "," + aux.getNombre() + "," + aux.getSexo() + "," + aux.getEdad() + "," + aux.getGrado() + "\n";
        }
        return res;
    }

    public Children Buscarid(String id) {

        if (this.nChil != 0) {
            Children aux = cab;

            while (!aux.sig.getId().equals(cab.getId())) {
                if (aux.getId().equals(id)) {
                    return aux;
                }
                aux = aux.sig;
            }
            if (aux.getId().equals(id)) {
                return aux;
            }
        }

        return null;
    }

    public boolean valEdad(int edad) {
        boolean res = false;
        if (edad >= 4 && edad <= 7) {

            res = true;
        }
        return res;
    }

    public Lista[] ListasPorGrado() {
        Lista Jar = new Lista();
        Lista PreJar = new Lista();
        Children au = cab;
        if (this.nChil==0) {
            Lista[] a={Jar,PreJar};
            return a;
        }
        while (!au.sig.getId().equals(cab.getId())) {
            if (au.getGrado().equals("JARDIN")) {
                Jar.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
            }
            if (au.getGrado().equals("PREJARDIN")) {
                PreJar.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
            }
            au = au.sig;
        }
        if (au.getGrado().equals("JARDIN")) {
            Jar.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
        }
        if (au.getGrado().equals("PREJARDIN")) {
            PreJar.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
        }
        Lista[] res = {PreJar,Jar};
        return res;
    }

    public Lista[] ListasPorGenero(Lista lista) {
        Lista m = new Lista();
        Lista h = new Lista();
        Children au = lista.cab;
        if (lista.getnChil()==0) {
            Lista[] a={h,m};
            return a;
        }
        while (!au.sig.getId().equals(cab.getId())) {
            if (au.getSexo().equals("HOMBRE")) {
                h.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
            }
            if (au.getSexo().equals("MUJER")) {
                m.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
            }
            au = au.sig;
        }
        if (au.getSexo().equals("HOMBRE")) {
            h.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
        }
        if (au.getSexo().equals("MUJER")) {
            m.addFinal(au.getId(), au.getNombre(), au.getSexo(), au.getEdad(), au.getGrado());
        }
        Lista[] res = {h, m};
        return res;
    }

    public float[] PromedioEdad(Lista lista) {
        
        float[] res = new float[3];
        var hombres=lista.ListasPorGenero(lista)[0];
        var mujeres=lista.ListasPorGenero(lista)[1];
        int sumaH=0;
        int sumaM=0;
        if(hombres.nChil!=0){
            var aux=hombres.cab;
            while(!aux.sig.getId().equals(hombres.getCab().getId())){
                sumaH+=aux.getEdad();
                aux=aux.sig;
            }
            sumaH+=aux.getEdad();
            res[0]=sumaH/hombres.getnChil();
        }
        if(mujeres.nChil!=0){
            var aux=mujeres.cab;
            while(!aux.sig.getId().equals(mujeres.getCab().getId())){
                sumaM+=aux.getEdad();
                aux=aux.sig;
            }
            sumaM+=aux.getEdad();
            res[1]=sumaM/mujeres.getnChil();
        }
        if(mujeres.nChil!=0 && hombres.nChil!=0){
            res[2]=(sumaH+sumaM)/(hombres.nChil+mujeres.nChil);
        }else{
            if (hombres.nChil!=0) {
                res[2]=res[0];
            }
            if (mujeres.nChil!=0) {
                res[2]=res[1];
            }
        
        }
        return res;
    }
}
