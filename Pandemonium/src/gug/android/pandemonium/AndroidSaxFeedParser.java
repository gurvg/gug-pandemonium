package gug.android.pandemonium;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class AndroidSaxFeedParser extends BaseFeedParser {

	static final String RSS = "rss";
	static final String ATOM_NAMESPACE = "http://www.w3.org/2005/Atom";
	public AndroidSaxFeedParser(String feedUrl) {
		super(feedUrl);
	}

	public List<Message> parse() {
		final Message currentMessage = new Message();
		RootElement root = new RootElement(ATOM_NAMESPACE,FEED);
		final List<Message> messages = new ArrayList<Message>();
		//Element channel = root.getChild(CHANNEL);
		Element entry = root.getChild(ATOM_NAMESPACE,ENTRY);
		entry.setEndElementListener(new EndElementListener(){
			public void end() {
				messages.add(currentMessage.copy());
			}
		});
		entry.getChild(ATOM_NAMESPACE,TITLE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setTitle(body);
			}
		});
		entry.getChild(ATOM_NAMESPACE,LINK).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setLink(body);
			}
		});
		entry.getChild(ATOM_NAMESPACE,CONTENT).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setDescription(body);
			}
		});
		entry.getChild(ATOM_NAMESPACE,PUBLISHED).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setDate(body);
			}
		});
		entry.getChild(ATOM_NAMESPACE,AUTHOR).getChild(ATOM_NAMESPACE,AUTHORNAME).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setAuthor(body);
			}
		});
		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return messages;
	}
	
}









