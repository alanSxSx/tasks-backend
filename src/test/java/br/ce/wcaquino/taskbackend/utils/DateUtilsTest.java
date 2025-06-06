package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.now().plusDays(1);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}

	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		LocalDate date = LocalDate.now().minusDays(1);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
	}

	@Test
	public void deveRetornarTrueParaDataAtual() {
		LocalDate date = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
	}


}
