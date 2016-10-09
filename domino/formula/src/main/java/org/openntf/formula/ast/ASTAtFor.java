/* Generated By:JJTree: Do not edit this line. ASTAtFor.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
/*
 * © Copyright FOCONIS AG, 2014
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package org.openntf.formula.ast;

import java.util.Set;

import org.openntf.formula.FormulaContext;
import org.openntf.formula.FormulaReturnException;
import org.openntf.formula.ValueHolder;
import org.openntf.formula.ValueHolder.DataType;
import org.openntf.formula.parse.AtFormulaParserImpl;

/**
 * Implements the {@literal @}For function
 * 
 */
public class ASTAtFor extends SimpleNode {

	public ASTAtFor(final AtFormulaParserImpl p, final int id) {
		super(p, id);
	}

	/**
	 * Error handling is a little bit complexer, as we must return the Error-ValueHolders of init/condition/increment if one of them fail
	 */
	@Override
	public ValueHolder evaluate(final FormulaContext ctx) throws FormulaReturnException {
		Node init = children[0];
		Node condition = children[1];
		Node increment = children[2];

		ValueHolder vh = init.evaluate(ctx);
		if (vh.dataType == DataType.ERROR)
			return vh;

		while (true) {

			vh = condition.evaluate(ctx);
			if (vh.dataType == DataType.ERROR)
				return vh;
			if (!vh.isTrue(ctx))
				break;

			// execute statements
			for (int i = 3; i < children.length; ++i) {
				children[i].evaluate(ctx);
			}

			vh = increment.evaluate(ctx);
			if (vh.dataType == DataType.ERROR)
				return vh;
		}

		return ctx.TRUE; // returns always TRUE
	}

	/*
	 * (non-Javadoc)
	 * @see org.openntf.formula.ast.SimpleNode#analyzeThis(java.util.Set, java.util.Set, java.util.Set, java.util.Set)
	 */
	@Override
	protected void analyzeThis(final Set<String> readFields, final Set<String> modifiedFields, final Set<String> variables,
			final Set<String> functions) {
		functions.add("@for");
	}
}
/* JavaCC - OriginalChecksum=f70d2e6a39964b2d6bee287bd8bf7aca (do not edit this line) */
