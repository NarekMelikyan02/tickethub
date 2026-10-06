package com.tickethub.tickethub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TickethubApplicationTests {

	@Test
	void contextLoads() {
	}

}
