/**
 * Copyright 2016 Molindo GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.custom.mapping;

import org.springframework.data.mapping.model.BasicPersistentEntity;
import org.springframework.data.util.TypeInformation;

public class CustomPersistentEntityImpl<T> extends BasicPersistentEntity<T, CustomPersistentProperty> {

	public CustomPersistentEntityImpl(final TypeInformation<T> information) {
		super(information, null);
	}

	@Override
	protected CustomPersistentProperty returnPropertyIfBetterIdPropertyCandidateOrNull(final CustomPersistentProperty property) {
		return property.isIdProperty() ? property : null;
	}

}
