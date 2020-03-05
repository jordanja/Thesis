package thesis.Charter.PlotFolder;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import thesis.Charter.Axis.Axis;
import thesis.Charter.Axis.BarChartAxis;
import thesis.Charter.LegendPackage.Legend;
import thesis.Charter.Others.BarChartMeasurements;
import thesis.Charter.Others.XYChartMeasurements;
import thesis.DataFrame.DataFrame;
import thesis.DataFrame.DataItem;

public class BarChart extends XYChart{

	BarChartAxis axis;
	BarPlot plot;
	Legend legend;
	
	private String colorCodeLabel;
	private String[] colorCodeValues; 
	
	public BarChart(DataFrame dataFrame, String xAxis, String yAxis) {
		super(dataFrame, dataFrame.GetColumnAsArray(xAxis), dataFrame.GetColumnAsArray(yAxis), "Bar");
		
		this.axis = new BarChartAxis();
		this.plot = new BarPlot();
		this.legend = new Legend();
		
		cm = new BarChartMeasurements();

	}

	

	@Override
	public void Create() {
		
		String[] uniqueColorCodeValues = getUniqueColorCodeValues();
		String[] xDataFormatted = getXDataFormatted();
		HashMap<String, Object> data = getYDataFormatted(xDataFormatted, uniqueColorCodeValues);
		
		
//		this.axis.setXAxis(data.keySet().toArray(new String[0]));
		this.axis.setXAxis(xDataFormatted);
		this.axis.setYAxis(data);

//		if (this.legend.getIncludeLegend()) {
//			Object[] hueValies = { "a", "b" };
//			this.legend.calculateLegend("Hello", hueValies);
//		}

		cm.calculateChartImageMetrics(this.axis, this.plot, this.legend, getTitle(), getTitleFont());

		instantiateChart(cm);

		Graphics2D g = initializaGraphicsObject(cm);
		drawBackground(g, cm);

		this.plot.drawChartBackground(g, cm);

		this.axis.drawAxis(g, cm);

		this.plot.drawPlotOutline(g, cm);

		this.axis.drawAxisTicks(g, cm);

		this.plot.drawPlot(g, this.axis, data, cm);

		this.axis.drawXAxisLabel(g, cm);
		this.axis.drawYAxisLabel(g, cm);

////		if (this.legend.getIncludeLegend()) {
////			this.legend.drawLegend(g, cm, this.plot.getColorPalette());
////		}

		this.drawTitle(g, cm);
	}

	private HashMap<String, Object> getYDataFormatted(String[] xCatagories, String[] uniqueColorCodeValues) {
		
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		if (uniqueColorCodeValues.length > 0) {
			HashMap<String, HashMap<String, Double>> runningTotals = new HashMap<String, HashMap<String, Double>>();
			HashMap<String, HashMap<String, Integer>> runningCount = new HashMap<String, HashMap<String, Integer>>();
			for (String xCatagory : xCatagories) {
				HashMap<String, Double> colorCodeValueMap = new HashMap<String, Double>();
				HashMap<String, Integer> colorCodeRunningCountMap = new HashMap<String, Integer>();
				for (String colorCodeValue : uniqueColorCodeValues) {
					colorCodeValueMap.put(colorCodeValue, (double) 0);
					colorCodeRunningCountMap.put(colorCodeValue, 0);
				}
				
				runningTotals.put(xCatagory, colorCodeValueMap);
				runningCount.put(xCatagory, colorCodeRunningCountMap);
			}
			
			for (int i = 0; i < this.xData.length; i++) {
				String xValue = this.xData[i].getValueConvertedToString();
				double yValue = this.yData[i].getValueConvertedToDouble();
				String colorCodeValue = this.colorCodeValues[i];
				
				HashMap<String, Double> colorCodeTotalsMap = runningTotals.get(xValue);
				colorCodeTotalsMap.put(colorCodeValue, colorCodeTotalsMap.get(colorCodeValue) + yValue);
				
				HashMap<String, Integer> colorCodeRunningCountMap = runningCount.get(xValue);
				colorCodeRunningCountMap.put(colorCodeValue, colorCodeRunningCountMap.get(colorCodeValue) + 1);
				
				runningTotals.put(xValue, colorCodeTotalsMap);
				runningCount.put(xValue, colorCodeRunningCountMap);
			}
			
			
			for (String xValue : runningTotals.keySet()) {
				HashMap<String, Double> colorCodeMap = new HashMap<String, Double>();
				
				HashMap<String, Double> colorCodeTotalsMap = runningTotals.get(xValue);
				HashMap<String, Integer> colorCodeRunningCountMap = runningCount.get(xValue);
				for (String colorCodeValue: colorCodeTotalsMap.keySet()) {
					double averageValue = colorCodeTotalsMap.get(colorCodeValue)/colorCodeRunningCountMap.get(colorCodeValue);
					colorCodeMap.put(colorCodeValue, averageValue);
				}
				
				
				data.put(xValue, colorCodeMap);
			}
			
		} else {
			
			HashMap<String, Double> runningTotals = new HashMap<String, Double>();
			HashMap<String, Integer> runningCount = new HashMap<String, Integer>();
			
			for (int i = 0; i < this.xData.length; i++) {
				String xValue = this.xData[i].getValueConvertedToString();
				double yValue = this.yData[i].getValueConvertedToDouble();
				if (runningTotals.containsKey(xValue)) {					
					runningTotals.put(xValue, runningTotals.get(xValue) + yValue);
				} else {
					runningTotals.put(xValue, (double) 0);
				}
				
				if (runningCount.containsKey(xValue)) {					
					runningCount.put(xValue, runningCount.get(xValue) + 1);
				} else {
					runningCount.put(xValue, 0);
				}
			}
			
			for (String xValue : runningTotals.keySet()) {
				
				double averageValue = runningTotals.get(xValue)/runningCount.get(xValue);
				
				data.put(xValue, averageValue);
				System.out.println(xValue + ": " + averageValue);
			}
			
		}
		

		return data;
	}

	private String[] getXDataFormatted() {
		ArrayList<String> foundXCatagories = new ArrayList<String>();
		for (DataItem xValue : this.xData) {
			if (!foundXCatagories.contains(xValue.getValueConvertedToString())) {
				foundXCatagories.add(xValue.getValueConvertedToString());
			}
		}
		String[] xDataFormatted = new String[foundXCatagories.size()];
		xDataFormatted = foundXCatagories.toArray(xDataFormatted);
		return xDataFormatted;
	}

	private String[] getUniqueColorCodeValues() {
		Set<String> uniqueList = new HashSet<String>();

		if (this.colorCodeValues != null) {
			
			for (String nextElem : this.colorCodeValues) {
				uniqueList.add(nextElem);
			}
			
			if (uniqueList.size() == 1) {
				return new String[0];
			}
			
			return uniqueList.stream().toArray(String[]::new);
		} else {
			return new String[0];
		}
	}

	public Axis getAxis() {
		return this.axis;
	}

	public BarPlot getPlot() {
		return this.plot;
	}

	public Legend getLegend() {
		return this.legend;
	}
	
	public XYChartMeasurements getChartMeadurements() {
		return this.cm;
	}

	public void colorCode(String colorCodeLabel) {
		this.colorCodeLabel = colorCodeLabel;
		this.colorCodeValues = this.dataFrame.GetColumnAsStringArray(this.colorCodeLabel);
		this.legend.setIncludeLegend(true);
	}

}
