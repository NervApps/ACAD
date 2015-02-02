/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.util;

import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum MessageType {
    INFO ("alert alert-info", "icon-ok"),
    WARN ("alert alert-warning", ""),
    ERROR("alert alert-error", "icon-remove");
    
    @Getter private final String css;
    @Getter private final String icon;

    private MessageType(String css, String icon) {
        this.css = css;
        this.icon = icon;
    }
}
