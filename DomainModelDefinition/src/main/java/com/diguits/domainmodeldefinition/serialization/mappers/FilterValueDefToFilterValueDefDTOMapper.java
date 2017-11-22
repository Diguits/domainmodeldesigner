package com.diguits.domainmodeldefinition.serialization.mappers;

import com.diguits.domainmodeldefinition.definitions.DataType;
import com.diguits.domainmodeldefinition.definitions.FieldDef;
import com.diguits.domainmodeldefinition.serialization.dtomodel.FilterValueDefDTO;
import com.diguits.domainmodeldefinition.definitions.FilterValueDef;
import com.diguits.common.mapping.MappingContext;
import com.diguits.common.mapping.IMapperProvider;
import com.diguits.domainmodeldefinition.serialization.services.ObjectToStringConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FilterValueDefToFilterValueDefDTOMapper extends BaseDefToBaseDefDTOMapper<FilterValueDef, FilterValueDefDTO> {
    FilterValueDefToFilterValueDefDTOMapper filterDefToFilterValueDefDTOMapper;

    public FilterValueDefToFilterValueDefDTOMapper(IMapperProvider mapperProvider) {
        super(mapperProvider);
    }

    public FilterValueDefToFilterValueDefDTOMapper getFilterValueDefToFilterValueDefDTOMapper() {
        if (filterDefToFilterValueDefDTOMapper == null)
            filterDefToFilterValueDefDTOMapper = mapperProvider.getMapper(FilterValueDefToFilterValueDefDTOMapper.class);
        return filterDefToFilterValueDefDTOMapper;
    }

    public void map(FilterValueDef source, FilterValueDefDTO target, MappingContext context) {
        if (source == null || target == null) return;
        target.setLogicalOperator(source.getLogicalOperator());
        target.setPath(source.getPath());
        target.setOperator(source.getOperator());
        target.setValue(source.getValue());
        target.setValue2(source.getValue2());
        List<FilterValueDefDTO> FiltersList = target.getFilterValues();
        for (FilterValueDef item : source.getFilterValues()) {
            FiltersList.add(getFilterValueDefToFilterValueDefDTOMapper().map(item, context));
        }
        FieldDef field = source.getField();
        if (field != null && field.getDataType() != null) {
            target.setDataType(field.getDataType());
            target.setValue(ObjectToStringConverter.toString(field.getDataType(), source.getValue()));
            target.setValue2(ObjectToStringConverter.toString(field.getDataType(), source.getValue2()));
        }
        super.map(source, target, context);
    }

    public void mapBack(FilterValueDefDTO source, FilterValueDef target, MappingContext context) {
        if (source == null || target == null) return;
        target.setLogicalOperator(source.getLogicalOperator());
        target.setPath(source.getPath());
        target.setOperator(source.getOperator());
        List<FilterValueDef> filtersList = target.getFilterValues();
        for (FilterValueDefDTO item : source.getFilterValues()) {
            filtersList.add(getFilterValueDefToFilterValueDefDTOMapper().mapBack(item, context));
        }
        DataType dataType = source.getDataType();
        if (dataType != null) {
            String valueString = "";
            String value2String = "";
            if (source.getValue() != null && source.getValue() instanceof String) {
                valueString = (String) source.getValue();
            }
            if (source.getValue2() != null && source.getValue2() instanceof String) {
                value2String = (String) source.getValue2();
            }
            if (valueString.length() > 0) {
                target.setValue(ObjectToStringConverter.toObject(dataType, valueString));
            } else {
                target.setValue(source.getValue());
            }
            if (value2String.length() > 0) {
                target.setValue2(ObjectToStringConverter.toObject(dataType, value2String));
            } else {
                target.setValue2(source.getValue2());
            }
        } else {
            target.setValue(source.getValue());
            target.setValue2(source.getValue2());
        }
        super.mapBack(source, target, context);
    }

    @Override
    protected Class<FilterValueDefDTO> getToClass() {
        return FilterValueDefDTO.class;
    }

    @Override
    protected Class<FilterValueDef> getFromClass() {
        return FilterValueDef.class;
    }
}
