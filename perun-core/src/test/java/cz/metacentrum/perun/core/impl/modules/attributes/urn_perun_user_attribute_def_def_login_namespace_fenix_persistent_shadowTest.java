package cz.metacentrum.perun.core.impl.modules.attributes;

import cz.metacentrum.perun.core.api.BeansUtils;
import cz.metacentrum.perun.core.api.CoreConfig;
import cz.metacentrum.perun.core.impl.modules.ModulesConfigLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Vojtech Sassmann <vojtech.sassmann@gmail.com>
 */
public class urn_perun_user_attribute_def_def_login_namespace_fenix_persistent_shadowTest {

	private final ModulesConfigLoader mockedModulesConfigLoader = Mockito.mock(ModulesConfigLoader.class);
	private final CoreConfig mockedCoreConfig = Mockito.mock(CoreConfig.class, RETURNS_DEEP_STUBS);
	private CoreConfig originConfig;

	private urn_perun_user_attribute_def_def_login_namespace_fenix_persistent_shadow module;

	@Before
	public void setUp() {
		originConfig = BeansUtils.getCoreConfig();
		// this config has to be mocked, because it is used in the module's super class
		BeansUtils.setConfig(mockedCoreConfig);
		module = new urn_perun_user_attribute_def_def_login_namespace_fenix_persistent_shadow(mockedModulesConfigLoader);
	}

	@After
	public void tearDown() {
		BeansUtils.setConfig(originConfig);
		Mockito.reset(mockedModulesConfigLoader);
	}

	@Test
	public void testGetExtSourceNameFenix() {
		String testValue = "ExtSourceName";
		when(mockedModulesConfigLoader.loadString(any(), any()))
			.thenReturn(testValue);

		assertThat(module.getExtSourceNameFenix()).isEqualTo(testValue);
	}

	@Test
	public void testGetDomainNameFenix() {
		String testValue = "Domain name";
		when(mockedModulesConfigLoader.loadString(any(), any()))
			.thenReturn(testValue);

		assertThat(module.getDomainNameFenix()).isEqualTo(testValue);
	}

	@Test
	public void testExtSourceValueIsNotLoadedAgain() {
		String testValue = "ExtSourceName";

		when(mockedModulesConfigLoader.loadString(any(), any()))
			.thenReturn(testValue);

		module.getExtSourceNameFenix();
		module.getExtSourceNameFenix();

		verify(mockedModulesConfigLoader, times(1)).loadString(any(), eq("extSourceNameFenix"));
	}

	@Test
	public void testdomainNameFenixIsNotLoadedAgain() {
		String testValue = "Domain name";

		when(mockedModulesConfigLoader.loadString(any(), any()))
			.thenReturn(testValue);

		module.getDomainNameFenix();
		module.getDomainNameFenix();

		verify(mockedModulesConfigLoader, times(1)).loadString(any(), eq("domainNameFenix"));
	}
}
