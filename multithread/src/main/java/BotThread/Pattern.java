package BotThread;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pattern {

	private static final long serialVersionUID = 1L;

	private String template;
	private String response;
	private ArrayList<String> responseType = new ArrayList<>();

	public Pattern(String template, String response) {
		this.template = template;
		this.response = response;
		this.responseType.add("notroll");
		this.responseType.add("shuffle");
	}

	public String getTemplate() {
		return this.template;
	}

	public String getResponse() {
		String response = "";
		try {
			response = (String) Pattern.class.getDeclaredMethod(
				this.responseType.get(
					(int)(Math.random() * this.responseType.size())
				)
			).invoke(this);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	private String notroll()
	{
		return this.response;
	}

	private String shuffle()
	{
		List<Character> chars = this.response.chars().mapToObj(e->(char)e).collect(Collectors.toList());;
    	Collections.shuffle(chars);
    	return chars.toString()
			.substring(1, 3 * chars.size() - 1)
			.replaceAll(", ", "");
	}
}
