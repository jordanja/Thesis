package thesis.Charter.Charts;

import java.awt.Color;
import java.awt.Graphics2D;

import thesis.Charter.Axis.GaugeAxis;
import thesis.Charter.ChartMeasurements.OnlyPlotChartMeasurements;
import thesis.Charter.Plots.GaugePlot;

public class GaugeChart extends Chart{

	private double value;
	private double min;
	private double max; 
	private boolean percentageGuage = true;
	
	private GaugePlot plot;
	private GaugeAxis axis;
	
	private OnlyPlotChartMeasurements cm;
	
	public GaugeChart(double value, double min, double max, boolean percentageGauge) {
		this.value = value;
		this.min = min;
		this.max = max;
		this.percentageGuage = percentageGauge;
		
		this.plot = new GaugePlot();
		this.axis = new GaugeAxis();
		this.cm = new OnlyPlotChartMeasurements();
	}
	
	
	@Override
	public void Create() {
		this.axis.setAxis(value, min, max);
		
		
		this.cm.calculateChartImageMetrics(this.getTitle(), this.getTitleFont());
		this.instantiateChart(this.cm);

		Graphics2D g = initializaGraphicsObject(this.cm);
		
		this.drawBackground(g, this.cm);
		
		this.plot.setPlotBackgroundColor(Color.white);
		
		this.plot.drawPlotBackground(g, this.cm);

		this.axis.drawAxis(g, this.cm);
		
		this.plot.drawPlotOutline(g, this.cm);
	
		this.plot.drawPlot(g, this.value, this.axis, this.cm);
		

		this.drawTitle(g, this.cm);
	
	}


	public boolean isPercentageGuage() {
		return percentageGuage;
	}


	public void setPercentageGuage(boolean percentageGuage) {
		this.percentageGuage = percentageGuage;
	}


	public GaugePlot getPlot() {
		return plot;
	}


	public void setPlot(GaugePlot plot) {
		this.plot = plot;
	}


	public GaugeAxis getAxis() {
		return axis;
	}


	public void setAxis(GaugeAxis axis) {
		this.axis = axis;
	}


	public OnlyPlotChartMeasurements getCm() {
		return cm;
	}


	public void setCm(OnlyPlotChartMeasurements cm) {
		this.cm = cm;
	}

}
