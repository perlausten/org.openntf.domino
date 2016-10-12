/*
 * � Copyright IBM Corp. 2009,2010
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
package org.openntf.domino.extmgr.events;

import org.openntf.domino.extmgr.EMBridgeEventParams;

public class FTIndexEvent extends DatabaseEvent {
	public static EMBridgeEventParams[] params = { EMBridgeEventParams.SourceDbpath, EMBridgeEventParams.Options,
			EMBridgeEventParams.Stopfile, EMBridgeEventParams.DocsAdded, EMBridgeEventParams.DocsUpdated, EMBridgeEventParams.DocsDelete,
			EMBridgeEventParams.BytesIndexed, EMBridgeEventParams.Username };

	@Override
	public EMBridgeEventParams[] getParams() {
		return params;
	}

	public FTIndexEvent() {
		super(EMEventIds.EM_FTINDEX.getId());
	}

	public int getOptions() {
		return (Integer) getEventValuesMap().get(EMBridgeEventParams.Options);
	}

	public String getStopFile() {
		return (String) getEventValuesMap().get(EMBridgeEventParams.Stopfile);
	}

	public long getDocsAdded() {
		return (Long) getEventValuesMap().get(EMBridgeEventParams.DocsAdded);
	}

	public long getDocsDeleted() {
		return (Long) getEventValuesMap().get(EMBridgeEventParams.DocsDelete);
	}

	public long getDocsUpdated() {
		return (Long) getEventValuesMap().get(EMBridgeEventParams.DocsUpdated);
	}

	public long getBytesIndexed() {
		return (Long) getEventValuesMap().get(EMBridgeEventParams.BytesIndexed);
	}

}