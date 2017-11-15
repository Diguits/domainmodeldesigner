package com.diguits.templateengine;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.diguits.templateengine.contract.ITemplate;
import com.diguits.templateengine.contract.ParamValue;

public abstract class TemplateBase implements ITemplate {
	private Writer writer;
	private Map<String, Object> params = new HashMap<String, Object>();
	protected TemplateHelper helper = new TemplateHelper();
	Logger logger;


	public Object getParamValue(String paramName) {
		return params.get(paramName);
	}

	boolean existsParam(String paramName) {
		return params.containsKey(paramName);
	}

	boolean isSetParam(String paramName) {
		return getParamValue(paramName) == null;
	}

	/**
	 * Gets a value and converts it to a Long.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Long value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Long getParamAsLong(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return value != null ? ((Number) value).longValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Long.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					logger.error("Cannot parse Long value for " + value + " at param " + paramName);
					return null;
				}
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Long: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to an Integer.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Integer value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Integer getParamAsInteger(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return value != null ? ((Number) value).intValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Integer.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					logger.error("Cannot parse Integer value for " + value + " at param " + paramName);
					return null;
				}
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Integer: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Short.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Short value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Short getParamAsShort(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return value != null ? ((Number) value).shortValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Short.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					logger.error("Cannot parse Short value for " + value + " at param " + paramName);
					return null;
				}
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Short: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Byte.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Byte value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Byte getParamAsByte(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return value != null ? ((Number) value).byteValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Byte.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					logger.error("Cannot parse Byte value for " + value + " at param " + paramName);
					return null;
				}
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Byte: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Double.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Double value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Double getParamAsDouble(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return value != null ? ((Number) value).doubleValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Double.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					logger.error("Cannot parse Double value for " + value + " at param " + paramName);
					return null;
				}
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Double: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Float.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Float value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Float getParamAsFloat(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return value != null ? ((Number) value).floatValue() : null;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return Float.valueOf(value.toString());
				} catch (NumberFormatException e2) {
					logger.error("Cannot parse Float value for " + value + " at param " + paramName);
					return null;
				}
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Float: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Boolean.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Boolean value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Boolean getParamAsBoolean(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return (Boolean) value;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				return Boolean.valueOf(value.toString());
			} else if (value instanceof Number) {
				return ((Number) value).intValue() != 0;
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Boolean: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value and converts it to a Boolean.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the Boolean value, or null if the value is missing or cannot be
	 *         converted
	 */
	public Date getParamAsDate(String paramName) {
		Object value = getParamValue(paramName);
		try {
			return (Date) value;
		} catch (ClassCastException e) {
			if (value instanceof CharSequence) {
				try {
					return java.text.DateFormat.getInstance().parse((String) value);
				} catch (ParseException e1) {
					logger.error("Cannot cast value for " + paramName + " to a Date: " + value, e);
					return null;
				}
			} else if (value instanceof Long) {
				return new Date((Long) value);
			} else {
				logger.error("Cannot cast value for " + paramName + " to a Date: " + value, e);
				return null;
			}
		}
	}

	/**
	 * Gets a value that is a byte array. Note that this method will not convert
	 * any other types to byte arrays.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the byte[] value, or null if the value is missing or not a byte[]
	 */
	public byte[] getParamAsByteArray(String paramName) {
		Object value = getParamValue(paramName);
		if (value instanceof byte[]) {
			return (byte[]) value;
		} else {
			return null;
		}
	}

	/**
	 * Gets the String value.
	 *
	 * @param paramName
	 *            the parameter name to get
	 * @return the String value, or null if the value is missing
	 */
	public String getParamAsString(String paramName) {
		Object value = getParamValue(paramName);
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
	}

	@Override
	public void write(Object value) {
		if (value != null)
			try {
				writer.write(value.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void apply(Writer writer, Object[] models, List<ParamValue> paramValues, Logger logger) {
		this.writer = writer;
		this.logger = logger;
		if(paramValues!=null)
		for (ParamValue paramValue : paramValues) {
			this.params.put(paramValue.getName(), paramValue.getValue());
		}
		apply(models);
	}

	protected abstract void apply(Object[] models);

}
