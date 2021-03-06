package thesis.DataFrame;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import thesis.Common.CommonArray;
import thesis.Common.CommonMath;
import thesis.Exceptions.DataItemTypeException;


public class DataItem {

	public enum StorageType { 
		String,
		Integer, 
		Double, 
		Null,
		Boolean, 
		LocalDate,
		LocalTime,
		LocalDateTime, 
		Period,
		Duration,
		BigDecimal,
		Color
	};
	
	private StorageType type;

	private String stringValue;
	private Integer intValue;
	private Double doubleValue;
	private Boolean booleanValue;
	private LocalDate localDateValue;
	private LocalTime localTimeValue;
	private LocalDateTime localDateTimeValue;
	private Period periodValue;
	private Duration durationValue;
	private BigDecimal bigDecimalValue;
	private Color colorValue;

	public DataItem() {
		this.type = StorageType.Null;
	}

	public DataItem(Object value, StorageType type) {
		initializeObjectValue(type, value);
	}

	public DataItem(Object value) {
		StorageType typeOfObject = DataItem.getStorageTypeOfObject(value);
		if (value instanceof DataItem) {
			this.replicateProperties((DataItem)value);
		} else {			
			initializeObjectValue(typeOfObject, value);
		}
	}
	
	public static StorageType getStorageTypeOfObject(Object value) {
		if (value == null) {			
			return StorageType.Null;
		} else {
			return javaClassToStorageType(value.getClass());
		}
	}

	
	public DataItem(DataItem item) {
		replicateProperties(item);
	}
	
	// String Value
	public DataItem(String value) {
		this.stringValue = value;
		this.type = StorageType.String;
	}

	// Integer Value
	public DataItem(Integer value) {
		this.intValue = value;
		this.type = StorageType.Integer;
	}

	public DataItem(int value) {
		this.intValue = value;
		this.type = StorageType.Integer;
	}

	// Double Value
	public DataItem(Double value) {
		this.doubleValue = value;
		this.type = StorageType.Double;
	}

	public DataItem(double value) {
		this.doubleValue = value;
		this.type = StorageType.Double;
	}
	
	// Float Value
	public DataItem(Float value) {
		this.doubleValue = value.doubleValue();
		this.type = StorageType.Double;
	}

	public DataItem(float value) {
		this.doubleValue = (double) value;
		this.type = StorageType.Double;
	}
	
	// Boolean Value
	public DataItem(Boolean value) {
		this.booleanValue = value;
		this.type = StorageType.Boolean;
	}
	
	public DataItem(boolean value) {
		this.booleanValue = value;
		this.type = StorageType.Boolean;
	}
	
	// Date Value
	public DataItem(LocalDate value) {
		if (value != null) {
			this.localDateValue = value;
			this.type = StorageType.LocalDate;
		} else {
			this.type = StorageType.Null;
		}
	}
	
	// Time Value
	public DataItem(LocalTime value) {
		if (value != null) {
			this.localTimeValue = value;
			this.type = StorageType.LocalTime;
		} else {
			this.type = StorageType.Null;
		}
	}
	
	// DateTime Value
	public DataItem(LocalDateTime value) {
		if (value != null) {
			this.localDateTimeValue = value;
			this.type = StorageType.LocalDateTime;
		} else {
			this.type = StorageType.Null;
		}
	}

	// DateTime Value
	public DataItem(Period value) {
		if (value != null) {
			this.periodValue = value;
			this.type = StorageType.Period;
		} else {
			this.type = StorageType.Null;
		}
	}
	
	// DateTime Value
	public DataItem(Duration value) {
		if (value != null) {
			this.durationValue = value;
			this.type = StorageType.Duration;
		} else {
			this.type = StorageType.Null;
		}
	}
	
	// BigDecimal Value
	public DataItem(BigDecimal value) {
		if (value != null) {
			this.bigDecimalValue = value;
			this.type = StorageType.BigDecimal;
		} else {
			this.type = StorageType.Null;
		}
	}
	
	// Color Value
	public DataItem(Color value) {
		if (value != null) {
			this.colorValue = value;
			this.type = StorageType.Color;
		} else {
			this.type = StorageType.Null;
		}
	}
	

	private void replicateProperties(DataItem item) {
		this.type = item.getType();
		if (this.type == StorageType.Integer) {			
			this.intValue = item.getIntegerValue().intValue();
		} else if (this.type == StorageType.Double) {			
			this.doubleValue = item.getDoubleValue();
		} else if (this.type == StorageType.String) {
			this.stringValue = item.getStringValue();
		} else if (this.type == StorageType.Boolean) {
			this.booleanValue = item.getBooleanValue().booleanValue();
		} else if (this.type == StorageType.LocalDate) {
			this.localDateValue = item.getDateValue();
		} else if (this.type == StorageType.LocalTime) {
			this.localTimeValue = item.getTimeValue();
		} else if (this.type == StorageType.LocalDateTime) {
			this.localDateTimeValue = item.getDateTimeValue();
		} else if (this.type == StorageType.Period) {
			this.periodValue = item.getPeriodValue();
		} else if (this.type == StorageType.Duration) {
			this.durationValue = item.getDurationValue();
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = item.getBigDecimalValue();
		} else if (this.type == StorageType.Color) {
			this.colorValue = item.getColorValue();
		}
	}
	
	private void initializeObjectValue(StorageType typeToUse, Object value) {
		try {			
			this.type = typeToUse;
			if (this.type == StorageType.String) {
				this.stringValue = value.toString();
			} else if (this.type == StorageType.Integer) {
				this.intValue = Integer.valueOf(value.toString());
			} else if (this.type == StorageType.Double) {
				this.doubleValue = Double.valueOf(value.toString());
			} else if (this.type == StorageType.Boolean) {
				this.booleanValue = parseBoolean(value.toString());
			} else if (this.type == StorageType.LocalDate) {
				this.localDateValue = LocalDate.parse(value.toString());
			} else if (this.type == StorageType.LocalTime) {
				this.localTimeValue = LocalTime.parse(value.toString());
			} else if (this.type == StorageType.LocalDateTime) {
				this.localDateTimeValue = LocalDateTime.parse(value.toString());
			} else if (this.type == StorageType.Period) {
				this.periodValue = Period.parse(value.toString());
			} else if (this.type == StorageType.Duration) {
				this.durationValue = Duration.parse(value.toString());
			} else if (this.type == StorageType.BigDecimal) {
				this.bigDecimalValue = new BigDecimal(value.toString());
			} else if (this.type == StorageType.Color) {
				this.colorValue = (Color) value;
			} else if (this.type == StorageType.Null) {
				
			} else {
				throw new DataItemTypeException("You have entered an incompatible type: " + value.getClass());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("incomplete-switch")
	public void setType(StorageType typeToUse) {
		
		if (this.type == typeToUse) {
			return;
		}
		
		String exceptionString = "Can't conver from " + this.type + " to " + typeToUse;
		try {

			if (this.type == StorageType.String) {
				// Current type is String
				switch (typeToUse) {
					case Integer:
						// Convert String to Integer
						this.intValue = Integer.parseInt(this.stringValue);
						this.stringValue = null;
						break;
					case Double:
						// Convert String to Double
						this.doubleValue = Double.parseDouble(this.stringValue);
						this.stringValue = null;
						break;
					case Boolean:
						// Convert String to Boolean
						this.booleanValue = parseBoolean(this.stringValue);
						this.stringValue = null;
						break;
					case LocalDate:
						// Convert String to LocalDate 
						this.localDateValue = LocalDate.parse(this.stringValue);
						this.stringValue = null;
						break;
					case LocalTime:
						// Convert String to LocalTime
						this.localTimeValue = LocalTime.parse(this.stringValue);
						this.stringValue = null;
						break;
					case LocalDateTime:
						// Convert String to LocalDateTime
						this.localDateTimeValue = LocalDateTime.parse(this.stringValue);
						this.stringValue = null;
						break;
					case Period:
						// Convert String to Period
						this.periodValue = Period.parse(this.stringValue);
						this.stringValue = null;
						break;
					case Duration:
						// Convert String to LocalDateTime
						this.durationValue = Duration.parse(this.stringValue);
						this.stringValue = null;
					case BigDecimal:
						// Convert String to BigDecimal
						this.bigDecimalValue = new BigDecimal(this.stringValue);
						this.stringValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
				
			} else if (this.type == StorageType.Integer) {
				// Current type is Integer
				switch(typeToUse) {
					case String:
						// Convert from Integer to String
						this.stringValue = this.intValue.toString();
						this.intValue = null;
						break;
					case Double:
						// Convert from Integer to Double
						this.doubleValue = this.intValue.doubleValue();
						this.intValue = null;
						break;
					case BigDecimal:
						// Convert from Integer to BigDecimal
						this.bigDecimalValue = new BigDecimal(this.intValue);
						this.intValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.Double) {
				// Current type is Double
				switch(typeToUse) {
					case String:
						// Convert from Double to String
						this.stringValue = this.doubleValue.toString();
						this.doubleValue = null;
						break;
					case Integer:
						// Convert from Double to Integer
						this.intValue = this.doubleValue.intValue();
						this.doubleValue = null;
					case BigDecimal:
						// Convert from Double to BigDecimal
						this.bigDecimalValue = new BigDecimal(this.doubleValue);
						this.doubleValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.Boolean) {
				// Current type is Boolean
				switch(typeToUse) {
					case Integer:
						// Convert from Boolean to Integer
						this.intValue = this.booleanValue ? 1 : 0;
						this.booleanValue = null;
						break;
					case Double:
						// Convert from Boolean to Double
						this.doubleValue = this.booleanValue ? 1.0 : 0.0;
						this.booleanValue = null;
						break;
					case String:
						// Convert from Boolean to String
						this.stringValue = this.booleanValue.toString();
						this.booleanValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.LocalDate) {
				// Current type is LocalDate
				switch(typeToUse) {
					case String:
						// Convert LocalDate to String
						this.stringValue = this.localDateValue.toString();
						this.localDateValue = null;
						break;
					case LocalDateTime:
						// Convert LocalDate to LocalDateTime
						this.localDateTimeValue = this.localDateValue.atStartOfDay();
						this.localDateValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.LocalTime) {
				// Current type is LocalTime
				switch(typeToUse) {
					case String:
						// Convert LocalTime to String
						this.stringValue = this.localTimeValue.toString();
						this.localTimeValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.LocalDateTime) {
				// Current type is LocalDateTime
				switch(typeToUse) {
					case String:
						// Convert LocalDateTime to String
						this.stringValue = this.localDateTimeValue.toString();
						this.localDateTimeValue = null;
						break;
					case LocalDate:
						// Convert LocalDateTime to LocalDate
						this.localDateValue = this.localDateTimeValue.toLocalDate();
						this.localDateTimeValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.Period) {
				// Current type is Period
				switch(typeToUse) {
					case String:
						// Convert from Period to String;
						this.stringValue = this.periodValue.toString();
						this.periodValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.Duration) {
				// Current type is Duration
				switch(typeToUse) {
					case String:
						// Convert from Duration to String;
						this.stringValue = this.durationValue.toString();
						this.durationValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.BigDecimal) {
				// Current type is BigDecimal
				switch(typeToUse) {
					case String:
						// Convert from BigDecimal to String
						this.stringValue = this.bigDecimalValue.toPlainString();
						this.bigDecimalValue = null;
						break;
					case Integer:
						// Convert from BigDecimal to Integer
						this.intValue = this.bigDecimalValue.intValue();
						this.bigDecimalValue = null;
						break;
					case Double:
						// Convert from BigDecimal to Double
						this.doubleValue = this.bigDecimalValue.doubleValue();
						this.bigDecimalValue = null;
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.Color) {
				// Current type if Color
				switch(typeToUse) {
					case String:
						// Convert from Color to String
						this.stringValue = this.colorValue.toString();
						break;
					default:
						throw new DataItemTypeException(exceptionString);
				}
			} else if (this.type == StorageType.Null) {
				this.stringValue = null;
				this.intValue = null;
				this.doubleValue = null;
				this.booleanValue = null;
				this.localDateValue = null;
				this.localTimeValue = null;
				this.localDateTimeValue = null;
				this.periodValue = null;
				this.durationValue = null;
				this.bigDecimalValue = null;
				this.colorValue = null;
			}
			this.type = typeToUse;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public StorageType getType() {
		return this.type;
	}

	public Object getObjectValue() {
		if (this.type == StorageType.String) {
			return this.stringValue;
		} else if (this.type == StorageType.Integer) {
			return this.intValue;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue;
		} else if (this.type == StorageType.Boolean) {
			return this.booleanValue;
		} else if (this.type == StorageType.LocalDate) {
			return this.localDateValue;
		} else if (this.type == StorageType.LocalTime) {
			return this.localTimeValue;
		} else if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue;
		} else if (this.type == StorageType.Period) {
			return this.periodValue;
		} else if (this.type == StorageType.Duration) {
			return this.durationValue;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue;
		} else if (this.type == StorageType.Color) {
			return this.colorValue;
		} else if (this.type == StorageType.Null) {
			return "null";
		}
		return null;
	}

	private Boolean parseBoolean(String str) {
		return (str.equals("True") || 
				str.equals("true") ||
				str.equals("T") ||
				str.equals("t") ||
				str.equals("Yes") ||
				str.equals("yes") || 
				str.equals("Y") ||
				str.equals("y")
		);
	}

	public static DataItem[] convertToDataItemList(Object[] values) {
		DataItem[] dataItems = new DataItem[values.length];
		for (int i = 0; i < values.length; i++) {
			dataItems[i] = new DataItem(values[i]);
		}
		return dataItems;
	}

	public static Double[] convertToDoubleList(DataItem[] dataItemList) {
		Double[] doubleList = new Double[dataItemList.length];
		for (int i = 0; i < dataItemList.length; i++) {
			doubleList[i] = dataItemList[i].getValueConvertedToDouble();
		}
		return doubleList;
	}
	
	public static double[] convertToPrimitiveDoubleList(DataItem[] dataItemList) {
		double[] doubleList = new double[dataItemList.length];
		for (int i = 0; i < dataItemList.length; i++) {
			doubleList[i] = dataItemList[i].getValueConvertedToDouble();
		}
		return doubleList;
	}

	public static String[] convertToStringList(DataItem[] dataItemList) {
		String[] stringList = new String[dataItemList.length];
		for (int i = 0; i < dataItemList.length; i++) {
			stringList[i] = dataItemList[i].getValueConvertedToString();
		}
		return stringList;
	}
	
	public String getStringValue() {
		return this.stringValue;
	}

	public Integer getIntegerValue() {
		return this.intValue;
	}

	public double getDoubleValue() {
		return this.doubleValue;
	}

	public Boolean getBooleanValue() {
		return this.booleanValue;
	}
	
	public LocalDate getDateValue() {
		return this.localDateValue;
	}
	
	public LocalTime getTimeValue() {
		return this.localTimeValue;
	}
	
	public LocalDateTime getDateTimeValue() {
		return this.localDateTimeValue;
	}

	public Period getPeriodValue() {
		return this.periodValue;
	}
	
	public Duration getDurationValue() {
		return this.durationValue;
	}
	
	public BigDecimal getBigDecimalValue() {
		return this.bigDecimalValue;
	}
	
	public Color getColorValue() {
		return this.colorValue;
	}
	
	public String getValueConvertedToString() {
		return getObjectValue().toString();
	}

	public boolean isNumber() {
		return this.type == StorageType.Integer || this.type == StorageType.Double || this.type == StorageType.BigDecimal;
	}
	
	public Double getValueConvertedToDouble() {
		if (this.type == StorageType.Integer) {
			return this.intValue.doubleValue();
		} else if (this.type == StorageType.Double) {
			return this.doubleValue.doubleValue();
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.doubleValue();
		} else if (this.type == StorageType.String) {
			return Double.parseDouble(this.stringValue);
		}
		return null;
	}
	
	public Float getValueConvertedToFloat() {
		if (this.type == StorageType.Integer) {
			return this.intValue.floatValue();
		} else if (this.type == StorageType.Double) {
			return this.doubleValue.floatValue();
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.floatValue();
		} else if (this.type == StorageType.String) {
			return Float.parseFloat(this.stringValue);
		}
		return null;
	}
	
	public Integer getValueConvertedToInt() {
		if (this.type == StorageType.Integer) {
			return this.intValue;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue.intValue();
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.intValue();
		} else if (this.type == StorageType.String) {
			return Integer.parseInt(this.stringValue);
		}
		return null;
	}
	
	public Boolean getValueConvertedToBoolean() {
		if (this.type == StorageType.Boolean) {
			return this.booleanValue;
		} else if (this.type == StorageType.String) {
			return parseBoolean(this.stringValue);
		}
		
		return null;
	}

	public Number getValueConvertedToNumber() {
		if (this.type == StorageType.Integer) {
			return this.intValue;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue;
		} else if (this.type == StorageType.String) {
			return Double.parseDouble(this.stringValue);
		}
			
		return null;
	}
	
	public void add(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue += value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue += value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.add(new BigDecimal(value));
			
		}
	}
	
	public void add(Period timePeriod) {
		if (this.type == StorageType.LocalDate) {
			this.localDateValue = this.localDateValue.plus(timePeriod);
		} else if (this.type == StorageType.LocalDateTime) {
			this.localDateTimeValue = this.localDateTimeValue.plus(timePeriod);
		} else if (this.type == StorageType.LocalTime) {
			this.localTimeValue = this.localTimeValue.plus(timePeriod);
		}
	}
	
	public void add(Duration timePeriod) {
		if (this.type == StorageType.LocalDate) {
			this.localDateValue = this.localDateValue.plus(timePeriod);
		} else if (this.type == StorageType.LocalDate) {
			this.localDateTimeValue = this.localDateTimeValue.plus(timePeriod);
		} else if (this.type == StorageType.LocalTime) {
			this.localTimeValue = this.localTimeValue.plus(timePeriod);
		}
	}
	
	public void add(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue + value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue += value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.add(new BigDecimal(value));
		}
	}

	public void add(float value) {
		add((double) value);
	}
	
	public void add(BigDecimal value) {
		if (this.type == StorageType.Integer) {
			this.bigDecimalValue = new BigDecimal(this.intValue).add(value);
			this.intValue = 0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.Double) {
			this.bigDecimalValue = new BigDecimal(this.doubleValue).add(value);
			this.doubleValue = 0.0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.add(value);
		}
	}
	
	public void add(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			add(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			add(value.getDoubleValue());
		} else if (value.getType() == StorageType.BigDecimal) {
			add(value.getBigDecimalValue());
		} else if (value.getType() == StorageType.Period) {
			add(value.getPeriodValue());
		} else if (value.getType() == StorageType.Duration) {
			add(value.getDurationValue());
		}
	}
	
	public void and(boolean value) {
		if (this.type == StorageType.Boolean) {
			this.booleanValue = this.booleanValue && value;
		}
	}
	
	public void and(DataItem value) {
		if (value.getType() == StorageType.Boolean) {
			and(value.getBooleanValue());
		}
	}
	
	public void or(boolean value) {
		if (this.type == StorageType.Boolean) {
			this.booleanValue = this.booleanValue || value;
		}
	}
	
	public void or(DataItem value) {
		if (value.getType() == StorageType.Boolean) {
			or(value.getBooleanValue());
		}
	}
	
	public void exclusiveOr(boolean value) {
		if (this.type == StorageType.Boolean) {
			this.booleanValue = (this.booleanValue && !value) || (!this.booleanValue && value);
		}
	}
	
	public void exclusiveOr(DataItem value) {
		if (value.getType() == StorageType.Boolean) {
			exclusiveOr(value.getBooleanValue());
		}
	}

	public void subtract(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue -= value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue -= value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.subtract(new BigDecimal(value));
		}
	}
	
	public void subtract(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue - value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue -= value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.subtract(new BigDecimal(value));
		}
	}

	public void subtract(float value) {
		subtract((double) value);
	}

	public void subtract(BigDecimal value) {
		if (this.type == StorageType.Integer) {
			this.bigDecimalValue = new BigDecimal(this.intValue).subtract(value);
			this.intValue = 0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.Double) {
			this.bigDecimalValue = new BigDecimal(this.doubleValue).subtract(value);
			this.doubleValue = 0.0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.subtract(value);
		}
	}
	
	public void subtract(Period timePeriod) {
		if (this.type == StorageType.LocalDate) {
			this.localDateValue = this.localDateValue.minus(timePeriod);
		} else if (this.type == StorageType.LocalDateTime) {
			this.localDateTimeValue = this.localDateTimeValue.minus(timePeriod);
		} else if (this.type == StorageType.LocalTime) {
			this.localTimeValue = this.localTimeValue.minus(timePeriod);
		}
	}
	
	public void subtract(Duration timePeriod) {
		if (this.type == StorageType.LocalDate) {
			this.localDateValue = this.localDateValue.minus(timePeriod);
		} else if (this.type == StorageType.LocalDateTime) {
			this.localDateTimeValue = this.localDateTimeValue.minus(timePeriod);
		} else if (this.type == StorageType.LocalTime) {
			this.localTimeValue = this.localTimeValue.minus(timePeriod);
		}
	}
	
	public void subtract(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			subtract(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			subtract(value.getDoubleValue());
		} else if (value.getType() == StorageType.BigDecimal) {
			subtract(value.getBigDecimalValue());
		} else if (value.getType() == StorageType.Period) {
			subtract(value.getPeriodValue());
		} else if (value.getType() == StorageType.Duration) {
			subtract(value.getDurationValue());
		}
	}
	
	public void multiply(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue *= value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue *= value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.multiply(new BigDecimal(value));
		}
	}
	
	public void multiply(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue * value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue *= value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.multiply(new BigDecimal(value));
		}
	}

	public void multiply(float value) {
		multiply((double) value);
	}

	public void multiply(BigDecimal value) {
		if (this.type == StorageType.Integer) {
			this.bigDecimalValue = new BigDecimal(this.intValue).multiply(value);
			this.intValue = 0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.Double) {
			this.bigDecimalValue = new BigDecimal(this.doubleValue).multiply(value);
			this.doubleValue = 0.0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.multiply(value);
		}
	}
	
	public void multiply(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			multiply(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			multiply(value.getDoubleValue());
		} else if (value.getType() == StorageType.BigDecimal) {
			multiply(value.getBigDecimalValue());
		}
	}
	
	
	public void divide(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue /= value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue /= value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.divide(new BigDecimal(value));
		}
	}
	
	public void divide(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue / value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue /= value;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.divide(new BigDecimal(value));
		}
	}

	public void divide(float value) {
		divide((double) value);
	}
	
	public void divide(BigDecimal value) {
		if (this.type == StorageType.Integer) {
			this.bigDecimalValue = new BigDecimal(this.intValue).divide(value);
			this.intValue = 0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.Double) {
			this.bigDecimalValue = new BigDecimal(this.doubleValue).divide(value);
			this.doubleValue = 0.0;
			this.type = StorageType.BigDecimal;
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.divide(value);
		}
	}
	
	public void divide(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			divide(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			divide(value.getDoubleValue());
		} else if (value.getType() == StorageType.BigDecimal) {
			divide(value.getBigDecimalValue());
		}
	}
	
	public int mod(int value, int modulo) {
		return value % modulo;
	}
	public void mod(int modulo) {
		if (this.type == StorageType.Integer) {
			this.intValue = mod(this.intValue, modulo);
		} else if (this.type == StorageType.Double) {
			this.intValue = mod(this.doubleValue.intValue(), modulo);
		} else if (this.type == StorageType.Double) {
			this.intValue = mod(this.bigDecimalValue.intValue(), modulo);
		}
	}
	
	public void mod(DataItem modulo) {
		mod(modulo.getValueConvertedToInt());
	}
	
	public void power(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue = (int)Math.pow(this.intValue, value);
		} else if (this.type == StorageType.Double) {
			this.doubleValue = Math.pow(this.getDoubleValue(), value);
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.pow(value);
		}
	}
	
	public void power(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = Math.pow((double)this.intValue, value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue = Math.pow(this.doubleValue, value);
		}
	}

	public void power(float value) {
		power((double) value);
	}
	
	public void power(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			power(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			power(value.getDoubleValue());
		}
	}
	
	public void intFloor() {
		if (this.type == StorageType.Double) {
			this.intValue = this.doubleValue.intValue();
			this.doubleValue = 0.0;
			this.type = StorageType.Integer;
		} else if (this.type == StorageType.BigDecimal) {
			this.intValue = this.bigDecimalValue.intValue();
			this.bigDecimalValue = null;
			this.type = StorageType.Integer;
		}
	}
	
	public void doubleFloor() {
		if (this.type == StorageType.Integer) {
			this.doubleValue = this.intValue.doubleValue();
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue = Math.floor(this.doubleValue);
		} else if (this.type == StorageType.Double) {
			this.doubleValue = Math.floor(this.bigDecimalValue.doubleValue());
			this.bigDecimalValue = null;
			this.type = StorageType.Double;
		}
	}
	
	public void intCeiling() {
		if (this.type == StorageType.Double) {
			this.intValue = (int) Math.ceil(this.doubleValue);
			this.doubleValue = 0.0;
			this.type = StorageType.Integer;
		} else if (this.type == StorageType.BigDecimal) {
			this.intValue = this.bigDecimalValue.intValue() + 1;
			this.bigDecimalValue = null;
			this.type = StorageType.Integer;
		}
	}
	
	public void doubleCeiling() {
		if (this.type == StorageType.Integer) {
			this.doubleValue = this.intValue.doubleValue();
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue = Math.ceil(this.doubleValue);
		} else if (this.type == StorageType.Double) {
			this.doubleValue = Math.ceil(this.bigDecimalValue.doubleValue());
			this.bigDecimalValue = null;
			this.type = StorageType.Double;
		}
	}
	
	public boolean lessThan(int value) {
		return lessThan((double) value);
	}
	
	public boolean lessThan(double value) {
		if (this.type == StorageType.Integer) {
			return this.intValue < value;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue < value;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.compareTo(new BigDecimal(value)) < 0;
		}
		return false;
	}

	public boolean lessThan(float value) {
		return lessThan((double) value);
	}
	
	public boolean lessThan(BigDecimal value) {
		if (this.type == StorageType.Integer) {
			return new BigDecimal(this.intValue).compareTo(value) < 0;
		} else if (this.type == StorageType.Double) {
			return new BigDecimal(this.doubleValue).compareTo(value) < 0;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.compareTo(value) < 0;
		}
		
		return false;
	}
	
	public boolean lessThan(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			return lessThan(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			return lessThan(value.getDoubleValue());
		} else if (value.getType() == StorageType.BigDecimal) {
			return lessThan(value.getBigDecimalValue());
		}
		return false;
	}
	
	public boolean before(LocalDate date) {
		if (this.type == StorageType.LocalDate) {
			return this.localDateValue.isBefore(date);
		} else if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.isBefore(date.atStartOfDay());
		}
		return false;
	}
	
	public boolean before(LocalDateTime date) {
		if (this.type == StorageType.LocalDate) {
			return this.localDateValue.isBefore(date.toLocalDate());
		} else if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.isBefore(date);
		}
		return false;
	}
	
	public boolean before(LocalTime time) {
		if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.toLocalTime().isBefore(time);
		} else if (this.type == StorageType.LocalTime) {
			return this.localTimeValue.isBefore(time);
		}
		return false;
	}
	
	public boolean equal(int value) {
		return equal((double) value);
	}
	
	public boolean equal(double value) {
		if (this.type == StorageType.Integer) {
			return this.intValue == value;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue == value;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.compareTo(new BigDecimal(value)) == 0;
		}
		return false;
	}

	public boolean equal(float value) {
		return equal((double) value);
	}
	
	public boolean equal(BigDecimal value) {
		if (this.type == StorageType.Integer) {
			return new BigDecimal(this.intValue).compareTo(value) == 0;
		} else if (this.type == StorageType.Double) {
			return new BigDecimal(this.doubleValue).compareTo(value) == 0;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.compareTo(value) == 0;
		}
		
		return false;
	}
	
	public boolean equal(Color value) {
		if (this.type == StorageType.Color) {
			return this.colorValue.equals(value);
		}
		return false;
	}
	
	public boolean equal(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			return equal(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			return equal(value.getDoubleValue());
		} else if (value.getType() == StorageType.BigDecimal) {
			return equal(value.getBigDecimalValue());
		} else if (value.getType() == StorageType.Color) {
			return equal(value.getColorValue());
		}
		return false;
	}
	
	public boolean sameDate(LocalDate date) {
		if (this.type == StorageType.LocalDate) {
			return this.localDateValue.equals(date);
		} else if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.toLocalDate().equals(date);
		}
		return false;
	}
	
	public boolean sameDate(LocalDateTime date) {
		if (this.type == StorageType.LocalDate) {
			return this.localDateValue.equals(date.toLocalDate());
		} else if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.equals(date);
		}
		return false;
	}
	
	public boolean sameTime(LocalTime time) {
		if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.toLocalTime().equals(time);
		} else if (this.type == StorageType.LocalTime) {
			return this.localTimeValue.equals(time);
		}
		return false;
	}
	
	public boolean greaterThan(int value) {
		return greaterThan((double) value);
	}
	
	public boolean greaterThan(double value) {
		if (this.type == StorageType.Integer) {
			return this.intValue > value;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue > value;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.compareTo(new BigDecimal(value)) > 0;
		}
		return false;
	}

	public boolean greaterThan(float value) {
		return greaterThan((double) value);
	}
	
	public boolean greaterThan(BigDecimal value) {
		if (this.type == StorageType.Integer) {
			return new BigDecimal(this.intValue).compareTo(value) > 0;
		} else if (this.type == StorageType.Double) {
			return new BigDecimal(this.doubleValue).compareTo(value) > 0;
		} else if (this.type == StorageType.BigDecimal) {
			return this.bigDecimalValue.compareTo(value) > 0;
		}
		
		return false;
	}
	
	public boolean greaterThan(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			return greaterThan(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			return greaterThan(value.getDoubleValue());
		} else if (value.getType() == StorageType.BigDecimal) {
			return greaterThan(value.getBigDecimalValue());
		}
		return false;
	}
	
	public boolean after(LocalDate date) {
		if (this.type == StorageType.LocalDate) {
			return this.localDateValue.isAfter(date);
		} else if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.isAfter(date.atStartOfDay());
		}
		return false;
	}

	public boolean after(LocalTime time) {
		if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.toLocalTime().isAfter(time);
		} else if (this.type == StorageType.LocalTime) {
			return this.localTimeValue.isAfter(time);
		}
		return false;
	}
	
	public boolean after(LocalDateTime date) {
		if (this.type == StorageType.LocalDate) {
			return this.localDateValue.isAfter(date.toLocalDate());
		} else if (this.type == StorageType.LocalDateTime) {
			return this.localDateTimeValue.isAfter(date);
		}
		return false;
	}
	
	public void flip() {
		if (this.type == StorageType.Boolean) {
			this.booleanValue = !this.booleanValue;
		}
	}
	
	public void clamp(int lowerBound, int upperBound) {
		if (this.type == StorageType.Integer) {
			this.intValue = CommonMath.clamp(this.intValue, lowerBound, upperBound);
		} else if (this.type == StorageType.Double) {
			this.doubleValue = CommonMath.clamp(this.doubleValue, (double) lowerBound, (double) upperBound);
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = CommonMath.clamp(this.bigDecimalValue, lowerBound, upperBound);
		}
	}
	
	public void clamp(double lowerBound, double upperBound) {
		if (this.type == StorageType.Integer) {
			this.intValue = CommonMath.clamp(this.intValue, (int)lowerBound, (int)upperBound);
		} else if (this.type == StorageType.Double) {
			this.doubleValue = CommonMath.clamp(this.doubleValue,lowerBound, upperBound);
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = CommonMath.clamp(this.bigDecimalValue, lowerBound, upperBound);
		}
	}
	
	public void clamp(BigDecimal lowerBound, BigDecimal upperBound) {
		if (this.type == StorageType.Integer) {
			this.intValue = CommonMath.clamp(this.intValue, lowerBound, upperBound);
		} else if (this.type == StorageType.Double) {
			this.doubleValue = CommonMath.clamp(this.doubleValue,lowerBound, upperBound);
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = CommonMath.clamp(this.bigDecimalValue, lowerBound, upperBound);
		}
	}
	
	public void clamp(LocalDate lowerBound, LocalDate upperBound) {
		if (this.type == StorageType.LocalDate) {
			this.localDateValue = CommonMath.clamp(this.localDateValue, lowerBound, upperBound);
		} else if (this.type == StorageType.LocalDateTime) {
			this.localDateTimeValue = CommonMath.clamp(this.localDateTimeValue, lowerBound.atStartOfDay(), upperBound.atStartOfDay());
		}
	}
	
	public void clamp(LocalDateTime lowerBound, LocalDateTime upperBound) {
		if (this.type == StorageType.LocalDate) {
			this.localDateValue = CommonMath.clamp(this.localDateValue, lowerBound.toLocalDate(), upperBound.toLocalDate());
		} else if (this.type == StorageType.LocalDateTime) {
			this.localDateTimeValue = CommonMath.clamp(this.localDateTimeValue, lowerBound, upperBound);
		}
	}
	
	public void clamp(LocalTime lowerBound, LocalTime upperBound) {
		if (this.type == StorageType.LocalTime) {
			this.localTimeValue = CommonMath.clamp(this.localTimeValue, lowerBound, upperBound);
		}
	}
	
	public void round(int decimalPlaces) {
		if (this.type == StorageType.Double) {
			BigDecimal bd = BigDecimal.valueOf(this.doubleValue);
		    bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
		    this.doubleValue = bd.doubleValue();
		} else if (this.type == StorageType.BigDecimal) {
			this.bigDecimalValue = this.bigDecimalValue.setScale(decimalPlaces, RoundingMode.HALF_UP);
		}
	}
	

	public void squareRoot() {
		
		try {			
			if (this.type == StorageType.Double) {
				this.doubleValue = Math.sqrt(this.doubleValue);
			} else if (this.type == StorageType.Integer) {
				this.doubleValue = Math.sqrt(this.intValue);
				this.type = StorageType.Double;
				this.intValue = 0;
			} else if (this.type == StorageType.BigDecimal) {
				throw new Exception("square root unavailble for type BigDecimal.");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static DataItem randomDataItem() {
		int typeVal = ThreadLocalRandom.current().nextInt(0, 9);
		if (typeVal == 0) {
			return DataItem.randomInt(0, 20);
		} else if (typeVal == 1) {
			return DataItem.randomDouble(0, 20);
		} else if (typeVal == 2) {
			return DataItem.randomString();
		} else if (typeVal == 3) {
			return DataItem.randomBoolean();
		} else if (typeVal == 4) {
			return DataItem.randomLocalDate();
		} else if (typeVal == 5) {
			return DataItem.randomLocalDateTime();
		} else if (typeVal == 6) {
			return DataItem.randomLocalTime();
		} else if (typeVal == 7) {
			return DataItem.randomDuration();
		} else if (typeVal == 8) {
			return DataItem.randomPeriod();
		} else if (typeVal == 9) {
			return DataItem.randomBigDecimal();
		}
		return null;
	}
	
	
	public static DataItem randomDataItem(StorageType typeToGet) {
		if (typeToGet == StorageType.String) {
			return DataItem.randomString();
		} else if (typeToGet == StorageType.Integer) {
			return DataItem.randomInt(0, 20);
		} else if (typeToGet == StorageType.Double) {
			return DataItem.randomDouble(0, 20);
		} else if (typeToGet == StorageType.Boolean) {
			return DataItem.randomBoolean();
		} else if (typeToGet == StorageType.LocalDate) {
			return DataItem.randomLocalDate();
		} else if (typeToGet == StorageType.LocalDateTime) {
			return DataItem.randomLocalDateTime();
		} else if (typeToGet == StorageType.LocalTime) {
			return DataItem.randomLocalTime();
		} else if (typeToGet == StorageType.Duration) {
			return DataItem.randomDuration();
		} else if (typeToGet == StorageType.Period) {
			return DataItem.randomPeriod();
		} else if (typeToGet == StorageType.BigDecimal) {
			return DataItem.randomBigDecimal();
		} else {
			return DataItem.randomNull();
		}
	}
	
	public static DataItem randomDataItem(Class<?> cls) {
		return DataItem.randomDataItem(DataItem.javaClassToStorageType(cls));
	}
	
	public static StorageType javaClassToStorageType(Class<?> cls) {
		if (cls == String.class) {
			return StorageType.String;
		} else if (cls == Integer.class) {
			return StorageType.Integer;
		} else if (cls == Double.class) {
			return StorageType.Double;
		} else if (cls == Boolean.class) {
			return StorageType.Boolean;
		} else if (cls == LocalDate.class) {
			return StorageType.LocalDate;
		} else if (cls == LocalDateTime.class) {
			return StorageType.LocalDateTime;
		} else if (cls == LocalTime.class) {
			return StorageType.LocalTime;
		} else if (cls == Duration.class) {
			return StorageType.Duration;
		} else if (cls == Period.class) {
			return StorageType.Period;
		} else if (cls == BigDecimal.class) {
			return StorageType.BigDecimal;
		} else if (cls == Color.class) {
			return StorageType.Color;
		} else {
			return StorageType.Null;
		}
	}

	
	public static DataItem[] randomDataItemSeries(int numValues, StorageType typeToGet) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomDataItem(typeToGet));
		return series;
	}
	
	public static DataItem[] randomDataItemIntSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomInt());
		return series;
	}
	
	public static DataItem[] randomDataItemIntSeries(int numValues, int minValue, int maxValue) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomInt(minValue, maxValue));
		return series;
	}
	
	public static DataItem[] randomDataItemDoubleSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomDouble());
		return series;
	}
	
	public static DataItem[] randomDataItemDoubleSeries(int numValues, double minValue, double maxValue) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomDouble(minValue, maxValue));
		return series;
	}
	
	public static DataItem[] randomDataItemStringSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomString());
		return series;
	}
	
	public static DataItem[] randomDataItemStringSeries(int numValues, int numCharacters) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomString(numCharacters));
		return series;
	}
	
	public static DataItem[] randomDataItemBooleanSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomBoolean());
		return series;
	}
	
	public static DataItem[] randomDataItemLocalDateSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomLocalDate());
		return series;
	}
	
	public static DataItem[] randomDataItemLocalDateSeries(int numValues, LocalDate minDate, LocalDate maxDate) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomLocalDate(minDate, maxDate));
		return series;
	}
	
	public static DataItem[] randomDataItemLocalDateTimeSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomLocalDateTime());
		return series;
	}
	
	public static DataItem[] randomDataItemLocalDateTimeSeries(int numValues, LocalDateTime minDateTime, LocalDateTime maxDateTime) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomLocalDateTime(minDateTime, maxDateTime));
		return series;
	}
	
	public static DataItem[] randomDataItemLocalTimeSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomLocalTime());
		return series;
	}
	
	public static DataItem[] randomDataItemLocalTimeSeries(int numValues, LocalTime minTime, LocalTime maxTime) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomLocalTime(minTime, maxTime));
		return series;
	}
	
	public static DataItem[] randomDataItemPeriodSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomPeriod());
		return series;
	}
	
	public static DataItem[] randomDataItemPeriodSeries(int numValues, Period minPeriod, Period maxPeriod) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomPeriod(minPeriod, maxPeriod));
		return series;
	}
	
	public static DataItem[] randomDataItemDurationSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomDuration());
		return series;
	}
	
	public static DataItem[] randomDataItemDurationSeries(int numValues, Duration minDuration, Duration maxDuration) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomDuration(minDuration, maxDuration));
		return series;
	}
	
	public static DataItem[] randomDataItemBigDecimalSeries(int numValues) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomBigDecimal());
		return series;
	}
	
	public static DataItem[] randomDataItemBigDecimalSeries(int numValues, BigDecimal minBigDecimal, BigDecimal maxBigDecimal) {
		DataItem[] series = new DataItem[numValues];
		IntStream.range(0, numValues).forEach(i -> series[i] = DataItem.randomBigDecimal(minBigDecimal, maxBigDecimal));
		return series;
	}
	
	public static DataItem[] randomDataItemSeries(int numValues, Class<?> cls) {
		return randomDataItemSeries(numValues, DataItem.javaClassToStorageType(cls));
	}
	
	public static DataItem randomInt() {
		return DataItem.randomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static DataItem randomInt(int inclusiveMin, int exclusiveMax) {
		return new DataItem(ThreadLocalRandom.current().nextInt(inclusiveMin, exclusiveMax));
	}
	
	public static DataItem randomDouble() {
		return DataItem.randomDouble(-10000, 10000);
	}
	
	public static DataItem randomDouble(double inclusiveMin, double exclusiveMax) {
		Double doubleValue = ThreadLocalRandom.current().nextDouble(inclusiveMin, exclusiveMax);
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		return new DataItem(Double.parseDouble(df.format(doubleValue)));
	}
	
	public static DataItem randomString() {
		return randomString(5);
	}
	
	public static DataItem randomString(int stringLength) {
		return new DataItem(CommonArray.randomString(stringLength));
	}
	
	public static DataItem randomBoolean() {
		return new DataItem(ThreadLocalRandom.current().nextBoolean());
	}
	
	public static DataItem randomLocalDate() {
		LocalDate minDay = LocalDate.of(1970, 1, 1);
		LocalDate maxDay = LocalDate.of(2030, 12, 31);
	    return DataItem.randomLocalDate(minDay, maxDay);
	}
	
	public static DataItem randomLocalDate(LocalDate earliestDate, LocalDate latestDate) {
		long minDay = earliestDate.toEpochDay();
	    long maxDay = latestDate.toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    return new DataItem(LocalDate.ofEpochDay(randomDay));
	}
	
	public static DataItem randomLocalDateTime() {
		LocalDateTime minDateTime = LocalDateTime.of(1970, 1, 1, 1, 1);
		LocalDateTime maxDateTime =  LocalDateTime.of(2030, 1, 1, 1, 1);
		return DataItem.randomLocalDateTime(minDateTime, maxDateTime);
	}
	
	public static DataItem randomLocalDateTime(LocalDateTime earliestLocalDateTime, LocalDateTime latestLocalDateTime) {
		long minDateTime = earliestLocalDateTime.toEpochSecond(ZoneOffset.UTC);
	    long maxDateTime =  latestLocalDateTime.toEpochSecond(ZoneOffset.UTC);
	    long randomDay = ThreadLocalRandom.current().nextLong(minDateTime, maxDateTime);
		return new DataItem(LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC));
	}
	
	public static DataItem randomLocalTime() {
		LocalTime minTime = LocalTime.of(0, 0, 0);
	    LocalTime maxTime = LocalTime.of(23, 59, 59);
		return DataItem.randomLocalTime(minTime, maxTime);
	}
	
	public static DataItem randomLocalTime(LocalTime earliestLocalTime, LocalTime latestLocalTime) {
		long minTime = earliestLocalTime.toSecondOfDay();
	    long maxTime = latestLocalTime.toSecondOfDay();
	    long randomTime = ThreadLocalRandom.current().nextLong(minTime, maxTime);
		return new DataItem(LocalTime.ofSecondOfDay(randomTime));
	}
	
	public static DataItem randomDuration() {
		Duration minDuration = Duration.ofSeconds(0);
		Duration maxDuration = Duration.ofSeconds(10000);
		return DataItem.randomDuration(minDuration, maxDuration);
	}
	
	public static DataItem randomDuration(Duration minDuration, Duration maxDuration) {
		long minSecs = minDuration.getSeconds();
		long maxSecs = maxDuration.getSeconds();
		long randomSecs = ThreadLocalRandom.current().nextLong(minSecs, maxSecs);
		return new DataItem(Duration.ofSeconds(randomSecs));
	}
	
	public static DataItem randomPeriod() {
		Period minPeriod = Period.ofDays(0);
		Period maxPeriod = Period.ofDays(10000);
		return DataItem.randomPeriod(minPeriod, maxPeriod);
	}
	
	public static DataItem randomPeriod(Period minPeriod, Period maxPeriod) {
		int minDays = minPeriod.getYears() * 365 + minPeriod.getMonths() * 12 + minPeriod.getDays();
		int maxDays = maxPeriod.getYears() * 365 + maxPeriod.getMonths() * 12 + maxPeriod.getDays();
		int randomDays = ThreadLocalRandom.current().nextInt(minDays, maxDays);
		return new DataItem(Period.ofDays(randomDays));
	}
	
	public static DataItem randomBigDecimal() {
		return randomBigDecimal(new BigDecimal(-1000), new BigDecimal(1000));
	}
	
	public static DataItem randomBigDecimal(BigDecimal min, BigDecimal max) {
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	    return new DataItem(randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP));
	}
	
	public static DataItem randomNull() {
		return new DataItem();
	}
	
	@Override
	public String toString() {
		return getValueConvertedToString();
	}
	
	@Override
	public DataItem clone() {
		DataItem newDataItem;
		
		if (this.type == StorageType.String) {
			newDataItem = new DataItem(this.stringValue.toString());
		} else if (this.type == StorageType.Integer) {
			newDataItem = new DataItem(this.intValue.intValue());
		} else if (this.type == StorageType.Double) {
			newDataItem = new DataItem(this.doubleValue.doubleValue());
		} else if (this.type == StorageType.Boolean) {
			newDataItem = new DataItem(this.booleanValue.booleanValue());
		} else if (this.type == StorageType.LocalDate) {
			newDataItem = new DataItem(this.localDateValue);
		} else if (this.type == StorageType.LocalDateTime) {
			newDataItem = new DataItem(this.localDateTimeValue);
		} else if (this.type == StorageType.Period) {
			newDataItem = new DataItem(this.periodValue);
		} else if (this.type == StorageType.Duration) {
			newDataItem = new DataItem(this.durationValue);
		} else if (this.type == StorageType.BigDecimal) {
			newDataItem = new DataItem(this.bigDecimalValue);
		} else if (this.type == StorageType.Color) {
			newDataItem = new DataItem(this.colorValue);
		} else {
			newDataItem = new DataItem();
		}
		
		return newDataItem;
	}
	@Override
	public boolean equals(Object otherItem) {
		if (otherItem instanceof DataItem) {
			DataItem formatted = (DataItem)otherItem;
			if (this.getType() == formatted.getType()) {
				if (this.getType() == StorageType.Integer) {
					return this.intValue.intValue() == formatted.intValue.intValue();
				} else if (this.getType() == StorageType.Double) {
					return this.doubleValue.doubleValue() == formatted.doubleValue.doubleValue();
				} else if (this.getType() == StorageType.Boolean) {
					return this.booleanValue.booleanValue() == formatted.booleanValue.booleanValue();
				} else if (this.getType() == StorageType.String) {
					return this.stringValue.equals(formatted.stringValue);
				} else if (this.getType() == StorageType.LocalDate) {
					return this.sameDate(formatted.localDateValue);
				} else if (this.getType() == StorageType.LocalDateTime) {
					return this.sameDate(formatted.localDateTimeValue);
				} else if (this.getType() == StorageType.LocalTime) {
					return this.sameTime(formatted.localTimeValue);
				} else if (this.getType() == StorageType.Period) {
					return this.periodValue.equals(formatted.periodValue);
				} else if (this.getType() == StorageType.Duration) {
					return this.durationValue.equals(formatted.durationValue);
				} else if (this.getType() == StorageType.BigDecimal) {
					return this.bigDecimalValue.equals(formatted.bigDecimalValue);
				} else if (this.getType() == StorageType.Color) {
					return this.colorValue.equals(formatted.colorValue);
				} else if (this.getType() == StorageType.Null) {
					return true;
				}
			}
		}
		return false;
	}

}
