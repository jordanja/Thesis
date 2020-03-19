//package thesis.Charter.Others;
//
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.Shape;
//import java.awt.font.FontRenderContext;
//import java.awt.font.GlyphVector;
//import java.awt.geom.AffineTransform;
//import java.awt.image.BufferedImage;
//import java.math.RoundingMode;
//import java.text.DecimalFormat;
//import java.util.Arrays;
//
//import thesis.Charter.Axis.Axis;
//import thesis.Charter.Axis.NumericAxis;
//import thesis.Charter.LegendPackage.Legend;
//import thesis.Charter.PlotFolder.Plot;
//import thesis.Charter.PlotFolder.ScatterPlot;
//import thesis.Charter.StringDrawer.DrawString;
//
//public class BarChartMeasurements extends XYChartMeasurements {
//		
//	@Override
//	public void calculateChartImageMetrics(Axis axis, Plot sPlot, Legend legend, String title, Font titleFont) {
//		
//		String[] xTicks = axis.getXTicks();
//		double[] yTicks = axis.getYTicksValues();
//		
//		DecimalFormat df = new DecimalFormat("#.##");
//		df.setRoundingMode(RoundingMode.HALF_DOWN);
//		
//		//This needs to be bug checked for when not entering a label but setting value to true
//		if ((axis.drawBottomXLabel() == true) && (axis.getXAxisLabel() != null)) {
//			double xAxisLabelHeight = axis.getXAxisLabelFont().createGlyphVector(new FontRenderContext(null, true, false), axis.getXAxisLabel()).getOutline().getBounds2D().getHeight();
//			this.bottomAxisLabelHeight = (int) Math.ceil(xAxisLabelHeight);
//		} else {
//			this.bottomAxisLabelHeight = 0;
//			this.bottomAxisLabelToBottomAxisHeight = 0;
//		}
//		
//		
//		if (axis.drawBottomXAxisValues()) {
//			AffineTransform rotationTransform = AffineTransform.getRotateInstance(Math.toRadians(axis.getXAxisRotation()), 0, 0);
//			
//			double maxHeight = 0;
//			
//			for (int tickCount = 0; tickCount < axis.getXTicks().length; tickCount++) {
//				String stringToDraw;
//				if (xTicks[0] instanceof String) {
//					stringToDraw = xTicks[tickCount];
//				} else {
//					stringToDraw = String.valueOf(df.format(xTicks[tickCount]));
//				}
//				
//				Shape sot = DrawString.getShapeOfText(axis.getXAxisFont(), stringToDraw);
//				double tickHeight = rotationTransform.createTransformedShape(sot).getBounds2D().getHeight();
//				if (tickHeight > maxHeight) {
//					maxHeight = tickHeight;
//				}
//			}
//			this.bottomAxisHeight = (int) Math.ceil(maxHeight);
//		} else {
//			this.bottomAxisHeight = 0;
//			this.bottomAxisToBottomTicksHeight = 0;
//
//
//		}
//		
//		
//		if (!axis.drawExteriorBottomXAxisTicks()) {
//			this.bottomTicksHeight = 0;
//		}
//				
//		if (!axis.drawExteriorTopXAxisTicks()) {
//			this.topTicksHeight = 0;
//		}
//		
//		
//		
//		if (axis.drawTopXAxisValues()) {
//			
//			AffineTransform rotationTransform = AffineTransform.getRotateInstance(Math.toRadians(axis.getXAxisRotation()), 0, 0);
//			
//			double maxHeight = 0;
//			
//			for (int tickCount = 0; tickCount < axis.getXTicks().length; tickCount++) {
//				String stringToDraw = String.valueOf(df.format(xTicks[tickCount]));
//				Shape sot = DrawString.getShapeOfText(axis.getXAxisFont(), stringToDraw);
//				double tickHeight = rotationTransform.createTransformedShape(sot).getBounds2D().getHeight();
//				if (tickHeight > maxHeight) {
//					maxHeight = tickHeight;
//				}
//			}
//			this.topAxisHeight = (int) Math.ceil(maxHeight);
//		} else {
//			this.topAxisHeight = 0;
//			this.topTicksToTopAxisHeight = 0;
//		}
//		
//		if (axis.drawTopXLabel()) {
//			double xAxisLabelHeight = axis.getXAxisLabelFont().createGlyphVector(new FontRenderContext(null, true, false), axis.getXAxisLabel()).getOutline().getBounds2D().getHeight();
//			this.topAxisLabelHeight = (int) Math.ceil(xAxisLabelHeight);
//
//		} else {
//			this.topAxisLabelHeight = 0;
//			this.topAxisToTopAxisLabelHeight = 0;
//
//		}
//		
//
//
//		if (title != null) {
//			Shape sot = DrawString.getShapeOfText(titleFont, title);
//			this.titleHeight = (int) Math.ceil(sot.getBounds2D().getHeight());
//		} else { 
//			this.titleHeight = 0;
//			this.topAxisLabelToTitleHeight = 0;
//
//		}
//		
//
//		
//		if ((axis.drawLeftYLabel()) && (axis.getYAxisLabel() != null)) {
//			double yAxisLabelHeight = axis.getYAxisLabelFont().createGlyphVector(new FontRenderContext(null, true, false), axis.getYAxisLabel()).getOutline().getBounds2D().getHeight();
//			this.leftAxisLabelWidth = (int) Math.ceil(yAxisLabelHeight);
//
//		} else {
//			this.leftAxisLabelWidth = 0;
//			this.leftAxisLabelToLeftAxisWidth = 0;
//
//		}
//		
//		
//		
//		if (axis.drawLeftYAxisValues()) {
//			
//			AffineTransform rotationTransform = AffineTransform.getRotateInstance(Math.toRadians(axis.getYAxisRotation()), 0, 0);
//			
//			double maxWidth = 0;
//			
//			for (int tickCount = 0; tickCount < axis.getYTicks().length; tickCount++) {
//				
//				String stringToDraw = String.valueOf(df.format(yTicks[tickCount]));
//				Shape sot = DrawString.getShapeOfText(axis.getYAxisFont(), stringToDraw);
//				double tickWidth = rotationTransform.createTransformedShape(sot).getBounds2D().getWidth();
//				if (tickWidth > maxWidth) {
//					maxWidth = tickWidth;
//				}
//			}
//			this.leftAxisWidth = (int) Math.ceil(maxWidth);
//		} else {
//			this.leftAxisWidth = 0;
//			this.leftAxisToLeftTicksWidth = 0;
//		}
//		
//		
//
//		if (!axis.drawExteriorLeftYAxisTicks()) {
//			this.leftTicksWidth = 0;
//		}
//				
//		if (!axis.drawExteriorRightYAxisTicks()) {
//			this.rightTicksWidth = 0;
//		}
//		
//		if (axis.drawRightYAxisValues()) {
//			AffineTransform rotationTransform = AffineTransform.getRotateInstance(Math.toRadians(axis.getYAxisRotation()), 0, 0);
//			
//			double maxWidth = 0;
//			
//			for (int tickCount = 0; tickCount < axis.getYTicks().length; tickCount++) {
//				String stringToDraw = String.valueOf(df.format(yTicks[tickCount]));
//				Shape sot = DrawString.getShapeOfText(axis.getYAxisFont(), stringToDraw);
//				double tickWidth = rotationTransform.createTransformedShape(sot).getBounds2D().getWidth();
//				if (tickWidth > maxWidth) {
//					maxWidth = tickWidth;
//				}
//			}
//			this.rightAxisWidth = (int) Math.ceil(maxWidth);
//		} else {
//			this.rightAxisWidth = 0;
//			this.rightTicksToRightAxisWidth = 0;
//		}
//		
//		if (axis.drawRightYLabel()) {
//			double yAxisLabelHeight = axis.getYAxisLabelFont().createGlyphVector(new FontRenderContext(null, true, false), axis.getYAxisLabel()).getOutline().getBounds2D().getHeight();
//			this.rightAxisLabelWidth = (int) Math.ceil(yAxisLabelHeight);
//		} else {
//			this.rightAxisToRightAxisLabelWidth = 0;
//			this.rightAxisLabelWidth = 0;
//		}
//		
//		if (legend.getIncludeLegend()) {			
//			this.legendWidth = legend.getLegendWidth();
//		} else {
//			this.legendWidth = 0;
//			this.rightAxisLabelToLegendWidth = 0;
//		}		
//	}
//	
//	
//	
//	
//	
//}
