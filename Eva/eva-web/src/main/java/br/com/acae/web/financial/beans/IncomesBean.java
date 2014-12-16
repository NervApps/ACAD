/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.web.financial.beans;

import br.com.acae.web.financial.dto.IncomesDTO;
import br.com.nerv.eva.core.web.mb.util.ManagedBean;
import br.com.nerv.eva.core.web.stereotypes.ViewModel;
import com.nerv.eva.core.enums.Severity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor Ribeiro de Oliveira
 */
@ViewModel
public class IncomesBean extends ManagedBean implements Serializable {
    
    @Getter @Setter private IncomesDTO dto;
    @Getter @Setter private IncomesDTO selected;
    @Getter private List<IncomesDTO> incomeList;
    
    @PostConstruct
    public void init() {
        dto = new IncomesDTO();
        incomeList = new ArrayList<>();
    }
    
    public void addIncome() {
        if (incomeList.contains(dto)) {
            addMessage("Dados duplicados", Severity.WARN);
        } else {
            incomeList.add(dto);
            dto = new IncomesDTO();
        }
    }
    
    public void remove() {
        incomeList.remove(selected);
    }
}