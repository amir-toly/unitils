package org.unitils.tapestry;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4BlockTestClassRunner;
import org.unitils.tapestry.annotation.RegistryFactory;
import org.unitils.tapestry.annotation.TapestryRegistry;

@RunWith(UnitilsJUnit4BlockTestClassRunner.class)
@TapestryRegistry(value = {Module.class})
public class TestCreatesRegistryWithRegistryFactoryAnnotationTest {

	private Registry registryCreatedByFactoryMethod;
	private Class<?>[] modulesPassedToCreateRegistry;
	@Inject
	private Registry injectedRegistry;
	
	@Test
	public void useAnnotatedMethodToCreateRegistry() {
		assertArrayEquals(new Class<?>[] { Module.class }, modulesPassedToCreateRegistry);
		assertNotNull(registryCreatedByFactoryMethod);
		assertSame(registryCreatedByFactoryMethod, injectedRegistry);
	}
	
	@TapestryRegistry(value = {}, registryFactoryMethodName = "createMethodRegistry")
	@Test
	public void registryFactoryMethodOverridesAnnotatedMethod() {
		assertArrayEquals(new Class<?>[] { }, modulesPassedToCreateRegistry);
		assertNotNull(registryCreatedByFactoryMethod);
		assertSame(registryCreatedByFactoryMethod, injectedRegistry);
	}
	
	public Registry createMethodRegistry(Class<?>[] modules) {
		modulesPassedToCreateRegistry = modules;
		RegistryBuilder builder = new RegistryBuilder();
		for (Class<?> module : modules) {
			builder.add(module);
		}
		builder.add(Module.class);
		registryCreatedByFactoryMethod = builder.build();
		return registryCreatedByFactoryMethod;
	}
	
	@RegistryFactory
	public Registry createRegistry(Class<?>[] modules) {
		modulesPassedToCreateRegistry = modules;
		RegistryBuilder builder = new RegistryBuilder();
		for (Class<?> module : modules) {
			builder.add(module);
		}
		registryCreatedByFactoryMethod = builder.build();
		return registryCreatedByFactoryMethod;
	}
	
}
