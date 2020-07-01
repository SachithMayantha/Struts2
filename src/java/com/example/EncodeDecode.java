/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Base64;

/**
 *
 * @author mayantha_f
 */
public class EncodeDecode {
    public String encode(String ep) {
		String en = Base64.getEncoder().encodeToString(ep.getBytes());
		return en;
	}
	public String decode(String encoded) {
		return new String(Base64.getDecoder().decode(encoded));
	}
}
