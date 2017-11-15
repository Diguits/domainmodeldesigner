package com.diguits.javafx.container.decorators;

import java.util.ArrayList;
import java.util.List;

import com.diguits.javafx.container.decorators.OptionSeparator.OptionSeparatorBuilder;

public class OptionGroup extends Option {
	private String name;
	private List<OptionBase> options;

	public OptionGroup(String name) {
		super();
		this.name = name;
		options = new ArrayList<>();
	}

	public List<OptionBase> getOptions() {
		return options;
	}

	public void setOptions(List<OptionBase> options) {
		this.options = options;
	}

	public String getName() {
		return name;
	}

	public void add(OptionBase option) {
		addOrdered(option);
	}

	private void addOrdered(OptionBase option) {
		options.add(option);
	}

	public OptionBuilder addOption() {
		OptionBuilder optionBuilderForList = new OptionBuilder();
		add(optionBuilderForList.option);
		return optionBuilderForList;
	}

	public OptionGroupBuilder addOptionGroup(String name) {
		OptionGroupBuilder optionGroupBuilderForList = new OptionGroupBuilder(name);
		add(optionGroupBuilderForList.option);
		return optionGroupBuilderForList;
	}

	public void addSeparator(int priority) {
		OptionSeparatorBuilder optionSeparatorBuilderForList = new OptionSeparatorBuilder();
		optionSeparatorBuilderForList.priority(priority);
		add(optionSeparatorBuilderForList.option);
	}

	public static class OptionGroupBuilder extends OptionBuilderBase<OptionGroupBuilder, OptionGroup> {

		OptionGroupBuilder(String name) {
			option = new OptionGroup(name);
		}
	}

	@Override
	public String toString() {
		String string = "Option Group: ";
		if (groupPath != null)
			string += groupPath + ".";
		if (name != null)
			string += name;
		if (action != null && action.getText() != null && !action.getText().isEmpty())
			string += " Text(" + action.getText() + ")";
		if (action != null && action.getImageName() != null && !action.getImageName().isEmpty())
			string += " Image name (" + action.getImageName() + ")";
		string += " Priority ("+priority+")";
		return string;
	}
}
