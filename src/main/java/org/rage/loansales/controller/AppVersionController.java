package org.rage.loansales.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A URI to retrieve the information about the version.
 * 
 * @author <roar109@gmail.com> Hector Mendoza
 * */
@RequestMapping("/AppVersion")
@Controller("appVersionController")
public class AppVersionController {
	
	@RequestMapping(produces="text/html")
	public @ResponseBody ResponseEntity getAppVersion(){
		//TODO tomar la version de el metainf 
		ResponseEntity re = new ResponseEntity("V 1.0.0",HttpStatus.OK);
		return re;
	}
}
