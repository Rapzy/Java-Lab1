package model;
import hotel.Hotel;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Serializer {
    public static void serializeObj(Hotel hotelObj) {
        String filePath = "Hotels.xml";
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc;
            Element rootElement;

            File file = new File(filePath);
            if(file.length() > 0) {
                doc = docBuilder.parse("Hotels.xml");
                //doc.getDocumentElement().normalize();
                rootElement = doc.getDocumentElement();
            }
            else
            {
                doc = docBuilder.newDocument();
                rootElement = doc.createElement("Hotels");
                doc.appendChild(rootElement);
            }
            boolean edited = false;
            NodeList hotels = doc.getElementsByTagName("Hotel");
            if(hotels.getLength()>0) {
                for (int i = 0; i < hotels.getLength(); i++) {
                    Element el = (Element) hotels.item(i);
                    if (el.getAttribute("id").equals(hotelObj.getId().toString())) {
                        NodeList childNodes = el.getChildNodes();
                        childNodes.item(0).setTextContent(hotelObj.getName());
                        childNodes.item(1).setTextContent(hotelObj.getAddress());
                        childNodes.item(2).setTextContent(hotelObj.getStars().toString());
                        childNodes.item(3).setTextContent(hotelObj.getFreeRooms().toString());
                        childNodes.item(4).setTextContent(hotelObj.getRooms().toString());
                        edited = true;
                        break;
                    }
                }
            }
            if(!edited){
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
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Hotels.xml"));
            transformer.transform(source, result);
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}