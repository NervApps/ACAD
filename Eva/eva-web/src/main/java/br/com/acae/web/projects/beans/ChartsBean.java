/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.web.projects.beans;

import br.com.nerv.eva.core.web.stereotypes.Model;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Vitor Ribeiro de Oliveira
 */
@Model
public class ChartsBean implements Serializable {
    
    @Getter private BarChartModel customers;
    @Getter private LineChartModel growth;
    private final String years = "Anos";
    
    @PostConstruct
    public void init() {
        create();
    }
    
    private void create() {
        createCustomerChart();
        createGrowthChart();
    }
    
    private void createGrowthChart() {
        final String projects = "Projetos";
        
        growth = new LineChartModel();
        growth.addSeries(getSeries("Novos Projetos", 5, 2, true));
        growth.setTitle("Projetos por ano");
        growth.setAnimate(true);
        growth.setLegendPosition("se");
        
        prepareLegend(growth, years, projects);
    }
    
    private void createCustomerChart() {
        final String milions = "Milhões";
        
        customers = new BarChartModel();
        customers.addSeries(getSeries("Cliente X", 17, 3, false));
        customers.addSeries(getSeries("Cliente Y", 8, 5, false));
        customers.addSeries(getSeries("Cliente Z", 12, 1, false));
        customers.setTitle("Prospecção Lucro Clientes");
        customers.setAnimate(true);
        customers.setLegendPosition("se");
        
        prepareLegend(customers, years, milions);
    }
    
    private ChartSeries getSeries(final String label, int number, 
            final int interval, final boolean isLineChart) {
        
        return isLineChart ? getLineSeries(label, number, interval)
                : getBarSeries(label, number, interval);
    }
    
    private ChartSeries getBarSeries(final String label, int milions, final int interval) {
        final ChartSeries series = new ChartSeries();
        series.setLabel(label);
        
        for (int i = 0; i < 5; i++) {
            series.set("201"+i, milions);
            milions = milions + interval;
        }
        
        return series;
    }
    
    private ChartSeries getLineSeries(final String label, int projects, final int interval) {
        int year = 2010;
        final LineChartSeries series = new LineChartSeries();
        series.setLabel(label);
        
        for (int i = 0; i < 5; i++) {
            year = year + i;
            series.set(year, projects);
            projects = projects + interval;
        }
        
        return series;
    }
    
    private void prepareLegend(final CartesianChartModel chart, final String xLabel,
            final String yLabel) {
        
        Axis axis = chart.getAxis(AxisType.X);
        axis.setLabel(xLabel);
        
        axis = chart.getAxis(AxisType.Y);
        axis.setLabel(yLabel);
    }
}