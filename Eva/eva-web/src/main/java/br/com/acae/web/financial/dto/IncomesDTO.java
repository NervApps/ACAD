/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.web.financial.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@EqualsAndHashCode
public class IncomesDTO implements Serializable {
    
    @Getter @Setter private String numDocumento;
    @Getter @Setter private String notaFiscal;
    @Getter @Setter private String cliente;
    @Getter @Setter private StatusDTO status;
    @Getter @Setter private String nomeProjeto;
    @Getter @Setter private String responsavel;
    @Getter @Setter private String descricao;
    @Getter @Setter private String obs;
    @Getter @Setter private Double valorTotal;
    @Getter @Setter private Integer qtdeParcelas;
    @Getter @Setter private List<ItemDTO> itens;
    
    public void addItem(final ItemDTO item) {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        
        itens.add(item);
    }
}