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
package org.springframework.data.custom.test;

import java.util.Collections;

public class CustomEntityRepositoryImpl implements CustomEntityRepositoryCustom {

	public static final int KNOWN_ID = 42;

	public static final String SOMETHING_ARG = "foo";
	public static final int SOMETHING = 4711;

	@Override
	public CustomEntity findOne(final Integer id) {
		return id != null && id == KNOWN_ID ? new CustomEntity(KNOWN_ID, "foo") : null;
	}

	@Override
	public Iterable<CustomEntity> findAll() {
		return Collections.singletonList(findOne(KNOWN_ID));
	}

	@Override
	public int findSomething(final String arg) {
		return SOMETHING_ARG.equals(arg) ? SOMETHING : null;
	}

}
