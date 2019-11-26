package com.sap.intellgentcam.demo1.tool;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author : Jenson.Liu
 * @date : 2019/11/22  5:35 下午
 */
public class XmlToolTest {

    @Test
    public void element() {
        System.out.println("hello");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            String strXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<entry xml:base=\"https://ccf-715.wdf.sap.corp:443/sap/opu/odata/sap/API_PURCHASEORDER_PROCESS_SRV/\" xmlns=\"http://www.w3.org/2005/Atom\" xmlns:m=\"http://schemas.microsoft.com/ado/2007/08/dataservices/metadata\" xmlns:d=\"http://schemas.microsoft.com/ado/2007/08/dataservices\">\n" +
                    "    <id>https://ccf-715.wdf.sap.corp:443/sap/opu/odata/sap/API_PURCHASEORDER_PROCESS_SRV/A_PurchaseOrder('4500003595')</id>\n" +
                    "    <title type=\"text\">A_PurchaseOrder('4500003595')</title>\n" +
                    "    <updated>2019-11-22T09:06:23Z</updated>\n" +
                    "    <category term=\"API_PURCHASEORDER_PROCESS_SRV.A_PurchaseOrderType\" scheme=\"http://schemas.microsoft.com/ado/2007/08/dataservices/scheme\"/>\n" +
                    "    <link href=\"A_PurchaseOrder('4500003595')\" rel=\"edit\" title=\"A_PurchaseOrderType\"/>\n" +
                    "    <link href=\"A_PurchaseOrder('4500003595')/to_PurchaseOrderItem\" rel=\"http://schemas.microsoft.com/ado/2007/08/dataservices/related/to_PurchaseOrderItem\" type=\"application/atom+xml;type=feed\" title=\"to_PurchaseOrderItem\"/>\n" +
                    "    <link href=\"A_PurchaseOrder('4500003595')/to_PurchaseOrderNote\" rel=\"http://schemas.microsoft.com/ado/2007/08/dataservices/related/to_PurchaseOrderNote\" type=\"application/atom+xml;type=feed\" title=\"to_PurchaseOrderNote\"/>\n" +
                    "    <content type=\"application/xml\">\n" +
                    "        <m:properties>\n" +
                    "            <d:PurchaseOrder>4500003595</d:PurchaseOrder>\n" +
                    "            <d:CompanyCode>1010</d:CompanyCode>\n" +
                    "            <d:PurchaseOrderType>NB</d:PurchaseOrderType>\n" +
                    "            <d:PurchasingDocumentDeletionCode/>\n" +
                    "            <d:PurchasingProcessingStatus>05</d:PurchasingProcessingStatus>\n" +
                    "            <d:CreatedByUser>_SAPI501695</d:CreatedByUser>\n" +
                    "            <d:CreationDate>2019-11-21T00:00:00</d:CreationDate>\n" +
                    "            <d:LastChangeDateTime>2019-11-21T08:41:03.4339560Z</d:LastChangeDateTime>\n" +
                    "            <d:Supplier>10300001</d:Supplier>\n" +
                    "            <d:PurchaseOrderSubtype/>\n" +
                    "            <d:Language>EN</d:Language>\n" +
                    "            <d:PaymentTerms>0004</d:PaymentTerms>\n" +
                    "            <d:CashDiscount1Days>0</d:CashDiscount1Days>\n" +
                    "            <d:CashDiscount2Days>0</d:CashDiscount2Days>\n" +
                    "            <d:NetPaymentDays>0</d:NetPaymentDays>\n" +
                    "            <d:CashDiscount1Percent>0.000</d:CashDiscount1Percent>\n" +
                    "            <d:CashDiscount2Percent>0.000</d:CashDiscount2Percent>\n" +
                    "            <d:PurchasingOrganization>1010</d:PurchasingOrganization>\n" +
                    "            <d:PurchasingDocumentOrigin>9</d:PurchasingDocumentOrigin>\n" +
                    "            <d:PurchasingGroup>001</d:PurchasingGroup>\n" +
                    "            <d:PurchaseOrderDate>2019-11-21T00:00:00</d:PurchaseOrderDate>\n" +
                    "            <d:DocumentCurrency>EUR</d:DocumentCurrency>\n" +
                    "            <d:ExchangeRate>1.00000</d:ExchangeRate>\n" +
                    "            <d:ExchangeRateIsFixed>false</d:ExchangeRateIsFixed>\n" +
                    "            <d:ValidityStartDate m:null=\"true\"/>\n" +
                    "            <d:ValidityEndDate m:null=\"true\"/>\n" +
                    "            <d:SupplierQuotationExternalID/>\n" +
                    "            <d:SupplierRespSalesPersonName/>\n" +
                    "            <d:SupplierPhoneNumber/>\n" +
                    "            <d:SupplyingSupplier>10300001</d:SupplyingSupplier>\n" +
                    "            <d:SupplyingPlant/>\n" +
                    "            <d:IncotermsClassification/>\n" +
                    "            <d:CorrespncExternalReference/>\n" +
                    "            <d:CorrespncInternalReference/>\n" +
                    "            <d:InvoicingParty>10300001</d:InvoicingParty>\n" +
                    "            <d:ReleaseIsNotCompleted>false</d:ReleaseIsNotCompleted>\n" +
                    "            <d:PurchasingCompletenessStatus>false</d:PurchasingCompletenessStatus>\n" +
                    "            <d:IncotermsVersion/>\n" +
                    "            <d:IncotermsLocation1/>\n" +
                    "            <d:IncotermsLocation2/>\n" +
                    "            <d:ManualSupplierAddressID/>\n" +
                    "            <d:IsEndOfPurposeBlocked/>\n" +
                    "            <d:AddressCityName/>\n" +
                    "            <d:AddressFaxNumber/>\n" +
                    "            <d:AddressHouseNumber/>\n" +
                    "            <d:AddressName/>\n" +
                    "            <d:AddressPostalCode/>\n" +
                    "            <d:AddressStreetName/>\n" +
                    "            <d:AddressPhoneNumber/>\n" +
                    "            <d:AddressRegion/>\n" +
                    "            <d:AddressCountry/>\n" +
                    "            <d:AddressCorrespondenceLanguage/>\n" +
                    "        </m:properties>\n" +
                    "    </content>\n" +
                    "</entry>";
            InputStream is = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            // 调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象，进行可以利用DOM特性对整个XML文档进行操作了。
            Document document = documentBuilder.parse(is);
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document d = builder.parse("src/main/resources/demo.xml");
            NodeList sList = document.getElementsByTagName("m:properties");
            XmlTool.node(sList);
//            XmlTool.element(sList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMessage() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<error xmlns=\"http://schemas.microsoft.com/ado/2007/08/dataservices/metadata\">\n" +
                    "    <code>VL/636</code>\n" +
                    "    <message xml:lang=\"en\">Goods receipt for this inbound delivery is already posted</message>\n" +
                    "    <innererror>\n" +
                    "        <application>\n" +
                    "            <component_id>LE-SHP-API</component_id>\n" +
                    "            <service_namespace>/SAP/</service_namespace>\n" +
                    "            <service_id>API_INBOUND_DELIVERY_SRV</service_id>\n" +
                    "            <service_version>0002</service_version>\n" +
                    "        </application>\n" +
                    "        <transactionid>F2500080C3EF0250E005DD7339DCAC22</transactionid>\n" +
                    "        <timestamp/>\n" +
                    "        <Error_Resolution>\n" +
                    "            <SAP_Transaction/>\n" +
                    "            <SAP_Note>See SAP Note 1797736 for error analysis (https://service.sap.com/sap/support/notes/1797736)</SAP_Note>\n" +
                    "        </Error_Resolution>\n" +
                    "        <errordetails>\n" +
                    "            <errordetail>\n" +
                    "                <code>VL/636</code>\n" +
                    "                <message>Goods receipt for this inbound delivery is already posted</message>\n" +
                    "                <propertyref/>\n" +
                    "                <severity>error</severity>\n" +
                    "                <target/>\n" +
                    "                <transition>false</transition>\n" +
                    "            </errordetail>\n" +
                    "        </errordetails>\n" +
                    "    </innererror>\n" +
                    "</error>";
            int start = str.indexOf("<message xml:lang=\"en\">");
            int end = str.indexOf(("</message>"),start);
            String message = "<message xml:lang=\"en\">";
            int length = message.length();

            System.out.println(str.substring(start+length,end));

            InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
            // 调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象，进行可以利用DOM特性对整个XML文档进行操作了。
            Document document = documentBuilder.parse(is);
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document d = builder.parse("src/main/resources/demo.xml");
            NodeList sList = document.getElementsByTagName("message");
            XmlTool.node(sList);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}