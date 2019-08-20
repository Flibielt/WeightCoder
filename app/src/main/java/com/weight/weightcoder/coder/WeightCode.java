package com.weight.weightcoder.coder;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightCode {
	/**
	 * The weight codes.
	 */
	private static String[] weights = {"==", ":==:", "::==::",
			"[==]", ":[==]:", "|==|", ":|==|:", 
			"::|==|::", "[|==|]",":[|==|]:", "||==||", 
			":||==||:", "::||==||::", "[||==||]", 
			":[||==||]:", "|||==|||"};
	
	/**
	 * Code the text into the [||==||] code.
	 * 
	 * @param text the original text
	 * @return the coded text
	 */
	public static String code(String text) {
		List<String> codedText = new ArrayList<String>();
		StringBuilder sb;
		for (int i = 0; i < text.length(); i++) {
			sb = new StringBuilder();
			int c = (text.charAt(i) & 61440) / 4096;
			int d = (text.charAt(i) & 3840) / 256;
			int a = (text.charAt(i) & 240) / 16;
			int b = text.charAt(i) & 15;
			sb.append(weights[c]).append(' ').append(weights[d]).append(' ')
				.append(weights[a]).append(' ').append(weights[b]);
			codedText.add(sb.toString());
		}
		return TextUtils.join(" ", codedText);
	}
	
	private static boolean isValidCode(String code) {
		return Arrays.asList(weights).contains(code);
	}
	
	private static int getWeightIndex(String code) throws WeightCodeException {
		if (isValidCode(code)) {
			for (int i = 0; i < weights.length; i++) {
				if (weights[i].equals(code)) {
					return i;
				}
			}
		}
		
		throw new WeightCodeException("There is no such weight code!");
	}
	
	private static String deleteDoubleSpaces(String coded) {
		if (coded.indexOf("  ") != -1) {
			return deleteDoubleSpaces(coded.replaceAll("  ", " "));
		}
		
		return coded;
	}
	
	/**
	 * Decodes the coded text.
	 * 
	 * @param coded the coded text
	 * @return the decoded text
	 * @throws WeightCodeException if the coded text is incorrect
	 */
	public static String decode(String coded) throws WeightCodeException {
		coded = coded.trim();
		coded = deleteDoubleSpaces(coded);
		StringBuilder decoded = new StringBuilder();
		
		int startPosition = -1;
		int[] spacePositions = new int[5];
		int[] letterInBits = new int[4];
		while (coded.indexOf(' ', startPosition) != -1) {
			spacePositions[0] = startPosition + 1;
			for (int i = 0; i < 4; i++) {
				spacePositions[i + 1] = coded.indexOf(' ', startPosition + 1);
				startPosition = spacePositions[i + 1];
			}
			if (coded.lastIndexOf(' ') == spacePositions[3]) {
				spacePositions[4] = coded.length();
			}
			
			if (spacePositions[1] != coded.indexOf(' ')) {
				for (int i = 0; i < spacePositions.length; i++) {
					if (spacePositions[i] == -1) {
						throw new WeightCodeException("The coded text is wrong!");
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				letterInBits[i] = getWeightIndex(coded.substring(spacePositions[i], spacePositions[i + 1]).trim());
			}
			
			decoded.append(Character.toChars(letterInBits[0] * 4096 + letterInBits[1] * 256 + letterInBits[2] * 16 + letterInBits[3]));
			if (spacePositions[4] == coded.length()) {
				break;
			}
		}
		
		return decoded.toString();
	}
}
