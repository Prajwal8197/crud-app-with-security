package com.casualthoughts.crud_app_with_security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "JWT_SECRET=this_is_a_dummy_secret_for_testing_purposes_only_32_chars"
})
class CrudAppWithSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

}
