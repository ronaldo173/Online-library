package test;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;

import java.util.*;
import java.io.*;

public class Dom03{

    PrintWriter out;//output stream
    //Save processing instruction nodes here
    static Vector procInstr = new Vector();

    public static void main(String argv[]){
        if (argv.length != 3){
            System.err.println(
                    "usage: java Dom03 "
                            + "xmlFileIn "
                            + "xformFileOut "
                            + "codeFileOut");
            System.exit(0);
        }//end if

        try{
            //Get a factory object for DocumentBuilder
            // objects
            ///
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            //Configure the factory object.  Change
            // the following parameter to false for a
            // non-validating parser.
            ///
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            //The following statement causes the parser
            // to ignore cosmetic whitespace between
            // elements.
            ///
            factory.
                    setIgnoringElementContentWhitespace(true);

            //Get a DocumentBuilder (parser) object
            ///
            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            //Parse the XML input file to create a
            // Document object that represents the
            // input XML file.
            ///
            Document document = builder.parse(
                    new File(argv[0]));

            //Instantiate an object of this class
            ///
            Dom03 thisObj = new Dom03();

            //TRANSFORMATION THROUGH PROGRAM CODE
            //Use program code to transform the
            // DOM tree into an output file.
            //
            //Get an output stream for the output
            // produced by the program code.  This
            // stream object is used by several
            // methods, so it was instantiated at this
            // point and saved as an instance variable
            // of the object.
            ///
            thisObj.out = new PrintWriter(
                    new FileOutputStream(argv[2]));

            //Process the DOM tree, beginning with the
            // Document node to produce the output.
            // Invocation of processDocumentNode starts
            // a recursive process that processes the
            // entire DOM tree.
            ///
            thisObj.processDocumentNode(document);


            //XSLT TRANSFORMATION
            //Use XSLT to transform the DOM tree into
            // an output file.  Note that the success
            // of this method call depends on the
            // stylesheet processing instruction having
            // been saved while the transformation was
            // being performed using program code
            // above.  Otherwise, it would be necessary
            // to include the code in this method to
            // search the DOM tree for the stylesheet
            // processing instruction. All processing
            // instructions are saved in a Vector
            // object, which is passed as the third
            // parameter to this method.
            ///
            thisObj.doXslTransform(
                    document,argv[1],procInstr);

        }catch(Exception e){
            //Note that no effort was made to provide
            // meaningful results in the event of an
            // exception or error.
            ///
            e.printStackTrace(System.err);
        }//end catch
    }// end main()
    //-------------------------------------------//

    //This method is used to produce any text
    // required in the output at the document
    // level, such as the XML declaration for an
    // XML document.
    void processDocumentNode(Node node){
        //Create the beginning of the XHTML document
        out.println("<?xml version=\"1.0\" "
                + "encoding=\"UTF-8\"?>");
        out.println(
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD "
                        + "XHTML 1.0 Transitional//EN\" "
                        + "\"http://www.w3.org/TR/xhtml1/"
                        + "DTD/xhtml1-transitional.dtd\">");
        out.println("<html xmlns=\"http://www.w3."
                + "org/1999/xhtml\" xml:lang=\"en\""
                + " lang=\"en\">");
        out.println("<head>");
        out.println(
                "<meta http-equiv=\"content-type\" "
                        + "content=\"text/html; charset="
                        + "UTF-8\"/>");
        out.println("<title>Generated XHTML file"
                + "</title>");
        out.println("</head>");
        out.println("<body>");
        //Output similar to the above applies to
        // most XHTML documents.

        //Now set up an XHTML table.  This is
        // peculiar to this particular example.
        out.println("<table border=\"2\" " +
                "cellspacing=\"0\" " +
                "cellpadding=\"0\" " +
                "width=\"330\" " +
                "bgcolor=\"#FFFF00\">" +
                "<tr><td>");

        //Go process the root (document) node. This
        // method call triggers a recursive process
        // that processes the entire DOM tree.
        processNode(node);

        //Finish the XHTML table.  This output is
        // peculiar to this particular example.
        out.println("</td></tr></table>");

        //Now finish the output document and flush
        // the output buffer.  This would apply to
        // most XHTML documents.
        out.println("</body></html>");
        out.flush();
    }//end processDocumentNode
    //-------------------------------------------//

    //There are seven kinds of nodes:
    // root or document
    // element
    // attribute
    // text
    // comment
    // processing instruction
    // namespace
    //
    //This method handles the first six.
    // Apparently it is not possible to handle
    // namespace nodes in Java because there is
    // no constant in the Node class to identify
    // namespace nodes
    ///
    void processNode(Node node){

        try{
            if (node == null){
                System.err.println(
                        "Nothing to do, node is null");
                return;
            }//end if

            //Process the incoming node based on its
            // type.
            ///
            int type = node.getNodeType();

            //To define an overriding template rule,
            // insert the matching condition in the
            // conditional clause of the if statement,
            // and provide code to implement the rule
            // in the body of the if statement.  If the
            // conditional clause evaluates to true,
            // the default rule for that element type
            // will not be processed.
            ///
            switch (type){
                case Node.TEXT_NODE:{
                    if(true){
                        out.println(node.getNodeValue());
                    }else{//invoke default behavior
                        //This won't be reached in this
                        // example, but I will leave it
                        // here as a reminder of the
                        // default behavior.
                        out.print(defTextOrAttrTemp(node));
                    }//end else
                    break;
                }//end case Node.TEXT_NODE

                case Node.ATTRIBUTE_NODE:{
                    if(false){
                        //Change conditional and write
                        // overriding handler here
                        ///
                    }else{//invoke default behavior
                        out.print(defTextOrAttrTemp(node));
                    }//end else
                    break;
                }//end case Node.ATTRIBUTE_NODE

                case Node.ELEMENT_NODE:{
                    if(node.getNodeName() == "B"){
                        //Process all XML child nodes
                        // recursively
                        applyTemplates(node,null);
                    }//end if

                    else if(node.getNodeName() == "C"){
                        //Begin XHTML paragraph element
                        out.println("<p>");
                        //Process all XML child nodes
                        // recursively
                        applyTemplates(node,null);
                        //End XHTML paragraph element
                        out.println("</p>");
                    }//end if

                    else if(node.getNodeName() == "D"){
                        //First process the child nodes
                        // named E.
                        out.println("List of items in E");
                        //Begin XHTML unordered list
                        out.println("<ul>");
                        //Iteratively put text from E
                        // elements and their children into
                        // the list.
                        forEach(node,"E");
                        //End XHTML unordered list
                        out.println("</ul>");

                        //Now process the child nodes
                        // named F.
                        out.println("List of items in F");
                        //Begin XHTML ordered list
                        out.println("<ol>");
                        //Iteratively put text from F
                        // elements and their children in the
                        // list.
                        forEach(node,"F");
                        //End XHTML ordered list
                        out.println("</ol>");
                    }//end if

                    else if(node.getNodeName() == "G"){
                        //Display children as XHTML bold
                        out.println("<b>");
                        applyTemplates(node,null);
                        out.println("</b>");
                    }//end if

                    //Create four levels of XHTML headers
                    else if(node.getNodeName() == "Q"){
                        out.println("<h1>");
                        applyTemplates(node,null);
                        out.println("</h1>");
                    }//end if

                    else if(node.getNodeName() == "R"){
                        out.println("<h2>");
                        applyTemplates(node,null);
                        out.println("</h2>");
                    }//end if

                    else if(node.getNodeName() == "S"){
                        out.println("<h3>");
                        applyTemplates(node,null);
                        out.println("</h3>");
                    }//end if

                    else if(node.getNodeName() == "T"){
                        out.println("<h4>");
                        applyTemplates(node,null);
                        out.println("</h4>");
                    }//end if

                    //The following rules for E and F
                    // are invoked as a result of the
                    // behavior of the forEach method. The
                    // code could have been placed inside
                    // the forEach method.  However, I
                    // elected to put it here in an attempt
                    // to confine all of the custom code
                    // to the methods named processNode and
                    // processDocumentNode.
                    else if(node.getNodeName() == "E"){
                        //Create an XHTML list item
                        // containing information from child
                        // nodes.
                        out.println("<li>");
                        applyTemplates(node,null);
                        out.println("</li>");
                    }//end if

                    else if(node.getNodeName() == "F"){
                        //Create an XHTML list item
                        // containing information from child
                        // nodes.
                        out.println("<li>");
                        applyTemplates(node,null);
                        out.println("</li>");
                    }//end if

                    else{//invoke default behavior
                        defElOrRtNodeTemp(node);
                    }//end else
                    break;
                }//end case ELEMENT_NODE

                case Node.DOCUMENT_NODE:{
                    if(false){
                        //Change conditional and write
                        // overriding handler here
                        ///
                    }else{//invoke default behavior
                        defElOrRtNodeTemp(node);
                    }//end else
                    break;
                }//end case DOCUMENT_NODE

                case Node.COMMENT_NODE:{
                    if(false){
                        //Change conditional and write
                        // overriding handler here
                        ///
                    }else{//invoke default behavior
                        defComOrProcInstrTemp(node);
                    }//end else
                    break;
                }//end case COMMENT_NODE

                case Node.PROCESSING_INSTRUCTION_NODE:{
                    if(false){
                        //Change conditional and write
                        // overriding handler here
                    }else{//invoke default behavior
                        //First save proc instr for later
                        // use.
                        procInstr.add(node);
                        //Now invoke default behavior.
                        defComOrProcInstrTemp(node);
                    }//end else
                    break;
                }//end case PROCESSING_INSTRUCTION_NODE

                default:{
                    //Ignore all other node types.
                }//end default

            }//end switch

        }catch(Exception e){
            e.printStackTrace(System.err);
        }//end catch
    }//end processNode(Node)
    //-------------------------------------------//

    //This method emulates the following default
    // template rule:
    //  <xsl:template match="text()|@*">
    //   <xsl:value-of select="."/>
    //  </xsl:template>
    ///
    String defTextOrAttrTemp(Node node)
            throws Exception{
        int nodeType = node.getNodeType();
        if((nodeType == Node.ATTRIBUTE_NODE)
                || (nodeType == Node.TEXT_NODE)){
            //Get and return the value of the context
            // node.
            ///
            return valueOf(node,".");
        }else{
            throw new Exception(
                    "Bad call to defaultTextOrAttr method");
        }//end else
    }//end defaultTextOrAttr
    //-------------------------------------------//

    //This method emulates the following default
    // template rule:
    //  <xsl:template match="*|/">
    //   <xsl:apply-templates/>
    //  </xsl:template>
    ///
    void defElOrRtNodeTemp(Node node)
            throws Exception{
        int nodeType = node.getNodeType();
        if((nodeType == Node.ELEMENT_NODE) ||
                (nodeType == Node.DOCUMENT_NODE)){
            //Note that the following is a recursive
            // method call.
            ///
            applyTemplates(node,null);
        }else{
            throw new Exception(
                    "Bad call to defElOrRtNodeTemp");
        }//end else
    }//end defElOrRtNodeTemp
    //-------------------------------------------//

    //This method emulates the following default
    // template rule:
    // <xsl:template
    //   match="processing-instruction()|comment()"
    ///
    String defComOrProcInstrTemp(Node node)
            throws Exception{
        int nodeType = node.getNodeType();
        if((nodeType == Node.COMMENT_NODE) ||
                (nodeType ==
                        Node.PROCESSING_INSTRUCTION_NODE)){
            //According to page Nutshell pg 148, the
            // default rule for comments and processing
            // instructions doesn't output anything
            // into the result tree.
            ///
            return "";//empty string
        }else{
            throw new Exception("Bad call to " +
                    "defalutCommentOrProcInstrTemplate");
        }//end else
    }//end defComOrProcInstrTemp
    //-------------------------------------------//

    //See Nutshell, pg 148 for an explanation as to
    // why it is not possible to write a Java
    // method that emulates the default namespace
    // template.
    ///
    void defaultNamespaceTemplate(Node node)
            throws Exception{
        throw new Exception("See Nutshell pg 148" +
                "regarding default behavior for " +
                "namespace template.");
    }//end defaultNamespaceTemplate
    //-------------------------------------------//

    //Simulates an XSLT apply-templates rule.
    //  <xsl:apply-templates
    //    optional select = "..."
    //    optional mode = "..."
    //   >
    //Note that the mode attribute is not supported
    // in this version.
    //If the select parameter is null, all child
    // nodes are processed.
    void applyTemplates(Node node,String select){
        NodeList children = node.getChildNodes();
        if (children != null){
            int len = children.getLength();
            //Iterate on NodeList of child nodes.
            for (int i = 0; i < len; i++){
                if((select == null) ||
                        (select.equals(children.item(i).
                                getNodeName()))){
                    //Note that the following is a
                    // recursive method call.
                    ///
                    processNode(children.item(i));
                }//end if
            }//end for loop
        }//end if children != null

    }//end applyTemplates
    //-------------------------------------------//

    //This method simulates an XSLT
    //   <xsl:value-of select="???"/>
    // The general form of the method call is
    //   valueOf(Node theNode,String select)
    //
    //The method recognizes three forms of call:
    //  valueOf(Node theNode,String "@attrName")
    //  valueOf(Node theNode,String ".")
    //  valueOf(Node theNode,String "nodeName")
    //
    //In the first form, the method returns the
    // text value of the named attribute of
    // theNode.  An attribute is specified by a
    // select value that begins with @.  If the
    // attribte doesn't exist, the method returns
    // an empty string.
    //
    //In the second form, the method returns the
    // concatenated text values of descendants of
    // the context node.
    //
    //In the third form, the method returns the
    // concatenated text values of all descendants
    // of a specified child node of the context
    // node.  If the context node has more than one
    // child node with the specified name, only the
    // first one found is processed.  The others
    // are ignored.
    //
    //The method does not support the following,
    // which are standard features of xsl:value-of:
    //   disable-output-escaping
    //   processing instruction nodes
    //   comment nodes
    //   namespace nodes
    ///

    public String valueOf(Node node,String select){

        if(select != null
                && select.charAt(0) == '@'){
            //This is a request for the value of an
            // attribute. Returns empty string if the
            // attribute doesn't exist on the element.
            String attrName = select.substring(1);
            NamedNodeMap attrList =
                    node.getAttributes();
            Node attrNode = attrList.getNamedItem(
                    attrName);
            if(attrNode != null){
                return attrNode.getNodeValue();
            }else{
                return "";//empty string
            }//end else
        }//end if on @

        else if(select != null
                && select.equals(".")){
            //This is a request to process the context
            // node
            int nodeType = node.getNodeType();
            if(nodeType == Node.ELEMENT_NODE){
                //Process the context node as an element
                // node.  Return the concatenated text
                // values of all descendants of the
                // context node.
                NodeList childNodes =
                        node.getChildNodes();
                int listLen = childNodes.getLength();
                String nodeTextValue = "";//result

                for(int j = 0; j < listLen; j++){
                    nodeTextValue +=
                            valueOf(childNodes.item(j),".");
                }//end for loop
                return nodeTextValue;
            }else if(nodeType == Node.TEXT_NODE){
                //Process the context node as a text
                // node.  Simply get and return its
                // value.
                return node.getNodeValue();
            }else{
                //ignore all other context node types
            }//end else
        }//end if for context node

        else if(select != null){
            //Process a child node whose name is
            // specified by the value of the incoming
            // parameter named select.  Get and return
            // the concatenated text values of all
            // descendants of the specified child node.
            //This process assumes that there is only
            // one child node with the specified name
            // and processes the first one that it
            // finds.
            NodeList children = node.getChildNodes();
            int len = children.getLength();
            for (int i = 0; i < len; i++){
                //Trap the specified child node
                if(children.item(i).getNodeName().
                        equals(select)){
                    //Make a recursive call and let
                    // existing code do the work.
                    return valueOf(children.item(i),".");
                    //The above return statement causes any
                    // additional child nodes having the
                    // same name to be ignored.
                }//end if getNodeName == select
            }//end for loop on all child nodes
        }//end else if(select != null)
        //Will reach here only if value of select
        // is null.
        ///
        return "";//empty string
    }//end method valueOf
    //-------------------------------------------//

    //This method simulates an XSLT for-each
    // template rule
    private void forEach(Node node,String select){
        NodeList children = node.getChildNodes();
        if (children != null){
            int len = children.getLength();
            //Iterate on NodeList of child nodes,
            // processing nodes that match the select.

            for (int i = 0; i < len; i++){
                if(children.item(i).getNodeName().
                        equals(select)){
                    //Make a recursive call from within
                    // this iterative template rule.
                    processNode(children.item(i));
                }//end if
            }//end for loop
        }//end if
    }//end forEach
    //-------------------------------------------//

    //This method uses an incoming XSLT stylesheet
    // file to transform an incoming Document
    // object into an output file.  Note that the
    // successful invocation of this method depends
    // on the processing instruction containing the
    // stylesheet having been saved in a Vector
    // object that is received as an incoming
    // parameter.  Otherwise, this method would
    // have to search the DOM for the stylesheet
    // processing instruction.
    ///
    void doXslTransform(Document document,
                        String outFile,
                        Vector procInstr)
            throws Exception{
        try{
            //Get stylesheet ID from proc instr.
            ProcessingInstruction pi = null;
            boolean piFlag = false;
            int size = procInstr.size();
            //Search for a stylesheet in the Vector
            // containing processing instruction nodes.
            ///
            for(int i = 0; i < size; i++){
                pi = (ProcessingInstruction)procInstr.
                        get(i);
                if(pi.getTarget().startsWith(
                        "xml-stylesheet") && pi.getData().
                        startsWith("type=\"text/xsl\"")){
                    //Looks like a good stylesheet.
                    ///
                    piFlag = true;
                    break;
                }//end if
            }//end for loop
            if(piFlag == false){//still false?
                throw new Exception(
                        "No valid stylesheet");
            }//end if
            //Get the stylesheet file reference
            ///
            String xslFile = pi.getData().
                    substring(pi.getData().indexOf(
                            "href=")+6);
            //Eliminate the quotation mark at the end
            ///
            xslFile = xslFile.substring(
                    0,xslFile.length()-1);

            //Get a TransformerFactory object
            ///
            TransformerFactory xformFactory =
                    TransformerFactory.newInstance();
            //Get an XSL Transformer object based on
            // the XSL file discovered above.
            ///
            Transformer transformer =
                    xformFactory.newTransformer(
                            new StreamSource(
                                    new File(xslFile)));
            //Get a DOMSource object that represents
            // the DOM tree.
            ///
            DOMSource source = new DOMSource(document);

            //Get an output stream for the output
            // file.
            ///
            PrintWriter xformStream = new PrintWriter(
                    new FileOutputStream(outFile));

            //Get a StreamResult object that points to
            // the output file.  Then transform the DOM
            // sending text to the output file.
            ///
            StreamResult xformResult =
                    new StreamResult(xformStream);

            //Do the transform
            ///
            transformer.transform(source,xformResult);
        }catch(Exception e){
            e.printStackTrace(System.err);
        }//end catch

    }//end doXslTransform

}// class Dom03