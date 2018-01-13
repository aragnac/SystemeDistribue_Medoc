/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import wsPackage.Analyses;
import wsSB.webServiceSBRemote;

/**
*
* @author Nicolas
*
*/
@WebService(serviceName = "WSResultat")
@Stateless()
public class WSResultat {

    @EJB
    private webServiceSBRemote webServiceSB;

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param numDemande
     * @return 
     */
    @WebMethod(operationName = "getAnalyses")
    public List<Analyses> getAnalyses(@WebParam(name = "numDemande") int numDemande) {

        return webServiceSB.getAnalyses(numDemande);
    }
}
