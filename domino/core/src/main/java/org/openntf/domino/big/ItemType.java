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
package org.openntf.domino.big;

import java.io.Externalizable;

import org.openntf.domino.Item;

public interface ItemType extends Externalizable {
	public boolean isProtected();

	public boolean isNames();

	public boolean isAuthors();

	public boolean isReaders();

	public boolean isSummary();

	public boolean isEncrypted();

	public boolean isSigned();

	public Item.Type getType();

	public void setType(Item.Type type);

	public Item.Flags[] getFlags();

	public void setFlags(int flags);

	public Class<?> getJavaType();
}
