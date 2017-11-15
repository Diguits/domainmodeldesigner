package com.diguits.domainmodeldefinition.serialization.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diguits.domainmodeldefinition.definitions.DataType;

public class DataTypeToJavaTypeConverter {
	public static Class<?> getJavaType(DataType dataType){
		switch(dataType){
		case Boolean:
			return Boolean.class;
		case Byte:
			return byte.class;
		case ByteArray:
			return byte[].class;
		case Char:
			return char.class;
		case Date:
			return Date.class;
		case DateTime:
			return Date.class;
		case Decimal:
			return BigDecimal.class;
		case Double:
			return double.class;
		case Entity:
			return UUID.class;
		case EntityList:
			return List.class;
		case Enum:
			return Enum.class;
		case Guid:
			return UUID.class;
		case Image:
			return byte[].class;
		case Int:
			return int.class;
		case Long:
			return long.class;
		case None:
			return null;
		case Short:
			return short.class;
		case Single:
			return float.class;
		case String:
			return String.class;
		case Time:
			return Date.class;
		default:
			return null;
		}

	}
}
