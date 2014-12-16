/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.common.converter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Classe que realiza a conversão de POJOs
 * com o método hashCode() implementado corretamente.
 * @author Vitor Ribeiro de Oliveira
 */
@FacesConverter(value = "beanConverter")
public class BeanConverter implements Converter {

    private static final Map<Object, String> beans = new ConcurrentHashMap<>();
    
    @Override
    public Object getAsObject(final FacesContext context, 
            final UIComponent component, final String value) {
        
        for (final Entry<Object, String> entry : beans.entrySet()) {
            if (entry.getValue().equals(value))
                return entry.getKey();
        }
        return null;
    }

    @Override
    public String getAsString(final FacesContext context, 
            final UIComponent component, final Object value) {

        if (value != null) {
            if (beans.containsKey(value))
                return beans.get(value);
            else {
                final String hash = String.valueOf(value.hashCode());
                beans.put(value, hash);
                return hash;
            }
        } else 
            return null;
    }
}