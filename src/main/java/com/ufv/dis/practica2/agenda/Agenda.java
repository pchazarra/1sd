package com.ufv.dis.practica2.agenda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Agenda {

	private static ArrayList<Contacto> contactos;
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public Agenda () {
		contactos = new ArrayList<Contacto>();
	}
	
	//Mostrar Contactos en la agenda
	public ArrayList<Contacto> getContactos() {
		return contactos;
	}	
	
	//Ver si el contacto existe en la agenda.    		Con Equals no funciona la comparación de los campos de los contactos.
	public boolean existeContacto(Contacto c){
		for (Contacto individuo : contactos) {
			if(individuo.getNombre()==c.getNombre() && individuo.getApellidos()==c.getApellidos() && individuo.getEmpresa()==c.getEmpresa() && individuo.getTelefono()==c.getTelefono() && individuo.getEmail()==c.getEmail()  && individuo.getDireccion()==c.getDireccion()) {
			return true;
			}
		}
		return false;
	}
	
	//Añadir Contacto a Contactos
	public boolean  addContacto(Contacto c) {
			boolean x = existeContacto(c);
			if(x == false) {
				contactos.add(c);
				return true;
			}
			return false;
		}
	
	//Buscar Contacto
	public Contacto buscarContacto(String Id){
		for (Contacto individuo : contactos) {
			if(individuo.getId() == Id) {
				return individuo;
			}
		}
		return null;
	}
	
	//Borrar Contacto
	public boolean eliminarContacto(String Id){
		for (Contacto individuo : contactos) {
			if(individuo.getId() == Id) {
				contactos.remove(individuo);
				return true;
			}
		}
		return false;
	}
	
	//Editar Contacto. 			Contacto c: Contacto a editar, Contacto a: la modificacion al contacto c. 
	public boolean editarContacto(Contacto c, Contacto a){
		for (Contacto individuo : contactos) {
			if(individuo.getId() == c.getId()) {
				contactos.remove(individuo);
				contactos.add(a);
				return true;
			}
		}
		return false;
	}
	
	//Guardar archivo a json 
	public IOException guardarJson(String ruta){
		File json = new File(ruta);
			try {
				json.createNewFile();
				FileWriter writer;
				writer = new FileWriter(ruta);
				gson.toJson(contactos, writer);
				writer.flush();
				writer.close();
				return null;
			} catch (IOException e) {
				return e;
			}
		
	}
	
	// Importar desde JSON
	public FileNotFoundException importarJson(String ruta) {
		Type ObjType = new TypeToken<ArrayList<Contacto>>(){}.getType();
		FileReader reader;
		try {
			reader = new FileReader(ruta);
			contactos = gson.fromJson(reader, ObjType);
			return null;
		} catch (FileNotFoundException e) {
			return e;
		}

	}
	

}
