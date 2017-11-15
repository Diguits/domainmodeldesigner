package com.diguits.domainmodeldefinition.serialization.dtomodel;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default(required = false)
public class EnumDefDTO extends SimpleDomainObjectDefDTO
{
	private boolean useIcon;
	private boolean useTitle;
	private boolean useColor;
	private List<EnumValueDefDTO> values;

    public EnumDefDTO()
    {
        values = new ArrayList<EnumValueDefDTO>();
    }

	public List<EnumValueDefDTO> getValues() {
		return values;
	}

	public void setValues(List<EnumValueDefDTO> values) {
		this.values = values;
	}

	public boolean getUseIcon() {
		return useIcon;
	}

	public void setUseIcon(boolean useIcon) {
		this.useIcon = useIcon;
	}

	public boolean getUseTitle() {
		return useTitle;
	}

	public void setUseTitle(boolean useTitle) {
		this.useTitle = useTitle;
	}

	public boolean getUseColor() {
		return useColor;
	}

	public void setUseColor(boolean useColor) {
		this.useColor = useColor;
	}


}
