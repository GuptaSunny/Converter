package com.demo.service;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.demo.exception.InvalidFormatException;

@Component
public class ConverterService {

	private DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);

	public String mapYear(String input) {
		if (!Pattern.matches("\\d{4}", input)) {
			throw new InvalidFormatException(String.format("Invalid String %s", input));
		}
		IConverter converter = parameter -> {
			return parameter;
		};
		return converter.convert(input);
	}

	public String mapQuarter(String input) {
		if (!Pattern.matches("^Q(-){1}+[0-9]{4}(-)[1-4]{1}$", input)) {
			throw new InvalidFormatException(String.format("Invalid String %s", input));
		}
		IConverter converter = parameter -> {
			StringBuffer output = new StringBuffer("Q");
			String[] values = parameter.split("-");
			output.append(values[2]);
			output.append("-");
			output.append(values[1].substring(2, 4));
			return output.toString();
		};
		return converter.convert(input);
	}

	public String mapSeason(String input) {
		if (!Pattern.matches("^SWS(-){1}+[0-9]{4}(-)(0?[1-9]|1[012])$", input)) {
			throw new InvalidFormatException(String.format("Invalid String %s", input));
		}
		IConverter converter = parameter -> {
			String[] values = parameter.split("-");
			StringBuffer output = new StringBuffer();
			int monthAsNumber = Integer.parseInt(values[2]);
			if (monthAsNumber > 03 && monthAsNumber < 10) {
				output.append("Sum-");
				output.append(values[1].substring(2, 4));
			} else {
				output.append("Win-");
				int yearAsNumber = Integer.parseInt(values[1].substring(2, 4));
				output.append(yearAsNumber);
				output.append("/");
				output.append(yearAsNumber + 1);
			}
			return output.toString();
		};
		return converter.convert(input);
	}

	public String mapMonth(String input) {
		if (!Pattern.matches("^M(-){1}+[0-9]{4}(-)(0?[1-9]|1[012])$", input)) {
			throw new InvalidFormatException(String.format("Invalid String %s", input));
		}
		IConverter converter = parameter -> {
			StringBuffer output = new StringBuffer();
			String[] values = parameter.split("-");
			int monthAsNumber = Integer.parseInt(values[2]);
			String month = dfs.getShortMonths()[monthAsNumber - 1];
			output.append(month);
			output.append("-");
			output.append(values[1]);
			return output.toString();
		};
		return converter.convert(input);
	}

}
