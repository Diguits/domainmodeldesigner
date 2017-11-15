package com.diguits.templateengine.contract;

import java.util.List;

public interface IDiagnosticCollector {
    /**
     * Invoked when a problem is found.
     *
     * @param diagnostic a diagnostic representing the problem that
     * was found
     * @throws NullPointerException if the diagnostic argument is
     * {@code null} and the implementation cannot handle {@code null}
     * arguments
     */
    void report(Diagnostic diagnostic);
    void report(List<Diagnostic> diagnostics);
    boolean hasError();
}
