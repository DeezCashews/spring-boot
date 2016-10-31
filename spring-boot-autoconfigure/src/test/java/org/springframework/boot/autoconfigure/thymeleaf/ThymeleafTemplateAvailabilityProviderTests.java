/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.thymeleaf;

import org.junit.Test;

import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ThymeleafTemplateAvailabilityProvider}.
 *
 * @author Andy Wilkinson
 */
public class ThymeleafTemplateAvailabilityProviderTests {

	private final TemplateAvailabilityProvider provider = new ThymeleafTemplateAvailabilityProvider();

	private final ResourceLoader resourceLoader = new DefaultResourceLoader();

	private final MockEnvironment environment = new MockEnvironment();

	@Test
	public void availabilityOfTemplateInDefaultLocation() {
		assertTrue(this.provider.isTemplateAvailable("home", this.environment,
				getClass().getClassLoader(), this.resourceLoader));
	}

	@Test
	public void availabilityOfTemplateThatDoesNotExist() {
		assertFalse(this.provider.isTemplateAvailable("whatever", this.environment,
				getClass().getClassLoader(), this.resourceLoader));
	}

	@Test
	public void availabilityOfTemplateWithCustomPrefix() {
		this.environment.setProperty("spring.thymeleaf.prefix",
				"classpath:/custom-templates/");

		assertTrue(this.provider.isTemplateAvailable("custom", this.environment,
				getClass().getClassLoader(), this.resourceLoader));
	}

	@Test
	public void availabilityOfTemplateWithCustomSuffix() {
		this.environment.setProperty("spring.thymeleaf.suffix", ".thymeleaf");

		assertTrue(this.provider.isTemplateAvailable("suffixed", this.environment,
				getClass().getClassLoader(), this.resourceLoader));
	}
}
