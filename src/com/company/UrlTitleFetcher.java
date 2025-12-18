package com.company;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class to fetch the title of a webpage from its URL.
 */
public class UrlTitleFetcher {

    // The regex pattern to find the <title> tag in an HTML document.
    // This pattern captures the text between the opening and closing title tags.
    // It is case-insensitive and handles multi-line titles.
    private static final Pattern TITLE_TAG_PATTERN =
            Pattern.compile("<title>(.*?)</title>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    public ArrayList<String> convertTextToUrlList(ArrayList<String> list){
        ArrayList<String> result = new ArrayList<>();
        for(String s : list){
            String[] charArr = s.toLowerCase().split(" ");
            StringBuilder sb = new StringBuilder();
            sb.append("https://leetcode.com/problems/");
            sb.append(charArr[0]);
            for(int i = 1; i < charArr.length ; i++){
                sb.append("-");
                sb.append(charArr[i]);
            }
            sb.append("/description/");
            result.add(getPageTitle(sb.toString()));

        }
        return result;
    }

    /**
     * Fetches and returns the title of the webpage at the specified URL.
     *
     * @param urlString The string representation of the URL.
     * @return The title of the webpage, or an empty string if the title cannot be found or an error occurs.
     */
    public String getPageTitle(String urlString) {
        try {
            // Create a URL object from the string.
            URL url = new URL(urlString);

            // Open a connection to the URL and get an InputStream.
            // Using a try-with-resources statement to ensure the reader is closed automatically.
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                StringBuilder content = new StringBuilder();
                String line;

                // Read the HTML content line by line.
                // We'll read up to a reasonable limit to avoid downloading huge files.
                int linesRead = 0;
                while ((line = reader.readLine()) != null && linesRead < 500) {
                    content.append(line);
                    // Check if the title tag is found to stop reading early.
                    if (content.toString().toLowerCase().contains("</title>")) {
                        break;
                    }
                    linesRead++;
                }

                // Use the regex pattern to find a match in the fetched content.
                Matcher matcher = TITLE_TAG_PATTERN.matcher(content.toString());

                // If a match is found, return the captured group (the title text).
                if (matcher.find()) {
                    // Group 1 contains the text captured by (.*?) in the pattern.
                    return matcher.group(1).trim();
                }
            }
        } catch (IOException e) {
            // Handle exceptions like MalformedURLException or if the host is unreachable.
            System.err.println("Error fetching title for URL: " + urlString);
            e.printStackTrace();
        }

        // Return a default message if no title was found.
        return "Title not found";
    }


}