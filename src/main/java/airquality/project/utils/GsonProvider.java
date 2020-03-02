package airquality.project.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {

	private static Gson gson = null;

	private GsonProvider() {
	};

	public static Gson getGsonInstance() {
		return gson != null ? gson : new GsonBuilder().setPrettyPrinting().create();
	}
}
