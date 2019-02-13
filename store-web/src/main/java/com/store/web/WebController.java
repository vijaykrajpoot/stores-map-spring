package com.store.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class WebController {
	private Logger log = LoggerFactory.getLogger(WebController.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String getAllSddStore() {
		log.debug("WebController:invoked:getAllSddStore");
		return "index";
	}

}
