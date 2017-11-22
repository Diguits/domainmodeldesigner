package com.diguits.domainmodeldefinition.serialization.services;

import com.diguits.domainmodeldefinition.definitions.DataType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ObjectToStringConverter {
    public static String toString(DataType dataType, Object value) {
        if (value != null) {
            if (dataType == DataType.Date
                    || dataType == DataType.DateTime
                    || dataType == DataType.Time) {
                SimpleDateFormat dateFormat = getDateFormat();
                if (value != null || value instanceof Date) {
                    return dateFormat.format((Date) value);
                }
            }
            return value.toString();
        }
        return null;
    }

    public static Object toObject(DataType dataType, String str) {
        if (str.length() > 0) {
            try {
                if (dataType == DataType.Enum
                        || dataType == DataType.Entity
                        || dataType == DataType.Guid
                        || dataType == DataType.ValueObject) {
                    return UUID.fromString(str);
                } else if (dataType == DataType.Boolean) {
                    return Boolean.parseBoolean(str);
                } else if (dataType == DataType.Double) {
                    return Double.parseDouble(str);
                } else if (dataType == DataType.Int || dataType == DataType.Decimal) {
                    return Integer.parseInt(str);
                } else if (dataType == DataType.Short) {
                    return Short.parseShort(str);
                } else if (dataType == DataType.Long) {
                    return Long.parseLong(str);
                } else if (dataType == DataType.Byte) {
                    return Byte.parseByte(str);
                } else if (dataType == DataType.Char) {
                    return str.charAt(0);
                } else if (dataType == DataType.String) {
                    return str;
                } else if (dataType == DataType.Single) {
                    return Float.parseFloat(str);
                } else if (dataType == DataType.Date || dataType == DataType.DateTime || dataType == DataType.Time) {
                    SimpleDateFormat simpleDateFormat = getDateFormat();
                    return simpleDateFormat.parse(str);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}
