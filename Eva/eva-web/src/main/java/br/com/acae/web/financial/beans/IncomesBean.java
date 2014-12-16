/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.web.financial.beans;

import br.com.acae.web.financial.dto.IncomesDTO;
import br.com.acae.web.financial.dto.ItemDTO;
import br.com.acae.web.financial.dto.StatusDTO;
import br.com.nerv.eva.core.web.mb.util.ManagedBean;
import br.com.nerv.eva.core.web.stereotypes.ViewModel;
import com.nerv.eva.core.enums.Severity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public void insert() {
        addItens();
    }
    
    public void confirm() {
        addMessage("Nota criada com sucesso", Severity.INFO);
        dto = new IncomesDTO();
    }
    
    private void addItens() {
        dto.setItens(new ArrayList());
        final BigDecimal value = new BigDecimal(dto.getValorTotal());
        final BigDecimal divisor = new BigDecimal(dto.getQtdeParcelas());
        final BigDecimal result = value.divide(divisor);
        
        for (int i = 0; i < divisor.intValue(); i++) {
            final ItemDTO item = new ItemDTO();
            item.setName("Item " +i);
            item.setValue(result.doubleValue());
            dto.addItem(item);
        }
    }
    
    public void remove() {
        incomeList.remove(selected);
    }
    
    public List<StatusDTO> getStatusList() {
        return Arrays.asList(StatusDTO.values());
    }
}