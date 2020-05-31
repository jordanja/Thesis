package thesis.DataFrame;

import java.time.LocalDate;


public class DataItem {
	private String strValue;
	private Integer intValue;
	private Double doubleValue;
	private LocalDate dateValue;
	private Boolean booleanValue;

	public enum StorageType { String, Integer, Double, Null, Date, Boolean };
	StorageType type;

	public DataItem() {
		this.type = StorageType.Null;
	}

	public DataItem(Object value, StorageType type) {
		initialize(type, value);
	}

	public DataItem(Object value) {
		StorageType typeOfObject = null;
		if (value == null) {
			type = StorageType.Null;
			typeOfObject = StorageType.Null;
		} else if (value instanceof Integer) {
			typeOfObject = StorageType.Integer;
		} else if (value instanceof Double) {
			typeOfObject = StorageType.Double;
		} else if (value instanceof LocalDate) {
			typeOfObject = StorageType.Date;
		} else if (value instanceof Boolean) {
			typeOfObject = StorageType.Boolean;
		} else {
			typeOfObject = StorageType.String;
		}

		initialize(typeOfObject, value);
	}

	// String Value
	public DataItem(String value) {
		this.strValue = value;
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

	// Date Value
	public DataItem(LocalDate value) {
		this.dateValue = value;
		this.type = StorageType.Date;
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

	public DataItem(StorageType typeToUse, Object value) {
		initialize(typeToUse, value.toString());
	}

	private void initialize(StorageType typeToUse, Object value) {
		setType(typeToUse);
		if (this.type == StorageType.String) {
			this.strValue = value.toString();
		} else if (this.type == StorageType.Integer) {
			this.intValue = (Integer) value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue = (Double) value;
		} else if (this.type == StorageType.Date) {
			this.dateValue = LocalDate.parse(value.toString());
		} else if (this.type == StorageType.Boolean) {
			this.booleanValue = parseBoolean(value.toString());
		} else if (this.type == StorageType.Null) {
		
		} else {
			System.out.println("You have entered an incompatible type: " + value.getClass());
		}
	}

	private Boolean parseBoolean(String str) {
		return (str.equals("True") || str.equals("true"));
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

	public static String[] convertToStringList(DataItem[] dataItemList) {
		String[] stringList = new String[dataItemList.length];
		for (int i = 0; i < dataItemList.length; i++) {
			stringList[i] = dataItemList[i].getValueConvertedToString();
		}
		return stringList;
	}

	public static Object[] convertToObjectList(DataItem[] dataItemList) {
		Object[] stringList = new String[dataItemList.length];
		for (int i = 0; i < dataItemList.length; i++) {
			stringList[i] = dataItemList[i].getObjectValue();
		}
		return stringList;
	}

	@SuppressWarnings("incomplete-switch")
	public void setType(StorageType typeToUse) {
		
		if (this.type == StorageType.String) {
			switch (typeToUse) {
				case Integer:
					this.intValue = Integer.parseInt(this.strValue);
					break;
				case Double:
					this.doubleValue = Double.parseDouble(this.strValue);
					break;
				case Date:
					this.dateValue = LocalDate.parse(this.strValue);
				case Boolean:
					this.booleanValue = parseBoolean(this.strValue);
					break;
				default:
					System.out.println("Can't conver from " + this.type + " to " + typeToUse);
			}
			this.strValue = null;
		} else if (this.type == StorageType.Integer) {
			switch(typeToUse) {
				case String:
					this.strValue = this.intValue.toString();
					break;
				case Double:
					this.doubleValue = this.intValue.doubleValue();
					break;
				default:
					System.out.println("Can't conver from " + this.type + " to " + typeToUse);
			}
			this.intValue = null;
		} else if (this.type == StorageType.Double) {
			switch(typeToUse) {
				case String:
					this.strValue = this.doubleValue.toString();
					break;
				case Integer:
					this.intValue = this.doubleValue.intValue();
					break;
				default:
					System.out.println("Can't conver from " + this.type + " to " + typeToUse);
			}
			this.doubleValue = null;
		} else if (this.type == StorageType.Date) {
			switch(typeToUse) {
				case String:
					this.strValue = this.dateValue.toString();
					break;
				default:
					System.out.println("Can't conver from " + this.type + " to " + typeToUse);
			}
			this.dateValue = null;
		} else if (this.type == StorageType.Boolean){
			switch(typeToUse) {
				case String:
					this.strValue = this.booleanValue.toString();
			}
		} else if (this.type == StorageType.Null) {
			this.strValue = null;
			this.intValue = null;
			this.doubleValue = null;
			this.dateValue = null;
		}
		this.type = typeToUse;

	}

	public StorageType getType() {
		return this.type;
	}

	public Object getObjectValue() {

		if (this.type == StorageType.String) {
			return this.strValue;
		} else if (this.type == StorageType.Integer) {
			return this.intValue;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue;
		} else if (this.type == StorageType.Date) {
			return this.dateValue;
		} else if (this.type == StorageType.Boolean) {
			return this.booleanValue;
		} else if (this.type == StorageType.Null) {
			return "null";
		}
		return null;
	}

	public String getStringValue() {
		return this.strValue;
	}

	public Integer getIntegerValue() {
		return this.intValue;
	}

	public double getDoubleValue() {
		return this.doubleValue;
	}

	public LocalDate getDateValue() {
		return this.dateValue;
	}
	
	public Boolean getBooleanValue() {
		return this.booleanValue;
	}

	public String getValueConvertedToString() {
		return getObjectValue().toString();
	}

	public Double getValueConvertedToDouble() {
		try {
			return Double.parseDouble(getObjectValue().toString());
		} catch (Exception e) {
			return null;
		}
	}
	
	public int getValueConvertedToInt() {
		try {			
			return Integer.parseInt(getObjectValue().toString());
		} catch (Exception e) {
			return 0;
		}
	}
	
	public Boolean getValueConvertedToBoolean() {
		if (this.type == StorageType.Boolean) {
			return this.booleanValue;
		} else if (this.type == StorageType.String) {
			return parseBoolean(this.strValue);
		}
		
		return null;
	}

	public Number getValueConvertedToNumber() {
		if (this.type == StorageType.Integer) {
			return this.intValue;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue;
		} else
			return null;
	}
	
	public void add(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue += value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue += value;
		}
	}
	
	public void add(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue + value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue += value;
		}
	}

	public void add(float value) {
		add((double) value);
	}
	
	public void add(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			add(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			add(value.getDoubleValue());
		}
	}
	

	public void subtract(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue -= value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue -= value;
		}
	}
	
	public void subtract(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue - value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue -= value;
		}
	}

	public void subtract(float value) {
		subtract((double) value);
	}
	
	public void subtract(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			subtract(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			subtract(value.getDoubleValue());
		}
	}
	
	public void multiply(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue *= value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue *= value;
		}
	}
	
	public void multiply(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue * value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue *= value;
		}
	}

	public void multiply(float value) {
		multiply((double) value);
	}
	
	public void multiply(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			multiply(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			multiply(value.getDoubleValue());
		}
	}
	
	
	public void divide(int value) {
		if (this.type == StorageType.Integer) {
			this.intValue /= value;
		} else if (this.type == StorageType.Double) {
			this.doubleValue /= value;
		}
	}
	
	public void divide(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = (double) (this.intValue / value);
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue /= value;
		}
	}

	public void divide(float value) {
		divide((double) value);
	}
	
	public void divide(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			divide(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			divide(value.getDoubleValue());
		}
	}
	
	public int mod(int value, int modulo) {
		return value % modulo;
	}
	public void mod(int modulo) {
		if (this.type == StorageType.Integer) {
			this.intValue = mod(this.intValue, modulo);
		} else if (this.type == StorageType.Double) {
			this.intValue = this.doubleValue.intValue();
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
		}
	}
	
	public void power(double value) {
		if (this.type == StorageType.Integer) {
			this.doubleValue = Math.pow(this.intValue, value);
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
		}
	}
	
	public void doubleFloor() {
		if (this.type == StorageType.Integer) {
			this.doubleValue = this.intValue.doubleValue();
			this.intValue = 0;
			this.type = StorageType.Double;
		} else if (this.type == StorageType.Double) {
			this.doubleValue = Math.floor(this.doubleValue);
		}
	}
	
	public void intCeiling() {
		if (this.type == StorageType.Double) {
			this.intValue = (int) Math.ceil(this.doubleValue);
			this.doubleValue = 0.0;
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
		}
		return false;
	}

	public boolean lessThan(float value) {
		return lessThan((double) value);
	}
	
	public boolean lessThan(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			return lessThan(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			return lessThan(value.getDoubleValue());
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
		}
		return false;
	}

	public boolean equal(float value) {
		return equal((double) value);
	}
	
	public boolean equal(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			return equal(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			return equal(value.getDoubleValue());
		}
		return false;
	}
	
	//
	public boolean greaterThan(int value) {
		return greaterThan((double) value);
	}
	
	public boolean greaterThan(double value) {
		if (this.type == StorageType.Integer) {
			return this.intValue > value;
		} else if (this.type == StorageType.Double) {
			return this.doubleValue > value;
		}
		return false;
	}

	public boolean greaterThan(float value) {
		return greaterThan((double) value);
	}
	
	public boolean greaterThan(DataItem value) {
		if (value.getType() == StorageType.Integer) {
			return greaterThan(value.getIntegerValue());
		} else if (value.getType() == StorageType.Double) {
			return greaterThan(value.getDoubleValue());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return getValueConvertedToString();
	}
	
	@Override
	public DataItem clone() {
		DataItem newDataItem;
		
		if (this.type == StorageType.String) {
			newDataItem = new DataItem(this.strValue.toString());
		} else if (this.type == StorageType.Integer) {
			newDataItem = new DataItem(this.intValue.intValue());
		} else if (this.type == StorageType.Double) {
			newDataItem = new DataItem(this.doubleValue.doubleValue());
		} else if (this.type == StorageType.Date) {
			newDataItem = new DataItem(this.dateValue);
		} else if (this.type == StorageType.Boolean) {
			newDataItem = new DataItem(this.booleanValue);
		} else {
			newDataItem = new DataItem();
		}
		
		return newDataItem;
	}

}
