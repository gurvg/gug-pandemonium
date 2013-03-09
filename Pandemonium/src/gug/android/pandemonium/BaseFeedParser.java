package gug.android.pandemonium;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseFeedParser implements FeedParser {

	// names of the XML tags
	static final String CHANNEL = "channel";
	static final String PUB_DATE = "pubDate";
	static final  String DESCRIPTION = "description";
	static final  String LINK = "id";
	static final  String TITLE = "title";
	static final  String ITEM = "item";
	static final  String FEED = "feed";
	static final  String ENTRY = "entry";
	static final  String CATEGORY = "category";
	static final  String CONTENT = "content";
	static final  String PUBLISHED = "published";
	static final  String AUTHOR = "author";
	static final  String AUTHORNAME = "name";
	
	
	
	
	private final URL feedUrl;

	protected BaseFeedParser(String feedUrl){
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	protected InputStream getInputStream() {
		try {
			return feedUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}