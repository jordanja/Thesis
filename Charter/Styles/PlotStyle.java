package thesis.Charter.Styles;

import java.awt.Color;
import java.awt.Image;

public interface PlotStyle {
	// Plot
	public abstract boolean getDrawBottomPlotOutline(); 
	public abstract boolean getDrawLeftPlotOutline();
	public abstract boolean getDrawTopPlotOutline();
	public abstract boolean getDrawRightPlotOutline();
	
	public abstract Color getBottomPlotOutlineColor();
	public abstract Color getLeftPlotOutlineColor();
	public abstract Color getTopPlotOutlineColor();
	public abstract Color getRightPlotOutlineColor();
	
	public abstract int getBottomPlotOutlineWidth();
	public abstract int getLeftPlotOutlineWidth();
	public abstract int getTopPlotOutlineWidth();
	public abstract int getRightPlotOutlineWidth();
	
	public abstract Color getPlotBackgroundColor();
	public abstract Image getPlotBackgroundImage();
	
	public abstract Color[] getColorPalette();
	
	// Box Plot
	public abstract Color getBoxPlotOutlineColor();
}
