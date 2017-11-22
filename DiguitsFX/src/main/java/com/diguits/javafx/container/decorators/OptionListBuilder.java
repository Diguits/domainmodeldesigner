package com.diguits.javafx.container.decorators;

import java.util.List;

import com.diguits.javafx.container.decorators.Option.OptionBuilder;
import com.diguits.javafx.container.decorators.OptionGroup.OptionGroupBuilder;
import com.diguits.javafx.container.decorators.OptionSeparator.OptionSeparatorBuilder;

public class OptionListBuilder {
	List<OptionBase> options;


	public OptionListBuilder(List<OptionBase> options) {
		super();
		this.options = options;
	}

	public OptionBuilder addOption() {
		OptionBuilder optionBuilderForList = new OptionBuilder();
		options.add(optionBuilderForList.option);
		return optionBuilderForList;
	}

	public OptionGroupBuilder addOptionGroup(String name) {
		OptionGroupBuilder optionGroupBuilderForList = new OptionGroupBuilder(name);
		options.add(optionGroupBuilderForList.option);
		return optionGroupBuilderForList;
	}

	public OptionSeparatorBuilder addSeparator() {
		OptionSeparatorBuilder optionSeparatorBuilderForList = new OptionSeparatorBuilder();
		options.add(optionSeparatorBuilderForList.option);
		return optionSeparatorBuilderForList;
	}

	public void addSeparator(int priority) {
		OptionSeparatorBuilder optionSeparatorBuilderForList = new OptionSeparatorBuilder();
		optionSeparatorBuilderForList.priority(priority);
		options.add(optionSeparatorBuilderForList.option);
	}

	public OptionBuilder addOption(String groupPath) {
		OptionBuilder optionBuilderForList = new OptionBuilder();
		Option option = optionBuilderForList.option;
		option.setGroupPath(groupPath);
		options.add(option);
		return optionBuilderForList;
	}

	public OptionGroupBuilder addOptionGroup(String name, String groupPath) {
		OptionGroupBuilder optionGroupBuilderForList = new OptionGroupBuilder(name);
		OptionGroup option = optionGroupBuilderForList.option;
		option.setGroupPath(groupPath);
		options.add(option);
		return optionGroupBuilderForList;
	}

	public OptionSeparatorBuilder addSeparator(String groupPath) {
		OptionSeparatorBuilder optionSeparatorBuilderForList = new OptionSeparatorBuilder();
		OptionSeparator option = optionSeparatorBuilderForList.option;
		option.setGroupPath(groupPath);
		options.add(option);
		return optionSeparatorBuilderForList;
	}

	public void addSeparator(String groupPath,int priority) {
		OptionSeparatorBuilder optionSeparatorBuilderForList = new OptionSeparatorBuilder();
		optionSeparatorBuilderForList.priority(priority);
		OptionSeparator option = optionSeparatorBuilderForList.option;
		option.setGroupPath(groupPath);
		options.add(option);
	}
}