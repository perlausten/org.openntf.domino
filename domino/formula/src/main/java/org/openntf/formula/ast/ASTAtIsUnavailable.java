/* Generated By:JJTree: Do not edit this line. ASTAtIsUnavailable.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.openntf.formula.ast;

import java.util.Set;

import org.openntf.formula.FormulaContext;
import org.openntf.formula.FormulaReturnException;
import org.openntf.formula.ValueHolder;
import org.openntf.formula.parse.AtFormulaParserImpl;

public class ASTAtIsUnavailable extends ASTAtIsAvailable {

	public ASTAtIsUnavailable(final AtFormulaParserImpl p, final int id) {
		super(p, id);
	}

	@Override
	public ValueHolder evaluate(final FormulaContext ctx) throws FormulaReturnException {
		return checkAvailable(ctx) ? ctx.FALSE : ctx.TRUE;
	}

	/* (non-Javadoc)
	 * @see org.openntf.formula.ast.ASTAtIsAvailable#analyzeThis(java.util.Set, java.util.Set, java.util.Set, java.util.Set)
	 */
	@Override
	protected void analyzeThis(final Set<String> readFields, final Set<String> modifiedFields, final Set<String> variables,
			final Set<String> functions) {
		functions.add("@isunavailable");
	}

}
/* JavaCC - OriginalChecksum=f3b6fabf50caf0edf2cf32de73733c67 (do not edit this line) */
