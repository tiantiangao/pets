package com.gtt.pets.web.util;

import com.gtt.kenshin.web.util.EncryptionUtils;
import com.gtt.pets.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author tiantiangao
 */
@Service
public class EncryptionInitializer {

	@Autowired
	private GlobalService globalService;

	@PostConstruct
	public void init() {
		EncryptionUtils.setEncryptKey(globalService.get("encryption.key"));
		EncryptionUtils.setEncryptIV(globalService.get("encryption.iv"));
	}

}
