/* CS3331 Adv. Object Oriented Programming
 * Instructor: Dr. Roach
   Program 2
 * Last modified on [9/13/19]
 * Modified and submitted by: [Justin Ruiloba]
*/
//intial version taken from xml Parser from piazza 9/12/19
//able to ignore path complications created menu method9/12/19
//added comments implemented all methods fully working program 9/13/19
//
//
/*Program Takes in a file input.txt given in well formed and valid XML 
 * prints the current element and then prints a menu in which he has 6 options
 * SHOW:Prints all  the current element's contents of the text file 
 * CHANGE: Changes the current element of the XML given the tag 
 * and what you would like to change content to
 * WRITE:which takes your changes and writes to a new file newTest.xml
 * NEXT: Iterates to next element and prints out all of element's contents
 * PREVIOUS:Iterates back and element and prints out all of elements contents   
 * EXIT:exits program completly
 * 
 * 
 * 
 * 
 * */

package CS3331;
//import all neessary libaries 
import java.io.File;
import java.util.Scanner; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class XMLParser {
	//prints menu for user to chose from
	public static void menu(){
		System.out.println("SHOW");
		System.out.println("CHANGE");
		System.out.println("WRITE");
		System.out.println("NEXT");
		System.out.println("PREVIOUS");
		System.out.println("EXIT");
	}
	//SHOW:Prints all  the current element's contents of the text file 
	public static void show(NodeList nList,int temp){
		//get current element
		Node nNode = nList.item(temp);
		//print elements name
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		//if contents is inside element
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			//print elements
			System.out.println("Student roll no : " + eElement.getAttribute("rollno"));
			System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
		}
		System.out.println();
		System.out.println();
			
		
		
	}
	//CHANGE: Changes the current element of the XML given the tag 
	//* and what you would like to change content to
	public static void change(Node nNode,NodeList a,String tag, String newtxt){
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			eElement.getElementsByTagName(tag).item(0).setTextContent(newtxt);
		}
		System.out.println();
		System.out.println();
	}
	//WRITE:which takes your changes and writes to a new file newTest.xml
	public static void write(Document doc){
		try{
			//saves to new file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        //outputs new file
	        StreamResult result = new StreamResult(new File(".\\newTest.xml"));
	        transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	         System.out.println();
	 		 System.out.println();
		}
		//catche exception
		catch(Exception e) {
			e.printStackTrace();
		}
		}
	//NEXT: Iterates to next element and prints out all of element's contents
	public static void next(NodeList nList,int temp){
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			System.out.println("Student roll no : " 
					+ eElement.getAttribute("rollno"));
			System.out.println("First Name : " 
					+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
		}
		System.out.println();
		System.out.println();
	}
	public static void previous(NodeList nList,int temp){
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			System.out.println("Student roll no : " 
					+ eElement.getAttribute("rollno"));
			System.out.println("First Name : " 
					+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
		}
		System.out.println();
		System.out.println();
		
	}
	public static void main(String[] args) {
		int temp =0;
		try {
			 Scanner scnr = new Scanner(System.in);
			 String tag= " ";
			 String waste=  " ";
		     String newtxt= " ";   
		    //infinite loop only ends when user hits exit
			while(true){
				
					File inputFile = new File("input.txt");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					NodeList nList = doc.getElementsByTagName("student");
					//System.out.println("----------------------------");
					Node nNode = nList.item(0);


					
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						System.out.println("Student roll no : " 
								+ eElement.getAttribute("rollno"));
						System.out.println("First Name : " 
								+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
					}
					menu();
				 //creates scanner
			   
			    String userinput = scnr.next();
			    //change option needs more input otherwise ignore
			    if(userinput.equalsIgnoreCase("change") ||userinput.equalsIgnoreCase("c")){
			    	 //tag text is in to change
			    	 tag = scnr.next();
			    	 //takes in empty space
			    	 waste = scnr.nextLine();
			    	 //txt to replace with 
			    	 newtxt = scnr.nextLine();
			    }
			    //trims spaces
			    userinput =userinput.trim();
			    //testing see if scanner worked
			    //System.out.println(userinput);
			    
			    //if user chooses show
			    if(userinput.equalsIgnoreCase("show")||userinput.equalsIgnoreCase("s") ){
			    	show(nList,temp);
			    }
			    //user chooses change
			    if(userinput.equalsIgnoreCase("change") ||userinput.equalsIgnoreCase("c")){
			    	change(nNode,nList,tag,newtxt);
			    }
			    //user chooses write
			    if(userinput.equalsIgnoreCase("write")||userinput.equalsIgnoreCase("w")){
			    	write(doc);
			    }
			    //user chooses next
			    if(userinput.equalsIgnoreCase("next")||userinput.equalsIgnoreCase("n")){
			    	//if list is not null
			    	if((temp+1)<nList.getLength()){
			    		temp++;	
			    		next(nList,temp);
			    	}
			    	else{
			    		System.out.println("No node exists at end of list");
			    	}
				}
			//if user chooses previous
			if(userinput.equalsIgnoreCase("previous")||userinput.equalsIgnoreCase("p")){
				//if list is not null
				if((temp-1)>=0){
					temp--;	
					previous(nList,temp);
				}
					else{
					System.out.println("No node exists at beggining  of list");
					}
			}
			//user chooses exit 
			if( userinput.equalsIgnoreCase("exit")||userinput.equalsIgnoreCase("e")){
				//print message
				System.out.println("Shutting Down");
				//shutdown program
				System.exit(0);
			}
			

			
		}
		  //catch execeptions
		} catch (Exception e) {
			e.printStackTrace();
		}
	}











}
