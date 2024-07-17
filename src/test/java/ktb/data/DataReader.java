package ktb.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<HashMap<String, String>> getJasonDataToMap() throws IOException
	{   // read json to string conert json to string
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//ktb//data//PurchaseOrder.json"), StandardCharsets.UTF_8);
		//StandardCharsets.UTF_8 return our string to encoding format on how to convert into string
		//convert string to HashMap we need to use dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		//This data is a list with two arguments. In 1st argument will hv one hashmap,2nd hv another hashmap
		return data;
		
	}

}
