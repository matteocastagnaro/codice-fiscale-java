package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import document.CitiesCodes;

public class Utils {

	private static CitiesCodes cc = new CitiesCodes();
	public static final String ERROR = "#EE#";

	public static CitiesCodes getCitiesCodes(){
		return cc;
	}
	
	public static String parseDate(String data){
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat to = new SimpleDateFormat("dd MMMM yyyy");
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}

	public static String parseDate(String data, String da, String a){
		SimpleDateFormat from = new SimpleDateFormat(da);
		SimpleDateFormat to = new SimpleDateFormat(a);
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);

		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}
	
	public static String dateToParse(int day, int month, int year){
		return year + "-" + month + "-" + day;
	}
	
	public static String reverseParsing(String data){
		SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat from = new SimpleDateFormat("dd MMMM yyyy");
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}
	
	public static String reverseParsing(String data, String formatFrom){
		SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat from = new SimpleDateFormat(formatFrom);
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}

}
