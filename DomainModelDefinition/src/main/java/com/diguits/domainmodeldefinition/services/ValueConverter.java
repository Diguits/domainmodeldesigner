package com.diguits.domainmodeldefinition.services;

import java.util.Date;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DataType;

public class ValueConverter {

	public static Object tryToConver(Object value, DataType n) {
		if (value != null) {
			if (n == DataType.Byte || n == DataType.Short || n == DataType.Int || n == DataType.Long) {
				Long longValue = null;
				if (value instanceof Byte)
					longValue = new Long((long) ((byte) value));
				if (value instanceof Short)
					longValue = new Long((long) ((short) value));
				if (value instanceof Integer)
					longValue = new Long((long) ((int) value));
				if (value instanceof Long)
					longValue = (Long) value;
				if (value instanceof Float)
					longValue = new Long(((Float) value).longValue());
				if (value instanceof Double)
					longValue = new Long(((Double) value).longValue());
				if (value instanceof String) {
					try {
						longValue = new Long(((String) value));
					} catch (Exception e) {
					}

				}

				if (longValue != null) {
					if (n == DataType.Byte && longValue >= Byte.MIN_VALUE && longValue <= Byte.MAX_VALUE)
						return new Byte((byte) ((long) longValue));
					if (n == DataType.Short && longValue >= Short.MIN_VALUE && longValue <= Short.MAX_VALUE)
						return new Short((short) ((long) longValue));
					if (n == DataType.Int && longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE)
						return new Integer((int) ((long) longValue));
					if (n == DataType.Long)
						return longValue;
				}
			}

			if (n == DataType.Single || n == DataType.Double || n == DataType.Decimal) {
				Double doubleValue = null;
				if (value instanceof Byte)
					doubleValue = new Double((double) ((byte) value));
				if (value instanceof Short)
					doubleValue = new Double((double) ((short) value));
				if (value instanceof Integer)
					doubleValue = new Double((double) ((int) value));
				if (value instanceof Long)
					doubleValue = new Double((double) ((long) value));
				if (value instanceof Float)
					doubleValue = new Double((double) ((float) value));
				if (value instanceof Double)
					doubleValue = (Double) value;
				if (value instanceof String) {
					try {
						doubleValue = new Double(((String) value));
					} catch (Exception e) {
					}

				}

				if (doubleValue != null) {
					if (n == DataType.Single && doubleValue >= Float.MIN_VALUE && doubleValue <= Float.MAX_VALUE)
						return new Float((float) ((double) doubleValue));
					if ((n == DataType.Double || n == DataType.Decimal) && doubleValue >= Double.MIN_VALUE
							&& doubleValue <= Double.MAX_VALUE)
						return doubleValue;
				}
			}

			if(n == DataType.Boolean){
				if(!(value instanceof Boolean)){
					return value.toString().toUpperCase().equals("TRUE");
				}
			}

			if (n == DataType.Date || n == DataType.DateTime) {
				if (value instanceof Date)
					return value;
			}

			if (n == DataType.String) {
				return value.toString();
			}

			if (n == DataType.Enum) {
				if(value!=null && value instanceof UUID)
				return value;
			}
		}
		return null;
	}
}
