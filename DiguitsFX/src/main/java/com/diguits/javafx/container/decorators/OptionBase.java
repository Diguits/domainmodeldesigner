package com.diguits.javafx.container.decorators;

public abstract class OptionBase {

	protected int priority;
	protected String groupPath;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getGroupPath() {
		return groupPath;
	}

	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}


	public static class OptionBaseBuilderBase<TBuilder extends OptionBaseBuilderBase<TBuilder, TOption>, TOption extends OptionBase> {
		protected TOption option;

		OptionBaseBuilderBase() {
		}

		@SuppressWarnings("unchecked")
		public TBuilder priority(int priority) {
			option.setPriority(priority);
			return (TBuilder) this;
		}

		public TOption get() {
			return option;
		}
	}
}
