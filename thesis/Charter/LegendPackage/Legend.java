package thesis.Charter.LegendPackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashSet;

import thesis.Charter.Others.XYChartMeasurements;
import thesis.Charter.StringDrawer.DrawString;

public class Legend {

	private boolean includeLegend;
	private String hueLabel;
	private String[] hueValues;
//	private Color[] colors;
	
	private int hueLabelWidth;
	private int widestHueValueWidth;
	private int legendLeftToDataPointWidth = 10;
	private int dataPointDiameter = 10;
	private int dataPointToHueValueWidth = 8;
	private int textRightToLegendRightWidth = 10;
	private int bottomLegendToBottomHueValue = 10;
	private int hueValueHeight;
	private int hueValueSpacingHeight = 5;
	private int hueValuesTopToHueLabelHeight = 10;
	private int hueLabelHeight;
	private int hueLabelToLegendTopHeight = 10;
	
	private Font hueLabelFont = new Font("Dialog", Font.BOLD, 20);
	private Font hueValueFont = new Font("Dialog", Font.PLAIN, 20);

	private Color backgroundColor = Color.WHITE;
	

	public void calculateLegend(String hueLabel, Object[] hueValues) {
		this.hueLabel = hueLabel;
		this.hueValues = new HashSet<>(Arrays.asList(hueValues)).toArray(new String[0]);
		
		
		this.hueValueHeight = 0;
		this.widestHueValueWidth = 0;
		for (String hueValue: this.hueValues) {		
			Rectangle2D bounds = this.hueValueFont.createGlyphVector(new FontRenderContext(null, true, false), hueValue).getOutline().getBounds2D();
			int height =  (int) Math.ceil(bounds.getHeight());
			if (height > this.hueValueHeight) {
				this.hueValueHeight = height;
			}
			
			int width = (int) Math.ceil(bounds.getWidth());
			if (width > this.widestHueValueWidth) {
				this.widestHueValueWidth = width;
			}
			
		}
		
		
		Rectangle2D bounds = this.hueLabelFont.createGlyphVector(new FontRenderContext(null, true, false), hueLabel).getOutline().getBounds2D();
		this.hueLabelHeight = (int)Math.ceil(bounds.getHeight());
		this.hueLabelWidth = (int)Math.ceil(bounds.getWidth());
		
		
		
	}
	
	public void drawLegend(Graphics2D g, XYChartMeasurements cm, Color[] colors) {
		
		int legendBottom = cm.imageBottomToPlotMidHeight() - this.getLegendHeight()/2;

		if (this.backgroundColor != null) {
			g.setColor(this.backgroundColor);
			g.fillRect(cm.imageLeftToLegendLeftWidth(), legendBottom, this.getLegendWidth(), this.getLegendHeight());
		}
		
		
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		
		g.drawRect(cm.imageLeftToLegendLeftWidth(), legendBottom, this.getLegendWidth(), this.getLegendHeight());
		
		g.setFont(this.hueLabelFont);
		DrawString.drawString(g, this.hueLabel, cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToTextLeftWidth(), legendBottom + this.getBottomlegentToHueLabelBottomHeight(), DrawString.xAlignment.LeftAlign, DrawString.yAlignment.BottomAlign, 0, cm);

		g.setFont(this.hueValueFont);
		for (int i = 0; i < this.hueValues.length; i++) {
			g.setColor(Color.BLACK);
			DrawString.drawString(g, this.hueValues[i], cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToTextLeftWidth(), legendBottom + getBottomLegendToHueValueBottomHeight(i), DrawString.xAlignment.LeftAlign, DrawString.yAlignment.BottomAlign, 0, cm);
			
			
//			Rectangle2D bounds = this.hueValueFont.createGlyphVector(new FontRenderContext(null, true, false), this.hueValues[i]).getVisualBounds().getBounds2D();
//			int other =  (int) Math.ceil(bounds.getHeight());
			
			
//			FontRenderContext frc = g.getFontRenderContext();
//	        GlyphVector gv = g.getFont().createGlyphVector(frc, this.hueValues[i]);
//	        Rectangle2D bounds = gv.getPixelBounds(null, cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToTextLeftWidth(), legendBottom + getBottomLegendToHueValueBottomHeight(i));
//			int height =  (int) Math.ceil(bounds.getHeight());
			

//			int height = g.getFontMetrics().getAscent();
//			System.out.println(height + " vs " + other);

			int height = DrawString.getShapeOfText(this.hueValueFont, this.hueValues[i]).getBounds().height;
			
			g.setColor(colors[i]);
			g.fillOval(cm.imageLeftToLegendLeftWidth() + legendLeftToDataPointWidth, legendBottom + getBottomLegendToHueValueBottomHeight(i) + height/2 - dataPointDiameter/2, dataPointDiameter, dataPointDiameter);
		}
//		drawDebugLines(g,cm);

	}


	private void drawDebugLines(Graphics2D g, XYChartMeasurements cm) {
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.GRAY);
		
		int imageBottomToLegendBottom = cm.imageBottomToPlotMidHeight() - this.getLegendHeight()/2;
		int imageBottomToLegendTop = cm.imageBottomToPlotMidHeight() + this.getLegendHeight()/2;
		
		g.drawLine(cm.imageLeftToLegendLeftWidth(), imageBottomToLegendBottom, cm.imageLeftToLegendLeftWidth(), imageBottomToLegendTop);
		g.drawLine(cm.imageLeftToLegendLeftWidth() + this.legendLeftToDataPointWidth, imageBottomToLegendBottom, cm.imageLeftToLegendLeftWidth() + this.legendLeftToDataPointWidth, imageBottomToLegendTop);
		g.drawLine(cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToDataPointRightWidth(), imageBottomToLegendBottom, cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToDataPointRightWidth(), imageBottomToLegendTop);
		g.drawLine(cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToTextLeftWidth(), imageBottomToLegendBottom, cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToTextLeftWidth(), imageBottomToLegendTop);
		g.drawLine(cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToTextRightWidth(), imageBottomToLegendBottom, cm.imageLeftToLegendLeftWidth() + this.getLegendLeftToTextRightWidth(), imageBottomToLegendTop);
		
	}

	private String[] objectToString(Object[] oldList) {
		String[] newList = new String[oldList.length];
		for (int i = 0; i < oldList.length; i++) {
			newList[i] = (String) oldList[i];
		}
		return newList;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public boolean getIncludeLegend() {
		return includeLegend;
	}
	public void setIncludeLegend(boolean includeLegend) {
		this.includeLegend = includeLegend;
	}

	
	public String getHueLabel() {
		return hueLabel;
	}
	public void setHueLabel(String hueLabel) {
		this.hueLabel = hueLabel;
	}

	
	public String[] getHueValues() {
		return hueValues;
	}
	public void setHueValues(String[] hueValues) {
		this.hueValues = hueValues;
	}
	
	
	public int getHueValueSpacingHeight() {
		return hueValueSpacingHeight;
	}
	public void setHueValueSpacingHeight(int hueValueSpacingHeight) {
		this.hueValueSpacingHeight = hueValueSpacingHeight;
	}

	
	public int getDataPointToHueValueWidth() {
		return this.dataPointToHueValueWidth;
	}
	public void setDataPointToHueValueWidth(int dataPointToHueValueWidth) {
		this.dataPointToHueValueWidth = dataPointToHueValueWidth;
	}

	
	public int getLegendLeftToDataPointWidth() {
		return legendLeftToDataPointWidth;
	}
	public void setLegendLeftToDataPointWidth(int legendLeftToDataPointWidth) {
		this.legendLeftToDataPointWidth = legendLeftToDataPointWidth;
	}


	public int getTextRightToImageRightWidth() {
		return textRightToLegendRightWidth;
	}
	public void setTextRightToImageRightWidth(int textRightToImageRightWidth) {
		this.textRightToLegendRightWidth = textRightToImageRightWidth;
	}


	public int getDataPointDiameter() {
		return dataPointDiameter;
	}
	public void setDataPointDiameter(int dataPointDiameter) {
		this.dataPointDiameter = dataPointDiameter;
	}


	public int getBottomLegendToBottomHueValue() {
		return bottomLegendToBottomHueValue;
	}
	public void setBottomLegendToBottomHueValue(int bottomLegendToBottomHueValue) {
		this.bottomLegendToBottomHueValue = bottomLegendToBottomHueValue;
	}
	
	
	public int getHueValuesTopToHueLabelHeight() {
		return hueValuesTopToHueLabelHeight;
	}
	public void setHueValuesTopToHueLabelHeight(int hueValuesTopToHueLabelHeight) {
		this.hueValuesTopToHueLabelHeight = hueValuesTopToHueLabelHeight;
	}


	public int getHueLabelHeight() {
		return hueLabelHeight;
	}
	public void setHueLabelHeight(int hueLabelHeight) {
		this.hueLabelHeight = hueLabelHeight;
	}


	public int getHueLabelToLegendTopHeight() {
		return hueLabelToLegendTopHeight;
	}
	public void setHueLabelToLegendTopHeight(int hueLabelToLegendTopHeight) {
		this.hueLabelToLegendTopHeight = hueLabelToLegendTopHeight;
	}
	
	public Font getHueLabelFont() {
		return hueLabelFont;
	}
	public void setHueLabelFont(Font hueLabelFont) {
		this.hueLabelFont = hueLabelFont;
	}


	public Font getHueValueFont() {
		return hueValueFont;
	}
	public void setHueValueFont(Font hueValueFont) {
		this.hueValueFont = hueValueFont;
	}
	
	public int getLongestTextLength() {
		if (this.hueLabelWidth > this.widestHueValueWidth) {
			return this.hueLabelWidth;
		} else {
			return this.widestHueValueWidth;
		}
	}
	
//	public int getImageLeftToLegendLeft() {
//		
//	}
	
	public int getLegendLeftToDataPointMidWidth() {
		return getLegendLeftToDataPointWidth() + 
			   this.dataPointDiameter/2;
	}
	public int getLegendLeftToDataPointRightWidth() {
		return getLegendLeftToDataPointWidth() + 
			   this.dataPointDiameter;
	}
	public int getLegendLeftToTextLeftWidth() {
		return getLegendLeftToDataPointRightWidth() + 
			   this.dataPointToHueValueWidth;
	}
	public int getLegendLeftToTextRightWidth() {
		return getLegendLeftToTextLeftWidth() +
			   this.getLongestTextLength();
	}
	public int getLegendWidth() {
		return getLegendLeftToTextRightWidth() +
			   this.textRightToLegendRightWidth;
	}
	
	
//	public int getimageBottomToLegendBottom() {
//		return cm.imageBottomToChartMidHeight() - this.getLegendHeight()/2;
//	}
	
	public int getBottomLegendToTopHueValueHeight() {
		return getBottomLegendToBottomHueValue() + 
			   ((this.hueValues.length - 1) * this.hueValueSpacingHeight) + 
			   ((this.hueValues.length) * this.hueValueHeight);
	}
	public int getBottomlegentToHueLabelBottomHeight() {
		return getBottomLegendToTopHueValueHeight() + 
			   this.hueValuesTopToHueLabelHeight;
	}
	public int getBottomlegentToHueLabelMidHeight() {
		return getBottomlegentToHueLabelBottomHeight() + 
			   this.hueLabelHeight/2;
	}
	public int getBottomlegentToHueLabelTopHeight() {
		return getBottomlegentToHueLabelBottomHeight() + 
			   this.hueLabelHeight;
	}
	public int getLegendHeight() {
		return getBottomlegentToHueLabelTopHeight() +
			   this.hueLabelToLegendTopHeight;
	}
	
	public int getBottomLegendToHueValueBottomHeight(int i) {
		return getBottomLegendToBottomHueValue() + 
			   ((this.hueValues.length - 1 - i) * (this.hueValueSpacingHeight + this.hueValueHeight));
	}
	
	public int getBottomLegendToHueValueMidHeight(int i) {
		return getBottomLegendToHueValueBottomHeight(i) + 
			   this.hueValueHeight/2;
	}
	
	
	
}
