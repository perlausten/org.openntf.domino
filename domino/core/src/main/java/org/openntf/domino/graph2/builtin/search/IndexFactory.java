/**
 * Copyright © 2013-2020 The OpenNTF Domino API Team
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
package org.openntf.domino.graph2.builtin.search;

import org.openntf.domino.graph2.DElementStore;
import org.openntf.domino.graph2.builtin.identity.Name;
import org.openntf.domino.graph2.impl.DConfiguration;
import org.openntf.domino.graph2.impl.DGraph;

/*
 * Demonstration class for index graph factory
 * @author Nathan T. Freeman
 */

public class IndexFactory {

	public IndexFactory() {

	}

	public void initGraph() {
		DElementStore termsStore = new org.openntf.domino.graph2.builtin.search.IndexStore();
		termsStore.setStoreKey("ODADemo/terms.nsf");
		termsStore.addType(Term.class);

		DElementStore valuesStore = new org.openntf.domino.graph2.builtin.search.IndexStore();
		valuesStore.setStoreKey("ODADemo/values.nsf");
		valuesStore.addType(Value.class);

		DElementStore namesStore = new org.openntf.domino.graph2.builtin.search.IndexStore();
		namesStore.setStoreKey("ODADemo/names.nsf");
		namesStore.addType(Name.class);

		DConfiguration config = new DConfiguration();
		DGraph graph = new DGraph(config);
		config.addElementStore(termsStore);
		config.addElementStore(valuesStore);
		config.addElementStore(namesStore);
	}

}
