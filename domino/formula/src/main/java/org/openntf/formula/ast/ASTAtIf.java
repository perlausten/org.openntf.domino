/* Generated By:JJTree: Do not edit this line. ASTAtIf.java Version 4.3 */
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
 * Implements the {@literal @}If formula
 * 
 * @author Roland Praml, Foconis AG
 * 
 */
public class ASTAtIf extends SimpleNode {

	public ASTAtIf(final AtFormulaParserImpl p, final int id) {
		super(p, id);
	}

	/**
	 * {@literal @}If returns an error, if the condition statement fails
	 */
	@Override
	public ValueHolder evaluate(final FormulaContext ctx) throws FormulaReturnException {
		ValueHolder nIf;
		int i = 0;
		nIf = children[i++].evaluate(ctx);

		while (i < children.length) {
			if (nIf.dataType == DataType.ERROR)
				return nIf;

			if (nIf.isTrue(ctx)) {
				return children[i++].evaluate(ctx);
			} else {
				i++;
			}
			nIf = children[i++].evaluate(ctx);
		}

		return nIf; // returns always TRUE

	}

	/*
	 * (non-Javadoc)
	 * @see org.openntf.formula.ast.SimpleNode#analyzeThis(java.util.Set, java.util.Set, java.util.Set, java.util.Set)
	 */
	@Override
	protected void analyzeThis(final Set<String> readFields, final Set<String> modifiedFields, final Set<String> variables,
			final Set<String> functions) {
		functions.add("@if");
	}
}
/* JavaCC - OriginalChecksum=bfde8d6612dc75ddc7c7b037fbeccfff (do not edit this line) */
