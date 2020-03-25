package thesis.Charter.Axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import thesis.Charter.ChartMeasurements.XYChartMeasurements;
import thesis.Charter.StringDrawer.DrawString;
import thesis.Charter.StringDrawer.DrawString.xAlignment;
import thesis.Charter.StringDrawer.DrawString.yAlignment;
import thesis.Common.CommonArray;
import thesis.Common.CommonHashMap;
import thesis.Common.CommonMath;
import thesis.Common.NiceScale;
import thesis.DataFrame.DataItem;

public class StackedBarChartAxis extends XYAxis {
	
	
	private boolean includeAxisLinesOnPlot = true;
	private Color axisLinesOnPlotColor = Color.WHITE;
	
	
	public void setXAxis(String[] xData) {
		this.xTicks = xData;
	}

	public void setYAxis() {		
		this.yTicks = new String[] {"0", "0.2", "0.4", "0.6", "0.8", "1"};
	}
	


	
	public void drawAxis(Graphics2D g, XYChartMeasurements cm) {
		int halfWidthOfXUnit = (cm.getPlotWidth()/(2 * this.xTicks.length));
		int count = 0;
		for (String xCatagory : this.xTicks) {
			int xPosition = CommonMath.map(
				count, 
				0, 
				this.xTicks.length - 1, 
				cm.imageLeftToPlotLeftWidth() + halfWidthOfXUnit, 
				cm.imageLeftToPlotRightWidth() - halfWidthOfXUnit
			);
			
			DrawString.setColor(this.xAxisColor);
			DrawString.setFont(this.xAxisFont);
			DrawString.setRotation(this.xAxisRotation);
			DrawString.setAlignment(DrawString.xAlignment.CenterAlign, DrawString.yAlignment.MiddleAlign);
			if (this.drawBottomXAxisValues) {				
				DrawString.write(g, xCatagory, xPosition, cm.imageBottomToBottomAxisMidHeight());
			}
			if (this.drawTopXAxisValues) {
				DrawString.write(g, xCatagory, xPosition, cm.imageBottomToTopAxisMidHeight());
			}
			count++;
		}
		

		double[] doubleYTicks = getYTicksValues();
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_DOWN);

		for (count = 1; count < this.yTicks.length - 1; count++) {
			int position = CommonMath.map(count, 0, this.yTicks.length - 1, cm.imageBottomToPlotBottomHeight(), cm.imageBottomToPlotTopHeight());
			String stringToDisplay = String.valueOf(df.format(doubleYTicks[count]));
			
			DrawString.setColor(this.yAxisColor);
			DrawString.setFont(this.yAxisFont);
			DrawString.setRotation(this.yAxisRotation);
			DrawString.setAlignment(DrawString.xAlignment.CenterAlign, DrawString.yAlignment.MiddleAlign);
			if (this.drawLeftYAxisValues) {				
				DrawString.write(g, stringToDisplay, cm.imageLeftToLeftAxisMidWidth(), position);
			}
			if (this.drawRightYAxisValues) {
				DrawString.write(g, stringToDisplay, cm.imageLeftToRightAxisMidWidth(), position);
			}

			if (this.includeAxisLinesOnPlot) {				
				g.setColor(this.axisLinesOnPlotColor);
				g.drawLine(cm.imageLeftToPlotLeftWidth(),position,cm.imageLeftToPlotRightWidth(),position);
			}

		}
		
		
		
	}
	
	public void drawAxisTicks(Graphics2D g, XYChartMeasurements cm) {
		
//		g.setStroke(new BasicStroke(1));
		int halfWidthOfXUnit = (cm.getPlotWidth()/(2 * this.xTicks.length));
		for (int count = 0; count < this.xTicks.length; count++) {
			int xPosition = CommonMath.map(count, 0, xTicks.length - 1, cm.imageLeftToPlotLeftWidth() + halfWidthOfXUnit, cm.imageLeftToPlotRightWidth() - halfWidthOfXUnit);
			
			g.setColor(this.bottomTickColor);
			if (this.drawExteriorBottomXAxisTicks) {
				g.setStroke(new BasicStroke(this.exteriorBottomTickThickness));
				g.drawLine(xPosition, cm.imageBottomToPlotBottomHeight(), xPosition, cm.imageBottomToBottomTicksEndHeight());
			}
			if (this.drawInteriorBottomXAxisTicks) {	
				g.setStroke(new BasicStroke(this.interiorBottomTickThickness));
				g.drawLine(xPosition, cm.imageBottomToPlotBottomHeight(), xPosition, cm.imageBottomToPlotBottomHeight() + cm.getBottomTicksHeight());
			}
			g.setColor(this.topTickColor);
			if (this.drawExteriorTopXAxisTicks) {
				g.setStroke(new BasicStroke(this.exteriorTopTickThickness));
				g.drawLine(xPosition, cm.imageBottomToPlotTopHeight(), xPosition, cm.imageBottomToTopTicksEndHeight());
			}
			if (this.drawInteriorTopXAxisTicks) {
				g.setStroke(new BasicStroke(this.interiorTopTickThickness));
				g.drawLine(xPosition, cm.imageBottomToPlotTopHeight(), xPosition, cm.imageBottomToPlotTopHeight() - cm.getTopTicksHeight());
			}
			
		}
		
		for (int count = 0; count < this.yTicks.length; count++) {
			int position = CommonMath.map(count, 0, this.yTicks.length - 1, cm.imageBottomToPlotBottomHeight(), cm.imageBottomToPlotTopHeight());
			
			g.setColor(this.leftTickColor);
			if (this.drawExteriorLeftYAxisTicks) {
				g.setStroke(new BasicStroke(this.exteriorLeftTickThickness));
				g.drawLine(cm.imageLeftToPlotLeftWidth(), position, cm.imageLeftToLeftTicksEndWidth(), position);
			}
			if (this.drawInteriorLeftYAxisTicks) {
				g.setStroke(new BasicStroke(this.interiorLeftTickThickness));
				g.drawLine(cm.imageLeftToPlotLeftWidth(), position, cm.imageLeftToPlotLeftWidth() + cm.getLeftTicksWidth(), position);
			}
			g.setColor(this.rightTickColor);
			if (this.drawExteriorRightYAxisTicks) {
				g.setStroke(new BasicStroke(this.exteriorRightTickThickness));
				g.drawLine(cm.imageLeftToPlotRightWidth(), position, cm.imageLeftToRightTicksEndWidth(), position);
			}
			if (this.drawInteriorRightYAxisTicks) {
				g.setStroke(new BasicStroke(this.interiorRightTickThickness));
				g.drawLine(cm.imageLeftToPlotRightWidth(), position, cm.imageLeftToPlotRightWidth() - cm.getRightTicksWidth(), position);
			}

		}
	}


}
