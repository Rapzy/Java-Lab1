package model;
import hotel.Hotel;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Serializer {
    public static void serializeObj(Hotel hotelObj) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Hotels");
            doc.appendChild(rootElement);

            Element hotel = doc.createElement("Hotel");
            rootElement.appendChild(hotel);
            hotel.setAttribute("id", hotelObj.getId().toString());

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(hotelObj.getName()));
            hotel.appendChild(name);

            Element address = doc.createElement("Address");
            address.appendChild(doc.createTextNode(hotelObj.getAddress()));
            hotel.appendChild(address);

            Element stars = doc.createElement("Stars");
            stars.appendChild(doc.createTextNode(hotelObj.getStars().toString()));
            hotel.appendChild(stars);

            Element freeRooms = doc.createElement("FreeRooms");
            freeRooms.appendChild(doc.createTextNode(Integer.toString(hotelObj.getFreeRooms().size())));
            hotel.appendChild(freeRooms);

            Element rooms = doc.createElement("Rooms");
            rooms.appendChild(doc.createTextNode(Integer.toString(hotelObj.getRooms().size())));
            hotel.appendChild(rooms);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Hotels.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
