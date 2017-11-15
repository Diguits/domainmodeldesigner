package com.diguits.javafx.container.decorators;

public class Option extends OptionBase {
	Action action;

	public Option() {
		super();
	}

	public Option(Action action, int priority) {
		super();
		this.action = action;
		this.priority = priority;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public static class OptionBuilderBase<TBuilder extends OptionBuilderBase<TBuilder, TOption>, TOption extends Option>
			extends OptionBaseBuilderBase<TBuilder, TOption> {
		@SuppressWarnings("unchecked")
		public TBuilder action(Action action) {
			option.setAction(action);
			return (TBuilder) this;
		}
	}

	public static class OptionBuilder extends OptionBuilderBase<OptionBuilder, Option> {
		OptionBuilder() {
			option = new Option();
		}
	}

	@Override
	public String toString() {
		String string = "Option: ";
		if (groupPath != null)
			string += groupPath;
		if (action != null && action.getText() != null && !action.getText().isEmpty())
			string += " Text(" + action.getText() + ")";
		if (action != null && action.getImageName() != null && !action.getImageName().isEmpty())
			string += " Image name (" + action.getImageName() + ")";
		string += " Priority ("+priority+")";
		return string;
	}
}
