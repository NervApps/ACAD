/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.web.financial.dto;

import lombok.Getter;

/**
 *
 * @author User
 */
public enum StatusDTO {
    A_RECEBER("A receber"),
    RECEBIDO("Recebido");
    
    @Getter private final String descr;
    
    private StatusDTO(final String descr) {
        this.descr = descr;
    }
}