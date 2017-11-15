package com.diguits.javafx.container.decorators;

public class OptionSeparator extends OptionBase{

	public static class OptionSeparatorBuilder extends OptionBaseBuilderBase<OptionSeparatorBuilder, OptionSeparator>{

		OptionSeparatorBuilder() {
			option = new OptionSeparator();
		}
	}

	@Override
	public String toString() {
		String string = "Separator: ";
		if (groupPath != null)
			string += groupPath;
		string += " ("+priority+")";
		return string;
	}
}
