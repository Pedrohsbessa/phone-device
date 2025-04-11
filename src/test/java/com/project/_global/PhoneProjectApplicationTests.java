package com.project._global;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PhoneProjectApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	void mainMethodStartsApplication() {
		PhoneProjectApplication.main(new String[] {});
		assertThat(applicationContext).isNotNull();
	}

	@Test
	void applicationHasAllRequiredBeans() {
		assertThat(applicationContext.getBeanDefinitionCount()).isGreaterThan(0);

		// Verificando os beans pelo tipo em vez do nome
		assertThat(applicationContext.getBeanNamesForType(com.project._global.controller.PhoneController.class))
				.isNotEmpty();
		assertThat(applicationContext.getBeanNamesForType(com.project._global.application.service.PhoneService.class))
				.isNotEmpty();
		assertThat(applicationContext
				.getBeanNamesForType(com.project._global.infrastructure.repository.PhoneRepository.class))
				.isNotEmpty();

		// Ou usando os nomes corretos dos beans (gerados automaticamente)
		assertThat(applicationContext.containsBeanDefinition("phoneController")).isTrue();
		assertThat(applicationContext.containsBeanDefinition("phoneServiceImpl")).isTrue(); // Note o 'Impl' aqui
		assertThat(applicationContext.containsBeanDefinition("phoneRepository")).isTrue();
	}

	@Test
	void applicationPropertiesAreLoaded() {
		assertThat(applicationContext.getEnvironment().getProperty("spring.application.name"))
				.isEqualTo("phone-project");
	}
}