import java.io.IOException;
import org.xml.sax.SAXException;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import java.util.ArrayList;
import java.awt.geom.Point2D;

public class XMLParser { 

	private XPath xpath;
	private Document doc;

	public ArrayList<Level> parseLevels() throws XPathExpressionException { 

		ArrayList<Level> levels = new ArrayList<Level>();
		
		int levelCount = Integer.parseInt(xpath.evaluate("count(//level)", doc));
		
		for (int i = 1; i <= levelCount; i++) {
			// Projectile Position
			Point2D.Double projPoint = getPoint("//level[@id='" + i + "']//projectile/position");

			// Projectile Dimensions
			int[] projDim = getDimension("//level[@id='" + i + "']//projectile/dimension");

			//Projectile init
			Projectile projectile = new Projectile(projPoint, projDim[0], projDim[1]);

			ArrayList<Obstruction> obstructions = new ArrayList<Obstruction>();
			
			int obstructionCount = Integer.parseInt(xpath.evaluate("count(//level[@id='" + i + "']//obstruction)", doc));
			System.out.println(obstructionCount);
			
			for (int j = 1; j <= obstructionCount; j++) {
				// Obstruction Position
				Point2D.Double obsPoint = getPoint("//level[@id='" + i + "']//obstruction[@id='" + j + "']/position");

				// Obstruction Dimensions
				int[] obsDim = getDimension("//level[@id='" + i + "']//obstruction[@id='" + j + "']/dimension");

				// Obstruction Image
				String obsImage = getImage("//level[@id='" + i + "']//obstruction[@id='" + j + "']/image");

				// Obstruction init
				Obstruction o = new Obstruction(obsPoint, obsDim[0], obsDim[1], obsImage);

				obstructions.add(o);
			}
			
			ArrayList<Target> targets = new ArrayList<Target>();
			
			int targetCount = Integer.parseInt(xpath.evaluate("count(//level[@id='" + i + "']//target)", doc));

			for (int j = 1; j <= targetCount; j++) {
				// Target Position
				Point2D.Double tarPoint = getPoint("//level[@id='" + i + "']//target[@id='" + j + "']/position");

				// Target Dimensions
				int[] tarDim = getDimension("//level[@id='" + i + "']//target[@id='" + j + "']/dimension");

				// Target init
				Target t = new Target(tarPoint, tarDim[0], tarDim[1]);

				targets.add(t);
			}
			
			Level level = new Level(projectile, obstructions, targets);
			levels.add(level);
		}
		return levels;

	}
	
	/*
	public Level parseMenu() throws XPathExpressionException {
		
		// Projectile Position
		Point2D.Double projPoint = getPoint("//menu/projectile/position");

		// Projectile Dimensions
		int[] projDim = getDimension("//menu/projectile/dimension");

		//Projectile init
		Projectile projectile = new Projectile(projPoint, projDim[0], projDim[1]);

		Level menu = new Level(projectile, null, null);
		
		return menu;
	}
	*/
	
	private Point2D.Double getPoint(String path) throws XPathExpressionException {
		String x = xpath.evaluate(path + "/xcoordinate", doc);
		String y = xpath.evaluate(path + "/ycoordinate", doc);
		return new Point2D.Double(Double.parseDouble(x), Double.parseDouble(y));
	}

	private int[] getDimension(String path) throws XPathExpressionException  {
		String w = xpath.evaluate(path + "/width", doc);
		String h = xpath.evaluate(path + "/height", doc);
		int[] dim = {Integer.parseInt(w), Integer.parseInt(h)};

		return dim;
	}

	private String getImage(String path) throws XPathExpressionException {
		String image = xpath.evaluate(path, doc);
		
		return image;
	}
	
	public XMLParser(String filename) {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			doc = builder.parse(filename);
		} catch (IOException e) {
			System.out.println("Message "+e.getMessage());
		} catch (SAXException e) {
			System.out.println("Message "+e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println("Message "+e.getMessage());
		}

		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
	}
}
