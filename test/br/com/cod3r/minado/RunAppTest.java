package br.com.cod3r.minado;

import org.junit.jupiter.api.Test;

class RunAppTest {

	@Test
	void rodandoAplicacao() {
		RunApp.main(new String[] {"arg1", "arg2", "arg3"});
	}
}