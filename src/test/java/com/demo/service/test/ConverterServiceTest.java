package com.demo.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.Application;
import com.demo.exception.InvalidFormatException;
import com.demo.service.ConverterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
public class ConverterServiceTest {
	@Autowired
	ConverterService mapperService;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testYearMapper() {
		String input = "1234";
		String output = mapperService.mapYear(input);
		assertThat(input).isEqualTo(output);
	}

	@Test
	public void testYearMapperWithWrongFormat() {
		String input = "abcd";
		exceptionRule.expect(InvalidFormatException.class);
		mapperService.mapYear(input);
	}

	@Test
	public void testQuarterMapper() {
		String input = "Q-2018-2";
		String output = "Q2-18";
		assertThat(output).isEqualTo(mapperService.mapQuarter(input));
	}

	@Test
	public void testQuarterMapperWhenQuarterGreaterThan4() {
		String input = "Q-2018-5";
		exceptionRule.expect(InvalidFormatException.class);
		mapperService.mapQuarter(input);
	}

	@Test
	public void testQuarterMapperWithWrongFormat() {
		String input = "Q-2018-20";
		exceptionRule.expect(InvalidFormatException.class);
		mapperService.mapQuarter(input);
	}

	@Test
	public void testWinterSeasonMapper() {
		String input = "SWS-2018-11";
		String output = "Win-18/19";
		assertThat(output).isEqualTo(mapperService.mapSeason(input));
	}

	@Test
	public void testSummerSeasonMapper() {
		String input = "SWS-2018-05";
		String output = "Sum-18";
		assertThat(output).isEqualTo(mapperService.mapSeason(input));
	}

	@Test
	public void testWinterSeasonMapperWithInvalidMonth() {
		String input = "SWS-2018-13";
		exceptionRule.expect(InvalidFormatException.class);
		mapperService.mapSeason(input);
	}

	@Test
	public void testSeasonMapperWithWrongFormat() {
		String input = "SWS-2018-013";
		exceptionRule.expect(InvalidFormatException.class);
		mapperService.mapSeason(input);
	}

	@Test
	public void testMonthMapper() {
		String input = "M-2018-04";
		String output = "Apr-2018";
		assertThat(output).isEqualTo(mapperService.mapMonth(input));
	}

	@Test
	public void testMonthMapperwithInvalidMonth() {
		String input = "M-2018-013";
		exceptionRule.expect(InvalidFormatException.class);
		mapperService.mapMonth(input);
	}

	@Test
	public void testMonthMapperWithWrongFormat() {
		String input = "MM-2018-013";
		exceptionRule.expect(InvalidFormatException.class);
		mapperService.mapMonth(input);
	}

}
