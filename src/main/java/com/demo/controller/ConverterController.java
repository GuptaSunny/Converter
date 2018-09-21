package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.ConverterService;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class ConverterController {

	@Autowired
	private ConverterService mapperService;

	@RequestMapping(path = "/year/{input}", method = RequestMethod.GET)
	public ResponseEntity<String> convertYear(@PathVariable(value = "input") String input) {
		return ResponseEntity.ok(mapperService.mapYear(input));
	}

	@RequestMapping(path = "/quarter/{input}", method = RequestMethod.GET)
	public ResponseEntity<String> convertQuarter(@PathVariable(value = "input") String input) {
		return ResponseEntity.ok(mapperService.mapQuarter(input));
	}

	@RequestMapping(path = "/season/{input}", method = RequestMethod.GET)
	public ResponseEntity<String> convertSeason(@PathVariable(value = "input") String input) {
		return ResponseEntity.ok(mapperService.mapSeason(input));
	}

	@RequestMapping(path = "/month/{input}", method = RequestMethod.GET)
	public ResponseEntity<String> convertMonth(@PathVariable(value = "input") String input) {
		return ResponseEntity.ok(mapperService.mapMonth(input));
	}

}
