/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Client.NaturalPerson;
import Client.Client;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author SIMON
 */
public class NaturalPersonJSON implements JSONClient {

    @Override
    public ArrayList<NaturalPerson> readJson() {
        Gson gson = new Gson();//Objeto con el cual se implementara la API Gson

        /* El siguiente fragmento de código muestra la lectura de ficheros en 
        Java, visto en cursos anteriores como técnicas de programación,
        las líneas leídas del arhivo e1.json se almacenan en una cadena de texto 
        llamada json */
        String json = "";

        try (BufferedReader br = new BufferedReader(new FileReader("natural_clients.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                json += line;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /*Una vez leído todo el fichero, se convertirán sus elementos a objetos
        de acuerdo a la coincidencias de atributos entre la clase Cuenta y los
        objetos del JSON, todos irán a un vector compuesto de objetos de la
        clase Cuenta.*/
        NaturalPerson[] clientVector = gson.fromJson(json, NaturalPerson[].class);
        ArrayList<NaturalPerson> clientArrayList = new ArrayList();
        if (clientVector == null) {
            return clientArrayList;
        }
        /*Si el JSON está vacío, el vector no se creará, entonces se devuelve un ArrayList vacío.
        De lo contrario, se toma el vector con los objetos que contiene el JSON y se convierte en un ArrayList.
         */
        clientArrayList.addAll(Arrays.asList(clientVector));
        return clientArrayList;
    }

    @Override
    public void addToJson(Client c) {

        Gson gson = new Gson();//Objeto con el cual se implementara la API Gson
        ArrayList<NaturalPerson> clients = readJson();
        clients.add((NaturalPerson) c);
        /* El cliente a añadir al archivo JSON entra como el parámetro 'c'. Los clientes que ya han sido añadidos al JSON
        están contenidos en 'clients'. Luego, 'c' es agregado a 'clients'.        
         */


 /*Mediante el método toJson(), se convierten los valores de cada objeto 
        del ArrayList 'clients' a formato de texto JSON.*/
        String json = "[\n";
        Client c2;
        for (int i = 0; i < clients.size() - 1; i++) {
            c2 = clients.get(i);
            json += gson.toJson(c2) + ",\n";
        }
        int i = clients.size() - 1;
        c2 = clients.get(i);
        json += gson.toJson(c2);
        json += "\n]";

        /* El siguiente fragmento de código muestra la escritura sobre 
        un fichero desde Java, visto en cursos anteriores como técnicas de 
        programación, se escribirá lo concatenado en la cadena de caracteres 
        json en un archivo llamado archivo_clientes.json*/
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("natural_clients.json"))) {
            bw.write(json);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n" + "Escritura sobre natural_clients.json" + "\n" + json);
    }

    @Override
    public void editJson(Client c) {
        Gson gson = new Gson();//Objeto con el cual se implementara la API Gson
        ArrayList<NaturalPerson> clients = readJson();
        String id = c.getId();
        boolean found = false;
        Client caux = new NaturalPerson();
        for (Client client : clients) {
            if (client.getId().equalsIgnoreCase(id)) {
                caux = client;
                found = true;
            }
        }
        if (found) {
            clients.remove((NaturalPerson) caux);
            clients.add((NaturalPerson) c);
        }


        /*Mediante el método toJson(), se convierten los valores de cada objeto 
        del ArrayList 'clients' a formato de texto JSON.*/
        String json = "[\n";
        Client c2;
        for (int i = 0; i < clients.size() - 1; i++) {
            c2 = clients.get(i);
            json += gson.toJson(c2) + ",\n";
        }
        int i = clients.size() - 1;
        c2 = clients.get(i);
        json += gson.toJson(c2);
        json += "\n]";

        /* El siguiente fragmento de código muestra la escritura sobre 
        un fichero desde Java, visto en cursos anteriores como técnicas de 
        programación, se escribirá lo concatenado en la cadena de caracteres 
        json en un archivo llamado archivo_clientes.json*/
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("natural_clients.json"))) {
            bw.write(json);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n" + "Escritura sobre natural_clients.json" + "\n" + json);
    }

}
