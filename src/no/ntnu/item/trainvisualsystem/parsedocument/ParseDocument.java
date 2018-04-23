package no.ntnu.item.trainvisualsystem.parsedocument;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import no.ntnu.item.arctis.runtime.Block;

public class ParseDocument extends Block {
	public void parse(final String xml) {
		runAsync(new Runnable() {
			public void run() {
				try {
					SAXReader reader = new SAXReader();
					Document document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
					logDebug("Decoding OK");
					sendToBlock("OK", document);
				} catch (UnsupportedEncodingException e) {
					logDebug("Unsupported encoding" + e);
					sendToBlock("FAILED", "Unsupported encoding " + e.toString());
				} catch (DocumentException e) {
					logDebug("Error parsing XML string " + e);
					sendToBlock("FAILED", e.toString());
				}
			}
		});
	}
}
