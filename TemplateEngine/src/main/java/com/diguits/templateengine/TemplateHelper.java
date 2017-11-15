package com.diguits.templateengine;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldefinition.definitions.FieldDef;

public class TemplateHelper {

	public String firstCharToLower(String fieldName){
        return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
    }

    public String format(Object value){
        if (value == null)
            return "null";
        if (value instanceof Boolean)
            return ((Boolean) value).toString().toLowerCase();
        return value.toString();
    }

    public String getJavaTypeName(FieldDef field){
    	return getJavaTypeName(field, field.getAllowNull());
    }
    public String getJavaTypeName(FieldDef field, boolean objectType)
    {
        switch (field.getDataType())
        {
            case Enum:
                return field.getEnumDef().getName();
            case Entity:
            case EntityList:
                return field.getRelatedEntity().getName();
            default:
                return getJavaTypeName(field.getDataType(), field.getAllowNull() || objectType);
        }
    }

    public String getJavaTypeName(DataType dataType, boolean allowNull)
    {
        switch (dataType){
            case Boolean:
                return allowNull ? "Boolean" : "boolean";
            case Byte:
                return allowNull ? "Byte" : "byte";
            case ByteArray:
                return "byte[]";
            case Char:
                return allowNull ? "Character" : "char";
            case Time:
            case Date:
            case DateTime:
                return "Date";
            case Decimal:
                return allowNull ? "Double" : "double";
            case Double:
                return allowNull ? "Double" : "double";
            case Guid:
                return "UUID";
            case Image:
                return "byte[]";
            case Int:
                return allowNull ? "Integer" : "int";
            case Long:
                return allowNull ? "Long" : "long";
            case Short:
                return allowNull ? "Short" : "short";
            case Single:
                return allowNull ? "Float" : "float";
            case String:
                return "String";
		default:
			break;
        }
        return "Error";
    }
}
