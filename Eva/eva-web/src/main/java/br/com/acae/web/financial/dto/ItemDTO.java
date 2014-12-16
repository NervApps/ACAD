/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.web.financial.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */
public class ItemDTO implements Serializable {
    
    @Getter @Setter private String name;
    @Getter @Setter private Double value;
}