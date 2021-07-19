/**
 * Copyright © 2013-2021 The OpenNTF Domino API Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* Generated By:JJTree&JavaCC: Do not edit this line. AtFormulaParser.java */
package org.openntf.domino.tests.rpr.formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lotus.domino.NotesException;
import lotus.domino.Session;

import org.openntf.domino.thread.DominoThread;
import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;
import org.openntf.formula.ASTNode;
import org.openntf.formula.FormulaContext;
import org.openntf.formula.FormulaParser;
import org.openntf.formula.Formulas;
import org.openntf.formula.Function;

public class TestRunner extends TestRunnerStdIn {
	public static void main(final String[] args) {
		DominoThread thread = new DominoThread(new TestRunner(), "My thread");
		thread.start();
	}

	public TestRunner() {
		// whatever you might want to do in your constructor, but stay away from Domino objects
	}

	@SuppressWarnings({ "null", "unchecked" })
	@Override
	public void run() {
		Factory.enableCounters(true, false);
		try {
			System.out.println("Please type a Lotus domino @formula. Quit with CTRL+Z:");
			ASTNode n = null;
			List<Object> v = null;
			//String str = "t:={start}; @for(i:=1;i != 10; i:= i + 1; t:=t:@if(i = 1; {one} ; i <= 3; {two or three}; {four or more})); t";
			//String str = "x:=1:2*+32:64:1;x**x**x**x";
			String str = "@time(1800;2;3;4;15;18)";
			//String str = "@Transform((1:2:3)*+(0:3:6:9);{x};x*x)";
			System.out.println("Formula to test: " + str);

			long time = System.currentTimeMillis();

			//String str = "\"ab\\n\\x\\\"xyzz\"";
			//String str = "t:={start}; @for(i:=1;i != 10; i:= i + 1; t:=t:@Text(i)); @Transform(t;{x};x+{ test }+t)";
			//System.out.println(str);
			List<Function> funcs = new ArrayList<Function>();
			funcs.addAll(Formulas.getFunctionFactory().getFunctions().values());

			Collections.sort(funcs, new Comparator<Function>() {
				@Override
				public int compare(final Function o1, final Function o2) {
					return o1.toString().compareTo(o2.toString());
				}
			});
			for (Function func : funcs) {
				System.out.println(func);
			}

			FormulaParser parser = Formulas.getParser();

			for (int i = 1; i < 10000; i++) {
				java.io.StringReader sr = new java.io.StringReader(str);
				//java.io.Reader r = new java.io.BufferedReader(sr);
				n = parser.parse(sr, false);
			}
			time = System.currentTimeMillis() - time;
			System.err.println("[FormulaEngine] 10000x building AST tree\ttook " + time + "ms.");
			//n.dump("");

			time = System.currentTimeMillis();
			for (int i = 1; i < 10000; i++) {
				FormulaContext ctx = Formulas.createContext(null, parser);
				v = n.solve(ctx);
			}
			time = System.currentTimeMillis() - time;
			System.err.println("[FormulaEngine] 10000x evaluating AST tree\ttook " + time + "ms.");

			System.out.println("Result:\t" + v);

			org.openntf.domino.Session odaSess = Factory.getSession(SessionType.CURRENT);
			Session sess = odaSess.getFactory().toLotus(odaSess);
			@SuppressWarnings("unused")
			long startEvaluate = System.currentTimeMillis();
			try {
				time = System.currentTimeMillis();
				for (int i = 1; i < 10000; i++) {
					v = sess.evaluate(str);
				}
				time = System.currentTimeMillis() - time;
				System.err.println("[NotesNative] 10000x calling session.evaluate\ttook " + time + "ms.");
				System.out.println("Result:\t" + v);

			} catch (NotesException e) {
				e.printStackTrace();
			}

			System.out.println("Thank you.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
