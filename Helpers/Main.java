package thesis.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Font;
import java.io.ObjectInputStream.GetField;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import thesis.Charter.Axis.BaseAxis;
import thesis.Charter.Axis.BarChartAxis;
import thesis.Charter.Axis.NumericAxis;
import thesis.Charter.Axis.StripChartAxis;
import thesis.Charter.ChartMeasurements.XYChartMeasurements;
import thesis.Charter.Charts.BarChart;
import thesis.Charter.Charts.BoxChart;
import thesis.Charter.Charts.Chart;
import thesis.Charter.Charts.GaugeChart;
import thesis.Charter.Charts.HistogramChart;
import thesis.Charter.Charts.LineChart;
import thesis.Charter.Charts.MultiChart;
import thesis.Charter.Charts.PieChart;
import thesis.Charter.Charts.PolarAreaChart;
import thesis.Charter.Charts.RadarChart;
import thesis.Charter.Charts.ScatterChart;
import thesis.Charter.Charts.StackedAreaChart;
import thesis.Charter.Charts.StackedBarChart;
import thesis.Charter.Charts.StripChart;
import thesis.Charter.Charts.WordCloudChart;
import thesis.Charter.Charts.GridCartogramChart;
import thesis.Charter.Charts.GridCartogramChart.ChartType;
import thesis.Charter.Charts.GridCartogramChart.MapType;
import thesis.Charter.Legend.Legend;
import thesis.Charter.Plots.BarPlot;
import thesis.Charter.Plots.BoxPlot;
import thesis.Charter.Plots.LinePlot;
import thesis.Charter.Plots.PiePlot;
import thesis.Charter.Plots.ScatterPlot;
import thesis.Charter.Plots.StackedBarPlot;
import thesis.Charter.Plots.StripPlot;
import thesis.Charter.StringDrawer.DrawString.xAlignment;
import thesis.Charter.StringDrawer.DrawString.yAlignment;
import thesis.Charter.Styles.Styles;
import thesis.Common.CommonArray;
import thesis.Common.CommonSampleText;
import thesis.DataFrame.*;
import thesis.DataFrame.DataItem.StorageType;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		// correlationChart();
		// histogram();
		// stackedBarChart();
		// pieChart();
		// stripCharting();
		// boxCharting();
		// lineCharting();
		// dateLineCharting();
		// barCharting();
		// scatterCharting();
		// bubbleChart();
		// radarChart();
		// polarAreaChart();
		gaugeChart();
		// stackedAreaChart();
		// usaMapChart();
		// usaMapChartCategory();
		// worldMapChart();
		// wordCloudChart();
		// scatterChartingDiamond();
		// multiChartStylesDemo();
		// multiChart();
		// dfPlay();
		System.out.println("\n\nFINISHED EXECUTION");
	}

	private static void multiChartStylesDemo() {
		DataFrame dfPie = new DataFrame("Datasets/own.csv", true, false);

		Chart chart1 = getLineChart();
		Chart chart2 = getLineChart();
		Chart chart3 = getScatterChart();
		Chart chart4 = getBoxChart();

		// chart1.getLegend().setIncludeLegend(false);

		MultiChart mc = new MultiChart(2, 4);
		mc.setChart(0, 0, kidsChart());
		mc.setChart(3, 1, seabornChart());
		mc.setChart(1, 0, excelChart());
		mc.setChart(1, 1, chartJSChart());
		mc.setChart(2, 0, nighttimeChart());
		mc.setChart(2, 1, infoGramChart());
		mc.setChart(3, 0, matplotlibChart());

		mc.setTitle("Multichart Options");
		mc.setTitleFont(new Font("Dialog", Font.PLAIN, 24));

		mc.create();
		mc.WriteFile("Chart Images/Multi-Chart.png");

	}

	private static void multiChart() {
		DataFrame dfPie = new DataFrame("Datasets/own.csv", true, false);

		Chart chart1 = getLineChart();
		Chart chart2 = getHistogramChart();
		Chart chart3 = getScatterChart();
		Chart chart4 = getBoxChart();

		// chart1.getLegend().setIncludeLegend(false);

		MultiChart mc = new MultiChart(2, 2);
		mc.setChart(0, 0, chart1);
		mc.setChart(0, 1, chart2);
		mc.setChart(1, 0, chart3);
		mc.setChart(1, 1, chart4);
		

		mc.setTitle("Multichart");
		mc.setTitleFont(new Font("Dialog", Font.PLAIN, 24));

		mc.create();
		mc.WriteFile("Chart Images/Multi-Chart.png");

	}

	public static LineChart lineChartForStyle() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("subject", StorageType.String);
		columnTypes.put("timepoint", StorageType.Integer);
		columnTypes.put("event", StorageType.String);
		columnTypes.put("region", StorageType.String);
		columnTypes.put("signal", StorageType.Double);
		DataFrame df = DataFrame.readCSV("Datasets/fmri.csv", true, false, columnTypes);

		LineChart lc = new LineChart(df, "timepoint", "signal");
		lc.getPlot().setShadeUnderLine(true);
		lc.colorCode("event");
		return lc;
	}

	public static LineChart matplotlibChart() {
		LineChart lc = lineChartForStyle();
		lc.setStyle(Styles.Matplotlib);
		lc.setTitle("Matplotlib Style");
		return lc;
	}

	public static LineChart seabornChart() {
		LineChart lc = lineChartForStyle();
		lc.setStyle(Styles.Seaborn);
		lc.setTitle("Seaborn Style");
		return lc;
	}

	public static LineChart excelChart() {
		LineChart lc = lineChartForStyle();
		lc.setStyle(Styles.Excel);
		lc.setTitle("Excel Style");
		return lc;
	}

	public static LineChart chartJSChart() {
		LineChart lc = lineChartForStyle();
		lc.setStyle(Styles.ChartJS);
		lc.setTitle("ChartJS Style");
		return lc;
	}

	public static LineChart nighttimeChart() {
		LineChart lc = lineChartForStyle();
		lc.setStyle(Styles.Nighttime);
		lc.setTitle("Nighttime Style");
		return lc;
	}

	public static LineChart infoGramChart() {
		LineChart lc = lineChartForStyle();
		lc.setStyle(Styles.InfoGram);
		lc.setTitle("InfoGram Style");
		return lc;
	}

	public static LineChart kidsChart() {
		LineChart lc = lineChartForStyle();
		lc.setStyle(Styles.Kids);
		lc.setTitle("Kids Style");
		return lc;
	}

	private static void correlationChart() {
		// DataFrame df = new DataFrame("Datasets/correlation.csv", true, true);
		DataFrame df = new DataFrame("Datasets/duplColNames.csv", true, false);

		System.out.println(df);
	}

	private static void stackedAreaChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("Day", StorageType.Integer);
		columnTypes.put("Team", StorageType.String);
		columnTypes.put("Points", StorageType.Integer);
		DataFrame df = DataFrame.readCSV("Datasets/stacked-area-chart.csv", true, false, columnTypes);
		StackedAreaChart sac = new StackedAreaChart(df, "Day", "Points", "Team");
		// sac.setStyle(Styles.InfoGram);
		sac.setTitle("NBA points per game");
		sac.setTitleFont(new Font("Dialog", Font.PLAIN, 30));
		sac.getAxis().setXAxisLabel("Game Number");

		sac.getPlot().setColorPalette(Palette.Seventies);

		sac.create();
		sac.writeImage("Chart Images/Stacked Area Chart.png");
	}

	public static HistogramChart getHistogramChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("tree_heights", StorageType.Double);
		DataFrame df = DataFrame.readCSV("Datasets/histogram.csv", true, false, columnTypes);
		HistogramChart hc = new HistogramChart(df, "tree_heights");
		hc.getXYChartMeasurements().setPlotWidth(800);
		hc.getXYChartMeasurements().setPlotHeight(200);
		hc.setTitle("Tree Hights");

		hc.getAxis().setYAxisLabel("Tree Heights (m)");
		
		// hc.getPlot().setBarColor(Color.darkGray);
		hc.getPlot().setDrawRugLines(true);
		hc.getPlot().setRugLineColor(Color.darkGray);
		hc.getPlot().setRugLineHeight(20);

		return hc;
	}

	private static void histogram() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("tree_heights", StorageType.Double);
		DataFrame df = DataFrame.readCSV("Datasets/histogram.csv", true, false, columnTypes);
		HistogramChart hc = new HistogramChart(df, "tree_heights");
		hc.getXYChartMeasurements().setPlotWidth(800);
		hc.getXYChartMeasurements().setPlotHeight(200);
		hc.setTitle("Tree Hights");

		hc.getAxis().setYAxisLabel("Tree Heights (m)");
		
		// hc.getPlot().setBarColor(Color.darkGray);
		hc.getPlot().setDrawRugLines(true);
		hc.getPlot().setRugLineColor(Color.darkGray);
		hc.getPlot().setRugLineHeight(20);

		// hc.setStyle(Styles.Seaborn);
		hc.create();
		hc.writeImage("Chart Images/Histogram Chart.png");
	}

	private static void wordCloudChart() {
		String message = CommonSampleText.getStringMessage(800);

		WordCloudChart wcc = new WordCloudChart(message, 20);
		wcc.setNumStringsToShow(10);

		wcc.getPlot().setSmallestFontSize(20);
		wcc.getPlot().setLargestFontSize(60);
		
		wcc.getPlot().setColorPalette(Palette.SnowBoard);

		wcc.getPlot().setXOffset(-30);
		wcc.getPlot().setYOffset(10);

		// wcc.getPlot().setXOffset(-240);
		// wcc.getPlot().setYOffset(30);
		wcc.getChartMeasurements().setImageLeftToPlotLeftWidth(100);
		wcc.getChartMeasurements().setPlotRightToChartRightWidth(100);
		wcc.setTitle("Word Cloud Chart");
		wcc.setTitleFont(new Font("Dialog", Font.PLAIN, 30));
		wcc.create();
		wcc.writeImage("Chart Images/Word Cloud Chart.png");
	}

	private static void usaMapChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("state", StorageType.String);
		columnTypes.put("abbreviation", StorageType.String);
		columnTypes.put("cases-per-day", StorageType.Integer);
		DataFrame df = DataFrame.readCSV("Datasets/usa-map-fake-gradient.csv", true, false, columnTypes);
		GridCartogramChart usa = new GridCartogramChart(df, "abbreviation", "cases-per-day", MapType.USAStates,
				ChartType.Gradient);
		usa.setTitle("Coronavirus cases per day (7 July 2020)");
		usa.setTitleFont(new Font("Dialog", Font.PLAIN, 20));

		usa.getPlot().setSquareOutlineWidth(5);

		usa.create();
		usa.writeImage("Chart Images/USA Map Chart Gradient.png");
	}

	private static void usaMapChartCategory() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("state", StorageType.String);
		columnTypes.put("abbreviation", StorageType.String);
		columnTypes.put("open-carry-status", StorageType.String);
		DataFrame df = DataFrame.readCSV("Datasets/usa-map-category.csv", true, false, columnTypes);
		GridCartogramChart usa = new GridCartogramChart(df, "abbreviation", "open-carry-status", MapType.USAStates,
				ChartType.Category);
		usa.setTitle("Gun Laws");
		usa.setTitleFont(new Font("Dialog", Font.PLAIN, 20));

		usa.getPlot().setColorPalette(Palette.Contrast);

		usa.create();
		usa.writeImage("Chart Images/USA Map Chart Category.png");
	}

	private static void worldMapChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("country", StorageType.String);
		columnTypes.put("abbreviation", StorageType.String);
		columnTypes.put("happiness", StorageType.Integer);
		DataFrame df = DataFrame.readCSV("Datasets/world-map-gradient.csv", true, false, columnTypes);
		GridCartogramChart world = new GridCartogramChart(df, "abbreviation", "happiness", MapType.WorldCountries,
				ChartType.Gradient);

		System.out.println(df.head());

		world.setTitle("Countries by Happiness Level");
		world.setTitleFont(new Font("Dialog", Font.PLAIN, 20));

		world.create();
		world.writeImage("Chart Images/World Map Chart.png");
	}

	private static void gaugeChart() {
		GaugeChart gc = new GaugeChart(10, 5, 30);

		gc.setTitleFont(new Font("Dialog", Font.PLAIN, 50));
		gc.setTitle("Gauge Chart");

		

		// gc.getPlot().setArcColors(new Color[] { Color.RED, Color.ORANGE, Color.GREEN });
		// gc.getPlot().setInnerRadiusDifference(100);

		gc.create();
		gc.drawArrow(150, 125, 100, 280, Color.BLACK, 5);
		
		gc.write("Danger", 140, 110, xAlignment.CenterAlign, yAlignment.MiddleAlign, Color.black, new Font("Dialog", Font.PLAIN, 40), 0);
		gc.writeImage("Chart Images/Gauge Chart.png");
	}

	private static void polarAreaChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("Fruit", StorageType.String);
		columnTypes.put("Quantity", StorageType.Double);
		DataFrame df = DataFrame.readCSV("Datasets/own.csv", true, false, columnTypes);
		PolarAreaChart pac = new PolarAreaChart(df, "Fruit", "Quantity");

		pac.setTitleFont(new Font("Dialog", Font.PLAIN, 60));
		pac.setTitle("Polar Area Chart");
		pac.setStyle(Styles.ChartJS);

		pac.getPlot().setColorPalette(Palette.ChartJS);
		
		pac.getAxis().setAxisColor(Color.magenta);
		pac.getAxis().setAxisWeight(5);

		pac.create();
		pac.writeImage("Chart Images/Polar Area Chart.png");
	}

	private static void radarChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("Fruit", StorageType.String);
		columnTypes.put("Supermarket", StorageType.String);
		columnTypes.put("Quantity", StorageType.Double);
		DataFrame df = DataFrame.readCSV("Datasets/radarchart.csv", true, false, columnTypes);

		RadarChart rc = new RadarChart(df, "Fruit", "Supermarket", "Quantity");

		rc.setTitle("Supermarket Quantities of Fruit");

		rc.getChartMeasurements().setPlotWidth(600);
		rc.getChartMeasurements().setPlotHeight(600);

		rc.setStyle(Styles.ChartJS);

		rc.getAxis().setAxisOutlineColor(Color.red);
		rc.getAxis().setAxisSpikeColor(Color.red);

		rc.create();
		rc.writeImage("Chart Images/Radar Chart.png");
	}

	private static void stackedBarChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("Quarter", StorageType.String);
		columnTypes.put("Region", StorageType.String);
		columnTypes.put("Sales", StorageType.Double);

		DataFrame df = DataFrame.readCSV("Datasets/stacked.csv", true, false, columnTypes);

		StackedBarChart sbc = new StackedBarChart(df, "Quarter", "Sales", "Region");
		StackedBarPlot plot = sbc.getPlot();
		plot.setDrawValuesOnBar(false);
		plot.setColorPalette(Palette.Seventies);
		// sbc.setStyle(Styles.ChartJS);

		sbc.getXYChartMeasurements().setPlotWidth(400);
		sbc.getXYChartMeasurements().setPlotHeight(400);

		sbc.getXYChartMeasurements().setTopAxisLabelToTitleHeight(20);

		sbc.setTitle("Sales by region and Quater");
		sbc.setTitleFont(new Font("Dialog", Font.PLAIN, 30));

		sbc.create();
		sbc.writeImage("Chart Images/Stacked Bar Chart.png");
	}

	public static Chart getPieChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("Fruit", StorageType.String);
		columnTypes.put("Quantity", StorageType.Double);
		DataFrame df = DataFrame.readCSV("Datasets/own.csv", true, false, columnTypes);
		PieChart pc = new PieChart(df, "Fruit", "Quantity");
		PiePlot plot = pc.getPlot();

		pc.setTitle("Quantity of Fruit");

		// plot.setShatter(new double[] { 0.3, 0.3, 0.3, 0.3, 0.3 });

		// plot.setIncludeProportionsOnPie(true);
		// plot.setProportionsColor(Color.WHITE);

		// plot.setDonutAmount(0.5f);

		pc.setStyle(Styles.ChartJS);

		pc.getPlot().setColorPalette(Palette.Mystery);

		pc.setTitleFont(new Font("Dialog", Font.PLAIN, 50));

		return pc;
	}

	public static void pieChart() {
		Chart pc = getPieChart();

		pc.create();
		pc.writeImage("Chart Images/Pie Chart.png");
	}

	public static Chart getStripChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("total_bill", StorageType.Double);
		columnTypes.put("tip", StorageType.Double);
		columnTypes.put("sex", StorageType.String);
		columnTypes.put("smoker", StorageType.String);
		columnTypes.put("day", StorageType.String);
		columnTypes.put("time", StorageType.String);
		columnTypes.put("size", StorageType.Integer);
		DataFrame df = DataFrame.readCSV("Datasets/tips.csv", true, false, columnTypes);

		// StripChart sc = new StripChart(df, "total_bill");
		StripChart sc = new StripChart(df, "day", "total_bill");
		sc.colorCode("smoker");

		// StripChart sc = new StripChart(df, "sex", "total_bill");
		// sc.colorCode("day");

		sc.setOrder(new String[] { "Thur", "Fri", "Sat", "Sun" });

		BaseAxis axis = sc.getAxis();
		axis.setXAxisFont(new Font("Dialog", Font.PLAIN, 80));

		StripPlot plot = sc.getPlot();
		plot.setDodge(false);

		XYChartMeasurements cm = sc.getXYChartMeasurements();
		cm.setPlotWidth(900);
		cm.setPlotHeight(250);

		sc.setStyle(Styles.ChartJS);

		return sc;
	}

	public static void stripCharting() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("total_bill", StorageType.Double);
		columnTypes.put("tip", StorageType.Double);
		columnTypes.put("sex", StorageType.String);
		columnTypes.put("smoker", StorageType.String);
		columnTypes.put("day", StorageType.String);
		columnTypes.put("time", StorageType.String);
		columnTypes.put("size", StorageType.Integer);
		DataFrame df = DataFrame.readCSV("Datasets/tips.csv", true, false, columnTypes);
		// StripChart sc = new StripChart(df, "total_bill");
		StripChart sc = new StripChart(df, "day", "total_bill");
		// sc.colorCode("smoker");

		// sc.setOrder(new String[] { "Thur", "Fri", "Sat", "Sun" });
		StripChartAxis axis = sc.getAxis();
		
		StripPlot plot = sc.getPlot();
		// plot.setDodge(false);
		XYChartMeasurements cm = sc.getXYChartMeasurements();
		
		axis.setXAxisLabel("Days of week");
		axis.setYAxisLabel("Total Bill");
		sc.setTitle("Total bill for patrons");
		// sc.setStyle(Styles.ChartJS);
		// axis.setOrientation("h");
		plot.setColorPalette(Palette.Fire);
		sc.create();
		sc.writeImage("Chart Images/Strip Chart.png");
	}

	private static Chart getBoxChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("total_bill", StorageType.Double);
		columnTypes.put("tip", StorageType.Double);
		columnTypes.put("sex", StorageType.String);
		columnTypes.put("smoker", StorageType.String);
		columnTypes.put("day", StorageType.String);
		columnTypes.put("time", StorageType.String);
		columnTypes.put("size", StorageType.Integer);
		DataFrame df = DataFrame.readCSV("Datasets/tips.csv", true, false, columnTypes);
		// BoxChart bc = new BoxChart(df, "total_bill");
		BoxChart bc = new BoxChart(df, "day", "total_bill");
		bc.colorCode("smoker");

		// bc.setOrder(new String[] { "Thur", "Fri", "Sat", "Sun" });

	

		XYChartMeasurements cm = bc.getXYChartMeasurements();

		cm.setPlotWidth(400);
		cm.setPlotHeight(400);

		// bc.getPlot().setColorPalette(Palette.Seventies);
		// bc.setStyle(Styles.Nighttime);

		bc.getAxis().setXAxisLabel("Day of the week");
		bc.getAxis().setYAxisLabel("Tip Size($)");
		bc.setTitle("Tip Size");

		BoxPlot plot = bc.getPlot();
		// plot.setColorPalette(Palette.generateUniqueColors(10));
		return bc;
	}

	public static void boxCharting() {
		Chart bc = getBoxChart();

		bc.create();
		bc.writeImage("Chart Images/Box Chart.png");
	}

	public static void dateLineCharting() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("date", StorageType.LocalDate);
		columnTypes.put("value", StorageType.Integer);

		DataFrame df = DataFrame.readCSV("Datasets/date_line.csv", true, false, columnTypes);
		LineChart lc = new LineChart(df, "date", "value");

		lc.create();

		System.out.println(df);
	}

	public static LineChart getLineChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("subject", StorageType.String);
		columnTypes.put("timepoint", StorageType.Integer);
		columnTypes.put("event", StorageType.String);
		columnTypes.put("region", StorageType.String);
		columnTypes.put("signal", StorageType.Double);
		columnTypes.put("letter", StorageType.String);
		DataFrame df = DataFrame.readCSV("Datasets/fmri.csv", true, false, columnTypes);

		LineChart lc = new LineChart(df, "timepoint", "signal");
		lc.getPlot().setShadeUnderLine(true);
		// lc.setStyle(Styles.InfoGram);
		lc.colorCode("subject");
		// lc.getPlot().setColorPalette(Palette.generateUniqueColors(14));
		lc.getPlot().setColorPalette(Palette.generateUniqueColors(14));

		lc.setTitle("Timepoint vs. Signal");
		return lc;
	}

	public static void lineCharting() {

		// BaseAxis axis = lc.getAxis();
		// LinePlot plot = lc.getPlot();
		//
		// plot.setShadeUnderLine(true);
		//
		// plot.setLineColor(Color.RED);
		// plot.setLineThickness(2);
		// plot.setMarkerDotColor(Color.WHITE);
		//// plot.setMarkerDotOutlineColor(Color.BLACK);
		//
		// plot.setColorPalette(Palette.generateUniqueColors(14));
		//
		//// lc.colorCode("subject");
		//
		// axis.setXAxisLabel("sepal_length");
		// axis.setYAxisLabel("sepal_width");

		// lc.setTitle("sepal_length vs sepal_width");
		// lc.setTitleFont(new Font("Dialog", Font.PLAIN, 20));
		Chart lc = getLineChart();
		lc.create();
		lc.writeImage("Chart Images/Line Chart.png");

	}

	private static void barCharting() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("total_bill", StorageType.Double);
		columnTypes.put("tip", StorageType.Double);
		columnTypes.put("sex", StorageType.String);
		columnTypes.put("smoker", StorageType.String);
		columnTypes.put("day", StorageType.String);
		columnTypes.put("time", StorageType.String);
		columnTypes.put("size", StorageType.Integer);
		columnTypes.put("category", StorageType.String);
		DataFrame df = DataFrame.readCSV("Datasets/tips_mod.csv", true, false, columnTypes);
		BarChart bc = new BarChart(df, "day", "total_bill");

		bc.setTitle("Tip Size");

		bc.colorCode("sex");

		// bc.setStyle(Styles.Nighttime);
		bc.setTitleFont(new Font("Dialog", Font.PLAIN, 20));
		// bc.getAxis().setOrientation("h");
		// bc.getPlot().setBarWidthPercentage(0.1f);

		bc.create();
		bc.writeImage("Chart Images/Bar Chart.png");
	}

	private static void scatterChartingDiamond() {
		DataFrame dfDiamonds = new DataFrame("Datasets/diamonds.csv", true, false);

		ScatterChart themeCategorical = new ScatterChart(dfDiamonds, "carat", "price");

		themeCategorical.colorCode("clarity");

		themeCategorical.setTitle("Diamon Analysis by clarity");
		themeCategorical.setTitleFont(new Font("Dialog", Font.PLAIN, 20));

		themeCategorical.create();
		themeCategorical.writeImage("Chart Images/Scatter Chart (diamond).png");
	}

	private static void bubbleChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		columnTypes.put("age", StorageType.Double);
		columnTypes.put("height", StorageType.Double);
		columnTypes.put("gender", StorageType.String);
		columnTypes.put("wealth", StorageType.Double);
		DataFrame df = DataFrame.readCSV("Datasets/bubble.csv", true, false, columnTypes);
		ScatterChart sc = new ScatterChart(df, "age", "height");
		NumericAxis axis = sc.getAxis();
		ScatterPlot plot = sc.getPlot();
		Legend legend = sc.getLegend();
		sc.colorCode("gender");
		sc.setBubbleSize("wealth");
		sc.setStyle(Styles.ChartJS);

		sc.create();
		sc.writeImage("Chart Images/Bubble Chart.png");

	}

	private static Chart getScatterChart() {
		Map<String, StorageType> columnTypes = new HashMap<String, StorageType>();
		// columnTypes.put("total_bill", StorageType.Double);
		// columnTypes.put("tip", StorageType.Double);
		// columnTypes.put("sex", StorageType.String);
		// columnTypes.put("smoker", StorageType.String);
		// columnTypes.put("day", StorageType.String);
		// columnTypes.put("time", StorageType.String);
		// columnTypes.put("size", StorageType.Integer);
		columnTypes.put("sepal_length", StorageType.Double);
		columnTypes.put("sepal_width", StorageType.Double);
		columnTypes.put("petal_length", StorageType.Double);
		columnTypes.put("petal_width", StorageType.Double);
		columnTypes.put("species", StorageType.String);

		DataFrame df = DataFrame.readCSV("Datasets/iris.csv", true, false, columnTypes);

		ScatterChart sc = new ScatterChart(df, "sepal_length", "sepal_width");
		sc.setStyle(Styles.ChartJS);

		sc.getAxis().setXAxisLabel("sepal_length");
		sc.getAxis().setYAxisLabel("sepal_width");
		sc.setTitle("Weight vs. Height of Students");

		// sc.getPlot().setPlotBackgroundImage("water.jpg");

		NumericAxis axis = sc.getAxis();
		
		// axis.includeXAxisLinesOnPlot(false);
		// axis.includeYAxisLinesOnPlot(false);

		sc.colorCode("species");
		// sc.setBubbleSize("size");

		return sc;
	}

	private static void scatterCharting() {

		Chart sc = getScatterChart();

		sc.create();
		sc.writeImage("Chart Images/Scatter Chart.png");

	}

	private static void dfPlay() {
		DataFrame df = play();
		// DataFrame df = csvConstructor();
		// DataFrame df = hashColsConstructor();
		// DataFrame df = hashRowsConstructor();
		// DataFrame df = existingDataFrame();
		// DataFrame df = oneColumn();
		// DataFrame df = multipleColumns();
		// DataFrame df = arrays();
		// DataFrame df = rowColNameEmplty();
		// DataFrame df = rowColNameEmpltyArray();

		// DataFrame df = time();
		// DataFrame df = groupBy();
		System.out.println(df);

	}

	private static DataFrame rowColNameEmpltyArray() {
		String[] rowNames = new String[] { "a", "a", "a", "a", "b", "b" };
		String[] colNames = new String[] { "q", "q", "q", "w", "w", "w" };
		DataFrame df = new DataFrame(colNames, rowNames);

		return df;
	}

	private static DataFrame rowColNameEmplty() {
		ArrayList<String> rowNames = new ArrayList<>();
		rowNames.add("a");
		rowNames.add("a");
		rowNames.add("a");
		rowNames.add("b");
		rowNames.add("b");
		rowNames.add("b");

		ArrayList<String> colNames = new ArrayList<>();
		colNames.add("r");
		colNames.add("r");
		colNames.add("r");
		colNames.add("g");
		colNames.add("g");
		colNames.add("g");

		DataFrame df = new DataFrame(colNames, rowNames);
		df.resetRowNames();

		rowNames = new ArrayList<>();
		rowNames.add("a");
		rowNames.add("a");
		rowNames.add("a");
		rowNames.add("b");
		rowNames.add("b");
		rowNames.add("b");
		df.setRowNames(rowNames);

		return df;
	}

	public static DataFrame time() {
		DataFrame df = new DataFrame("Datasets/time.csv", true, false);
		df.setColumnType(0, DataItem.StorageType.LocalDate);
		df.setColumnType(1, DataItem.StorageType.Integer);
		System.out.println(df.getValue(0, 0).getType());
		System.out.println(df.getValue(1, 0).getType());

		return df;
	}

	public static DataFrame groupBy() {
		DataFrame df = new DataFrame(3, 12, null);
		String[] cities = new String[] { "new york", "new york", "new york", "new york", "mumbai", "mumbai", "mumbai",
				"mumbai", "paris", "paris", "paris", "paris" };
		int[] temperatures = new int[] { 32, 36, 28, 33, 90, 85, 87, 92, 45, 50, 54, 42 };

		int[] humidity = new int[] { 12, 43, 25, 74, 52, 23, 75, 45, 98, 34, 52, 15 };

		df.setColumnNames(new String[] { "city", "temperature", "humitidy" });

		df.setColumnValues(0, cities);
		df.setColumnValues(1, temperatures);
		df.setColumnValues(2, humidity);

		df.swapTwoColumns(0, 1);

		System.out.println(df);

		GroupBy gb = df.groupBy("city");

		DataFrame ave = gb.average();
		System.out.println("average:");
		System.out.println(ave);

		DataFrame max = gb.max();
		System.out.println("max:");
		System.out.println(max);

		DataFrame min = gb.min();
		System.out.println("min:");
		System.out.println(min);

		DataFrame mediun = gb.mediun();
		System.out.println("mediun:");
		System.out.println(mediun);

		DataFrame sum = gb.sum();
		System.out.println("sum:");
		System.out.println(sum);

		DataFrame product = gb.product();
		System.out.println("product:");
		System.out.println(product);

		DataFrame numUnique = gb.numUnique();
		System.out.println("numUnique:");
		System.out.println(numUnique);

		DataFrame variance = gb.variance(1);
		System.out.println("variance:");
		System.out.println(variance);

		DataFrame std = gb.standardDeviation(1);
		System.out.println("standardDeviation:");
		System.out.println(std);
		return df;
	}

	public static DataFrame play() {

		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("col_n");
		columnNames.add("col_f");
		columnNames.add("col_z");
		// columnNames.add("col_r");
		// columnNames.add("col_w");
		// columnNames.add("col_A");
		// columnNames.add("col_S");
		// columnNames.add("col_m");
		// columnNames.add("col_l");
		// columnNames.add("col_t");

		ArrayList<String> rowNames = new ArrayList<String>();
		rowNames.add("row_one");
		rowNames.add("row_two");
		rowNames.add("row_three");
		rowNames.add("row_four");
		rowNames.add("row_five");
		// rowNames.add("row_six");
		// rowNames.add("row_seven");
		// rowNames.add("row_eight");
		// rowNames.add("row_nine");
		// rowNames.add("row_ten");

		int[][] arrs = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };

		DataFrame df = new DataFrame(new String[] { "one", "two", "three" }, arrs, true);

		df.insertColumn(1, "new_col", Color.RED);

		return df;
	}

	private static DataFrame hashColsConstructor() {
		HashMap<String, List<Object>> map = new HashMap<String, List<Object>>();

		List<Object> arr1 = new ArrayList<Object>();
		arr1.add(1);
		arr1.add(2);
		arr1.add(3);

		List<Object> arr2 = new ArrayList<Object>();
		arr2.add(3);
		arr2.add(4);
		arr2.add(5);

		map.put("one", arr1);
		map.put("two", arr2);

		DataFrame df = new DataFrame(map, false);
		return df;
	}

	private static DataFrame csvConstructor() {
		DataFrame df = new DataFrame("Datasets/date_data.csv", true, false);
		df.setColumnType(0, DataItem.StorageType.LocalDate);
		return df;
	}

	private static DataFrame hashRowsConstructor() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("one", 1);
		map1.put("two", 2);
		map1.put("three", 3);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("one", 10);
		map2.put("two", 20);
		map2.put("three", 30);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);

		DataFrame df = new DataFrame(list, true);

		return df;
	}

	private static DataFrame existingDataFrame() {
		DataFrame df = new DataFrame(hashRowsConstructor());
		return df;
	}

	private static DataFrame oneColumn() {
		ArrayList<Object> arr = new ArrayList<Object>();
		arr.add(1);
		arr.add(2);
		arr.add(3);

		DataFrame df = new DataFrame("hello", arr, true);
		return df;
	}

	public static DataFrame multipleColumns() {
		List<Object> arr1 = new ArrayList<Object>();
		arr1.add(1);
		arr1.add(2);
		arr1.add(3);

		List<Object> arr2 = new ArrayList<Object>();
		arr2.add(4);
		arr2.add(5);
		arr2.add(6);

		List<Object> arr3 = new ArrayList<Object>();
		arr3.add(7);
		arr3.add(8);
		arr3.add(9);

		List<List<Object>> combined = new ArrayList<List<Object>>();
		combined.add(arr1);
		combined.add(arr2);
		combined.add(arr3);

		ArrayList<String> names = new ArrayList<String>();
		names.add("one");
		names.add("two");
		names.add("three");

		DataFrame df = new DataFrame(names, combined, true);

		return df;

	}

	public static DataFrame arrays() {
		Integer[] list = new Integer[] { 1, 2, 3, 4, 5 };

		DataFrame df = new DataFrame("hello", list, true);

		return df;

	}

	private static void numJaPlay() {
		// NumJa numJa = new NumJa();

	}

}
