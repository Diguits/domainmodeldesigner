package com.diguits.templateengine.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.diguits.templateengine.contract.Diagnostic.Kind;

public final class DiagnosticCollector implements IDiagnosticCollector {
	private List<Diagnostic> diagnostics = Collections.synchronizedList(new ArrayList<Diagnostic>());

	public void report(Diagnostic diagnostic) {
		diagnostics.getClass();
		diagnostics.add(diagnostic);
	}


	@Override
	public void report(List<Diagnostic> diagnostics) {
		if (diagnostics != null && diagnostics.size() > 0)
			for (Diagnostic diagnostic : diagnostics) {
				report(diagnostic);
			}
	}
	/**
	 * Gets a list view of diagnostics collected by this object.
	 *
	 * @return a list view of diagnostics
	 */
	public List<Diagnostic> getDiagnostics() {
		return Collections.unmodifiableList(diagnostics);
	}

	@Override
	public boolean hasError() {
		for (Diagnostic diagnostic : diagnostics) {
			if (diagnostic.getKind() == Kind.ERROR)
				return true;
		}
		return false;
	}

}