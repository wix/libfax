
package com.concordfax;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strPIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="request" type="{urn:FaxWS}FaxBroadcastStatusRequest"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userID",
    "strPIN",
    "request"
})
@XmlRootElement(name = "GetFaxBroadcastStatus")
public class GetFaxBroadcastStatus {

    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(required = true)
    protected String strPIN;
    @XmlElement(required = true)
    protected FaxBroadcastStatusRequest request;

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the strPIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrPIN() {
        return strPIN;
    }

    /**
     * Sets the value of the strPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrPIN(String value) {
        this.strPIN = value;
    }

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link FaxBroadcastStatusRequest }
     *     
     */
    public FaxBroadcastStatusRequest getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link FaxBroadcastStatusRequest }
     *     
     */
    public void setRequest(FaxBroadcastStatusRequest value) {
        this.request = value;
    }

}
