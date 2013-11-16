package br.com.unilog.utilitario;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Utilitario {

	public static Object[][] valoresForJtable(List<?> lista){
		
		if(lista!=null && lista.size()>0){
			int size = lista.size();
			Object x = lista.get(0);
			int n = x.getClass().getDeclaredFields().length;
			Object[][] objs = new Object[size][n+1];
			
			for (int i = 0; i < lista.size(); i++) {
				Object item = lista.get(i);
				Object[] objects = objs[i];
				for (int k = 0; k < item.getClass().getDeclaredFields().length; k++) {
					Method methods = methods(item.getClass()
							.getDeclaredMethods(), (item.getClass()
							.getDeclaredFields()[k]).getName().toString());
					try {
						objects[k] = (methods.invoke(item)).toString();
					} catch (IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			
			return objs;
		}
		
		return null;
	}
	
	private static Method methods(Method[] methods, String find){
		for (int i = 0; i < methods.length; i++) {
			if(methods[i].getName().toLowerCase().contains("get"+find.toLowerCase())){
				return methods[i];
			}
		}
		return null;
	}
}
