package gug.android.pandemonium;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Message implements Comparable<Message>{
	static SimpleDateFormat GETFORMATTER = 
		new SimpleDateFormat("EEEE, d MMM yyyy HH:mm", Locale.FRANCE);
	static SimpleDateFormat SETFORMATTER_YOUTUBE = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
	
	static SimpleDateFormat SETFORMATTER_FOFO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
	//2013-02-05T17:47:44.000Z
	//2013-02-28T17:09:55+00:00
	//"dd-MM-yyyy HH:mm:ss, SSS [z]"
	
	private String title;
	private String author;
	private URL link;
	private String description;
	private Date date;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}
	
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author.trim();
	}
	
	// getters and setters omitted for brevity 
	public URL getLink() {
		return link;
	}
	
	public void setLink(String link) {
		try {
		this.link = new URL(link);
		  //this.link = new URL("http://pandemonium-varimathras.fr");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getDate() {
		return GETFORMATTER.format(this.date);
	}

	public void setDate(String date) {
		// pad the date if necessary
		//while (!date.endsWith("00")){
		//	date += "0";}
		
		try {
			//date = date.replaceAll(".000Z", "");
			//date = date.replaceAll("+00:00", "");
			
			if (date.contains(".000Z"))			
			this.date = SETFORMATTER_YOUTUBE.parse(date.trim());
			else
		    this.date = SETFORMATTER_FOFO.parse(date.trim());
			
		} catch (Exception e) {
			//throw new RuntimeException(e);
			// si erreur on met la date du jour
		     Calendar c = Calendar.getInstance();
			this.date = c.getTime();
			
		}
	}
	
	public Message copy(){
		Message copy = new Message();
		copy.title = title;
		copy.link = link;
		copy.author = author;
		copy.description = description;
		copy.date = date;
		return copy;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title: ");
		sb.append(title);
		sb.append('\n');
		sb.append("Author: ");
		sb.append(author);
		sb.append('\n');
		sb.append("Date: ");
		sb.append(this.getDate());
		sb.append('\n');
		sb.append("Link: ");
		sb.append(link);
		sb.append('\n');
		sb.append("Description: ");
		sb.append(description);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public int compareTo(Message another) {
		if (another == null) return 1;
		// sort descending, most recent first
		return another.date.compareTo(date);
	}
}

