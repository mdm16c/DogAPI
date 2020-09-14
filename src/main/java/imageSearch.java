import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class imageSearch {

    public static void saveImage(String imageUrl) throws IOException {

        //setup connection and file
        URL url = new URL(imageUrl);
        String fileName = url.getFile();
        String currentDir = System.getProperty("user.dir");
        File dir = new File(currentDir + (String) File.separator + "images");
        if (!dir.exists()) {
            dir.mkdir();
        }
        String destName = dir.getAbsolutePath() + (String) File.separator + fileName.substring(fileName.lastIndexOf("/"));
        System.out.println("Image File Path: " + destName);

        //open streams
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destName);

        //setup byte array for content
        byte[] b = new byte[2048];
        int length;

        //write to file from connection
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        //close streams
        is.close();
        os.close();
    }

    public static String getURL(String breed) throws IOException {

        //setup connection
        URL url;
        if (breed.equals("")) {
            url = new URL("https://dog.ceo/api/breeds/image/random");
        }
        else {
            url = new URL("https://dog.ceo/api/breed/" + breed + "/images/random");
        }
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        //check that API call did not fail
        if (con.getResponseCode() != 200 && (!breed.equals(""))) {
            con.disconnect();
            throw new RuntimeException("API call failed! " + breed + " is not an available parameter.");
        }
        else if (con.getResponseCode() != 200) {
            con.disconnect();
            throw new RuntimeException("API call failed!");
        }

        //retrieve info
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        //close connections
        in.close();
        con.disconnect();

        //save JSON to string
        String result = content.toString();

        //sanitize
        result = result.substring(12);
        result = result.replace("\\","");
        result = result.substring(0 , result.indexOf(",") - 1);

        //return resulting URL
        return result;
    }

    public static void getBreedList() throws IOException {

        //setup connection
        URL url = new URL("https://dog.ceo/api/breeds/list/all");
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        //check that API call did not fail
        if (con.getResponseCode() != 200) {
            con.disconnect();
            throw new RuntimeException("API call failed!");
        }

        //retrieve info
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        //close connections
        in.close();
        con.disconnect();

        //save JSON to string
        String result = content.toString();

        //make it pretty
        JSONObject json = new JSONObject(result);
        System.out.println(json.toString(4));
    }

    public static void main(String[] args) throws IOException {

        //setup variables
        int limit = 1;
        String breed = "";

        //get user input
        if (args.length > 0) { 
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-breed") && i < args.length) {
                    breed = args[i+1];
                    breed.trim();
                }
                else if (args[i].equals("-limit") && i < args.length) {
                    try {
                        limit = Integer.parseInt(args[i+1]);
                        if (limit < 1) {
                            limit = 1;
                            System.out.println("The value entered for limit was less than 1. Limit has been set to 1.");
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("The value entered for limit was a non numeric type. Limit has been set to 1.");
                        limit = 1;
                    }
                }
            } 
        }

        //make function calls
        for (int i = 0; i < limit; i++) {
            saveImage(getURL(breed));
        }

        System.out.println("Image(s) Saved");
    } 
}