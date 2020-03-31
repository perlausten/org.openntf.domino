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
package org.openntf.domino.graph2.builtin;

import java.util.List;

import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Incidence;
import com.tinkerpop.frames.VertexFrame;

public interface Editor extends VertexFrame {
	@Incidence(label = Edits.LABEL_EDITS)
	public List<Edits> getEdits();

	@Incidence(label = Edits.LABEL_EDITS)
	public Edits addEdits(Editable editable);

	@Incidence(label = Edits.LABEL_EDITS)
	public void removeEdits(Editable editable);

	@Adjacency(label = Edits.LABEL_EDITS)
	public List<Editable> getEditables();

	@Adjacency(label = Edits.LABEL_EDITS)
	public Editable addEditable(Editable editable);

	@Adjacency(label = Edits.LABEL_EDITS)
	public void removeEditable(Editable editable);
}
