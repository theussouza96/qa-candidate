package qa.candidate.candidate;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import qa.candidate.model.GenerateTokenRequest;
import qa.candidate.service.JwtService;
import qa.candidate.service.impl.JwtServiceImpl;

@SpringBootTest
class CandidateApplicationTests {

	String tokenValido = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
	String tokenInvalido = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg26";

	@Test
	void testValidarTokenValido() {
		JwtServiceImpl jwt = new JwtServiceImpl();
		Assert.isTrue(jwt.verificarToken(tokenValido));
	}

	@Test
	void testValidarTokenInvalido() {
		JwtServiceImpl jwt = new JwtServiceImpl();
		Assert.isTrue(!jwt.verificarToken(tokenInvalido));
	}

	@Test //TODO
	void testGerarToken() {
		JwtServiceImpl jwt = new JwtServiceImpl();
}}
