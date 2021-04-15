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
package org.openntf.domino.graph2.builtin.workflow.definition;

import org.openntf.domino.graph2.annotations.TypedProperty;
import org.openntf.domino.graph2.builtin.DEdgeFrame;

import com.tinkerpop.frames.InVertex;
import com.tinkerpop.frames.OutVertex;

public interface FollowedBy extends DEdgeFrame {
	public final static String LABEL_FLOWFOLLOWEDBY = "FlowFollowedBy"; //$NON-NLS-1$

	@TypedProperty("NeededOutcome")
	public String getNeededOutcome();

	@TypedProperty("NeededOutcome")
	public void setNeededOutcome(String outcome);

	@OutVertex
	public TaskDefinition getSourceDefinition();

	@InVertex
	public TaskDefinition getTargetDefinition();

}
